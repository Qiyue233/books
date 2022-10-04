let Key = "";
function showTip(msg){

    const tip=$(".tips");
    tip.empty();
    tip.append("<p style='color: red'>*"+msg+"</p>");
}
function login(){
    const telNumber = $("#telNumber").val();
    const password = $("#password").val();
    if (!/^((13\d)|(14\d)|(15\d)|(17\d)|(18\d))\d{8}$/.test(telNumber)){
        showTip("账号格式不正确！");return;
    }else if (!/^[a-zA-Z_\w]{8,16}$/.test(password)){
        showTip("密码格式不正确！");return;
    }
    loginAjax(telNumber,password);
}
function jm(content){
    const encrypt=new JSEncrypt();
    encrypt.setPublicKey(Key);
    return  encrypt.encrypt(content);
}
function getKeyAjax(telNumber){
    $.ajax({
        url: '/key',
        type: "GET",
        async: false,
        data: {telNumber:telNumber},
        success:function (result){
        if (result.code==200){
            Key=result.extend.key;
        }else {
            showTip("用户名错误");
        }
    }
    })
}

function loginAjax(telNumber,password){
    getKeyAjax(telNumber);
    if (Key!=""){
        password=jm(password);
    }else {
        return;
    }
    $.ajax({
        url:'/record',
        type:"GET",
        headers:{},
        data: {telNumber:telNumber,password:password},
        success:function (result){
            if (result.code==200){
                $.cookie('token', result.extend.token, { path: '/' });
                window.location.href="index";
            }else {
                showTip(result.msg);
            }
        }
    });
}

