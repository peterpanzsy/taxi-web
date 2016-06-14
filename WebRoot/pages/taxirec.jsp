<%@ page language="java" import="java.util.*,cn.edu.cup.map.business.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
	<script src="js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
	
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/holder.min.js"></script>
	 
	<script src="js/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="js/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
	
	
	<script type="text/javascript" src="js/upload/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="js/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.message.js"></script>
	
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=53fb5a6c9ddba227f1af1992d3476d6d"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<title>麻点图</title>
</head>
<body>
<div>
<div style="float:left;margin-right:30px;margin-left:20px;">
<h3>打车点推荐</h3>
<span>当前坐标:108.95384,34.229471</span>
<br><span>推荐结果列表:</span>
<ul>
<li>陕西省西安市雁塔区长翠路</li>
<li>陕西省西安市雁塔区小寨东路172号</li>
<li>陕西省西安市雁塔区曲江商圈长安中路97号</li>
<li>陕西省西安市雁塔区雁塔西路76</li>
<li>陕西省西安市雁塔区曲江商圈长安中路99号</li>


</ul>
</div>

<div id="allmap" style="margin-top:10px;float:left;width:1000px;"></div>
</div>

<div id="select" style="display:none;">
	<select id="selectType" name="selectType"  onchange=taxiList(this.value)>		                  
    	<option value="easy_take_top">好打车的地方</option>
    	<option value="shift_top">交接班地点</option>
    </select>
</div>

</body>
</html>
<script type="text/javascript">

// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(108.953098,34.2778), 14);
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
var MAX = 10;
var markers = [];
var pt = null;
var i = 0;
for (; i < MAX; i++) {
   pt = new BMap.Point(Math.random() * 40 + 85, Math.random() * 30 + 21);
   markers.push(new BMap.Marker(pt));
   map.addOverlay(new BMap.Marker(pt));
}


taxiList($("#selectType").val());
function taxiList(typeValue){
	map.clearOverlays();
	$.ajax({
		type : 'POST',
		url : 'listpapoints.action',
		data : {
			type:typeValue
		},
		success : function(data) {
		 	$.each(data.papointsList,function(i,point){
			//alert(point.longitude+" "+point.latitude);
			pt = new BMap.Point(point.lon, point.lat);
			var myIcon = new BMap.Icon("images/icons/taxi_round2.png", new BMap.Size(4,4));
		  	//map.addOverlay(new BMap.Marker(pt,{icon: myIcon})); 
		  	map.addOverlay(new BMap.Marker(pt));
				//markers.push(new BMap.Marker(new BMap.Point(point.longitude,point.latitude)));
			});   
			
				
		},
		error:function(msg){
			alert(msg);
		}
	});
	
}
	
/* 
 * 接单点
markers.push(new BMap.Marker(new BMap.Point(108.953436,34.230799)));
markers.push(new BMap.Marker(new BMap.Point(108.953301,34.230001)));
markers.push(new BMap.Marker(new BMap.Point(108.953939,34.229158)));
markers.push(new BMap.Marker(new BMap.Point(108.950723,34.228889)));
markers.push(new BMap.Marker(new BMap.Point(108.95331,34.227755)));
markers.push(new BMap.Marker(new BMap.Point(108.956203,34.229098))); */

markers.push(new BMap.Marker(new BMap.Point(108.955718,34.235921)));
markers.push(new BMap.Marker(new BMap.Point(108.954865,34.22902)));
markers.push(new BMap.Marker(new BMap.Point(108.953302,34.227826)));
markers.push(new BMap.Marker(new BMap.Point(108.947517,34.224304)));
markers.push(new BMap.Marker(new BMap.Point(108.953069,34.227647)));
markers.push(new BMap.Marker(new BMap.Point(108.956203,34.229098)));

//最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});

</script>
