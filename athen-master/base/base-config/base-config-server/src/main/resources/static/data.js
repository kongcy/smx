
window.onload = function () {
    selectData();
};

function selectData() {
    var application = $('#application').val();
    var profile = $('#profile').val();
    $.ajax({
        url: '/config-server/view/tableList?application=' + application + "&profile=" + profile,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            var code = result.code;
            if (code == 1) {
                var size = result.data.length;
                if (size > 0) {
                    splitTag(result.data, size);
                }
            }
        },
        error: function () {
        }
    });
}



function splitTag(data, size, tableId) {
    $("#table1").css("width","100%");
    if (data != null) {
        var count = size - 1;
        var html="<tr bgcolor='#7fffd4' style='height: 50px;'><td style='width: 15%'>application</td><td style='width: 10%'>module</td><td style='width: 15%'>key</td><td style='width: 20%'>value</td><td style='width: 20%'>desc</td></tr>";
        $.each(data, function (index, item) {
            if (count != index) {
                html += "<tr>"
            }
            $.each(item, function (vlaIndex, valItem) {
                console.log(item + "----" + vlaIndex + "---" + valItem);
                if (vlaIndex == 'application' || vlaIndex == 'module') {
                    html += "<td >";
                    html += valItem;
                    html += "</td>";
                } else if (vlaIndex == 'key') {
                    html += "<td>";
                    html += valItem;
                    html += "</td>";
                }else if(vlaIndex == 'value'){
                    html += "<td>";
                    html += valItem;
                    html += "</td>";
                }else if(vlaIndex=='desc'){
                    html += "<td>";
                    html += valItem;
                    html += "</td>";
                }
            });
            html += "</tr>";
        });
        $("#table1").html(html);
    }
}

function isBack(value) {
    return (typeof value == "undefined" || value == null) ? true : false;
}

function isNull(value) {
    return (typeof value == "undefined" || value == null || value =="") ? true : false;
}
