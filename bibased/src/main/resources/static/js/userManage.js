/**
 * Created by boboLei on 2018/5/5.
 */
$(function () {
    if (document.cookie)
    $.ajax({
        dataType: 'json',
        type:"get",
        url:"/bibased/userManager/super",
        success:function (result) {
            document.cookie = "count = 1";
            if (result.code == 1){
                //alert(result.msg);
                var data = result.data;
                var roleCode = result.flag;
                if (roleCode == 2){
                    var superItem = "<p style='margin-top: 200px'>您没有权限，请移步！</p>"
                    $('.body').append(superItem);
                    $('.role-navigation').hide();
                    $('.roleData').hide();
                    return 0;
                }
                var item;
                $.each(data,function (i,users) {
                    item=
                        "<tr><td>"+users['id']+"</td><td>"+users['userName']+"</td>" +
                        "<td>"+users['userRole']+"</td><td>"+users['mobilePhone']+"</td></tr>";
                    $('.table').append(item);
                })
            }else {
                alert(result.msg);
                window.location.href="/bibased/index";
            }
        }
    })
});


$(function () {
    $("#superUser").click(function () {
        //location.reload();
        $(".table tr:not(:first)").empty("");
        //$("#table tr:not(:first)").html("");
        document.getElementById("superUser").className = "btn btn-primary";
        document.getElementById("patientUser").className = "btn btn-default";
        document.getElementById("doctorUser").className = "btn btn-default";
        $.ajax({
            dataType: 'json',
            type:"get",
            dataType:"json",
            url:"/bibased/userManager/super",
            success:function (result) {
                if (result.code == 1){
                    //alert(result.msg);
                    var data = result.data;
                    var item;
                    $.each(data,function (i,users) {
                        item=
                            "<tr><td>"+users['id']+"</td><td>"+users['userName']+"</td>" +
                            "<td>"+users['userRole']+"</td><td>"+users['mobilePhone']+"</td></tr>";
                        $('.table').append(item);
                    })
                }else {
                    alert(result.msg);
                    window.location.href="/bibased/index";
                }
            }
        })
    })
});

$(function () {
    $("#doctorUser").click(function () {
        //location.reload();
        $(".table tr:not(:first)").empty("");
        ///$("#table tr:not(:first)").html("");
        $("#table tbody").children().remove();
        document.getElementById("superUser").className = "btn btn-default";
        document.getElementById("patientUser").className = "btn btn-default";
        document.getElementById("doctorUser").className = "btn btn-primary";
        $.ajax({
            dataType: 'json',
            type:"get",
            dataType:"json",
            url:"/bibased/userManager/doctor",
            success:function (result) {
                if (result.code == 1){
                    //alert(result.msg);
                    var data = result.data;
                    var item;
                    $.each(data,function (i,users) {
                        item=
                            "<tr><td>"+users['id']+"</td><td>"+users['userName']+"</td>" +
                            "<td>"+users['userRole']+"</td><td>"+users['mobilePhone']+"</td></tr>";
                        $('.table').append(item);
                    })
                }else {
                    alert(result.msg);
                    window.location.href="/bibased/index";
                }
            }
        })
    })
});


$(function () {
    $("#patientUser").click(function () {
        //location.reload();
        $(".table tr:not(:first)").empty("");
        //$("#table tr:not(:first)").html("");
        document.getElementById("superUser").className = "btn btn-default";
        document.getElementById("patientUser").className = "btn btn-primary";
        document.getElementById("doctorUser").className = "btn btn-default";
        $.ajax({
            dataType: 'json',
            type:"get",
            dataType:"json",
            url:"/bibased/userManager/patient",
            success:function (result) {
                if (result.code == 1){
                    //alert(result.msg);
                    var data = result.data;
                    var item;
                    $.each(data,function (i,users) {
                        item=
                            "<tr><td>"+users['id']+"</td><td>"+users['userName']+"</td>" +
                            "<td>"+users['userRole']+"</td><td>"+users['mobilePhone']+"</td></tr>";
                        $('.table').append(item);
                    })
                }else {
                    alert(result.msg);
                    window.location.href="/bibased/index";
                }
            }
        })
    })
});