updateShow();

function updateShow(){
    const id= $.cookie("updateBookId");
    getBookAjax(id);
}
//回显
function showBook(books){
    const name =$("#name").attr("value",books.bookName);
    const isbn =$("#isbn").attr("value",books.isbn);
    const cip =$("#cip").attr("value",books.cip);
    const author =$("#author").attr("value",books.author);
    const type =$("#type").attr("value",books.type);
    const publisher =$("#publisher").attr("value",books.publisher);
    const content =$("#content").attr("value",books.content);
    const out_date =$("#out_date").attr("value",books.outDate);
    const in_number =$("#in_number").attr("value",books.inNumber);
    const int_price =$("#int_price").attr("value",books.intPrice);
    const out_price = $("#out_price").attr("value",books.outPrice);
}
function getBookAjax(id){
    $.ajax({
        url:"/updateById",
        type:"GET",
        handlers:{},
        data:{id:id},
        success:function (result){
            showBook(result.extend.books);
    }
    });
}
function update(){
    const isbn=$("#isbn").val();
    const content=$("#content").val();
    const in_number=$("#in_number").val();
    const int_price=$("#int_price").val();
    const out_price=$("#out_price").val();
    updateAjax(content, in_number, out_price, int_price,isbn);
}
function updateAjax(content, in_number, out_price, int_price,isbn){
    $.ajax({
        url: "/updateAjax",
        type: "POST",
        handlers: {},
        data: {content:content,in_number:in_number,out_price:out_price,int_price:int_price,isbn:isbn},
        success:function (result){
            window.location.href="/books";
        }
    });
}
function giveUp(){
    window.location.href="/books";
}