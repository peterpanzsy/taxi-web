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
<div id="select">
	<select id="selectType" name="selectType"  onchange=taxiList(this.value)>		                  
    	<option value="陕AT7009">陕AT7009</option>
    	<option value="陕AU4815">陕AU4815</option>
    </select>
</div>
<div id="allmap" style="margin-top:10px;"></div>

</body>
</html>
<script type="text/javascript">

// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(108.953098,34.2778), 14);
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
//var MAX = 10;
var markers = [];
var pt = null;
//var i = 0;
//for (; i < MAX; i++) {
 //  pt = new BMap.Point(Math.random() * 40 + 85, Math.random() * 30 + 21);
 //  markers.push(new BMap.Marker(pt));
//}
taxiList($("#selectType").val());
function taxiList(typeValue){
	//map.cleanOverlays();
	map.clearOverlays();
	//if(markers!=[]){
	//	markerClusterer.removeMarkers(markers);
		//markers=[];
	//}
	
	$.ajax({
		type : 'POST',
		url : 'taxiList.action',
		data : {
			type:typeValue
		},
		success : function(data) {
			$.each(data.dataList,function(i,point){
			//alert(point.longitude+" "+point.latitude);
			pt = new BMap.Point(point.longitude, point.latitude);
			var myIcon = new BMap.Icon("images/icons/taxi_round2.png", new BMap.Size(4,4));
		  	map.addOverlay(new BMap.Marker(pt,{icon: myIcon})); 
				//markers.push(new BMap.Marker(new BMap.Point(point.longitude,point.latitude)));
			});
			
				
		},
		error:function(msg){
			alert(msg);
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmList").trigger("reloadGrid");
		}
	});
	
}
	
//markers.push(new BMap.Marker(new BMap.Point(108.954196,34.263022)));
//markers.push(new BMap.Marker(new BMap.Point(108.960380,34.264340)));
//最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
//var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});

</script>
