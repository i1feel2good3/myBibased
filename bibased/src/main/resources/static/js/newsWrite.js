/**
 * Created by boboLei on 2018/5/26.
 */
$(function () {
   // $("#sendToUser").val(localStorage.getItem("doctorInfo").doctorId+"号医生"+localStorage.getItem("doctorInfo").realName);
    $("#sendToUser").val(localStorage.getItem("doctorId")+"号医生");
    $("#sendToUser").attr("readonly","readonly");
});

$(function () {
    $("#sendLetter").click(function () {
        var doctorId = $("#sendToUser").val().substr(0,1);
        var letterTheme = $.trim($("#letterTheme").val());
        if (letterTheme == null && letterTheme.length == 0){
            alert("邮件主题不能为空！")
            return false;
        }
        var letterContent = $.trim($("#letterContent").val());
        if (letterContent == null && letterContent.length ==0){
            alert("邮件内容不能为空！")
            return false;
        }
        alert(doctorId+"_"+letterTheme+"_"+letterContent);
        var newsSaveModel = {};
        newsSaveModel["toUserId"] = doctorId;
        newsSaveModel["newsTheme"] = letterTheme;
        newsSaveModel["newsContent"] = letterContent;

        $.ajax({
            type:"post",
            url:"/bibased/news/save",
            data: JSON.stringify(newsSaveModel),
            dataType:'json',
            contentType:"application/json;charset=utf-8",
            success:function (result) {
                if (result.code == 1){
                    alert(result.msg);
                    //关闭窗口
                    window.opener=null;
                    window.open('','_self');
                    window.close();
                }else {
                    alert(result.msg+"返回上一个页面！");
                    window.history.back(-1);
                }
            }
        })
    })
});
