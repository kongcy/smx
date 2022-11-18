<#import "/layout/$.ftl" as layout/>
<script>
    function isHidden() {
        if($("#error").css("display")=="none"){
            $("#error").show();
        }else{
            $("#error").hide();
        }
    }
</script>
<@layout.main title="系统异常">
<h2>500 - 系统异常,请联系管理员!</h2>

<p>
    <a href="${SessionUtil.getReferer()}">返回</a>
    <a href="${Render.url(domain.getManager(), "/main")}">返回首页</a>
</p>
<p><a href="#" onclick="isHidden()"><h4>查看详细错误!</h4></a><br/></p>
<p id="error" style="display:none">${error}</p>

</@layout.main>
