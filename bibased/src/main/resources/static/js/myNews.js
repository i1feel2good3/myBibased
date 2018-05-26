/**
 * Created by boboLei on 2018/5/26.
 */

$(function () {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/news/noticeList",
        success:function (result) {
            if (result.code == 1){
                //$(".notice-list").empty("");
                var data = result.data;
                if (data == ""){
                    var remind = "<p>您还没有通知！</p>"
                    $(".notice-list").append(remind);
                }
                var item;
                $.each(data,function (i,newsNoticeList) {
                    item = "<li> <div class='left'> " +
                        "<img src='/img/notice.jpg'/> " +
                        "<strong>系统</strong>通知您，关于 " +
                        "<span>"+newsNoticeList['newsTheme']+"</span> " +
                        "</div> <div class='right'>" +
                        "<span>"+(new Date(newsNoticeList['createTime'])).Format("yyyy-MM-dd hh:mm:ss")/*.substr(9,12)*/+"</span>&nbsp;|&nbsp;" +
                        "<button type='button' class='btn btn-default' onclick='letterDetails("+newsNoticeList['id']+")'>详情</button> " +
                        "</div> </li>";
                    $(".noticeList").append(item);
                })
            }else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }
    });
});

$(function () {
    $("#notice").click(function () {
        window.location.reload();
    });
});

$(function () {
    $("#sendLetter").click(function () {
        $.ajax({
            type:"get",
            dataType: 'json',
            url:"/bibased/news/letterSendList",
            success:function (result) {
                if (result.code == 1){
                    $(".notice-list").empty("");
                    var data = result.data;
                    if (data == ""){
                        var remind = "<p>您还没有通知！</p>"
                        $(".notice-list").append(remind);
                    }
                    var item;
                    $.each(data,function (i,letterSendList) {
                        item = "<li> <div class='left'> " +
                            "<img src='/img/letter.jpg'/> " +
                            "<strong>您</strong>向"+letterSendList['toUserId']+"发送了私信 ->" +
                            "<span>"+letterSendList['newsTheme']+"</span> " +
                            "</div> <div class='right'>" +
                            "<span>"+(new Date(letterSendList['createTime'])).Format("yyyy-MM-dd hh:mm:ss")+"</span>&nbsp;|&nbsp;" +
                            "<button type='button' class='btn btn-default' onclick='letterDetails("+letterSendList['id']+")'>详情</button> " +
                            "</div> </li>";
                        $(".noticeList").append(item);
                    })
                }else {
                    alert(result.msg);
                    window.location.href="/bibased/index";
                }
            }
        });
    });
});

$(function () {
    $("#receivedLetter").click(function () {
        $.ajax({
            type:"get",
            dataType: 'json',
            url:"/bibased/news/letterReceivedList",
            success:function (result) {
                if (result.code == 1){
                    $(".notice-list").empty("");
                    var data = result.data;
                    if (data == ""){
                        var remind = "<p>您还没有通知！</p>"
                        $(".notice-list").append(remind);
                    }
                    var item;
                    $.each(data,function (i,letterReceivedList) {
                        item = "<li> <div class='left'> " +
                            "<img src='/img/letter.jpg'/> " +
                            "<strong>来自</strong>"+letterReceivedList['fromUserId']+"的私信" +
                            "<span>"+letterReceivedList['newsTheme']+"</span> " +
                            "</div> <div class='right'>" +
                            "<span>"+(new Date(letterReceivedList['createTime'])).Format("yyyy-MM-dd hh:mm:ss")+"</span>&nbsp;|&nbsp;&nbsp;&nbsp;" +
                            "<button type='button' class='btn btn-default' onclick='letterDetails("+letterReceivedList['id']+")'>详情</button> " +
                            "</div> </li>";
                        $(".noticeList").append(item);
                    })
                }else {
                    alert(result.msg);
                    window.location.href="/bibased/index";
                }
            }
        });
    });
});

function letterDetails(newsId) {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/news/letterDetails?newsId="+newsId,
        success:function (result) {
            if (result.code == 1){
                var data = result.data;
                localStorage.setItem("fromUserId",data.fromUserId);
                localStorage.setItem("toUserId",data.toUserId);
                localStorage.setItem("newsTheme",data.newsTheme);
                localStorage.setItem("newsContent",data.newsContent);
                window.open("/bibased/news/detailLetter");
            }else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }
    })
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
}