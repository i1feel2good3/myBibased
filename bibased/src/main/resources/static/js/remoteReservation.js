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
                    var item;
                    $.each(data,function (i,validDoctor) {
                        //var docInfo;
                        //docInfo = validDoctor["id"]+"_"+validDoctor["realName"];
                        item=
                            /*"<tr><td>"+validDoctor['id']+"</td><td>"+validDoctor['doctorId']+"</td>" +
                            "<td>"+validDoctor['doctorPlan']+"</td><td>"+validDoctor['planTime']+"</td>" +
                            "<td>"+"<button type='button' onclick='change(" + validDoctor['id'] + ")'>修改</button>"+"</td></tr>";*/
                            "<div class='ValidPlanDoc'> " +
                            "<div class='top'> " +
                            "<img src='/img/doctor.jpg'/> " +
                            "<div>"+validDoctor['realName']+ "</div> " +
                            "<div>"+validDoctor['age']+" </div> " +
                            "<div>"+validDoctor['email']+" </div> " +
                            "</div> <div class='center'> " +
                            "<div class='subject' > " +
                            "<span>科目：</span> "+"<p>"+validDoctor['subject']+"</p>"+"</div> " +
                            "<div class='fields' > " +
                            "<span>擅长：</span> " +
                            "<p>"+validDoctor['fields']+"</p> " +
                            "</div> </div> " +
                            "<div class='bottom'> " +
                            "<button type='button' class='btn btn-default' onclick='orderPlan("+validDoctor['id']+")'>预约</button> " +
                            //"<button type='button' class='btn btn-default' onclick='goPrivateLetter("+validDoctor['id']+","+validDoctor['realName']+")'>私信</button>"+
                            "<button type='button' class='btn btn-default' onclick='goPrivateLetter("+validDoctor['id']+")'>私信</button>"+
                            "</div> </div>"
                        //document.getElementById("")
                        $('.validDocTable').append(item);
                    })
                }

                if (userInfo.roleCode == 0){
                    var superItem;
                    superItem = "<p>您是管理员，请换普通用户身份或者医生来预约</p>"
                    $('.validDocTable').append(superItem);
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
                if (data == ""){
                    var remind = "<p>您还没有可以修改的计划，请先添加计划！</p>"
                    $(".table").append(remind)
                }
                var item;
                $.each(data,function (i,personalPlan) {
                    item=
                        "<tr><td>"+personalPlan['id']+"</td><td>"+personalPlan['doctorId']+"</td>" +
                        "<td>"+personalPlan['doctorPlan']+"</td><td>"+personalPlan['planTime']+"</td>" +
                        "<td>"+"<button type='button' class='btn btn-default' onclick='change(" + personalPlan['id'] + ")'>修改</button>"+"</td></tr>";
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
//根据id返回医生7天内的计划
function orderPlan(docId) {
    $("#reservableDoc").show();
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/bibased/remote/getDocValidPlan?docId="+docId,
        success:function (result) {
            if (result.code == 1){
                var headItem = "<tr class='first'> <th scope='col'> " +
                    "<span>"+getWeek(0)+"</span> <p>"+getDateStr(0)+"</p> </th> " +
                    "<th scope='col'> <span>"+getWeek(1)+"</span> <p>"+getDateStr(1)+"</p> " +
                    "</th> <th scope='col'> <span>"+getWeek(2)+"</span> <p>"+getDateStr(2)+"</p> " +
                    "</th> <th scope='col'> <span>"+getWeek(3)+"</span> <p>"+getDateStr(3)+"</p> " +
                    "</th> <th scope='col'> <span>"+getWeek(4)+"</span> <p>"+getDateStr(4)+"</p> " +
                    "</th> <th scope='col'> <span>"+getWeek(5)+"</span> <p>"+getDateStr(5)+"</p> " +
                    "</th> <th scope='col'> <span>"+getWeek(6)+"</span> <p>"+getDateStr(6)+"</p> </th> </tr>";
                $("#orderTable").prepend(headItem);
                var data = result.data;
                $.each(data,function (i,sevenPlanList) {
                    var col = planBelongToCol(sevenPlanList['planTime']);
                    //alert("第几列数据："+col);
                    var planMorning = sevenPlanList['doctorPlan'].substr(0,2);
                    var planAfternoon = sevenPlanList['doctorPlan'].substr(2,2);
                    switch (col){
                        case 1:
                            var oneToOne = $(".second").children("td").eq(0);
                            oneToOne.find("input").val("1_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    oneToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    oneToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                oneToOne.prepend("约满");
                            }
                            var oneToTwo = $(".third").children("td").eq(0);
                            oneToTwo.find("input").val("1_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    oneToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    oneToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                oneToTwo.prepend("约满");
                            }
                            break;
                        case 2:
                            var twoToOne = $(".second").children("td").eq(1);
                            twoToOne.find("input").val("2_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    twoToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    twoToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                twoToOne.prepend("约满");
                            }
                            var twoToTwo = $(".third").children("td").eq(1);
                            twoToTwo.find("input").val("2_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    twoToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    twoToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                twoToTwo.prepend("约满");
                            }
                            break;
                        case 3:
                            var thirdToOne = $(".second").children("td").eq(2);
                            thirdToOne.find("input").val("3_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    thirdToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    thirdToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                thirdToOne.prepend("约满");
                            }
                            var thirdToTwo = $(".third").children("td").eq(2);
                            thirdToTwo.find("input").val("3_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    thirdToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    thirdToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                thirdToTwo.prepend("约满");
                            }
                            break;
                        case 4:
                            var fourToOne = $(".second").children("td").eq(3);
                            fourToOne.find("input").val("4_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    fourToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    fourToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                fourToOne.prepend("约满");
                            }
                            var fourToTwo = $(".third").children("td").eq(3);
                            fourToTwo.find("input").val("4_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    fourToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    fourToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                fourToTwo.prepend("约满");
                            }
                            break;
                        case 5:
                            var fiveToOne = $(".second").children("td").eq(4);
                            fiveToOne.find("input").val("5_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    fiveToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    fiveToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                fiveToOne.prepend("约满");
                            }
                            var fiveToTwo = $(".third").children("td").eq(4);
                            fiveToTwo.find("input").val("5_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    fiveToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    fiveToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                fiveToTwo.prepend("约满");
                            }
                            break;
                        case 6:
                            var sixToOne = $(".second").children("td").eq(5);
                            sixToOne.find("input").val("6_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    sixToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    sixToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                sixToOne.prepend("约满");
                            }
                            var sixToTwo = $(".third").children("td").eq(5);
                            sixToTwo.find("input").val("6_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    sixToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    sixToTwo.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                sixToTwo.prepend("约满");
                            }
                            break;
                        case 7:
                            var sevenToOne = $(".second").children("td").eq(6);
                            sevenToOne.find("input").val("7_1_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planMorning != "00"){
                                if (planMorning == "11"){
                                    sevenToOne.prepend("可约<br><p>2</p>");
                                }else {
                                    sevenToOne.prepend("可约<br><p>1</p>");
                                }
                            }else {
                                sevenToOne.prepend("约满");
                            }
                            var sevenToTwo = $(".third").children("td").eq(6);
                            sevenToTwo.find("input").val("7_2_"+sevenPlanList['planTime']+"_"+sevenPlanList['id']);
                            if (planAfternoon != "00"){
                                if (planAfternoon == "11"){
                                    sevenToTwo.prepend("可约<br><p>2</p>");
                                }else {
                                    sevenToTwo.prepend("可约<br><p>1</p>");
                                }
                                sevenToTwo.prepend("约满");
                            }
                            break;
                    }
                })
            }else {
                alert(result.msg);
                window.location.reload();
            }
        }
    })
}
//确认预约信息
$(function () {
    $(".orderThis").click(function () {
        //trSeq,tdSeq分别表示行和列，都是从0开始数，按照table
        //info是行和列只按照预约中的数据（不含表头），从1开始数
        var tdSeq = $(this).parent().find("td").index($(this)[0]);
        var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);
        //alert("第" + (trSeq) + "行，第" + (tdSeq) + "列");
        var info = $(this).find("input").val();
        if (info == ""){
            alert("此时间段不可预约，请预约可预约时间段！")
        }else {
            var res = confirm("请确认预约!");
            if(res == true){
                //alert(info);/*5_1_2018-05-29_6*/
                var remainOrderNum = $(this).find("p").text();
                order(info,remainOrderNum);
            }else{
                alert("已取消预约！")
            }
        }
    });
});

function order(info,remainOrderNum) {
    var col = info.substr(0,1);
    var row = info.substr(2,1);
    var time = info.substr(4,10);
    var planDoctorId = info.substr(15,1);
    var setZeroNum;
    if (row == 1 && remainOrderNum == 2){
        setZeroNum = 1;
        time = time + " 08:00:00";
    }
    if (row == 1 && remainOrderNum == 1){
        setZeroNum = 2;
        time = time + " 10:00:00";
    }
    if (row == 2 && remainOrderNum == 2){
        setZeroNum = 3;
        time = time + " 14:00:00";
    }
    if (row == 2 && remainOrderNum == 1){
        setZeroNum = 3;
        time = time + " 16:00:00";
    }
    var oneOrder = {};
    oneOrder["planDoctorId"] = planDoctorId;
    oneOrder["orderTime"] = time;
    oneOrder["setZeroNum"] = setZeroNum;
    $.ajax({
        type:"post",
        url:"/bibased/remote/andOrder",
        data: JSON.stringify(oneOrder),
        dataType:'json',
        contentType:"application/json;charset=utf-8",
        success:function (result) {
            if (result.code == 1){
                $("#reservableDoc").hide();
                alert("预订成功！");
            }else {
                alert(result.msg);
                window.location.reload();
            }
        }
    })
}

$(function () {
    $("#cancelOrder").click(function () {
        $("#reservableDoc").hide();
        $(".first").remove();
        //window.location.reload();
    });
});

//获取当前的计划信息属于那一列
function planBelongToCol(planTime) {
    if (planTime == getDateStr(0)){
        return 1;
    }
    if (planTime == getDateStr(1)){
        return 2;
    }
    if (planTime == getDateStr(2)){
        return 3;
    }
    if (planTime == getDateStr(3)){
        return 4 ;
    }
    if (planTime == getDateStr(4)){
        return 5;
    }
    if (planTime == getDateStr(5)){
        return 6;
    }
    if (planTime == getDateStr(6)){
        return 7;
    }
}

function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    if (m >= 1 && m <= 9){
        m = "0" + m;
    }
    var d = dd.getDate();
    if (d >= 0 && d <= 9){
        d = "0" + d;
    }
    return y+"-"+m+"-"+d;
}

function getWeek(addDayCount) {
    var myDate=new Date();
    var myddy = myDate.getDay();//获取存储当前日期
    var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    switch (addDayCount){
        case 0:
            return weekday[myddy];
            break;
        case 1:
            return weekday[(myddy+1)%7];
            break;
        case 2:
            return weekday[(myddy+2)%7];
            break;
        case 3:
            return weekday[(myddy+3)%7];
            break;
        case 4:
            return weekday[(myddy+4)%7];
            break;
        case 5:
            return weekday[(myddy+5)%7];
            break;
        case 6:
            return weekday[(myddy+6)%7];
            break;
    }
}

function goPrivateLetter(doctorId) {

    /*doctorInfo["doctorId"] = doctorId;
    doctorInfo["realName"] = realName;*/
    //alert(doctorInfo);
    localStorage.setItem("doctorId",doctorId);
    //localStorage.setItem("doctorInfo",doctorInfo);
    window.open("/bibased/news/writeLetter");
}

