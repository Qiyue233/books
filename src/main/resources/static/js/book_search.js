getSearchName();
//页面跳转后从cookie中获取
function getSearchName(){
    const book_name= $.cookie("searchName");
    if(book_name!=null){
        searchBooksByName(book_name);
    }
}
//从当前页面中获取搜索名
function getInputSearchName(){
    const book_name = $("#inputName").val();
    if(book_name!=null){
        searchBooksByName(book_name);
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
        var publisher=$("<td></td>").append(books[i].publisher);
        var inNumber=$("<td></td>").append(books[i].inNumber);
        var outPrice=$("<td></td>").append(books[i].outPrice);
        var update=$("<button></button>").append("修改").attr("onclick","update("+books[i].id+")")
            .addClass("btn btn-outline-primary").attr("type","button");
        var del=$("<button></button>").append("删除").attr("onclick","del("+books[i].id+")")
            .addClass("btn btn-outline-danger").attr("type","button");
        var but=$("<td></td>").append(update).append(" ").append(del);
        tr.append(id).append(book_name).append(isbn).append(type).append(author).append(outDate)
            .append(publisher).append(inNumber).append(outPrice).append(but);
        tr.appendTo("#Tbody")
    }
    $("#table").addClass("table table-hover")
}