getUser()
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
            }else {
            }
        }
    });
}