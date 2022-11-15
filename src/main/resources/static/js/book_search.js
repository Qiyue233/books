var bookId=[];  //保存当前页面所有的书籍id
var saveId=[];  //选中的书籍id
var pageNum;    //当前页面
var searchType; //当前页面搜索的方式 判断应该调用什么方法查询
var searchContext;//搜索的内容

getSearchName();
getUser();
//获取登录名
function getUser(){
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

//从cookie中获取搜索内容和方式
function getSearchName(){
    if($.cookie("searchType")==1){
        searchContext= $.cookie("searchContext");
        searchType=1;
        searchBooksByName(1,searchContext);
    }else if($.cookie("searchType")==2){
        const bookType= $.cookie("searchContext");
        searchType=2;
        getTypeByType(bookType)
    }else if($.cookie("searchType")==3){
        searchContext= $.cookie("searchContext");
        searchBooksByIsbn(1,searchContext)
    }else if($.cookie("searchType")==null){
        alert("莫的权限，请登录");
        window.location.href="/";
    }
}
function getTypeByType(type){
    $.ajax({
        url:"/getTypeByType",
        type:"GET",
        handlers:{},
        data:{type:type},
        success:function (result){
            if (result.code==200){
                searchContext=result.extend.type;
                searchBooksByType(1,result.extend.type)
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
        searchBooksByName(pageNum,searchContext)
    }else if(searchType==2){
        //类别
        searchContext=$("#keyword").val();
        pageNum=1;
        getTypeByType(searchContext)
    }else {
        //书号(isbn)
        searchContext=$("#keyword").val();
        pageNum=1;
        searchBooksByIsbn(pageNum,searchContext)
    }

}

function searchBooksByName(pageNum,book_name){
    $.ajax({
        url:"/getBooksByName",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,book_name:book_name},
        success:function (result){
            if(result.code==200){
                searchShow(result.extend.type,result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books);
                ShowPrePage(result.extend.books.prePage)
                ShowNextPage(result.extend.books.nextPage)
                showBookRight(result.extend.type,result.extend.books.list[0])
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
function searchBooksByType(pageNum,bookType){
    $.ajax({
        url:"/getBooksByType",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,type:bookType},
        success:function (result){
            if(result.code==200){
                searchShow(result.extend.type,result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books);
                ShowPrePage(result.extend.books.prePage)
                ShowNextPage(result.extend.books.nextPage)
                showBookRight(result.extend.type,result.extend.books.list[0])
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
function searchBooksByIsbn(pageNum,isbn){
    $.ajax({
        url:"/getBooksByIsbn",
        type:"GET",
        handlers:{},
        data:{pageNum:pageNum,isbn:isbn},
        success:function (result){
            if (result.code==200){
                searchShow(result.extend.type,result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books);
                ShowPrePage(result.extend.books.prePage)
                ShowNextPage(result.extend.books.nextPage)
                showBookRight(result.extend.type,result.extend.books.list[0])
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
}
//显示查询到的书籍
function searchShow(bookType,nums,books){
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
    $("#searchBody").empty();
    var pre=$('<li class="page-item"></li>');
    var prePage=$('<button  class="page-link" id="prePage"></button>').append("上一页").attr("onclick","prePage("+books.prePage+")").attr("type","button");
    pre.append(prePage)
    pre.appendTo("#searchBody");

    for(var i=theFirstPage;i<=theLastPage;i++){
        var ul=$('<li class="page-item"></li>');
        if(books.pageNum ==i){
            var page=$('<button  class="btn btn-info"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");
        }
        else {
            var page=$('<button class="page-link"></button>').append(i).attr("onclick","pageNumAjax("+i+")").attr("type","button");

        }
        ul.append(page).append("");
        ul.appendTo("#searchBody");
    }

    var next=$('<li class="page-item"></li>');
    var nextPage=$('<button  class="page-link" id="nextPage"></button>').append("下一页").attr("onclick","nextPage("+books.nextPage+")").attr("type","button");
    next.append(nextPage)
    next.appendTo("#searchBody");
}
function prePage(prePage){
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchBooksByName(prePage,searchContext)
    }else if(searchType==2){
        searchBooksByType(prePage,searchContext)
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
function pageNumAjax(page){
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchBooksByName(page,searchContext)
    }else if(searchType==2){
        searchBooksByType(page,searchContext)
    }
}
function nextPage(nextPage){
    bookId.splice(0,bookId.length);
    if(searchType==1){
        searchBooksByName(nextPage,searchContext)
    }else if(searchType==2){
        searchBooksByType(nextPage,searchContext)
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

//修改、删除、查看
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
                        if(searchType==1){
                            emptyCheck();
                            searchBooksByName(pageNum,searchContext)
                        }else if(searchType==2){
                            emptyCheck();
                            searchBooksByType(pageNum,searchContext)
                        }
                    }else if (result.code==303){
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
            if (result.code==200){
                if(searchType==1){
                    searchBooksByName(pageNum,searchContext)
                }else if(searchType==2){
                    searchBooksByType(pageNum,searchContext)
                }
            }else if (result.code==303){
                alert("莫的权限，请登录");
                window.location.href="/";
            }

        }
    });
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