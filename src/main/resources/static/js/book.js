var bookId=[];  //保存当前页面所有的书籍id
var saveId=[];  //选中的书籍id
var pageNum;    //当前页面
var token= $.cookie("token");
getUser();
getAllBook(1);
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

//获取书籍类型
function showType(nums,type){
    $("#typeSelect").empty();
    for (var i = 0; i < nums; i++){
        var  option= $("<option></option>").append(type[i].type);
        option.appendTo("#typeSelect");
    }
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

//根据type分类获取对应type书籍
function selectType(){
    const type=$("#typeSelect").val()
    $.ajax({
        url:"/getTypeByType",
        type:"GET",
        handlers:{},
        data:{type:type},
        success:function (result){
            if (result.code==200){
                getAllBooksByType(1,result.extend.type)
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}
function getAllBooksByType(pageNum,type){
    $.ajax({
        url:"/getBooksByType",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,type:type},
        success:function (result){
            if(result.code==200){
                showBooKs(result.extend.type,result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books);
                ShowPrePage(result.extend.books.prePage)
                ShowNextPage(result.extend.books.nextPage)
                showBookRight(result.extend.type,result.extend.books.list[0])
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
    }
    })
}

//获取搜索的条件和内容
function selectTypeRight(){
    const type=$("#inputSelect").val()
    return type;
}
function getSelectTypeAndName(){
    const type=selectTypeRight();
    const searchContext=$("#keyword").val();
    searchBook(type,searchContext)
}

//显示书籍信息
function showBooKs(bookType,nums,books) {
    $("#Tbody").empty();
    for (var i = 0; i < nums; i++) {
        var tr = $("<tr id='tr'></tr>").attr("onclick","check("+books[i].id+")");
        var state=false;
        for(var j=0;j<saveId.length;j++) {
            if (saveId[j] == books[i].id){
                state=true;
            }
        }
        if(state==true){
            var select=$('<td></td>').append($('<input type="checkbox" style="margin-top: 10px;margin-left: 0px" id="checkbox" checked="checked"  class="form-check-input" style="margin-left: 0px">').attr("onclick","ckClick("+nums+','+books[i].id+")"));

        }else {
            var select=$('<td></td>').append($('<input type="checkbox"  style="margin-top: 10px;margin-left: 0px" id="checkbox"   class="form-check-input" style="margin-left: 0px">').attr("onclick","ckClick("+nums+','+books[i].id+")"));
        }
        var id = $("<td></td>").append(i + 1);
        var bookName = $("<td></td>").append(books[i].bookName);
        var isbn = $("<td></td>").append(books[i].isbn);
        var type = $("<td></td>").append(bookType[books[i].type-1].type);
        var outDate = $("<td></td>").append(books[i].outDate);
        var outNumber = $("<td></td>").append(books[i].outNumber);
        var inNumber = $("<td></td>").append(books[i].inNumber);

        bookId.push(books[i].id);
        tr.append(select).append(id).append(bookName).append(isbn).append(type).append(outDate)
            .append(inNumber).append(outNumber)
        tr.appendTo("#Tbody")
    }
    $("#table").addClass("table table-hover")
}
function getAllBook(pageNum){
    $.ajax({
        url:"/getAllBooks",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum},
        success:function (result){
            if(result.code==200){
                showBooKs(result.extend.type,result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books);
                ShowPrePage(result.extend.books.prePage)
                ShowNextPage(result.extend.books.nextPage)
                showBookRight(result.extend.type,result.extend.books.list[0])
                /*防止返回首页重新搜索导致cookie混乱*/
                $.cookie("searchContext",null);
                $.cookie("searchType",null);
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    })
}

//获取选中书籍信息然后回显
function check(id){
    $.ajax({
        url:"/checkById",
        type:"GET",
        handlers:{},
        data:{id,id},
        success:function (result){
            if (result.code==200)
            showBookRight(result.extend.type,result.extend.books)
            else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}
// 在右侧显示书籍信息
function showBookRight(bookType,books){
    //当使用val(),导致attr("value",books.bookName)失效时，可以通过prop()来设置value值
    if (books==null){
        if (pageNum-1<=0){
            $("#bookName").prop("value",null);
            $("#author").prop("value",null);
            $("#isbn").prop("value",null);
            $("#cip").prop("value",null);
            $("#type").prop("value",null);
            $("#outDate").prop("value",null);
            $("#publisher").prop("value",null);
            $("#inNumber").prop("value",null)
            $("#outNumber").prop("value",null)
            $("#setPrice").prop("value",null)
        }else {
            prePage(pageNum-1);
        }
    }else {
        $("#bookName").prop("value",books.bookName);
        $("#author").prop("value",books.author);
        $("#isbn").prop("value",books.isbn);
        $("#cip").prop("value",books.cip);
        $("#type").prop("value",bookType[books.type-1].type);
        $("#outDate").prop("value",books.outDate);
        $("#publisher").prop("value",books.publisher);
        $("#inNumber").prop("value",books.inNumber)
        $("#outNumber").prop("value",books.outNumber)
        $("#setPrice").prop("value",books.setPrice)
    }

}
//判断选中的书籍数量是否是当前页面的所有
function ckClick(nums,bookId){
    //判断选中的书籍是否已被选中，如果选中则删除选中的id,否则添加id
    var boolean=false;
    for (var i=0;i<saveId.length;i++){
        if(saveId[i]==bookId){
            saveId.splice($.inArray(bookId, saveId), 1);
            boolean=true;
        }
    }
    if(boolean==false)
        saveId.push(bookId);

    var len=nums;
    //取得所有被选中的复选框的个数
    var length=$("#checkbox:checked").length;
    if(len == length){
        $("#allCheck").prop("checked",true);
    }else
    {
        $("#allCheck").prop("checked",false);
    }
}
//选中全部
function allCheck(){
    var ischeck=$("#allCheck").prop("checked");
    if (ischeck==true){
        saveId.splice(0,saveId.length);
        for (var i=0;i<bookId.length;i++){
            saveId.push(bookId[i]);
        }
        $("#Tbody input:checkbox").prop("checked",ischeck);
    }else {
        saveId.splice(0,saveId.length);
        $("#Tbody input:checkbox").prop("checked",ischeck);
    }


}
function emptyCheck(){
    $("#allCheck").prop("checked",false);
}
//分页
function getPageNum(books){
    pageNum=books.pageNum;
    if(books.pageNum>2){
        //控制显示最多几个页码 以及变化的页码数
        var theFirstPage=books.pageNum-2;
        var theLastPage=books.pageNum+2;
        //heLastPage>lastPage 控制最大页码数
        //lastPage>=5 控制 最大页码数小于5时导致出现页码0
        if(theLastPage>books.pages &&books.pages>=5){
            var theFirstPage=books.pages-4;
            var theLastPage=books.pages;
        }else if(theLastPage>books.pages){
            var theFirstPage=1;
            var theLastPage=books.pages;
        }
    }else {
        //显示首页加载页码
        var theFirstPage=1;
        var theLastPage=5;
        if(theLastPage>books.pages){
            var theLastPage=books.pages;
        }
    }
    $("#body").empty();
    var pre=$('<li class="page-item"></li>');
    var prePage=$('<button  class="page-link" id="prePage"></button>').append("上一页").attr("onclick","prePage("+books.prePage+")").attr("type","button");
    pre.append(prePage)
    pre.appendTo("#body");

    for(var i=theFirstPage;i<=theLastPage;i++){
        var ul=$('<li class="page-item"></li>');
        if(books.pageNum ==i){
            var page=$('<button  class="btn btn-info"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");
        }
        else {
            var page=$('<button class="page-link"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");

        }
        ul.append(page).append("");
        ul.appendTo("#body");
    }

    var next=$('<li class="page-item"></li>');
    var nextPage=$('<button  class="page-link" id="nextPage"></button>').append("下一页").attr("onclick","nextPage("+books.nextPage+")").attr("type","button");
    next.append(nextPage)
    next.appendTo("#body");
}
function prePage(prePage){
    bookId.splice(0,bookId.length);
    getAllBook(prePage)
}
function ShowPrePage(prePage){
    if(prePage!=0){
        $("#prePage").attr('disabled',false);
    }else {
        $("#prePage").attr('disabled',true)
            .css({'background-color' : '#DDDDDD',color:"#FFFFFF"});
    }
}
function pageNumAjax(page){
    bookId.splice(0,bookId.length);
    getAllBook(page);
}
function nextPage(nextPage){
    bookId.splice(0,bookId.length);
    getAllBook(nextPage)
}
function ShowNextPage(nextPage){
    if(nextPage!=0){
        $("#nextPage").attr('disabled',false);
    }else {
        $("#nextPage").attr('disabled',true)
            .css({'background-color' : '#DDDDDD',color:"#FFFFFF"})
    }
}

//删改查
function delAjax() {
    if (saveId.length==0){
        alert("请选择将要删除的数据！")
    }else{
        if(confirm("您确定删除"+saveId.length+"条数据吗？")) {
            $.ajax({
                url: "/del",
                type: "GET",
                handlers: {},
                traditional: true,
                data: {id: saveId},
                success: function (res) {
                    if (res.code == 200) {
                        saveId.splice(0, saveId.length);
                        bookId.splice(0, bookId.length);
                        emptyCheck();
                        getAllBook(pageNum);
                    }else if (res.code==303){
                        alert("莫的权限，请登录");
                        window.location.href="/";
                    }

                }
            });
        }
    }

}
function update(){
    const isbn=$("#isbn").val();
    const inNumber=$("#inNumber").val();
    const outNumber=$("#outNumber").val();
    const setPrice=$("#setPrice").val();
    updateAjax(inNumber, outNumber, setPrice,isbn);
}
function updateAjax(inNumber, outNumber, setPrice,isbn){
    $.ajax({
        url: "/updateAjax",
        type: "POST",
        handlers: {},
        data: {inNumber:inNumber,outNumber:outNumber,setPrice:setPrice,isbn:isbn},
        success:function (result){
            if(result.code==200){
                getAllBook(pageNum);
            }
            else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}

//将搜索时获取到的 搜索方式和内容存在cookie里面,然后跳转页面
function searchBook(searchType,searchContext) {
    $.cookie('searchContext', searchContext, {path: '/'});
    $.cookie('searchType', searchType, {path: '/'});
    window.location.href = "searchBook";
}

