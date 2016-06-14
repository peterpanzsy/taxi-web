<%@ page language="java" import="java.util.*,cn.edu.cup.manage.business.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--Copyright 2010 Scriptoid s.r.l-->
<head>
<title>HTML5 diagram editor</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9" />

<script type="text/javascript" src="./lib/kinetic-v5.1.0.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="../js/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../js/jqueryUI/themes/redmond/jquery.ui.theme.css" />  
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/carousel.css">
<link rel="stylesheet" media="screen" type="text/css"
	href="./assets/css/style.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="./assets/css/minimap.css" />



<script type="text/javascript" src="./assets/javascript/json2.js"></script>
<script type="text/javascript" src="./assets/javascript/jquery-1.11.0.min.js"></script>
<script src="../js/jquery/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="./assets/javascript/ajaxfileupload.js"></script>
<script src="../js/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="../js/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../bootstrap/js/holder.min.js"></script>
<script type="text/javascript" src="../js/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script src="../js/easytabs/jquery.hashchange.min.js" type="text/javascript"></script>
<script src="../js/easytabs/jquery.easytabs.js" type="text/javascript"></script>

<script defer="defer" type="text/javascript" src="./lib/init3.js"></script>
<script defer="defer" type="text/javascript" src="./lib/platform.js"></script>
<script defer="defer" type="text/javascript" src="./lib/leftpoly.js"></script>
<script defer="defer" type="text/javascript" src="./lib/Paintings.js"></script>
<script defer="defer" type="text/javascript" src="./lib/tools.js"></script>
<script defer="defer" type="text/javascript" src="./lib/kinecttab.js"></script>

<link type='text/css' href='./assets/simplemodal/css/diagramo.css' rel='stylesheet' media='screen' />
<link rel="stylesheet" media="screen" type="text/css" href="./assets/css/colorPicker_new.css" />
<!-- <link rel="stylesheet" media="screen" type="text/css" href="./assets/css/jquery.contextMenu.css" /> -->


<!--[if IE]>
        <script src="./assets/javascript/excanvas.js"></script>
        <![endif]-->
<style type="text/css">
	#contextmenu {
		border: 1px solid #aaa;
		border-bottom: 0;
		background: #eee;
		position: absolute;
		list-style: none;
		margin: 0;
		padding: 0;
		display: none;
	}
																			   
	#contextmenu li a {
		display: block;
		padding: 10px;
		border-bottom: 1px solid #aaa;
		cursor: pointer;
	}
																			   
	#contextmenu li a:hover {
		background: #fff;
	}
	
	
	.etabs { margin: 0; padding: 0; }
	.tab { display: inline-block; zoom:1; *display:inline; background: #eee; border: solid 1px #999; border-bottom: none; -moz-border-radius: 4px 4px 0 0; -webkit-border-radius: 4px 4px 0 0; }
	.tab a { font-size: 14px; line-height: 2em; display: block; padding: 0 10px; outline: none; }
	.tab a:hover { text-decoration: underline; }
	.tab.active { background: #fff; padding-top: 6px; position: relative; top: 1px; border-color: #666; }
	.tab a.active { font-weight: bold; }
	.tab-container .panel-container { background: #fff; border: solid #666 1px; padding: 10px; -moz-border-radius: 0 4px 4px 4px; -webkit-border-radius: 0 4px 4px 4px; }
</style>
</head>
<body onload="initLight('');" id="body">

	<div id="header">
		<span> <img src="." /> </span>
	</div>

	<div id="actions">

		<a href="javascript:createNewModal();"
			title="新建">
			<img
			src="icons/sign_add.png" border="0" />
		</a> 
		<img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:listGUIProGrid();"
			title="打开">
			<img
			src="icons/folder.png" border="0" />
		</a> <img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:save();"
			title="保存"><img
			src="icons/save_labled.png" border="0"
			alt="Organic" />
		</a> <img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:scaleCenter(1.25);"
			title="放大画布"><img
			src="icons/arrow_expand.png" border="0" alt="Container" />
		</a> 
		<img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:scaleCenter(0.8);"
			title="缩小画布"><img
			src="icons/arrow_contract.png" border="0" alt="Container" />
		</a> 
		<img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <input type="checkbox" checked=true
			onclick="showGrid();" id="gridCheckbox" title="Show grid" /> <span
			class="toolbarText">Show grid</span> <img class="separator"
			src="assets/images/toolbar_separator.gif" border="0" width="1"
			height="16" /> <input type="checkbox" onclick="snapToGrid();"
			id="snapCheckbox" title="Snap elements to grid" /> <span
			class="toolbarText">Snap to grid</span> <img class="separator"
			src="assets/images/toolbar_separator.gif" border="0" width="1"
			height="16" /> <a href="javascript:action('front');"
			title="Move to front"><img src="assets/images/icon_front.gif"
			border="0" />
		</a> <img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:action('back');" title="Move to back"><img
			src="assets/images/icon_back.gif" border="0" />
		</a> <img class="separator" src="assets/images/toolbar_separator.gif"
			border="0" width="1" height="16" /> <a
			href="javascript:action('moveforward');"
			title="Move (one level) to front"><img
			src="assets/images/icon_forward.gif" border="0" />
		</a>
		<!-- TODO: From Janis: we have to create a nice icon for duplicate, currently this is the only command without an icon -->
		<!--
            <a href="javascript:action('duplicate');">Copy (Ctrl-D)</a>
            -->

		<!-- <a href="javascript:action('redo');" title="Redo (Ctrl-Y)"><img src="assets/images/arrow_redo.png" border="0"/></a> -->
		<!--
            <input type="text" id="output" />                
            <img style="vertical-align:middle;" src="../assets/images/toolbar_separator.gif" border="0" width="1" height="16"/>
            <a href="javascript:action('duplicate');">Copy</a>
            <img style="vertical-align:middle;" src="../assets/images/toolbar_separator.gif" border="0" width="1" height="16"/>
            <a href="javascript:action('group');">Group</a>
            <img style="vertical-align:middle;" src="../assets/images/toolbar_separator.gif" border="0" width="1" height="16"/>
            <a href="javascript:action('ungroup');">Ungroup</a>
            -->
	</div>
	
	
	
	
	




	


	<div id="editor">
			<input id="selectedID" style="display: none;"/> 
			<div id="tab-container" class="tab-container"  style="position: absolute;z-index:100;margin-left:120px;margin-top:2px;">
			  <ul id="paintingTabs" class='etabs'>
			    <!--
			    <li class='tab'><a href="#tabs1-html">HTML Markup</a></li>
			    <li class='tab'><a href="#tabs1-js">Required JS</a></li>
			    <li class='tab'><a href="#tabs1-css">Example CSS</a></li>  
			    -->
			    
			  </ul>
			  <!-- 
			  <div id="tabs1-html">
			    <h2>HTML Markup for these tabs</h2>
			  </div>
			  <div id="tabs1-js">
			    <h2>JS for these tabs</h2>
			  </div>
			  <div id="tabs1-css">
			    <h2>CSS Styles for these tabs</h2>
			  </div> 
			  -->
			</div>
		
			<div id="container">
			</div>
	

		<!--Right panel-->
		<div id="right">
			<center>
				<div id="minimap"></div>
			</center>
			<div style="overflow: scroll;" id="edit"></div>
		</div>

	</div>

	<!--The import panel-->
	<div id="import-dialog"
		style="background-color: white; display: none; margin-top: auto; margin-bottom: auto;">
		<form action="./common/controller.php" method="POST"
			enctype="multipart/form-data">
			<input type="hidden" name="action" value="importDiagramExe" />
			<h2>Import Diagramo file</h2>
			<p />
			<input type="file" name="diagramFile" id="diagramFile" />
			<p />
			<input type="image" src="./assets/images/import.gif" />
		</form>
	</div>

	<!--Insert Image dialog content-->
	<div id="insert-image-dialog">
		<h2>Insert Image</h2>
		<form action="./common/controller.php" method="POST"
			target="upload_target" enctype="multipart/form-data">
			<input type="hidden" name="action" value="insertImage" />
			<div class="insert-image-line">
				<input type="radio" name="image-group" value="URL" checked>
				<label>From URL:</label> <input type="text" class="url-input"
					name="imageURL" id="imageURL" />
			</div>
			<div class="insert-image-line">
				<input type="radio" name="image-group" value="Upload"> <label>Upload:</label>
				<input type="file" class="right-offset" name="imageFile"
					id="imageFile" />
			</div>
			<div class="insert-image-line">
				<input type="radio" name="image-group" value="Reuse"
					id="insert-image-reuse-group"> <label>Reuse:</label> <select
					id="insert-image-reuse" name="reuseImageFile">
				</select>
			</div>
			<div id="upload-image-error"></div>
			<div class="submit-container">
				<input type="submit" value="Insert" />
			</div>
		</form>
	</div>

	<!--Insert Image hidden iframe-->
	<iframe id="upload_target" name="upload_target"
		style="width:0;height:0;border:0px;"></iframe>

	<script type="text/javascript">
		$(function(){
			$('#tab-container').easytabs();
		});
		function loadFill(check) {
			if (check.checked === true) {
				if ($('#colorpickerHolder3').css('display') === 'none') {
					$('#colorSelector3').click();
				}
			} else {
				if ($('#colorpickerHolder3').css('display') === 'block') {
					$('#colorSelector3').click();
				}
			}
		}
	</script>
	<br />
	<? //require_once dirname(__FILE__) . '/common/analytics.php';?>


	<ul id="contextmenu" style="display:none;z-index:100">	
		<li><a>顺时针旋转90°</a></li>
		<li><a>逆时针旋转90°</a></li>	
		<li><a>更改颜色</a></li>
		<li><a>放大</a></li>
		<li><a>缩小</a></li>	
		<li><a>删除该节点</a></li>
    </ul>

	<!-- 新建图形项目的模态框 -->   	
		<div class="modal fade" id="add_GUI_modal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" style="font-weight:bold;font-family:幼圆">添加图形文件</h4>
		      </div>
		      <div class="modal-body">
		     	 <form id="addGUIForm" action="addGUIPro.action" method="post"> 
		     	 	<table width="100%" cellpadding="0" cellspacing="0" class="post_table">		      		
		      			<tr>
		      				<td><label width="30%" align="right"style="font-weight:bold;font-family:黑体;font-size:20px;" >名称:</label></td>
				            <td><input id="proname" type="text" class="input2" name="proname" maxlength="30"/><em style="color:red">*</em></td>
		      			</tr>
		      			<tr>
		      				<td><label align="right" style="font-weight:bold;font-family:黑体;font-size:20px;" >描述：</label></td>
		      				<td><input id="description" type="text" class="input2" name="description" maxlength="10" /></td>
		      			</tr>
		      			<tr>
		      				<td><label align="right" style="font-weight:bold;font-family:黑体;font-size:20px;" >类型：</label></td>
		      				<td><input id="type" type="text" class="input2" name="type" maxlength="10" /></td>
		      			</tr>
		      			<tr>
		      				<td><label align="right" style="font-weight:bold;font-family:黑体;font-size:20px;">作者：</label></td>
			        		<td><select id="authorID" name="authorID"></select><em style="color:red">*</em></td>   						
		      			</tr>
		      				    				
				   </table>
				   <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="submit" class="btn btn-primary"  >保存</button>
				   </div>
				 </form> 
		      </div>
		     
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
					
    		<!-- 查看图形项目列表的模态框 -->   	
		<div class="modal fade" id="listGUIPro_modal">
		  <div class="modal-dialog">
		    <div class="modal-content" style="width:1300">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		       <!--  <h4 class="modal-title" style="font-weight:bold;font-family:幼圆">查看输入</h4> -->
		      </div>
		      <div class="modal-body">  
		      	<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<table id="GUIProList" class="table table-striped table-bordered table-hover datatable " ></table>
				      		<div style="border:3px dashed #336699;box-shadow:2px 2px 10px #333300;border-radius: 11px;width:1230" >
				      			<div id="GUIProPager" ></div>
				      		</div>	      		
						</div>
					</div>
				</div>  
		      </div>
		     
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		
		
</body>
</html>
	
	