/**
 * Created by boboLei on 2018/5/2.
 */
$(function () {
    $("#logout").click(function () {
        $.ajax({
            type:"get",
            url:"/bibased/logout",
            success:function (result) {
                if (result.code == 1){
                    window.location.href="/bibased/index";
                    alert(result.msg);
                }else {
                    alert(result.msg);
                }
            }
        })

    });
});