<#import "/layout/$.ftl" as layout>
<@layout.render>
<!DOCTYPE html>
<html>
<head>
<@layout.head title="登录页" />
<@layout.style csses=["manager/global/css/openSans.css",
                      "manager/global/plugins/font-awesome/css/font-awesome.min.css",
                "manager/global/plugins/simple-line-icons/simple-line-icons.min.css",
                "manager/global/plugins/bootstrap/css/bootstrap.min.css",
                "manager/global/plugins/uniform/css/uniform.default.css",
                "manager/global/plugins/select2/select2.css",
                "manager/global/css/components.css",
                "manager/global/css/plugins.css", "manager/admin/layout/css/layout.css",
                "manager/admin/layout/css/themes/default.css",
                "manager/admin/layout/css/custom.css"]/>
    <style>
        .login{
            background-color: #666 !important;
        }
        .login .logo{
            margin: 0 auto;
            margin-top: 60px;
            padding: 15px;
            text-align: center;
        }
        .login .content{
            background: "manager/admin/pages/img/bg-white-lock.png" repeat;
            width: 360px;
            margin: 0 auto;
            margin-bottom: 0px;
            padding: 30px;
            padding-top: 20px;
            padding-bottom: 15px;
        }
        .login .content h3{
            color: #eee;
        }
        .login .content h4{
            color: #eee;
        }
        .login .content p,
        .login .content label{
            color: #fff;
        }
        .login .content .login-form{
            padding: 0px;
            margin: 0px;
        }
        .login .content .form-control{
            background-color: #fff;
        }
        .login .content .form-title{
            font-weight: 300;
            margin-bottom: 25px;
        }
        .login .content .form-actions{
            background-color: transparent;
            clear: both;
            border: 0px;
            padding: 0px 30px 25px 30px;
            margin-left: -30px;
            margin-right: -30px;
        }
        .login .content .form-actions .checkbox{
            margin-left: 0;
            padding-left: 0;
        }
        .login .content .forget-form .form-actions{
            border: 0;
            margin-bottom: 0;
            padding-bottom: 20px;
        }
        .login .content .form-actions .checkbox{
            margin-top: 8px;
            display: inline-block;
        }
        .login .content .form-actions .btn{
            margin-top: 1px;
        }
        /* select2 dropdowns */
        .login .content .select2-container i{
            display: inline-block;
            position: relative;
            color: #ccc;
            z-index: 1;
            top: 1px;
            margin: 4px 4px 0px -1px;
            width: 16px;
            height: 16px;
            font-size: 16px;
            text-align: center;
        }
        .login .content .has-error .select2-container i{
            color: #b94a48;
        }
        .login .content .select2-container a span{
            font-size: 13px;
        }
        .login .content .select2-container a span img{
            margin-left: 4px;
        }
        /* footer copyright */
        .login .copyright{
            text-align: center;
            margin: 0 auto;
            padding: 10px;
            color: #eee;
            font-size: 13px;
        }
        @media (max-width: 480px){
            .login .logo{
                margin-top: 10px;
            }
            .login .content{
                padding: 30px;
                width: 222px;
            }
            .login .content h3{
                font-size: 22px;
            }
            .login .checkbox{
                font-size: 13px;
            }
        }
    </style>
    <script>var ctx = "${Render.url(domain.getManager(), "")}";</script>
</head>
<body class="login">
<div class="logo">
    <a href="${Render.url(domain.getManager(), "/")}">
        <img src="${Render.url(domain.getStill(), "manager/admin/layout/img/LOGO-3.png")}" alt=""/>
    </a>
</div>
<div class="menu-toggler sidebar-toggler">
</div>
<div class="content">
    <form class="login-form" action="${Render.url(domain.getManager(), "login")}" method="post">
        <h3 class="form-title">登录系统账号</h3>
        <#if error??>
        <div class="alert alert-danger">
            <button class="close" data-close="alert"></button>
            <span>${error!}</span>
        </div>
        </#if>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
                       placeholder="用户名" name="username" value="${username!}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密&nbsp;&nbsp;码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn blue pull-right">登陆 <i class="m-icon-swapright m-icon-white"></i></button>
        </div>
    </form>
</div>
<div class="copyright">@${DateUtil.now()?string("yyyy")} &copy;****网络科技有限公司.</div>
<@layout.commonJs/>
<@layout.script ["manager/global/plugins/respond.min.js",
             "manager/global/plugins/excanvas.min.js",
             "manager/global/plugins/jquery-1.11.0.min.js",
             "manager/global/plugins/jquery-migrate-1.2.1.min.js",
             "manager/global/plugins/bootstrap/js/bootstrap.min.js",
             "manager/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js",
             "manager/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js",
             "manager/global/plugins/jquery.blockui.min.js",
             "manager/global/plugins/jquery.cokie.min.js",
             "manager/global/plugins/uniform/jquery.uniform.min.js",
             "manager/global/plugins/jquery-validation/js/jquery.validate.min.js",
             "manager/global/plugins/select2/select2.min.js",
             "manager/global/scripts/metronic.js",
             "manager/admin/layout/scripts/layout.js",
             "manager/global/plugins/backstretch/jquery.backstretch.min.js"] />
<script>
    jQuery(document).ready(function(){
        Metronic.init();
        Layout.init();
    });
    $('.login-form').validate({
        errorElement:'span',
        errorClass:'help-block',
        focusInvalid:false,
        rules:{
            username:{
                required:true
            },
            password:{
                required:true
            },
            remember:{
                required:false
            }
        },
        messages:{
            username:{
                required:"用户名不能为空."
            },
            password:{
                required:"密码不能为空."
            }
        },
        invalidHandler:function(event,validator){
            $('.alert-danger',$('.login-form')).show();
        },
        highlight:function(element){
            $(element).closest('.form-group').addClass('has-error');
        },
        success:function(label){
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement:function(error,element){
            error.insertAfter(element.closest('.input-icon'));
        },
        submitHandler:function(form){
            form.submit();
        }
    });
    $('.login-form input').keypress(function(e){
        if(e.which==13){
            if($('.login-form').validate().form()){
                $('.login-form').submit();
            }
            return false;
        }
    });
    $.backstretch(["${Render.url(domain.getStill(), "manager/admin/pages/media/bg/1.jpg")}"],{
        fade:1000,
        duration:8000
    });
</script>
</body>
</html>
</@layout.render>
