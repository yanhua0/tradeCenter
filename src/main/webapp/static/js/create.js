$(function () {
    $("#save").click(function () {

        if (check() != false) {
            alert("保存成功！");
            window.document.f.action = "createInfo";
            window.document.f.submit();
        }

    });
    $("#confirm").click(function () {
        if (check() != false) {
            $.Pop('提交采购订单成功,请等待审核！', {Animation: 'layer-spread'});
            $(".showAlert").css({"top": "130px", "left": "240px"});
            setInterval(go, 2000);
        }
    });

    function check() {
        var a = document.getElementById("u172_input").checked;
        var b = document.getElementById("u143_input").value;
        var c = document.getElementById("u178_input").checked;
        var d = document.getElementById("u181_input").value;
        if (a == true) {
            if (b == "") {
                alert("请填写报价保证金缴纳");
                return false;
            }
        }
        if (c == true) {
            if (d == "") {
                alert("请填写履约保证金缴纳");
                return false;
            }

        }


        if ($("#u73_input").val().length == 0) {
            alert("请填写申请单位");
            return false;
        }

        if ($("#u151_input").val().length == 0) {
            alert("请填写申请人");
            return false;
        }

        if ($("#u152_input").val().length == 0) {
            alert("请填写签发人");
            return false;
        }

        if ($("#u155_input").val().length == 0) {
            alert("请填写申请日期");
            return false;
        }

        if ($("#u75_input").val().length == 0) {
            alert("请填写需要采购数量");
            return false;
        }
        if ($("#u79_input").val().length == 0) {
            alert("请填写交货地点");
            return false;
        }
        if ($("#u164_input").val().length == 0) {
            alert("请填写最高限价");
            return false;
        }
        if ($("#u148_input").val().length == 0) {
            alert("请填写最低限价");
            return false;
        }

        if ($("#u142_input").val().length == 0) {
            alert("请填写限价说明");
            return false;
        }

        if ($("#u141-input").val() == "") {
            alert("请说明结算付款方式");
            return false;
        }

        if ($("#u90_input").val().length == 0) {
            alert("请填写报价截止时间");
            return false;
        }

        if ($("#u155_input").val().length == 0) {
            alert("请填写申请时间");
            return false;
        }
        if ($("#u76_input").val().length == 0 || $("#u156_input").val().length == 0) {
            alert("请填写交货时间");
            return false;
        }
        if ($("#u80_input").val().length == 0 || $("#u86_input").val().length == 0 || $("#u88_input").val().length == 0) {
            alert("请完善对煤质的要求");
            return false;
        }

        if ($("#u199_input").val().length == 0) {
            alert("请填写备注");
            return false;
        }
        var stopdays, begaindays, timer1, timer2, convertDate, convert1Date;
        stopdays = $("#u90_input").val();
        begaindays = $("#u155_input").val();
        begainday = begaindays.split("-");
        stopday = stopdays.split("-");
        convertDate = new Date(begainday[0], begainday[1], begainday[2]);
        convert1Date = new Date(stopday[0], stopday[1], stopday[2]);
        timer1 = convertDate.getTime();
        timer2 = convert1Date.getTime();
        console.log(timer2 - timer1 + "-------------");
        if (timer2 - timer1 < 259200000) {
            $.Pop('报价截止时间与申请日期之差不能低于3天！', {Animation: 'layer-rollIn'});
            $(".showAlert").css({"top": "130px", "left": "240px"});
            return false;
        }

    }

    function go() {
        window.document.f.action = "save";
        window.document.f.submit();
    }
})