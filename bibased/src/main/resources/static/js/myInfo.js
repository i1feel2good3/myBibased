/**
 * Created by boboLei on 2018/5/4.
 */
$(function () {
    $.ajax({
        type:"get",
        url:"/bibased/personal/roleInfo",
        success:function (result) {
            if (result.code == 1){
                alert(result.msg);

                var data = result.data;
                $('#id').val(data.id);
                $("#userName").val(data.userName);
                $("#realName").val(data.realName);
                $("#phoneName").val(data.mobilePhone);

                if (data.userRole == "USER_SUPER"){
                    hideSuperInfo();
                    $("#role").val("超级管理员");
                }
                if (data.userRole == "USER_PATIENT"){
                    hidePatientInfo();
                    $("#role").val("普通用户");
                }
                if (data.userRole == "USER_DOCTOR"){
                    $("#role").val("医生");
                }


                if (data.idType == "IDENTITY_CARD"){
                    $("#idType").val("身份证");
                }
                if(data.idType == "MILITARY_CARD"){
                    $("#idType").val("军官证");
                }
                if (data.idType == "OTHER"){
                    $("#idType").val("其他");
                }
                $("#idNumber").val(data.idNumber);
                $("#age").val(data.age);
                $("#email").val(data.email);
                $("#address").val(data.address);
                $("#title").val(data.title);
                $("#subject").val(data.subject);
                $("#fields").val(data.fields);
                setReadOnly();
            }else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }
    })

});

function setReadOnly() {
    var readOnly = document.getElementsByTagName("input");
    for(var i = 0;i < readOnly.length;i++){
        readOnly[i].readOnly = true;
    }
}

function hideSuperInfo() {
    $("#hide1").hide();
    $("#hide2").hide();
    $("#hide3").hide();
    $("#hide4").hide();
    $("#hide5").hide();
    $("#hide6").hide();
}

function hidePatientInfo() {
    $("#hide4").hide();
    $("#hide5").hide();
    $("#hide6").hide();
}