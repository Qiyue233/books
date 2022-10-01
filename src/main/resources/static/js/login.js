let Key = "";
function login(){
    const user = $("#username").val();
    const password = $("#password").val();
    loginAjax(user,password);
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
function loginAjax(user,password){
    getKeyAjax(user);
    if (Key!=""){
        password=jm(password);
    }else {
        return;
    }
    $.ajax({
        url:'/record',
        type:"GET",
        headers:{},
        data: {username:user,password:password},
        success:function (result){
            if (result.code==200){
                window.location.href="index";
            }else {
                alert(result.msg);
            }
        }
    });
}
