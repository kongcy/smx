<#import "/layout/$.ftl" as layout/>
<#assign css>
    <@layout.style ["manager/global/plugins/select2/select2.css",
    "manager/global/plugins/jstree/dist/themes/default/style.min.css"]/>
</#assign>
<#assign js>
    <@layout.script [
    "manager/global/plugins/bootbox/bootbox.min.js",
    "manager/global/plugins/jstree/dist/jstree.js",
    "manager/global/common/commonUtil.js"]/>
<script>
var tree=$("#resourceTree").jstree({
    "checkbox":{"keep_selected_style":false},
    "core":{
        "multiple":true,
        "animation":0,
        "themes":{
            theme:"classic",
            "dots":true,
            "icons":true
        },
        "check_callback":true,
        'data':${resourceTree}
    },
    "plugins":["wholerow","checkbox"],
});
$("select[name=active] option[value='${role.active}']").attr("selected","selected");
$('#roleBtnSave').click(function(){
    var array=$.jstree.reference('#resourceTree').get_selected(true);
    var ar=new Array();
    if(array){
        for(var i=0; i<array.length; i++){
            var a=array[i];
            ar.push(a.id);
            var parents = a.parents;
            if(parents){
                for(var j=0;j<parents.length;j++){
                    var b = parents[j];
                    if(b!="#"){
                        ar.push(b);
                    }
                }
            }else{
                if(a.parent!="#"){
                    ar.push(a.parent);
                }
            }
        }
    }
    $('input[name=ids]').val(ar.join(","));
    var name =$('#name').val();
    var code= $('#code').val();
    if(name&&code){
        $('#roleForm').submit();
    }else{
        alertHint("请输入完整信息!");
    }
});
</script>
</#assign>

<@layout.main title="角色编辑" pageCss="${css}" pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "")}">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="${Render.url(domain.getManager(), "system/role/")}">角色管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-gift"></i><#if action?? && action =='create'>新建<#else>修改</#if>角色
                </div>
            </div>
            <div class="portlet-body form">
                <form action="${Render.url(domain.getManager(), "system/role/${action}")}" class="form-horizontal form-bordered" method="POST" id="roleForm">
                    <input type="hidden" name="id" value="${role.id}"/>
                    <input type="hidden" name="ids"/>

                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 control-label">名称<span class="required">*</span></label>

                            <div class="col-md-10">
                                <input type="text"  id="name" class="form-control required" placeholder="(必填)录入角色名称" name="name" value="${role.name}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">编码<span class="required">*</span></label>

                            <div class="col-md-10">
                                <input type="text"  id="code" class="form-control" placeholder="(必填)录入角色编码" name="code" value="${role.code}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>

                            <div class="col-md-10">
                                <select name="active" class="form-control">
                                    <option value="ENABLE">启用</option>
                                    <option value="DISABLE">禁用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">描述</label>

                            <div class="col-md-10">
                                <textarea name="description" type="text" class="form-control" placeholder="请输入角色描述">${role.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">菜单权限</label>

                            <div class="col-md-10">
                                <div id="resourceTree"></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="button" class="btn green" id="roleBtnSave">保存</button>
                            <button type="button" class="btn default" onclick="javascript:window.location.href='${Render.url(domain.getManager(), "system/role")}';">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@layout.main>
