/**
 * Created by boboLei on 2018/3/15
 */
$(function () {
    $("#login").click(function () {
        /*这个也是可以的
        var login = {
            name:$("#inputName").val(),
            pw:$("#inputPassword3").val(),
            code:$("#userType").val()
        };*/
        var login = {}
        var name = $.trim($("#inputName").val());
        if (name == null || name.length == 0){
            alert("用户名不能为空");
            return;
        }else {
            login["name"] = name;
            var pw = $.trim($("#inputPassword3").val());
            if (pw == null || pw.length == 0){
                alert("密码不能为空");
                return;
            }else {
                login["pw"] = pw;
            }
        }
        login["code"] = $("#userType").val();

        $.ajax({
            type: 'post',
            url: '/bibased/login',
            data: JSON.stringify(login),
            dataType:'json',
            contentType:"application/json;charset=utf-8",
            success:function (result) {
                if (result.code == 1){
                    alert(result.msg);
                    var data = result.data;
                    alert(data.id);
                    window.location.href="/bibased/personal/info";
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

