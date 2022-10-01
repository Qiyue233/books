function Submit(){
    const isbn=$("#isbn").val();
    const cip=$("#cip").val();
    const type=$("#type").val();
    const book_name=$("#book_name").val();
    const content=$("#content").val();
    const author = $("#author").val();
    const out_date = $("#out_date").val();
    /*const publisher = $("#publisher").val();*/
    const entire_number = $("#entire_number").val();
    const int_price = $("#int_price").val();
    const set_price = $("#set_price").val();
    BookInsert(isbn,cip,type,book_name,content,author,out_date,entire_number,int_price,set_price);


}
function BookInsert(isbn,cip,type,book_name,content,author,out_date,entire_number,int_price,set_price){
    $.ajax({
        url: "/putBook",
        type:"POST",
        handlers: {},
        data:{isbn:isbn,cip:cip,type:type,book_name:book_name,content:content,
            author:author,out_date:out_date,entire_number:entire_number,
            int_price:int_price,set_price:set_price},
        success:function (result){
            if (result.code==200){
                //TODO 拿到返回内容放入本地

            }else {

            }
        }
    });
}