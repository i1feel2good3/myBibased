/**
 * Created by boboLei on 2018/5/26.
 */
$(function () {
    localStorage.setItem("fromUserId",data.fromUserId);
    localStorage.setItem("toUserId",data.toUserId);
    localStorage.setItem("newsTheme",data.newsTheme);
    localStorage.setItem("newsContent",data.newsContent);

    $("#letterTheme").val(localStorage.getItem("newsTheme"));
    $("#letterTheme").attr("readonly","readonly");
    localStorage.removeItem("newsTheme");

    $("#letterContent").val(localStorage.getItem("newsContent"));
    $("#letterContent").attr("readonly","readonly");
    localStorage.removeItem("newsContent");

    localStorage.removeItem("fromUserId");
    localStorage.removeItem("toUserId");
});

$(function () {
    $("#shutDown").click(function () {
        //关闭窗口
        window.opener=null;
        window.open('','_self');
        window.close();
    })
});
