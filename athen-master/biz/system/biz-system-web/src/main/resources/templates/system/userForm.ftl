<#import "/layout/$.ftl" as layout/>
<#assign css>
    <@layout.style ["manager/global/plugins/select2/select2.css",
    "manager/global/plugins/data-tables/DT_bootstrap.css",
    "manager/global/plugins/bootstrap-datepicker/css/datepicker.css"]/>
</#assign>
<#assign js>
    <@layout.script ["manager/global/plugins/select2/select2.min.js",
    "manager/global/plugins/uniform/jquery.uniform.min.js",
    "manager/global/plugins/jquery-validation/js/lib/jquery.form.js",
    "manager/global/plugins/jquery-validation/js/jquery.validate.min.js",
    "manager/global/plugins/jquery-validation/js/additional-methods.min.js",
    "manager/global/plugins/jquery-validation/js/localization/messages_zh.js"]/>
<script>
    var select=$(".select").select2();
        <#if action =='update'>
        $("select[name=state] option[value='${u.state}']").attr("selected","selected");
        var data=[];
            <#list u.roles as r>
            data.push({id:${r.id},text:'${r.name}'});
            </#list>
        select.select2("data",data);
        </#if>
    var form=$('#userForm');
    var error=$('.alert-danger',form);
    form.validate({
        errorElement:'span',
        errorClass:'help-block help-block-error',
        focusInvalid:true,
        messages:{
            loginName:{remote:"登陆名已经存在"}
        },
        rules:{
            priceOne:{
                number:true
            }
        },
        invalidHandler:function(event,validator){
            error.show();
            Metronic.scrollTo(error,-200);
        },
        highlight:function(element){
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight:function(element){
            $(element).closest('.form-group').removeClass('has-error');
        },
        success:function(label){
            label.closest('.form-group').removeClass('has-error');
        },
        submitHandler:function(form){
            error.hide();
            form.submit();
        }
    });
</script>
</#assign>

<@layout.main title="系统用户管理" pageCss="${css}" pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "")}">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>

            <li><a href="${Render.url(domain.getManager(), "system/user/")}">用户管理</a>
            </li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-gift"></i><#if action =='create'>新建<#else>修改</#if>用户
                </div>
            </div>
            <div class="portlet-body form">
                <form action="${Render.url(domain.getManager(), "system/user/${action}")}" class="form-horizontal form-bordered" method="POST" id="userForm">
                    <input type="hidden" name="id" value="${u.id}"/>

                    <div class="form-body">
                        <div class="alert alert-danger display-hide">
                            <button class="close" data-close="alert"></button>
                            请检查后再提交
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">登录名<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-icon right">
                                    <i class="fa"></i>

                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                        <input type="text" class="form-control" name="loginName" placeholder="请输入登录名" <#if action=='update'&&ManagerSessionUtil.isNotSuper()> readonly </#if> value="${u.loginName}"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">用户名<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="text" class="form-control" name="userName" placeholder="请输入用户名" value="${u.userName}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">电子邮件<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input name="email" type="text" class="form-control" value="${u.email}" placeholder="电子邮件地址">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">联系方式<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input name="phone" type="text" class="form-control" value="${u.phone}" placeholder="联系方式">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">密码</label>

                            <div class="col-md-10">
                                <div class="input-icon right">
                                    <i class="fa"></i>

                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                        <input name="plainPassword" type="password" class="form-control" placeholder="请输入登录密码">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>

                            <div class="col-md-10">
                                <select name="state" class="form-control">
                                    <option value="0">启用</option>
                                    <option value="1">禁用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">角色</label>

                            <div class="col-md-10">
                                <select name="ids" multiple="multiple" class="select" style="width: 100%;">
                                <#list roles as r>
                                    <option value="${r.id}">${r.name}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <button type="button" class="btn default" onclick="javascript:window.location.href='${Render.url(domain.getManager(), "system/user")}';">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@layout.main>
