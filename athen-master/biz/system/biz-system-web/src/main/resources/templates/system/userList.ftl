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
            "sAjaxSource":"${Render.url(domain.getManager(), "system/user")}",
            "sDom":"<'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>r>>",/*dataTable翻页,只保留表格底部翻页样式*/
            "oLanguage":{
                "sProcessing":'<img src="${Render.url(domain.getStill(), "manager/global/img/loading-spinner-grey.gif")}"/><span>&nbsp;&nbsp;加载中...</span>',
            },
            "aoColumns":[
                { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox_user" onclick="checkAllBox(this,\'checkBox_user\')" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
                    return '<div class="checker"  ><span class=""><input type="checkbox" class="checkBox_user" checked="" name="checkBox_user" value="'+full.id+'"></span></div>';
                }},
                {"sWidth":"5%", "sTitle":"登陆名","mData":"loginName"},
                { "sWidth":"5%","sTitle":"姓名","mData":"userName"},
                { "sWidth":"5%","sTitle":"邮箱","mData":"email","mRender":function(data){
                    return "<a href='mailto:"+data+"'>"+data+"</a>";
                }},
                { "sWidth":"5%","sTitle":"联系方式","mData":"phone"},
                { "sWidth":"5%","sTitle":"状态","mData":"state.value","sDefaultContent":""},
                { "sWidth":"5%","sTitle":"操作","mData":"id","sDefaultContent":"","mRender":function(data,type,row){
                    var a='<a class="btn btn-xs red" href="${Render.url(domain.getManager(), "system/user/delete/")}'+row.id+'" ><i class="fa fa-trash-o"></i>删除</a>';
                    var b='<a class="btn btn-xs green" href="${Render.url(domain.getManager(), "system/user/update/")}'+row.id+'" ><i class="glyphicon glyphicon-pencil"></i>编辑</a>';
                    var c='<a class="btn btn-xs yellow" href="javascript:void(0);" ><i class=""></i>禁止操作</a>';
                    if(row.loginName=='admin'){
                        return b+c;
                    }
                    return b+a;
                }}
            ]
        }
    });
    /**
     * 分配 角色 信息
     * */

    var grid_see_role=new Datatable();
    grid_see_role.init({
        src:$("#role_data_table"),
        onSuccess:function(grid_employee){
        },
        onError:function(grid_employee){
        },
        dataTable:{
            "bServerSide":true,
            "sAjaxSource":"${Render.url(domain.getManager(), "system/role/list")}",
            "aaSorting":[
                [ 1,"desc" ]
            ],
            "aLengthMenu":[
                [10,20,30,50,100],
                [10,20,30,50,100]
            ],
            "aoColumnDefs":[
                { "bSortable":false,"aTargets":[ 0,1,2,3,4,5] }
            ],/*设置不排序得列*/
            "aoColumns":[
                { "sWidth":"1%","sTitle":'<input type="checkbox" class= "checkAllBox_role" onclick="checkAllBox(this,\'checkBox_role\')" title="全选" class="group-checkable" />',"sDefaultContent":"","mRender":function(data,type,full){
                    return '<div class="checker"  ><span class=""><input type="checkbox" class="checkBox_role" checked="" name="checkBox_role" value="'+full.id+'"></span></div>';
                }},
                { "sTitle":"名称","mData":"name","mRender":function(data,type,row){
                    return data ;
                }},
                { "sTitle":"代号","mData":"code"},
                { "sTitle":"描述","mData":"description"},
                {"sWidth":"10%","sTitle":"状态","mData":"active","sDefaultContent":"","mRender":function(data){
                    return "ENABLE"==data?"启用":"禁用";
                }}

            ]
        }
    });
    /**
     *  选择框选中事件
     * */
    jQuery('#role_data_table').on('change','tbody tr .checkBox_role',function(){
        $(this).parents('span').toggleClass("checked");
    });

    /**
     *  选择框选中事件
     * */
    jQuery('#user_list_table').on('change','tbody tr .checkBox_user',function(){
        $(this).parents('span').toggleClass("checked");
    });
    /**
     *  分配角色信息
     */
    function exportData(tip){
        $('#tip_msg').val(tip);
        if(1==tip||'1'==tip){
            if(!checkUserInfo()){
                bootbox.alert('请选择需要分配的用户');
                return false;
            }
        }
        var ch=$('.checkAllBox_role').attr('checked');
        if(ch){
            $(ch).parent("span").removeClass("checked");
            $("input[name='checkBox_role']").attr("checked",false).parent('span').removeClass('checked');
        }
        $('#set_role').modal('show');
    }

    /**
     *  检测是否选择用户信息
     */
    function checkUserInfo(){
        var ids=[], ch=$("#user_list_table span.checked >input[name='checkBox_user']"),bool= false;
        if(ch){
            ch.each(function(i,n){
                var obj = n.value;
                if(null !=obj && '' != obj && obj.length){
                    ids.push(obj);
                }
            });
        }
        if(null !=ids && '' != ids && ids.length > 0){
            bool = true;
        }
        return bool;
    }
    /**
     * 保存用户 角色关系
     */
    function setRole(){
        var userList=[],roleList=[],tip =$('#tip_msg').val();
        var ch = $("#user_list_table span.checked >input[name='checkBox_user']");
        if(0== tip ||'0' == tip){
            userList.push(0);
        }else{
            if(ch){
                ch.each(function(i,n){
                    var obj = n.value;
                    if(null !=obj && '' != obj && obj.length){
                        userList.push(obj);
                    }
                });
            }
            if(userList==''||userList==null||userList.length==0){
                bootbox.alert('请选择需要分配的用户');
                return false;
            }
        }
        var role = $("#role_data_table span.checked >input[name='checkBox_role']");
        if(role){
            role.each(function(i,n){
                var obj = n.value;
                if(null !=obj && '' != obj && obj.length){
                    roleList.push(obj);
                }
            });
        }
        if(roleList==''||roleList==null||roleList.length==0){
            bootbox.alert('请选择需要分配的角色');
            return false;
        }
        $('#set_role').modal('hide');
        Metronic.startPageLoading();
        $.ajax({
            url:'${Render.url(domain.getManager(), "system/user/save-role-user")}',
            type:'POST',
            data: {'roleList':roleList,'userList':userList,'tip':tip},
            dataType:"json",
            traditional:true,
            success:function(msg){
                Metronic.stopPageLoading();
                if(msg.stat){
                    alertHint(msg.info);
                }else{
                    bootbox.alert(msg.info);
                }
            }
        });
    }

    function compare(){var a = 2600*0.6; var b = 601717* 0.005;if(a>b){return b}else{return a}}
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

            <li><a href="#">用户管理</a>
            </li>
        </ul>
    </div>
</div>
<div class="row _userList">
    <div class="col-md-12">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>用户列表
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a href="${Render.url(domain.getManager(), "system/user/create")}" class="btn green addUser">
                            <i class="fa fa-plus"></i>
                            <span class="hidden-480">新用户</span>
                        </a>
                        <a class="btn blue" href="javascript:void(0)" data-toggle="dropdown">
                            <i class="fa fa-user"></i>
                            <span class="hidden-480">分配角色</span>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:exportData(0);">
                                    全部分配角色
                                </a>
                            </li>
                            <li>
                                <a href="javascript:exportData(1);">
                                    选择分配角色
                                </a>
                            </li>
                        </ul>
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
                    <label >登陆名: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 180px;" placeholder="登陆名" name="loginName">
                    </label>
                    <label>名称: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 180px;" placeholder="用户名" name="userName">
                    </label>
                    <label>邮箱: </label>
                    <label style="margin-left: 10px;">
                        <input type="text" class="form-filter input-sm" style="width: 180px;" placeholder="邮箱" name="email">
                    </label>
                    <label>状态: </label>
                    <select id="state" name="state"  class="form-filter input-sm" style="width: 150px;">
                        <option selected="selected" value>请选择</option>
                        <option value="Enable">开启</option>
                        <option value="Disable">锁定</option>
                    </select>

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
                        <tr role="row" class="filter">
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- 分配角色信息begin -->
                <div id="set_role" class="modal fade" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog" style="width: 950px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">分配角色</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row" id="tableDatas_role">
                                    <div class="col-md-12">
                                        <div class="portlet box light-grey">
                                            <div class="portlet-body">

                                                <table class="table table-striped table-hover table-bordered" id="role_data_table">
                                                    <thead>
                                                    <tr role="row" class="heading">
                                                        <th width="1%">
                                                            <input type="checkbox" class="group-checkable">
                                                        </th>
                                                        <th width="8%">
                                                            名称
                                                        </th>
                                                        <th width="8%">
                                                            代号
                                                        </th>
                                                        <th width="8%">
                                                            描述
                                                        </th>
                                                        <th width="10%">
                                                            状态
                                                        </th>
                                                        <th width="10%">
                                                            操作
                                                        </th>
                                                    </tr>
                                                    <tr role="row" class="filter">
                                                        <td></td>
                                                        <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_name">  </td>
                                                        <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_code"> </td>
                                                        <td><input type="text" class="form-control form-filter input-sm" name="search_LIKE_description"></td>
                                                        <td>
                                                            <select name="search_EQ_active" class="form-control  form-filter input-sm">
                                                                <option selected="selected" value="">请选择</option>
                                                                <option value="ENABLE">启用</option>
                                                                <option value="DISABLE">禁用</option>
                                                            </select>
                                                        </td>
                                                        <td>
                                                            <button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i>搜索</button>
                                                            <button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i>重置</button>
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
                                <div class="modal-footer">
                                    <button class="btn green" onclick="setRole();">确定</button>
                                    <button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 分配角色信息end -->
            </div>
        </div>
    </div>
</div>
</@layout.main>
