/**
 * Created by boboLei on 2018/4/30.
 */
$(function () {
    $("#submit").click(function () {
        var  data = checkRegisterData();
        $.ajax({
            type:'post',
            url:'/bibased/patientSave',
            data:JSON.stringify(data),
            dataType:'json',
            contentType:'application/json;charset=utf-8',
            success:function (result) {
                if (result.code == 1){
                    window.location.href="/bibased/index";
                    alert(result.msg);
                }else {
                    alert(result.msg);
                }
            },
            error:function (result) {
                alert(result.msg);
            }
        })
    })
});

function checkRegisterData() {
    var registerData = {}
    var userName =$.trim($("#userName").val());
    if (userName == null || userName.length == 0){
        alert("用户名不能为空");
        return false;
    }else {
        registerData["userName"] = userName;
    }

    var realName = $.trim($("#realName").val());
    if (realName == null || realName.length == 0){
        alert("真实姓名不能为空");
        return false;
    }else {
        registerData["realName"] = realName;
    }

    var passWord1 = $.trim($("#passWord1").val());
    var passWord2 = $.trim($("#passWord2").val());
    if (passWord1 == null || passWord1.length == 0){
        alert("请填写密码");
        return false;
    }else {
        if (passWord2 == null || passWord2.length == 0){
            alert("请确认密码");
            return false;
        }else {
            if (passWord1 == passWord2){
                registerData["passWord"] = passWord2;
            }else {
                alert("两次密码不一致，请检查");
                return false;
            }
        }
    }

    var mobilePhone = $.trim($("#phone").val());
    if (mobilePhone == null || mobilePhone.length == 0){
        alert("请输入正确的手机号");
        return false;
    }else {
        registerData["mobilePhone"] = mobilePhone;
    }

    registerData["idType"] = $.trim($("#userIdType").val());

    var idNumber = $.trim($("#idNumber").val());
    if (idNumber == null || idNumber.length == 0){
        alert("请输入证件号");
        return false;
    }else {
        registerData["idNumber"] = idNumber;
    }

    registerData["age"] = $.trim($("#age").val());
    registerData["email"] = $.trim($("#email").val());
    registerData["address"] = $.trim($("#address").val());

    return registerData;
}
