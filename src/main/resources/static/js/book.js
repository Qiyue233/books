getAllBook(1);
getType();


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
            }
        }
    });
}

//分页
function getPageNum(books){
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
    getAllBook(page);
}
function nextPage(nextPage){
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

//根据type分类获取对应type书籍
function selectType(){
    const type=$("#typeSelect").val()
    if(type=='默认'){
         getAllBook(1)
    }
    else if(type=='编程'){
        const bookType=2;
        getAllBooksByType(bookType);
    }else if (type=='小说'){
        const bookType=3;
        getAllBooksByType(bookType);
    }else {
        const  bookType=4;
        getAllBooksByType(bookType);
    }




}
function getAllBooksByType(type){
    $.ajax({
        url:"/getAllBooksByType",
        type:"GET",
        handlers:{},
        data:{type:type},
        success:function (result){
            if(result.code==200){
                showBooKs(result.extend.type,result.extend.nums,result.extend.books.extend.type);
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
    alert(type)
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
function showBooKs(bookType,nums,books) {
    $("#Tbody").empty();
    for (var i = 0; i < nums; i++) {
        var tr = $("<tr></tr>").attr("onclick","check("+books[i].id+")");
        var select=$('<td></td>').append($('<input type="checkbox" id="checkbox" class="form-check-input" style="margin-left: 0px">'))
            .attr("onclick","ckClick("+nums+")").attr("value","books[i].id");
        var id = $("<td></td>").append(i + 1);
        var bookName = $("<td></td>").append(books[i].bookName);
        var isbn = $("<td></td>").append(books[i].isbn);
        var type = $("<td></td>").append(bookType[books[i].type-1].type);
        var outDate = $("<td></td>").append(books[i].outDate);
        var entire_number = $("<td></td>").append(books[i].entireNumber);
        var inNumber = $("<td></td>").append(books[i].inNumber);

        tr.append(select).append(id).append(bookName).append(isbn).append(type).append(outDate)
            .append(entire_number).append(inNumber)
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
            showBookRight(result.extend.books.list)
        }
    });
}
//TODO 在右侧显示书籍信息
function showBookRight(){


}
//判断选中的书籍数量是否是当前页面的所有
function ckClick(nums){
    //当前页面总个数
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
    $("#Tbody input:checkbox").prop("checked",ischeck);

}

//删改查
function update(id){
    $.cookie('updateBookId', id, {  path: '/' });
    window.location.href="update";
}
//TODO 删除
function del(){


}
function delById(id){
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
