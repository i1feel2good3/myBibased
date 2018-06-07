/**
 * Created by boboLei on 2018/6/7.
 */

$(function () {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/remote/getYourOrder",
        success:function (result) {
            var data = result.data;
            var userInfo = result.flag;
            if (result.code == 1){
                if (userInfo.roleCode == 0){
                    var superItem;
                    superItem = "<p>超管，请换个身份登录查看！</p>";
                    $(".diagnosis").hide();
                    $(".body").prepend(superItem);
                }
                if (userInfo.roleCode == 1){
                    alert(data);
                }
                if (userInfo.roleCode == 2){
                    var patientItem;
                    patientItem = "<p>请等待！</p>";
                    $(".body").prepend(patientItem);
                }
            }
            if (result.code == 0){
                var item;
                item = "<p style='margin-top: 100px'>您今日没有预约！</p>"
                $(".diagnosis").hide();
                $(".body").append(item);
            }
            else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }
    })
});

$(function () {
    $("#remoteVideo").click(function () {
        window.open("/bibased/remote/video");
    });
});
