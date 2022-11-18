<#import "/layout/$.ftl" as layout>
<#assign js>
<!-- 图表 -->
<@layout.script ["manager/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js",
    "manager/global/plugins/flot/jquery.flot.min.js",
    "manager/global/plugins/flot/jquery.flot.resize.min.js",
    "manager/global/plugins/flot/jquery.flot.categories.min.js"]/>
<script>
var Dashboard=function(){
    return {
        initCharts:function(){
            if(!jQuery.plot){
                return;
            }
            function showChartTooltip(x,y,xValue,yValue){
                $('<div id="tooltip" class="chart-tooltip">'+yValue+'<\/div>').css({
                    position:'absolute',
                    display:'none',
                    top:y-40,
                    left:x-40,
                    border:'0px solid #ccc',
                    padding:'2px 6px',
                    'background-color':'#fff'
                }).appendTo("body").fadeIn(200);
            }

            if($('#site_statistics').size()!=0){
                $('#site_statistics_loading').hide();
                $('#site_statistics_content').show();
                var plot_statistics=$.plot($("#site_statistics"),[
                    {
                        data:chatData,
                        lines:{
                            fill:0.6,
                            lineWidth:0
                        },
                        color:['#f89f9f']
                    },
                    {
                        data:chatData,
                        points:{
                            show:true,
                            fill:true,
                            radius:5,
                            fillColor:"#f89f9f",
                            lineWidth:3
                        },
                        color:'#fff',
                        shadowSize:0
                    }
                ],{
                    xaxis:{
                        tickLength:0,
                        tickDecimals:0,
                        mode:"categories",
                        min:0,
                        font:{
                            lineHeight:14,
                            style:"normal",
                            variant:"small-caps",
                            color:"#6F7B8A"
                        }
                    },
                    yaxis:{
                        ticks:5,
                        tickDecimals:0,
                        tickColor:"#eee",
                        font:{
                            lineHeight:14,
                            style:"normal",
                            variant:"small-caps",
                            color:"#6F7B8A"
                        }
                    },
                    grid:{
                        hoverable:true,
                        clickable:true,
                        tickColor:"#eee",
                        borderColor:"#eee",
                        borderWidth:1
                    }
                });
                var previousPoint=null;
                $("#site_statistics").bind("plothover",function(event,pos,item){
                    $("#x").text(pos.x.toFixed(2));
                    $("#y").text(pos.y.toFixed(2));
                    if(item){
                        if(previousPoint!=item.dataIndex){
                            previousPoint=item.dataIndex;
                            $("#tooltip").remove();
                            var x=item.datapoint[0].toFixed(2), y=item.datapoint[1].toFixed(2);
                            showChartTooltip(item.pageX,item.pageY,item.datapoint[0],item.datapoint[1]+' 次下载');
                        }
                    }else{
                        $("#tooltip").remove();
                        previousPoint=null;
                    }
                });
            }
        }
    };
}();
var ios, android, chatData;
jQuery(document).ready(function(){
    /**
     * 获取应用下版本的下载量信息,用于生成图形界面
     */
    $.ajax({
        url:'${Render.url(domain.getManager(), "system/list/download-total")}',
        type:'POST',
        dataType:"json",
        traditional:true,
        success:function(msg){
            if(msg.code==1){
                ios=eval('('+msg.data.ios+')');
                android=eval('('+msg.data.android+')');
                chatData=ios;
                Dashboard.initCharts();
            }
        }
    });
    /**
     * 标签切换
     */
    $('#option1').on('click',function(){
        chatData=ios;
        Dashboard.initCharts();
    });
    /**
     * 标签切换
     */
    $('#option2').on('click',function(){
        chatData=android;
        Dashboard.initCharts();
    });
    /**
     * 获取角色,用户
     */
    $.ajax({
        url:'${Render.url(domain.getManager(), "system/list/get-num-list")}',
        type:'POST',
        dataType:"json",
        traditional:true,
        success:function(msg) {
            if(msg.code==1) {
                $('#roleNum').text(msg.data.roleNum+'个');
                $('#userNum').text(msg.data.userNum+'个');
            }
        }
    });
});
</script>
</#assign>

<@layout.main pageJs="${js}">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-title">***
            <small>运营管理平台</small>
        </h3>
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="${Render.url(domain.getManager(), "/")}"> 主页 </a>
            </li>
            <li class="pull-right">
                <div id="dashboard-report-range" class="dashboard-date-range tooltips"
                     data-placement="top" data-original-title="Change dashboard date range">
                    <i class="fa fa-calendar"></i><span></span>
                    <i class="fa fa-angle-down"></i>
                </div>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
        <div class="dashboard-stat green">
            <div class="visual">
                <i class="fa icon-ICON-07"></i>
            </div>
            <div class="details">
                <div class="number" id="roleNum">0个</div>
                <div class="desc">角色</div>
            </div>
        <#if SessionUtil.isSuper()>
            <a class="more" href="${Render.url(domain.getManager(), "system/role")}">查看角色 <i class="m-icon-swapright m-icon-white"></i></a>
        </#if>
        </div>
    </div>
    <div class="col-lg-6 col-md-6 col-sm-10 col-xs-12">
        <div class="dashboard-stat yellow">
            <div class="visual">
                <i class="fa icon-ICON-05"></i>
            </div>
            <div class="details">
                <div class="number" id="userNum">0</div>
                <div class="desc">用户</div>
            </div>
        <#if SessionUtil.isSuper()>
            <a class="more" href="${Render.url(domain.getManager(), "system/user")}">查看用户<i class="m-icon-swapright m-icon-white"></i></a>
        </#if>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-sm-6">
        <div class="portlet solid bordered light-grey">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-bar-chart-o"></i>应用下载量</div>
                <div class="tools">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn option default btn-sm active" id="option1">
                            <input type="radio" name="options" class="toggle">ios </label>
                        <label class="btn option default btn-sm" id="option2">
                            <input type="radio" name="options" class="toggle">android </label>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <div id="site_statistics_loading">
                    <img src="${Render.url(domain.getStill(), "manager/global/img/loading.gif", true)}" alt="loading"/>
                </div>
                <div id="site_statistics_content" class="display-none">
                    <div id="site_statistics" class="chart"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.main>