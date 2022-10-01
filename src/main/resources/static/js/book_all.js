getAllBook();

function showBooKs(nums,books){
    $("#Tbody").empty();
    for(var i=0;i<nums;i++){
        var tr=$("<tr></tr>");
        var bookName=$("<td></td>").append(books[i].bookName);
        var isbn=$("<td></td>").append(books[i].isbn);
        var type=$("<td></td>").append(books[i].type);
        var author=$("<td></td>").append(books[i].author);
        var outDate=$("<td></td>").append(books[i].outDate);
        var publisher=$("<td></td>").append(books[i].publisher);
        var inNumber=$("<td></td>").append(books[i].inNumber);
        var outPrice=$("<td></td>").append(books[i].outPrice);
        var update=$("<button></button>").append("修改").attr("id","update")
            .addClass("btn").attr("type","button");
        var del=$("<button></button>").append("删除").attr("onclick","del("+books[i].bookId+")")
            .addClass("btn").attr("type","button");
        var but=$("<td></td>").append(update).append(" ").append(del)
        tr.append(bookName).append(isbn).append(type).append(author).append(outDate)
            .append(publisher).append(inNumber).append(outPrice).append(but);
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

