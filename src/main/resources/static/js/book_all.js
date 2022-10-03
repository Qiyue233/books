getAllBook();
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
        var entire_number=$("<td></td>").append(books[i].entireNumber);
        var inNumber=$("<td></td>").append(books[i].inNumber);

        var update=$("<button></button>").append("修改").attr("onclick","update("+books[i].id+")")
            .addClass("btn btn-outline-primary").attr("type","button");
        var del=$("<button></button>").append("删除").attr("onclick","del("+books[i].id+")")
            .addClass("btn btn-outline-danger").attr("type","button");
        var look=$('<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">查看</button>')
            .attr("onclick","look("+books[i].id+")");
        var but=$("<td></td>").append(update).append(" ").append(del).append(" ").append(look);
        tr.append(id).append(bookName).append(isbn).append(type).append(content).append(outDate)
            .append(entire_number).append(inNumber).append(but);
        tr.appendTo("#Tbody")
    }
    $("#table").addClass("table table-hover")
}
function getAllBook(){
    $.ajax({
        url:"/getAllBooks",
        type:"GET",
        handlers:{},
        data:{},
        success:function (result){
            if(result.code==200){
                showBooKs(result.extend.nums,result.extend.books);
            }
        }
    })
}
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
            window.location.href="books";
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

//TODO 将搜索时获取到的 搜索方式和内容存在cookie里面
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
