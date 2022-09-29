function login(){
    const user = $("#username").val();
    const password = $("#password").val();
    loginAjax(user,password);
}

function loginAjax(user,password){
    $.ajax({
        url:'/login',
        type:"POST",
        headers:{},
        data: {username:user,password:password},
        success:function (result){
            if (result.code==200){
                //TODO 拿到返回内容放入本地
                window.location.href="index";
            }else {

            }
        }
    });
}