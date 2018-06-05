/**
 * Created by boboLei on 2018/5/27.
 */
$(function () {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/history/list",
        success:function (result) {
            if (result.code == 1){
                $(".allList").empty("");
                var data = result.data;
                var userInfo = result.flag;
                if (data == ""){
                    var remind = "<p>您还没有历史记录！</p>"
                    $(".allList").append(remind);
                }
                if (userInfo.roleCode == 0){
                    var item;
                    $.each(data,function (i,historyList) {
                        var done;
                        if (historyList['isDone'] == false){
                            done = "未完成";
                        }else {
                            done = "已完成";
                        }
                        item = "<li> <img src='/img/history.jpg'/> " +
                        "<span>"+historyList['patientName']+"</span>预约了 " +
                            "<span>"+historyList['doctorName']+"</span>预约 " +
                            "<span>"+done+"</span> " +
                                "时间："+
                                "<span>"+historyList['orderTime']+"</span>"+
                            "</li>";
                        $(".allList").append(item);
                    });
                }
                if (userInfo.roleCode == 1){
                    var item;
                    $.each(data,function (i,historyList) {
                        var done;
                        if (historyList['isDone'] == false){
                            done = "未完成";
                        }else {
                            done = "已完成";
                        }
                        item = "<li> <img src='/img/history.jpg'/> " +
                            "<span>"+historyList['patientName']+"</span>预约了 " +
                            "<span>您&nbsp;&nbsp;</span>预约 " +
                            "<span>"+done+"</span> " +
                            "时间："+
                            "<span>"+historyList['orderTime']+"</span>"+
                            "</li>";
                        $(".allList").append(item);
                    });
                }
                if (userInfo.roleCode == 2){
                    var item;
                    $.each(data,function (i,historyList) {
                        var done;
                        if (historyList['isDone'] == false){
                            done = "未完成";
                        }else {
                            done = "已完成";
                        }
                        item = "<li> <img src='/img/history.jpg'/> " +
                            "<span>您</span>预约了医生 " +
                            "<span>"+historyList['doctorName']+"</span>预约 " +
                            "<span>"+done+"</span> " +
                            "时间："+
                            "<span>"+historyList['orderTime']+"</span>"+
                            "</li>";
                        $(".allList").append(item);
                    });
                }
            }else {
                alert(result.msg);
            }
        }
    });
});

