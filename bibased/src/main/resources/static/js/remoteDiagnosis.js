/**
 * Created by boboLei on 2018/6/7.
 */

$(function () {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/remote/video/getYourOrders",
        success:function (result) {
            var data = result.data;
            var userInfo = result.flag;
            //alert(userInfo.roleCode);
            //alert("haha");
            if (result.code == 1 || result.code == 0){
                if (userInfo.roleCode == 0){
                    var superItem;
                    superItem = "<p>超管，请换个身份登录查看！</p>";
                    $(".diagnosis").hide();
                    $(".body").prepend(superItem);
                }
                if (userInfo.roleCode == 1){
                    //alert(data);
                    $(".doctor").html("leibobo1");
                    $(".patient").html("雷博博");
                }
                if (userInfo.roleCode == 2){
                    //alert(userInfo.roleCode)
                    var patientItem;
                    patientItem = "<p style='margin-top: 200px;;'>请等待！接近预约时间，请注意</p>";
                    $(".body").append(patientItem);
                    $(".diagnosis").hide();
                }
            }
            /*if (result.code == 0){
                var item;
                item = "<p style='margin-top: 100px'>您今日没有预约！</p>"
                $(".diagnosis").hide();
                $(".body").append(item);
            }*/
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
