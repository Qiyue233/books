getAllBook(1);
var nowPage;
var firstPage;
var lastPage;
//获取搜索的条件和内容
function selectType(){
   const type=$("#selectType").val()
    return type;
}
function getSelectTypeAndName(){
    const type=selectType();
    if(type==1){
        //书名
       const bookName=$("#keyword").val();
        searchBookByName(bookName)
    }else if(type==2){
        //类别
        const bookType=$("#keyword").val();
        searchBookByType(bookType)
    }else {
        //书号(isbn)
        const isbn=$("#keyword").val();
        searchBookByIsbn(isbn)
    }

}

//显示书籍信息
function showBooKs(nums,books){
    $("#Tbody").empty();
    for(var i=0;i<nums;i++){
        var tr=$("<tr></tr>");
        var id=$("<td></td>").append(i+1);
        var bookName=$("<td></td>").append(books[i].bookName);
        var isbn=$("<td></td>").append(books[i].isbn);
        var type=$("<td></td>").append(books[i].type);
        var content=$("<td></td>").append(books[i].content);
        var outDate=$("<td></td>").append(books[i].outDate);
        /*var entire_number=$("<td></td>").append(books[i].entireNumber);
        var inNumber=$("<td></td>").append(books[i].inNumber);*/
        /*var int_price=$("<td></td>").append(books[i].intPrice);*/
        var state=$("<td></td>").append(books[i].state);
        var borrower=$("<td></td>").append(books[i].borrower);
        var update=$("<button></button>").append("修改").attr("onclick","update("+books[i].id+")")
            .addClass("btn btn-outline-primary").attr("type","button");
        var del=$("<button></button>").append("删除").attr("onclick","del("+books[i].id+")")
            .addClass("btn btn-outline-danger").attr("type","button");
        var look=$('<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">查看</button>')
            .attr("onclick","look("+books[i].id+")");
        var but=$("<td></td>").append(update).append(" ").append(del).append(" ").append(look);

        tr.append(id).append(bookName).append(isbn).append(type).append(content).append(outDate)
           .append(state).append(borrower).append(but);
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
                nowPage=result.extend.books.pageNum
                lastPage=result.extend.books.navigateLastPage;
                firstPage=result.extend.books.navigateFirstPage;
                showBooKs(result.extend.books.size,result.extend.books.list);
                getPageNum(result.extend.books.pageNum)

            }
        }
    })
}
//删改查
function update(id){
    $.cookie('updateBookId', id, {  path: '/' });
    window.location.href="update";
}
function del(id){
    $.ajax({
        url:"/del",
        type: "GET",
        handlers: {},
        data: {id:id},
        success:function (result){
            getAllBook();
        }
    });
}
function look(id){
    $.ajax({
        url:"/updateById",
        type:"GET",
        handlers:{},
        data:{id:id},
        success:function (result){
            look_details(result.extend.books);
        }
    });
}
function look_details(books){
    const isbn =$("#isbn").attr("value",books.isbn);
    const cip =$("#cip").attr("value",books.cip);
    const publisher =$("#publisher").attr("value",books.publisher);
    const out_price = $("#out_price").attr("value",books.outPrice);
}
//页面跳转
function getPageNum(nowPage){
    if(nowPage>2){
        //控制显示最多几个页码 以及变化的页码数
        var theFirstPage=nowPage-2;
        var theLastPage=nowPage+2;
        //heLastPage>lastPage 控制最大页码数
        //lastPage>=5 控制 最大页码数小于5时导致出现页码0
        if(theLastPage>lastPage &&lastPage>=5){
            var theFirstPage=lastPage-4;
            var theLastPage=lastPage;
        }else if(theLastPage>lastPage){
            var theFirstPage=1;
            var theLastPage=lastPage;
        }
    }else {
        //显示首页加载页码
        var theFirstPage=1;
        var theLastPage=5;
        if(theLastPage>lastPage){
            var theLastPage=lastPage;
        }
    }

    $("#body").empty();
    for(var i=theFirstPage;i<=theLastPage;i++){
        var ul=$('<li class="page-item"></li>');
        if(nowPage ==i){
            var page=$('<button  class="btn btn-dark"></button>').append(i).attr("onclick","pageJump("+i+")").attr("type","button");;
        }
        else {
            var page=$('<button class="page-link"></button>').append(i).attr("onclick","pageJump("+i+")").attr("type","button");;

        }
        ul.append(page).append("");
        ul.appendTo("#body");
        /*$('#table tr:even').css('backgroundColor','#E8E6E1');*/
    }
}
function prePage(){
    if(nowPage ==firstPage){
        //TODO 显示这是第一页
        alert("已经是第一页");
    } else if(nowPage>3){
        nowPage=nowPage-1;
        /*var firstPage=nowPage-2;*/
        getPageNum(nowPage)
        getAllBook(nowPage);
    }else {
        nowPage=nowPage-1;
        getAllBook(nowPage);
    }
}
function pageJump(page){
    nowPage=page;
    getAllBook(page);


}
function nextPage(){
    var nextPage=nowPage+1;
    if(nowPage==lastPage){
        //TODO 显示这是最后一页
        alert("已经是最后一页");
    } else if(nowPage>=3){
        getPageNum(nextPage)
        getAllBook(nextPage);
    }else {
        getAllBook(nextPage);
    }

}

// 将搜索时获取到的 搜索方式和内容存在cookie里面
function searchBookByName(bookName) {
    $.cookie('searchName', bookName, {path: '/'});
    window.location.href = "searchBook";
}
function searchBookByType(bookType) {
    $.cookie('bookType', bookType, {path: '/'});
    window.location.href = "searchBook";
}
function searchBookByIsbn(isbn) {
    $.cookie('isbn', isbn, {path: '/'});
    window.location.href = "searchBook";
}
