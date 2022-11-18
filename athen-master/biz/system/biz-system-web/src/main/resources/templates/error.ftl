<#import "/layout/$.ftl" as layout/>
<@layout.main title="无权访问">
    <h2>404 - 对不起，页面不存在!</h2>

    <p>
        <a href="${SessionUtil.getReferer()}">返回</a>
        <a href="${Render.url(domain.getManager(), "/main")}">返回首页</a>
    </p>
</@layout.main>
ß