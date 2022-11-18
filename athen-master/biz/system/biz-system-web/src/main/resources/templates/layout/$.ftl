
<#import "/layout/header.ftl" as header/>
<#import "/layout/footer.ftl" as footer/>
<#import "/layout/sidebar.ftl" as side/>

<#macro head title="">
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="Author" content="13322 Lawyer Team">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0,user-scalable=no">

<title><#if title != "">${title!} - </#if>** -- **  运营平台</title>
<link rel="shortcut icon" href="${Render.url(domain.getStill(), "favicon.ico")}"/>
</#macro>


<#-- 引多个 css -->
<#macro style csses=[]>
<#list csses as css>
<link rel="stylesheet" href="${Render.url(domain.getStill(), css, true)}"/>
</#list>
</#macro>


<#-- 公共 js -->
<#macro commonJs>
 <!--<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
<!--<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</#macro>

<#-- 引多个 js -->
<#macro script jses=[]>
<#list jses as js>
<script src="${Render.url(domain.getStill(), js, true)}"></script>
</#list>
</#macro>

<#-- 充当 js 工具的代码 -->
<#macro jsfun>
<script>
/* "{0} is bad, use {1}! {0} {2}".format("jsp", "thymeleaf") */
/* output : jsp is bad, use thymeleaf! jsp {2} */
if (!String.prototype.format) {
    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != "undefined" ? args[number] : match;
        });
    };
}
/* String.format("{0} is bad, use {1}! {0} {2}", "jsp", "thymeleaf"); */
/* output : jsp is bad, use thymeleaf! jsp {2} */
if (!String.format) {
    String.format = function(format) {
        var args = Array.prototype.slice.call(arguments, 1);
        return format.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != "undefined" ? args[number] : match;
        });
    };
}
</script>
</#macro>


<#-- 线上环境时去除页面中的注释并输出成一行, 非线上环境时只删除空白行 -->
<#macro render>
<#local html><#nested></#local>
<#if online>
<#local page><@compress single_line=true>${Render.comment(html)}</@compress></#local>
${page?replace("> ", ">")}
<#else>
<#compress>${html!}</#compress>
</#if>
</#macro>


<#-- 标准模板内容 -->
<#macro content title="" css=[] js=[] pageCss="" pageJs="">
<@render>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <@head title/>
    <@style css/>
    ${pageCss!}<#-- @ftlvariable name="js" type="java.util.ArrayList" -->
    <#if js?size gt 0 || pageJs != ""><@commonJs/></#if>
</head>
<body>
    <#nested/>
    <@script js/>${pageJs!}
</body>
</html>
</@render>
</#macro>


<#-- !!!页面模板!!! -->
<#macro main title="" pageCss="" pageJs="">
<@render>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <@head title/>
    <@style ["manager/global/css/openSans.css",
    "manager/global/plugins/font-awesome/css/font-awesome.min.css",
    "manager/global/plugins/simple-line-icons/simple-line-icons.min.css",
    "manager/global/plugins/bootstrap/css/bootstrap.min.css",
    "manager/global/plugins/uniform/css/uniform.default.css",
    
    "manager/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css",
    "manager/global/plugins/fancybox/source/jquery.fancybox.css",
    
    "manager/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"] />
    <!-- page css start -->
    ${pageCss!}
    <!-- page css end -->
    <@style ["manager/global/css/components.css", "manager/global/css/plugins.css",
    "manager/admin/layout/css/layout.css", "manager/admin/layout/css/themes/default.css",
    "manager/admin/layout/css/custom.css"] />
    <script>
        /* 主站域名. 结尾拼接斜杠  */
        var main="${U.addSuffix(Render.url(domain.getManager(), ""))}";
        /* 静态资源域名. 结尾拼接斜杠 */
        var ctx="${U.addSuffix(Render.url(domain.getStill(), ""))}";
    </script>
    <@commonJs/>
</head>
<body class="page-header-fixed ">
<!-- head start -->
<@header.head/>
<!-- head end -->
<div class="clearfix"></div>
<div class="page-container">
    <!-- bar start -->
    <@side.side/>
    <!-- bar end -->
    <!-- content start -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <#nested/>
        </div>
    </div>
    <!-- content end -->
</div>
<!-- foot start -->
<@footer.foot/>
<!-- foot end -->
<@jsfun/>
<!--[if lt IE 9]>
<@script ["manager/global/plugins/respond.min.js", "manager/global/plugins/excanvas.min.js"]/>
<![endif]-->
<@script ["manager/global/plugins/jquery-1.11.0.min.js",
"manager/global/plugins/jquery.cokie.min.js",
"manager/global/plugins/jquery-migrate-1.2.1.min.js",
"manager/global/plugins/bootstrap/js/bootstrap.min.js",
"manager/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js",
"manager/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js",
"manager/global/plugins/jquery.blockui.min.js",
"manager/global/plugins/uniform/jquery.uniform.min.js"]/>

<@script ["manager/global/plugins/fancybox/source/jquery.fancybox.pack.js",
"manager/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js",
"manager/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"]/>

<!--[if (gte IE 8)&(lt IE 10)]>
<@script ["manager/global/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js"]/>
<![endif]-->

<@script ["manager/global/scripts/metronic.js", "manager/admin/layout/scripts/layout.js"]/>
<script>
jQuery(document).ready(function() {
    Metronic.init();
    Layout.init();
});
window.onload=function() {
    if($('.page-sidebar-menu .open a')[0] != undefined) {
        $('.page-breadcrumb li a')[0].innerText = $('.page-sidebar-menu .open a')[0].innerText;
    }
    if($('.page-sidebar-menu .open ul .active')[0] != undefined) {
        var current=$('.page-sidebar-menu .open ul .active')[0].innerText;
        var currentUrl=$('.page-sidebar-menu .open ul .active')[0].baseURI;
        $('.page-breadcrumb li a')[1].innerText=clearBr(current);
        $('.page-breadcrumb li a')[1].href=currentUrl;
    }
};
function clearBr(key) {
    key = key.replace(/<\/?.+?>/g,"");
    key = key.replace(/[\r\n]/g, "");
    return key;
}
</script>

<!-- page js start -->
${pageJs!}
<!-- page js end -->
</body>
</html>
</@render>
</#macro>
