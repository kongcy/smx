<#import "/layout/$.ftl" as layout/>
<#assign css>
<style>
    form textarea{
        resize: none;
    }
</style>
    <@layout.style ["manager/global/plugins/select2/select2.css",
    "manager/global/plugins/data-tables/DT_bootstrap.css",
    "manager/global/plugins/bootstrap-datepicker/css/datepicker.css"]/>
</#assign>
<#assign js>
    <@layout.script ["manager/global/plugins/bootbox/bootbox.min.js",
    "manager/global/plugins/select2/select2.min.js",
    "manager/global/plugins/data-tables/jquery.dataTables.js",
    "manager/global/plugins/data-tables/DT_bootstrap.js",
    "manager/global/scripts/datatable.js",
    "manager/global/common/common.js",
    "manager/global/common/commonUtil.js"]/>
<script>
    var grid=new Datatable();
    grid.init({
        src:$('#user_list_table'),
        dataTable:{
            "bServerSide":true,
            "sAjaxSource":"${Render.url(domain.getManager(), "system/config")}",
            "sDom":"<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",/*dataTable翻页,只保留表格底部翻页样式*/
            "oLanguage":{
                "sProcessing":'<img src="${Render.url(domain.getStill(), "manager/global/img/loading-spinner-grey.gif")}"/><span>&nbsp;&nbsp;加载中...</span>',
            },
            "aoColumns":[
                { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox_user" onclick="checkAllBox(this,\'checkBox_user\')" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
                    return '<div class="checker"  ><span class=""><input type="checkbox" class="checkBox_user" checked="" name="checkBox_user" value="'+full.id+'"></span></div>';
                }},
                { "sTitle":"参数Key值","mData":"conKey","mRender":function(data,type,row){
                    return "<span style='color:red;'>" + data + "</span>";
                }},
                { "sTitle":"参数值","mData":"conValue"},
                { "sTitle":"说明","mData":"conComment"},
                { "sTitle":"操作","mData":"id","sDefaultContent":"","mRender":function(data,type,row){
                    return'<a class="btn btn-xs green" href="${Render.url(domain.getManager(), "system/config/update/")}'+row.id+'" ><i class="glyphicon glyphicon-pencil"></i>编辑</a>';
                }}
            ]
        }
    });
</script>
</#assign>

<@layout.main title="系统设置" pageCss="${css}" pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "")}">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>

            <li><a href="#">系统参数</a>
            </li>
        </ul>
    </div>
</div>
<div class="row _userList">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>系统参数
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a href="${Render.url(domain.getManager(), "system/config/create")}" class="btn green addUser">
                            <i class="fa fa-plus"></i>
                            <span class="hidden-480">添加参数</span>
                        </a>

                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <input type="hidden" id="tip_msg" />
                <#if message??>
                    <div class="alert alert-success">
                        <button data-dismiss="alert" class="close">×</button>
                    ${(message)!}
                    </div>
                </#if>
                <div id="data_table_search">
                    <label>参数key: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 200px;" placeholder="参数key值查询" name="conKey">
                    </label>

                    <label>参数值: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 300px;" placeholder="参数值查询" name="conValue">
                    </label>


                    <label style="margin-left: 10px;">
                        <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索" onclick="search(this,grid)"><i class="fa fa-search"></i> 搜索</button>
                        <button class="btn btn-sm red filter-cancel" onclick="reset(this,[],grid)"><i class="fa fa-times"></i> 重置</button>
                    </label>
                </div>
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover" id="user_list_table">
                        <thead>
                        <tr role="row" class="heading">
                        </tr>
                        <tr role="row" class="filter"></tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.main>
