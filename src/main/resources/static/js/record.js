//批量借出
var bookId=[];  //保存当前页面所有的书籍id
var saveId=[];  //选中的书籍id
var userName;
var pageNum;    //当前页面
var searchType; //当前页面搜索的方式 判断应该调用什么方法查询
var searchContext;//搜索的内容


getUser();
getAllBook(1);
//获取登录名
function getUser(){
    let strings = $.cookie('token').split("."); //截取token，获取载体
    var userinfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析
    getUserAjax(userinfo.name);
}
function showName(name){
    $("#userName").empty();
    userName=name;
    $("#userName").append("您好,"+name);
}
function getUserAjax(name){
    $.ajax({
        url:'/getUserInfo',
        type:"GET",
        headers:{},
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

// 从当前页面中获取搜索方式
function selectTypeRight(){
    const type=$("#inputSelect").val()
    searchType=type;
}
function getSelectTypeAndName(){
    if(searchType==1){
        //书名
        searchContext=$("#keyword").val();
        pageNum=1;
        searchRecordByName(pageNum,searchContext)
    }else if(searchType==2){
        //借阅人
        searchContext=$("#keyword").val();
        pageNum=1;
        searchRecordByUserName(pageNum,searchContext)
    }else {
        //练习电话
        searchContext=$("#keyword").val();
        pageNum=1;
        searchBooksByTel(1,searchContext)
    }

}


function searchRecordByName(pageNum,book_name){
    $.ajax({
        url:"/searchRecordByName",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,bookName:book_name},
        success:function (result){
            if(result.code==200){
                searchShow(result.extend.record.size,result.extend.record.list);
                getPageNum(result.extend.record);
                ShowPrePage(result.extend.record.prePage);
                ShowNextPage(result.extend.record.nextPage);
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
function searchRecordByUserName(pageNum,userName){
    alert(userName)
    $.ajax({
        url:"/searchRecordByUserName",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,userName:userName},
        success:function (result){
            if(result.code==200){
                searchShow(result.extend.record.size,result.extend.record.list);
                getPageNum(result.extend.record);
                ShowPrePage(result.extend.record.prePage);
                ShowNextPage(result.extend.record.nextPage);
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
function searchBooksByTel(pageNum,tel){
    $.ajax({
        url:"/searchBooksByTel",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,tel:tel},
        success:function (result){
            if (result.code==200){
                searchShow(result.extend.record.size,result.extend.record.list);
                getPageNum(result.extend.record);
                ShowPrePage(result.extend.record.prePage);
                ShowNextPage(result.extend.record.nextPage);
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
//显示查询到的书籍
function getAllBook(pageNum){
    $.ajax({
        url:"/getAllBorrowRecords",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum},
        success:function (result){
            if(result.code==200){
                searchShow(result.extend.record.size,result.extend.record.list);
                getPageNum(result.extend.record);
                ShowPrePage(result.extend.record.prePage)
                ShowNextPage(result.extend.record.nextPage)
            /*    showBookRight(result.extend.type,result.extend.record.list[0])*/
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    })
}
function searchShow(nums,records){
    $("#Tbody").empty();
    alert(nums);
    for (var i = 0; i < nums; i++) {
        var tr = $("<tr id='tr'></tr>").attr("onclick","check("+records[i].id+")");
        //判断是否选中回显时状态
        var state=false;
        for(var j=0;j<saveId.length;j++) {
            if (saveId[j] == records[i].id){
                state=true;
            }
        }
        if(state==true){
            var select=$('<td></td>').append($('<input type="checkbox" style="margin-top: 10px;margin-left: 0px" id="checkbox" checked="checked"  class="form-check-input" style="margin-left: 0px">').attr("onclick","ckClick("+nums+','+records[i].id+")"));

        }else {
            var select=$('<td></td>').append($('<input type="checkbox"  style="margin-top: 10px;margin-left: 0px" id="checkbox"   class="form-check-input" style="margin-left: 0px">').attr("onclick","ckClick("+nums+','+records[i].id+")"));
        }
        var id = $("<td></td>").append(i + 1);
        var bookName = $("<td></td>").append(records[i].bookName);
        var userName= $("<td></td>").append(records[i].userName);
        var telNumber=$("<td></td>").append(records[i].telNumber);;
        var outDate= $("<td></td>").append(records[i].outDate);
        var estimatedDate=$("<td></td>").append(records[i].estimatedDate);
        var realDate=$("<td></td>").append(records[i].realDate);

        bookId.push(records[i].id);
        tr.append(select).append(id).append(bookName).append(userName).append(telNumber)
            .append(outDate).append(estimatedDate).append(realDate)

        tr.appendTo("#Tbody")
    }
    $("#table").addClass("table table-hover")
}
function getFirstPage(){
    $("#keyword").val("");
    $("#inputSelect").val("筛选");
    searchType=null;
    getAllBook(1);
}
//分页
function getPageNum(record){
    pageNum=record.pageNum;
    if(record.pageNum>2){
        //控制显示最多几个页码 以及变化的页码数
        var theFirstPage=record.pageNum-2;
        var theLastPage=record.pageNum+2;
        //heLastPage>lastPage 控制最大页码数
        //lastPage>=5 控制 最大页码数小于5时导致出现页码0
        if(theLastPage>record.pages &&record.pages>=5){
            var theFirstPage=record.pages-4;
            var theLastPage=record.pages;
        }else if(theLastPage>record.pages){
            var theFirstPage=1;
            var theLastPage=record.pages;
        }
    }else {
        //显示首页加载页码
        var theFirstPage=1;
        var theLastPage=5;
        if(theLastPage>record.pages){
            var theLastPage=record.pages;
        }
    }
    $("#body").empty();
    var pre=$('<li class="page-item"></li>');
    var prePage=$('<button  class="page-link" id="prePage"></button>').append("上一页").attr("onclick","prePage("+record.prePage+")").attr("type","button");
    pre.append(prePage)
    pre.appendTo("#body");

    for(var i=theFirstPage;i<=theLastPage;i++){
        var ul=$('<li class="page-item"></li>');
        if(record.pageNum ==i){
            var page=$('<button  class="btn btn-info"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");
        }
        else {
            var page=$('<button class="page-link"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");

        }
        ul.append(page).append("");
        ul.appendTo("#body");
    }

    var next=$('<li class="page-item"></li>');
    var nextPage=$('<button  class="page-link" id="nextPage"></button>').append("下一页").attr("onclick","nextPage("+record.nextPage+")").attr("type","button");
    next.append(nextPage)
    next.appendTo("#body");
}
function prePage(prePage){
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchRecordByName(prePage,searchContext)
    }else if(searchType==2){
        searchRecordByUserName(prePage,searchContext)
    }else if(searchType==3){
        searchBooksByTel(prePage,searchContext);
    }else {
        getAllBook(prePage)
    }
}
function ShowPrePage(prePage){
    if(prePage!=0){
        $("#prePage").attr('disabled',false);
    }else {
        $("#prePage").attr('disabled',true)
            .css({'background-color' : '#DDDDDD',color:"#FFFFFF"});
    }
}
function pageNumAjax(page)  {
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchRecordByName(page,searchContext)
    }else if(searchType==2){
        searchRecordByUserName(page,searchContext)
    }else if(searchType==3){
        searchBooksByTel(page,searchContext);
    }else {
        getAllBook(page)
    }
}
function nextPage(nextPage){
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchRecordByName(nextPage,searchContext)
    }else if(searchType==2){
        searchRecordByUserName(nextPage,searchContext)
    }else if(searchType==3){
        searchBooksByTel(nextPage,searchContext);
    }else {
        getAllBook(nextPage)
    }

}
function ShowNextPage(nextPage){
    if(nextPage!=0){
        $("#nextPage").attr('disabled',false);
    }else {
        $("#nextPage").attr('disabled',true)
            .css({'background-color' : '#DDDDDD',color:"#FFFFFF"})
    }
}


//获取选中书籍信息然后回显
function check(id){
    $.ajax({
        url:"/checkById",
        type:"GET",
        handlers:{},
        data:{id:id},
        success:function (result){
            if (result.code==200){
                showBookRight(result.extend.type,result.extend.books)
            } else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}
//在右侧显示书籍信息
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
            $("#state").prop("value",null)
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
        $("#inNumber").prop("value",books.inNumber);
        $("#outNumber").prop("value",books.outNumber);
        $("#setPrice").prop("value",books.setPrice);
        $("#state").prop("value",books.state);
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

//借出   还需要判断当前书籍是否已经借出、修改书籍状态
function borrow(){
    $.ajax({
        url:"/selectBorrowInfo",
        type:"GET",
        data:{id:saveId},
        traditional: true,
        handlers:{},
        success:function (result){
            if(result.extend.info==true){
                alert("选中的书籍有一部分或者全部已经借出")
            }else {
                borrowAjax();
            }

        }
    });
}

function borrowAjax(){
    $.ajax({
        url:"/borrowById",
        type:"GET",
        data:{id:saveId,userName:userName},
        traditional: true,
        handlers:{},
        success:function (result){
            if (result.code==200){
                getAllBook(1);
            } else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }
        }
    });
}