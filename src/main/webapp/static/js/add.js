$(function () {
    var opo="";
    $("#redirect").click(function () {
        window.location.href="member";
    });
    $(".sub").click(function () {
        opo=$(this).val();
        if(opo=="审核通过"){
            opo="yes";
        }if(opo=="驳回"){
            opo="no";
        }
        console.log(opo);
        $.post("checkInfo1",{opo:opo,advice:$("#advice").val(),id:parseInt($("#id").val())},function (data) {
            console.log(data);
            if(data==0){
                window.location.reload();
            }
            else {
                if(opo=="yes"){
                    alert("审核成功,信息已经提交给分子公司审核!");
                }if(opo=="no"){
                    alert("驳回采购信息成功!");
                }
                window.location.href="member";
            }
        });
    })
})