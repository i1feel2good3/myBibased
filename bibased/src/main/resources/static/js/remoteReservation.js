/**
 * Created by boboLei on 2018/5/5.
 */
//init
$(function () {
    $.ajax({
        type:"get",
        dataType: 'json',
        url:"/bibased/remote/getPlanTime",
        success:function (result) {
            if (result.code == 1){
                alert(result.data);
                var data = result.data;
                var userInfo = result.flag;
                if (userInfo.roleCode == 1){
                    //初始化医生安排界面
                    $("#changePlanDiv").hide();
                    initDoctor(data);
                }

                if (userInfo.roleCode == 2){
                    //初始化普通用户预约界面
                    alert(userInfo.roleCode);
                    $("#remoteDoctor").hide();
                    $("#reservableDoc").hide();

                }

                if (userInfo.roleCode == 0){
                    //初始化超及管理员界面
                }
            }else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }

    })
});

//提交计划成功与否都刷新页面
$(function () {
    $("#submitPlan").click(function () {
        var plan = {};
        var date = $("#date").val();
        var morning = $("#morning").val();
        var afternoon = $("#afternoon").val();
        plan["date"] = date;
        plan["docPlan"] = morning.concat(afternoon);
        $.ajax({
            type: 'post',
            url: '/bibased/remote/and',
            data: JSON.stringify(plan),
            dataType:'json',
            contentType:"application/json;charset=utf-8",
            success:function (result) {
                if (result.code == 1){
                    alert(result.msg);
                    window.location.reload();
                }else {
                    alert("请重新提交，本次提交无效");
                    window.location.reload();
                }
            }
        })
    });
});

//点击添加计划按钮事件(属于医生的初始化)
$(function () {
    $("#andPlan").click(function () {
        window.location.reload();
    })
});

//点击修改计划，弹出所有可修改的计划列表，后有修改按钮
$(function () {
    $("#changePlan").click(function () {
        document.getElementById("andPlan").className = "btn btn-default";
        document.getElementById("changePlan").className = "btn btn-primary";
        $(".table tr:not(:first)").empty("");
        $.ajax({
            type:"get",
            dataType:"json",
            url:"/bibased/remote/personalPlanList",
            success:function(result) {
            if (result.code == 1){
                $("#remotePatient").hide();
                $("#andPlanDiv").hide();
                $("#openDiv").hide();
                $("#changePlanDiv").show();
                var data = result.data;
                var item;
                $.each(data,function (i,personalPlan) {
                    item=
                        "<tr><td>"+personalPlan['id']+"</td><td>"+personalPlan['doctorId']+"</td>" +
                        "<td>"+personalPlan['doctorPlan']+"</td><td>"+personalPlan['planTime']+"</td>" +
                        "<td>"+"<button type='button' onclick='change(" + personalPlan['id'] + ")'>修改</button>"+"</td></tr>";
                    $('.table').append(item);
                })
            }else {
                alert(result.msg);
                window.location.reload();
            }
        }
        })
    })
});

/*//点击修改计划按钮事件(属于医生的初始化)
$(function () {
    $("#changePlan").click(function () {
        document.getElementById("andPlan").className = "btn btn-default";
        document.getElementById("changePlan").className = "btn btn-primary";
        var time = getDateStr(7);
        $("#date").val(time);
        $.ajax({
            type:"get",
            dataType: 'json',
            url:"/bibased/remote/getOnePlan?planTime="+time,
            success:function (result) {
                if (result.code == 1){
                    $("#remotePatient").hide();
                    $("#andPlanDiv").hide();
                    $("#changePlanDiv").show();

                    var data = result.data;
                    var morning = data.planTime.substr(0,2);
                    $("#morning").val();
                    var afternoon = data.planTime.substr(2,2);
                    $("#afternoon").val();
                }else {
                    alert(result.msg);
                    window.location.reload();
                }
            }
        })
    })
});*/

function initDoctor(data) {
    $("#remotePatient").hide();
    $("#date").val(data);
    $("#changePlanDiv").hide();
    $("#openDiv").hide();
}

function change(id) {
    $("#openDiv").show();
    $("#alter").click(function () {
        var changePlan = {};
        var morning = $("#openMorning").val();
        var afternoon = $("#openAfternoon").val();
        changePlan["id"] = id;
        changePlan["docPlan"] = morning.concat(afternoon);
        $.ajax({
            type:"post",
            url:"/bibased/remote/change",
            data: JSON.stringify(changePlan),
            dataType:'json',
            contentType:"application/json;charset=utf-8",
            success:function (result) {
                if (result.code == 1){
                    alert("修改成功！");
                    $("#openDiv").hide();
                }else {
                    alert(result.msg);
                    window.location.reload();
                }
            }
        })
    })
    $("#cancel").click(function () {
        $("#openDiv").hide();
    })
}

function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}