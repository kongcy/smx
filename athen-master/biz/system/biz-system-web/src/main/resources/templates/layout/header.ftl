
<#macro head>
<div class="page-header navbar navbar-fixed-top">
    <div class="page-header-inner">
        <div class="page-logo">
            <a href="${Render.url(domain.getManager(), "main")}">
                <img src="${Render.url(domain.getStill(), "manager/admin/layout/img/LOGO-3.png")}" alt="logo" class="logo-default"/>
            </a>
            <div class="menu-toggler sidebar-toggler hide"></div>
        </div>
        <div class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"></div>
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <img alt="" class="img-circle" src="${Render.url(domain.getStill(), "manager/admin/layout/img/avatar3_small.jpg")}"/>
                        <span class="username">${SessionUtil.getUserName()}</span>
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <#--<li><a href="#"><i class="fa fa-user"></i>我的资料</a></li>
                        <li><a href="#"><i class="fa fa-calendar"></i>我的日程 </a></li>
                        <li><a href="#"><i class="fa fa-envelope"></i>我的信箱 </a></li>
                        <li><a href="#"><i class="fa fa-tasks"></i>我的任务 </a></li>
                        <li class="divider"></li>
                        <#--<li><a href="#"><i class="fa fa-lock"></i>我要锁屏</a></li>&ndash;&gt;-->
                            <li><a href="${Render.url(domain.getManager(), "/update-info")}"><i class="fa fa-key"></i>修改资料</a></li>
                            <li><a href="${Render.url(domain.getManager(), "/logout")}"><i class="fa fa-key"></i>我要退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
</#macro>
