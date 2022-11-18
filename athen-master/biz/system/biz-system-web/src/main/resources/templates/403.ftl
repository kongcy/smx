<#import "/layout/$.ftl" as layout/>
<@layout.main title="无权访问">
<h2>403 - 用户权限不足,请联系管理员!</h2>

<p>
    <a href="${SessionUtil.getReferer()}">返回</a>
    <a href="${Render.url(domain.getManager(), "/main")}">返回首页</a>
</p>
</@layout.main>
