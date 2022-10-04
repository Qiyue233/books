getSearchName();
//TODO 页面跳转后从cookie中获取
function getSearchName(){
    if($.cookie("searchName")!=null){
        const book_name= $.cookie("searchName");
        searchBooksByName(book_name);
    }else if($.cookie("bookType")!=null){
        const bookType= $.cookie("bookType");
        searchBooksByType(bookType)
    }else if($.cookie("isbn")!=null){
        const isbn= $.cookie("isbn");
        searchBooksByIsbn(isbn)
    }
}

//TODO 从当前页面中获取搜索名
function selectType(){
    const type=$("#selectType").val()
    return type;
}
function getSelectTypeAndName(){
    const type=selectType();
    if(type==1){
        //书名
        const bookName=$("#keyword").val();
        searchBooksByName(bookName)
    }else if(type==2){
        //类别
        const bookType=$("#keyword").val();
        searchBooksByType(bookType)
    }else {
        //书号(isbn)
        const isbn=$("#keyword").val();
        searchBooksByIsbn(isbn)
    }

}

function searchBooksByName(book_name){
    $.ajax({
        url:"/getBooksByName",
        type:"GET",
        handlers:{},
        data:{book_name:book_name},
        success:function (result){
            searchShow(result.extend.nums,result.extend.books);
        }
    });
}
function searchBooksByType(bookType){
    $.ajax({
        url:"/getBooksByType",
        type:"GET",
        handlers:{},
        data:{type:bookType},
        success:function (result){
            searchShow(result.extend.nums,result.extend.books);
        }
    });
}
function  searchBooksByIsbn(isbn){
    $.ajax({
        url:"/getBooksByIsbn",
        type:"GET",
        handlers:{},
        data:{isbn:isbn},
        success:function (result){
            searchShow(result.extend.nums,result.extend.books);
        }
    });
}
//TODO 显示查询到的书籍
function searchShow(nums,books){
    $("#Tbody").empty();
    for(var i=0;i<nums;i++){
        var tr=$("<tr></tr>");
        var id=$("<td></td>").append(i+1);
        var book_name=$("<td></td>").append(books[i].bookName);
        var isbn=$("<td></td>").append(books[i].isbn);
        var type=$("<td></td>").append(books[i].type);
        var author=$("<td></td>").append(books[i].author);
        var outDate=$("<td></td>").append(books[i].outDate);
       /* var publisher=$("<td></td>").append(books[i].publisher);
        var inNumber=$("<td></td>").append(books[i].inNumber);
        var outPrice=$("<td></td>").append(books[i].outPrice);*/
        var state=$("<td></td>").append(books[i].state);
        var borrower=$("<td></td>").append(books[i].borrower);
        var update=$("<button></button>").append("修改").attr("onclick","update("+books[i].id+")")
            .addClass("btn btn-outline-primary").attr("type","button");
        var del=$("<button></button>").append("删除").attr("onclick","del("+books[i].id+")")
            .addClass("btn btn-outline-danger").attr("type","button");
        var look=$('<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">查看</button>')
            .attr("onclick","see("+books[i].id+")");
        var but=$("<td></td>").append(update).append(" ").append(del).append(" ").append(look);

        tr.append(id).append(book_name).append(isbn).append(type).append(author).append(outDate)
            .append(state).append(borrower).append(but);
        tr.appendTo("#Tbody")
    }
    $("#table").addClass("table table-hover")
}
//TODO 修改、删除、查看
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
            getSearchName();
        }
    });
}
function see(id){
    $.ajax({
        url:"/updateById",
        type:"GET",
        handlers:{},
        data:{id:id},
        success:function (result){
            see_details(result.extend.books);
        }
    });
}
function see_details(books){
    const isbn =$("#isbn").attr("value",books.isbn);
    const cip =$("#cip").attr("value",books.cip);
    const publisher =$("#publisher").attr("value",books.publisher);
    const out_price = $("#out_price").attr("value",books.outPrice);
}