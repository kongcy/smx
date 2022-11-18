
<#macro side>
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <ul class="page-sidebar-menu" data-auto-scroll="false" data-auto-speed="300">
            <li class="sidebar-toggler-wrapper">
                <div class="sidebar-toggler"></div>
            </li>
            <li class="sidebar-search-wrapper hidden-xs">
                <form class="sidebar-search" action="#" method="POST">
                    <a href="javascript:void();" class="remove"></a>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="搜索...">
                        <span class="input-group-btn"><input class="btn submit" type="button" type="button" value=" "/></span>
                    </div>
                </form>
            </li>
            <#list SessionUtil.getResource()?sort_by("seq") as r>
            <#if '${r.type!}'=='MENU'> <!--只显示菜单资源 -->
            <#if '/main'?starts_with('${r.url!}')>
            <li<#if '${r.url!}'?starts_with('${SessionUtil.getPath()}')> class="start active"</#if>>
                <a href="${Render.url(domain.getManager(), "${r.url!}")}">
                    <i class="fa fa-home"></i><span class="title">${r.name!}</span><span class='selected'></span></a>
            </li>
            <#else>
            <li<#if '${SessionUtil.getPath()}'?starts_with('${r.url!}')> class="active open"</#if>>
                <a href="javascript:void(0);"><i class="${r.icon}"></i><span class="title">${r.name!}</span>
                    <#if '${SessionUtil.getPath()}'?starts_with('${r.url!}')>
                        <span class="selected"></span>
                    </#if>
                    <span class="arrow<#if '${SessionUtil.getPath()}'?starts_with('${r.url!}')> open</#if>"></span>
                </a>
                <ul class='sub-menu'>
                    <#list r.children?sort_by("seq") as ch>
                        <#if '${ch.type!}'=='MENU'>
                            <li<#if '${SessionUtil.getPath()}'?starts_with('${ch.url!}')> class="active"</#if>>
                                <a href='${Render.url(domain.getManager(), "${ch.url!}")}'>
                                    <#if ch.icon??><i class="${ch.icon!?trim}"></i></#if>${ch.name!}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </li>
            </#if>
            </#if>
            </#list>
        </ul>
    </div>
</div>
</#macro>
