<#import "/layout/$.ftl" as layout>
<@layout.content>
版本: ${version!} <br/>

<#-- 这一行解析会出错, 如果上下文中没有这个变量的话: ${abcxyz} <br/> -->
这一行不会出错, 没有就不显示: ${abc!} <br/>
这一行会显示默认值: ${abc!'true'}<br/>

o: ${online?c} 要显示成 true false 这样的 String 要加 ?c !!!WTF!!!<br/>
p: ${domain.getIndex()} <br/>
a: ${domain.getApi()} <br/>
i: ${domain.getUpload()} <br/>

大于 gt <br>
小于 lt <br>
大于等于 gte <br>
小于等于 lte <br>

<#assign xyz = true, abc = "123", array = [1, 2, 3] />
<#if xyz>谁<#else>我</#if><br>

<#switch abc>
    <#case "123">
    已登录<#break>
    <#case "aaa">
    不存在<#break>
    <#case "bbb">
    错误<#break>
    <#default>
    请重试
</#switch><br>

<#list array as p>
    ${p}
</#list><br>

<br>大写: ${Money.toChinese("123.8912")}<br/><br>

时间: ${DateUtil.nowTime()} <br>
${DateUtil.now()?string("yyyy-MM-dd HH:mm:ss SSS")}<br><br>

src="http://www.abc.com?${version!}"<br>
src="${Render.mapping(domain.getManager(), 'IC#code')}"<br>
src="${Render.mapping('IC#code')}"<br>
src="${Render.mapping('IC#code').build()}"<br>
</@layout.content>
