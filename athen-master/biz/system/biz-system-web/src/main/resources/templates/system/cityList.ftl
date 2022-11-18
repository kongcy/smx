<#import "/layout/$.ftl" as layout/>
<#assign css>
<style>
    form textarea {
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
            "sAjaxSource":"${Render.url(domain.getManager(), "system/city")}",
            "sDom":"<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",/*dataTable翻页,只保留表格底部翻页样式*/
            "oLanguage":{
                "sProcessing":'<img src="${Render.url(domain.getStill(), "manager/global/img/loading-spinner-grey.gif")}"/><span>&nbsp;&nbsp;加载中...</span>',
            },
            "aoColumns":[
                { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox_user" onclick="checkAllBox(this,\'checkBox_user\')" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
                    return '<div class="checker"  ><span class=""><input type="checkbox" class="checkBox_user" checked="" name="checkBox_user" value="'+full.id+'"></span></div>';
                }},
                { "sTitle":"城市名称","mData":"name"},
                { "sTitle":"创建时间","mData":"createTime"},
                { "sTitle":"更新日期","mData":"updateTime"},
                { "sTitle":"操作","mData":"id","sDefaultContent":"","mRender":function(data,type,row){
                    return'<a class="btn btn-xs red" href="${Render.url(domain.getManager(), "system/city/delete/")}'+row.id+'" ><i class="fa fa-trash-o"></i>删除</a>';
                }}
            ]
        }
    });

    var gridmodalTable=new Datatable();
    gridmodalTable.init({
        src:$("#modalTable"),
        onSuccess:function(gridmodalTable){
        },
        onError:function(gridmodalTable){
        },
        dataTable:{
            "bServerSide":false,
            "sAjaxSource":"${Render.url(domain.getManager(), "system/city-list")}",
            "oLanguage":{
                "sProcessing":'<img src="${Render.url(domain.getStill(), "manager/global/img/loading-spinner-grey.gif")}"/><span>&nbsp;&nbsp;加载中...</span>',
            },
            "aoColumns":[
                { "sWidth":"1%","sTitle":"<input type='checkbox' class='group-checkable'>","mData":"id","mRender":function(data,type){
                    return '<input type="checkbox" class="group-checkable" value="'+data+'" name="checkBox2"/>';
                }},
                { "sWidth":"15%","sTitle":"城市名称","mData":"name"},
                { "sWidth":"15%","sTitle":"城市code","mData":"code"},
                { "sWidth":"15%","sTitle":"所属省会","mData":"userId"},
                { "sWidth":"15%","sTitle":"操作","mData":"id","sDefaultContent":"","mRender":function(data,type,row){
                    return'<a class="btn btn-xs red" href="${Render.url(domain.getManager(), "system/delete/")}'+row.id+'" ><i class="fa fa-trash-o"></i>删除</a>';
                }}
            ]
        }
    });

    function openCityWin(){
        Metronic.startPageLoading();
        $('#cityModel').modal('show');
        Metronic.stopPageLoading();
    }
    /*取消*/
    function cancelCity(){
        $('#cityModel').modal('hide');
    }
    /*保存城市*/
    function saveCity(){
        var parm="";
        var ch=$("span.checked >input[name='checkBox2']");
        if(ch){
            ch.each(function(i,n){
                if(i==0){
                    parm=n.value;
                }else{
                    parm+=','+n.value;
                }
            });
        }
        if(!parm){
            alertHint('请选择需要设置的城市');
            return false;
        }
        Metronic.startPageLoading();
        $.post("${Render.url(domain.getManager(), "system/save-city")}",{'rand':Math.random(),'ids':parm},function(result){
            Metronic.stopPageLoading();
            if(true==result){
                alertHint();
                $('#editDataDiv').modal('hide');
                grid.getDataTable().fnDraw();
            }
        });
    }
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

            <li><a href="#">热门城市</a>
            </li>
        </ul>
    </div>
</div>
<div class="row _userList">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>热门城市
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <button onclick="openCityWin()" class="btn blue">
                            <i class="fa fa-cogs"></i>
                            <span class="hidden-480">添加城市</span>
                        </button>

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
                    <label >城市: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 180px;" placeholder="城市名查询" name="name">
                    </label>

                    <label style="margin-left: 10px;">
                        <button class="btn btn-sm yellow margin-bottom filter-submit" value="搜索" onclick="search(this,grid)"><i class="fa fa-search"></i> 搜索</button>
                        <button class="btn btn-sm red filter-cancel" onclick="reset(this,[],grid)"><i class="fa fa-times"></i> 重置</button>
                    </label>
                </div>
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover" id="user_list_table">
                        <thead>
                          <tr role="row" class="heading"></tr>
                          <tr role="row" class="filter"></tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>

                <div id="set_role" class="modal fade" tabindex="-1" aria-hidden="true">

                </div>

            </div>
        </div>
    </div>
</div>
<!--设置城市-->
<div id="cityModel" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"  style="width: 1000px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="cancelCity()"></button>
                <h4 class="modal-title" id="editModTitle">设置热门城市</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-hover table-bordered" id="modalTable">
                    <thead>
                    <tr role="row" class="heading">
                        <th width="1%"><input type="checkbox" class="group-checkable"></th>
                        <th width="1%"></th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                        <th width="10%"></th>
                    </tr>
                    <tr role="row" class="filter">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn green" onclick="saveCity()">保存</button>
                <button type="button" class="btn default" data-dismiss="modal" onclick="cancelCity()">取消</button>
            </div>
        </div>
    </div>
</div>
</@layout.main>
