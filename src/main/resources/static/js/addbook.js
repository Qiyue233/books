var token= $.cookie("token");

getUser();
getType();
//获取登录名
function getUser(){
    if ( $.cookie('token')==null){
        alert("莫的权限，请登录");
        window.location.href="/";
    }
    let strings = $.cookie('token').split("."); //截取token，获取载体
    var userinfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析
    getUserAjax(userinfo.name);
}
function showName(name){
    $("#userName").empty();
    $("#userName").append("您好,"+name);
}
function getUserAjax(name){
    $.ajax({
        url:'/getUserInfo',
        type:"GET",
        headers:{token:token},
        data: {telNumber:name},
        success:function (result){
            if (result.code==200){
                showName(result.extend.name);
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}

function getType(){
    $.ajax({
        url:"/getType",
        type:"GET",
        handlers:{},
        data:{},
        success:function (result){
            if(result.code==200){
                showType(result.extend.nums,result.extend.type)
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}
function showType(nums,type){
    $("#typeSelect").empty();
    for (var i = 0; i < nums; i++){
        var  option= $("<option></option>").append(type[i].type);
        option.appendTo("#typeSelect");
    }
}

function add(){
    const bookName=$("#bookName").val();
    const isbn=$("#isbn").val();
    const cip=$("#cip").val();
    const author=$("#author").val();
    const outDate=$("#outDate").val();
    const publisher=$("#publisher").val();
    const type=$("#typeSelect").val()
    const setPrice=$("#setPrice").val();
    const inNumber=$("#inNumber").val();
    const price=$("#inputPrice").val();
    if(bookName.length<=0||isbn.length<=0||cip.length<=0||author.length<=0){
        alert("请填写完整信息");
    }else {
        $.ajax({
            url:"/getTypeByType",
            type:"GET",
            handlers:{},
            data:{type:type},
            success:function (result){
                if (result.extend.type==1){
                    alert("请选择图书类型");
                }else if(result.extend.type!=1){
                    addAjax(bookName,isbn,cip,author,outDate,publisher,result.extend.type,setPrice,inNumber,price)
                }
            }
        });
    }



}
function addAjax(bookName,isbn,cip,author,outDate,publisher,type,setPrice,inNumber,price){
    $.ajax({
        url:"addBook",
        type:"GET",
        handlers:{},
        data:{bookName:bookName,isbn:isbn,cip:cip,author:author,outDate:outDate,publisher:publisher
            ,type:type,setPrice:setPrice,number:inNumber,price:price},
        success:function (result){
            if(result.code==200){
                alert("添加成功");
                window.location.href="book";
            }else if (result.code==303){
                alert("莫得权限，请登录")
                window.location.href="/";
            }
        }
    });
}