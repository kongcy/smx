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
            <#if action=='create'>
                conKey:{
                    minlength:1,
                    maxlength:30,
                    required:true,
                    remote:'${Render.url(domain.getManager(), "system/config/check")}'
                },
            </#if>
            conValue:{
                required:true
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

<@layout.main title="系统参数编辑" pageCss="${css}" pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "")}">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>

            <li><a href="${Render.url(domain.getManager(), "system/config/")}">系统参数</a>
            </li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-gift"></i><#if action =='create'>新建<#else>修改</#if>参数
                </div>
            </div>
            <div class="portlet-body form">
                <form action="${Render.url(domain.getManager(), "system/config/${action}")}" class="form-horizontal form-bordered" method="POST" id="userForm">
                    <input type="hidden" name="id" value="${config.id}"/>

                    <div class="form-body">
                        <div class="alert alert-danger display-hide">
                            <button class="close" data-close="alert"></button>
                            请检查后再提交
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">参数KEY<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-icon right">
                                    <i class="fa"></i>

                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-key"></i></span>
                                        <input type="text" class="form-control" name="conKey" placeholder="请输入参数" value="${config.conKey}" <#if action=='update'> readonly</#if> />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">参数值<span class="required">*</span></label>

                            <div class="col-md-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" class="form-control" name="conValue" placeholder="请输入参数值" value="${config.conValue}"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">参数说明</label>

                            <div class="col-md-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" class="form-control" name="conComment" placeholder="请输入参数说明" value="${config.conComment}"/>
                                </div>
                            </div>
                        </div>
                        <!--
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>

                            <div class="col-md-10">
                                <select name="state" class="form-control">
                                    <option value="0">启用</option>
                                    <option value="1">禁用</option>
                                </select>
                            </div>
                        </div> -->
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <button type="button" class="btn default" onclick="javascript:window.location.href='${Render.url(domain.getManager(), "system/config")}';">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@layout.main>
