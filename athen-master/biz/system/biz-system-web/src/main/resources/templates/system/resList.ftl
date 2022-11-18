<#import "/layout/$.ftl" as layout/>
<#assign css>
    <@layout.style ["manager/global/plugins/select2/select2.css",
    "manager/global/plugins/data-tables/DT_bootstrap.css"]/>
</#assign>
<#assign js>
    <@layout.script ["manager/global/plugins/bootbox/bootbox.min.js",
    "manager/global/plugins/select2/select2.min.js",
    "manager/global/plugins/data-tables/jquery.dataTables.js",
    "manager/global/plugins/data-tables/DT_bootstrap.js",
    "manager/global/scripts/datatable.js",
    "manager/global/common/commonUtil.js"]/>
<script>
    var grid=new Datatable();
    grid.init({
        src:$('#role-list-table'),
        onSuccess:function(grid){
        },
        onError:function(grid){
        },
        dataTable:{
            "bSort": false,
            "bServerSide":true,
            "sAjaxSource":"${Render.url(domain.getManager(), "system/role/res?id=")}"+${role},
            "sDom":"<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",/*dataTable翻页,只保留表格底部翻页样式*/
            "oLanguage":{
                "sProcessing":'<img src="${Render.url(domain.getStill(), "manager/global/img/loading-spinner-grey.gif")}"/><span>&nbsp;&nbsp;加载中...</span>',
            },
            "aoColumns":[
                { "sWidth":"10%","sTitle":"模板菜单","mData":"name","mRender":function(data,type,row){
                    return'<a href="${Render.url(domain.getManager(), "system/role/update/")}'+row.id+'">'+data+'</a>';
                }},
                { "sWidth":"10%","sTitle":"路径","mData":"code"},
                { "sWidth":"10%","sTitle":"状态","mData":"state.value","sDefaultContent":"启用"},
                { "sWidth":"10%","sTitle":"类型","mData":"description"},
                { "sWidth":"20%", "sTitle":"操作权限","mData":"id","sDefaultContent":"","mRender":function(data,type,row){
                    var a='<a class="btn btn-xs red" href="javascript:void(0);" onclick="doDelete(\''+row.id+'\')" title ="删除"><i class="fa fa-trash-o"></i>删除</a>';
                    var b='<a class="btn btn-xs yellow" href="${Render.url(domain.getManager(), "system/role/")}'+row.id+'"  title ="设置权限"><i class="icon-lock"></i>设置权限</a>';
                    var c='<a class="btn btn-xs green" href="javascript:void(0);" onclick="seeUser(\''+row.id+'\')" title ="查看员工"><i class="fa fa-search"></i>查看员工</a>';
                    return a + b+c ;
                }}
            ]
        }
    });
    var userGrid=new Datatable();
    var $userList_data_table=$("#user_list_table");
    userGrid.init({
        src:$userList_data_table,
        onSuccess:function(userGrid){
            console.log(userGrid);
        },
        onError:function(userGrid){
        },
        dataTable:{
            "bServerSide":true,
            "sAjaxSource":"${Render.url(domain.getManager(), "system/role/query-user-list")}",
            "aoColumnDefs":[
                { "bSortable":false,"aTargets":[ 0,1,2,3] }
            ],/*设置不排序得列*/
            "aoColumns":[
                { "sTitle":"登陆名","mData":"loginName"},
                { "sTitle":"姓名","mData":"name"},
                { "sTitle":"邮箱","mData":"email","mRender":function(data){
                    return "<a href='mailto:"+data+"'>"+data+"</a>";
                }},
                { "sTitle":"操作","mData":"id","sDefaultContent":"","mRender":function(data,type,row){

                }}
            ]
        }
    });
    function doDelete(id){
        if(confirm("确认要删除此资源及其下级所有资源吗？"))
            $.ajax({
                url:'${Render.url(domain.getManager(), "system/role/delete/")}'+id,
                success:function(){
                    grid.getDataTable().fnDraw();
                }
            });
    }
    function seeUser(id){
        userGrid.setAjaxParam("roleId",id);
        $("#roleId").val(id);
        userGrid.getDataTable().fnDraw();
        $('#user_list_div').modal('show');
    }
</script>
</#assign>

<@layout.main title="设置权限" pageCss="${css}" pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "")}">系统设置</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">角色管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
        </ul>
    </div>
</div>
<div class="row _list">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>角色权限
                </div>
            </div>
            <div class="portlet-body">
                <#if message??>
                    <div class="alert alert-success">
                        <button data-dismiss="alert" class="close">×</button>
                    ${(message)!}
                    </div>
                </#if>
                <div class="table-container">
                    <table class="table table-striped table-bordered table-hover" id="role-list-table">
                        <thead>
                        <tr role="row" class="heading">
                        </tr>
                        <tr role="row" class="filter">
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="user_list_div" class="modal fade" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog" style="width:800px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h4 class="modal-title">用户列表</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row" id="tableDatas">
                                <div class="col-md-12">
                                    <div class="portlet">
                                        <div class="portlet-body">
                                            <table class="table table-striped table-bordered table-hover" id="user_list_table">
                                                <thead>
                                                <tr role="row" class="heading">
                                                    <th width="15%">登陆名</th>
                                                    <th width="15%">姓名</th>
                                                    <th width="15%">邮箱</th>
                                                    <th width="15%">操作</th>
                                                </tr>
                                                <tr role="row" class="filter">
                                                    <input type="hidden"  name="roleId" id="roleId">
                                                    <!-- 登陆名 -->
                                                    <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_loginName"></td>
                                                    <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_name"></td>
                                                    <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_email"></td>
                                                    <td>
                                                        <button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 搜索
                                                        </button>
                                                        <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 重置</button>
                                                    </td>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</@layout.main>
