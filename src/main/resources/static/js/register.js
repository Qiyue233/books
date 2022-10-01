let Key = "";
function register(){
    const user = $("#username").val();
    const password = $("#password").val();
    const password_R= $("#password-R").val();
    if (password!=password_R){
        return ;
    }
    const tel= $("#telNumber").val();
    registerAjax(user,password,tel);
}
function jm(content){
    const encrypt=new JSEncrypt();
    encrypt.setPublicKey(Key);
    return  encrypt.encrypt(content);
}
function getKeyAjax(user){
    $.ajax({
        url: '/key',
        type: "GET",
        async: false,
        data: {user:user},
        success:function (result){
            if (result.code==200){
                Key=result.extend.key;
            }else {

            }
        }
    })
}
function registerAjax(user,password,tel){
    getKeyAjax(user);
    if (Key!=""){
        password=jm(password);
    }else {
        return;
    }
    $.ajax({
        url:'/register',
        type:"POST",
        headers:{},
        data: {username:user,password:password},
        success:function (result){
            if (result.code==200){
                window.location.href="/";
            }else {

            }
        }
    });
}
