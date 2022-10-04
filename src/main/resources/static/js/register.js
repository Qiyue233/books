let Key = "";
function showTip(msg){
    const tip=$(".tips");
    tip.empty();
    tip.append("<p style='color: red'>*"+msg+"</p>");
}
function register(){
    const user = $("#username").val();
    const password = $("#password").val();
    const password_R= $("#password-R").val();
    const tel= $("#telNumber").val();
    if (!/^[\u4e00-\u9fa5a-zA-Z\w_]{1,10}$/.test(user)){
        showTip("用户名为10位以内的汉字、数字、字母、下划线组合！");return;
    }else if (!/^((13\d)|(14\d)|(15\d)|(17\d)|(18\d))\d{8}$/.test(tel)){
        showTip("电话号码输入有误！");return;
    }else if (!/^[a-zA-Z_\w]{8,16}$/.test(password)){
        showTip("密码为8-16位的数字、字母、下划线组合！");return;
    }else if(password!=password_R){
        showTip("两次输入的密码不一致！");return;
    }

    registerAjax(user,password,tel);
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
                showTip(result.msg);
            }
        }
    })
}
function registerAjax(user,password,telNumber){
    getKeyAjax(telNumber);
    if (Key!=""){
        password=jm(password);
    }else {
        return;
    }
    $.ajax({
        url:'/register',
        type:"POST",
        headers:{},
        data: {username:user,password:password,telNumber:telNumber},
        success:function (result){
            if (result.code==200){
                window.location.href="/";
            }else {
                showTip(result.msg);
            }
        }
    });
}
