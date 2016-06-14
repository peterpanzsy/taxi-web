
$(
	
	function() {

	var datagrid = jQuery("#PhysicalList")
			.jqGrid(
					{
						url : "listPhysical.action",// 后端的数据交互程序，改为你的
						datatype : "json",// 前后交互的格式是json数据
						mtype : 'POST',// 交互的方式是发送httpget请求						
						colNames : [ '编号', '物理量名称', '英文名称', '描述', 'ISO基本单位'],// 表格的列名
						colModel : [
								{
									name : 'ID',
									index : 'id',
									width : 50,
									align : "center",
									sortable:true
								},// 每一列的具体信息，index是索引名，当需要排序时，会传这个参数给后端
								{
									name : 'CName',
									index : 'CName',
									width : 150,
									align : "center",
									sortable:true
								},
								{
									name : 'EName',
									index : 'EName',
									width : 200,
									align : "center",
									sortable:true
								},
								{
									name : 'description',
									index : 'Description',
									width : 200,
									align : "center",
									sortable:true
								},
								{
									name:'ISOBasicUnit',
									index:'ISOBasicUnit',
									width:200,
									align:'center',
									sortable:true
								}
			
								],
//						autowidth:true,
						editurl:"editData.action",
						rowNum:10,//每一页的行数
						height: 'auto',
						width:1230,
						rowList:[10,20,30],
						pager: '#PhysicalPager',
						sortname: 'id',
						viewrecords: true,
						sortorder: "desc",
						multiselect: true,  //可多选，出现多选框 
					    multiselectWidth: 35, //设置多选列宽度 
						jsonReader: {//读取后端json数据的格式
						root: "dataList",//保存详细记录的名称
						total: "total",//总共有页
						page: "page",//当前是哪一页
						records: "records",//总共记录数
						repeatitems: false
						},
						caption: "物理量管理",//表格名称,
						jsonReader : {// 读取后端json数据的格式
							root : "dataList",// 保存详细记录的名称
							// total: "total",//总共有页
							// page: "page",//当前是哪一页
							records : "records",// 总共记录数
							repeatitems : false
						}
					}
					
					
			);

	datagrid.jqGrid('filterToolbar',{searchOperators:true});			
	datagrid.jqGrid('navGrid','#PhysicalPager',{
			edit : false,
			add : false,
			search:false,
			del : false}).jqGrid('navButtonAdd',"#PhysicalPager",{
					title:'添加',
					caption:"添加",
					id:"add_PhysicalList",
					onClickButton : function addModal(){
						// 配置对话框
							$('#add_physical_modal').modal();
					
					},
					position:"first"
				
			
				}).jqGrid('navButtonAdd',"#PhysicalPager",{
					title:'删除',
					caption:"删除",	
					id:"delete_PhysicalList",
					onClickButton:deletePhysical,
					position:"first"
				});
	
	function deletePhysical() {
		/*
		var gr = jQuery("#PhysicalList").jqGrid('getGridParam','selarrrow');
		if( gr != null ) jQuery("#PhysicalList").jqGrid('delGridRow',gr,{
												reloadAfterSubmit:false,
												caption:"删除记录",
												bSubmit:"确定",
												bCancel:"取消",
												url:"delPhysical.action"
									
												});
		else alert("Please Select Row to delete!");
		*/
		
	        var sels = $("#PhysicalList").jqGrid('getGridParam','selarrrow'); 
	        if(sels==""){ 
	           //$().message("请选择要删除的项！"); 
	           alert("请选择要删除的项!");
	        }else{ 
	        	var selectedIDs={};
	        	$.each(sels,function(i,n){ 
                  if(sels[i]!=""){ 
                	  var rowData = $("#PhysicalList").jqGrid("getRowData", sels[i]);
                	  selectedIDs["ids[" + i + "]"]=rowData.ID;
                  } 
	        	}); 

	           if(confirm("您是否确认删除？")){ 
	            $.ajax({ 
	              type: "POST", 
	              url: "delPhysical.action", 
	              data: selectedIDs, 
	              beforeSend: function() { 
	                   $().message("正在请求..."); 
	              }, 
	              error:function(){ 
	                   $().message("请求失败..."); 
	              }, 
	              
	              success: function(msg){ 
	            	alert("删除成功！");
//	            	alert(msg);
					$("#PhysicalList").trigger("reloadGrid");
	                   if(msg!=0){ 
	                       var arr = msg.split(','); 
	                       $.each(arr,function(i,n){ 
	                             if(arr[i]!=""){ 
	                                 $("#PhysicalList").jqGrid('delRowData',n);  
	                             } 
	                       }); 
	                       $().message("已成功删除!"); 
	                   }else{ 
	                       $().message("操作失败！"); 
	                   } 
	              } 
	            }); 
	           } 
	        } 
			

	}


	/*
	 * 单位管理列表
	 */
	var datagrid = jQuery("#MeasureList")
	.jqGrid(
			{
				url : "listMeasure.action",// 后端的数据交互程序，改为你的
				datatype : "json",// 前后交互的格式是json数据
				mtype : 'POST',// 交互的方式是发送httpget请求						
				colNames : [ '编号', '英文名称', '中文名称', '符号', '物理量','单位体系','转换率A','转换率B'],// 表格的列名
				colModel : [
						{
							name : 'ID',
							index : 'mid',
							width : 50,
							align : "center",
							sortable:true,
							sorttype:'int'
						},// 每一列的具体信息，index是索引名，当需要排序时，会传这个参数给后端
						{
							name : 'EName',
							index : 'mEName',
							width : 150,
							align : "center",
							sortable:true
						},
						{
							name : 'CName',
							index : 'mCName',
							width : 200,
							align : "center",
							sortable:true
						},
						{
							name : 'symbol',
							index : 'symbol',
							width : 200,
							align : "center",
							sortable:true
						},
						{
							name:'phy.CName',
							index:'pCName',
							width:200,
							align:'center',
							sortable:true
						},
						{
							name:'style.styleName',
							index:'styleName',
							width:200,
							align:'center',
							sortable:false
						},
						{
							name:'ratioA',
							index:'RatioA',
							width:200,
							align:'center',
							sortable:true
						},
						{
							name:'ratioB',
							index:'RatioB',
							width:200,
							align:'center',
							sortable:true
						}
	
						],
//				autowidth:true,
				rowNum:10,//每一页的行数
				height: 'auto',
				width:1230,
				rowList:[10,20,30],
				pager: '#MeasurePager',
				sortname: 'mid',
				viewrecords: true,
				sortorder: "desc",
				multiselect: true,  //可多选，出现多选框 
			    multiselectWidth: 35, //设置多选列宽度 
				jsonReader: {//读取后端json数据的格式
					root: "dataList",//保存详细记录的名称
					total: "total",//总共有多少页
					page: "page",//当前是哪一页
					records: "records",//总共记录数
					repeatitems: false
				},
				caption: "物理单位管理"//表格名称
				
			});
	datagrid.jqGrid('filterToolbar',{searchOperators:true});
	datagrid.jqGrid('navGrid','#MeasurePager',{
		edit : false,
		add : false,
		search:false,
		del : false}).jqGrid('navButtonAdd',"#MeasurePager",{
				title:'添加',
				caption:"添加",
				id:"add_MeasureList",
				onClickButton : function addModal(){
					// 配置对话框

						$('#add_measure_modal').modal();
				
				},
				position:"first"
			
		
			}).jqGrid('navButtonAdd',"#MeasurePager",{
				title:'删除',
				caption:"删除",	
				id:"delete_MeasureList",
				onClickButton:deleteMeasure,
				position:"first"
			});
	
	function deleteMeasure() {
		/*
		var gr = jQuery("#PhysicalList").jqGrid('getGridParam','selarrrow');
		if( gr != null ) jQuery("#PhysicalList").jqGrid('delGridRow',gr,{
												reloadAfterSubmit:false,
												caption:"删除记录",
												bSubmit:"确定",
												bCancel:"取消",
												url:"delPhysical.action"
									
												});
		else alert("Please Select Row to delete!");
		*/
		
	        var sels = $("#MeasureList").jqGrid('getGridParam','selarrrow'); 
	        if(sels==""){ 
	           //$().message("请选择要删除的项！"); 
	           alert("请选择要删除的项!");
	        }else{ 
	        	var selectedIDs={};
	        	$.each(sels,function(i,n){ 
                  if(sels[i]!=""){ 
                	  var rowData = $("#MeasureList").jqGrid("getRowData", sels[i]);
                	  selectedIDs["ids[" + i + "]"]=rowData.ID;
                  } 
	        	}); 

	           if(confirm("您是否确认删除？")){ 
	            $.ajax({ 
	              type: "POST", 
	              url: "delMeasure.action", 
	              data: selectedIDs, 
	              beforeSend: function() { 
	                   $().message("正在请求..."); 
	              }, 
	              error:function(){ 
	                   $().message("请求失败..."); 
	              }, 
	              
	              success: function(msg){ 
	            	alert("删除成功！");
//	            	alert(msg);
					$("#MeasureList").trigger("reloadGrid");
	                   if(msg!=0){ 
	                       var arr = msg.split(','); 
	                       $.each(arr,function(i,n){ 
	                             if(arr[i]!=""){ 
	                                 $("#MeasureList").jqGrid('delRowData',n);  
	                             } 
	                       }); 
	                       $().message("已成功删除!"); 
	                   }else{ 
	                       $().message("操作失败！"); 
	                   } 
	              } 
	            }); 
	           } 
	        } 
			

	}

	
	/*
	 * 参数管理列表
	 */
	var datagrid = jQuery("#ParameterList")
	.jqGrid(
			{
				url : "listParameter.action",// 后端的数据交互程序，改为你的
				datatype : "json",// 前后交互的格式是json数据
				mtype : 'POST',// 交互的方式是发送httppost请求						
				colNames : [ '编号', '显示', '名称','单位符号'],// 表格的列名
				colModel : [
						{
							name : 'ID',
							index : 'ID',
							width : 50,
							align : "center",
							sortable:true,
							sorttype:'int'
						},// 每一列的具体信息，index是索引名，当需要排序时，会传这个参数给后端
						{
							name : 'display',
							index : 'display',
							width : 150,
							align : "center",
							sortable:true
						},
						{
							name : 'name',
							index : 'name',
							width : 200,
							align : "center",
							sortable:true
						},
						{
							name : 'measureSymbol',
							index : 'measureSymbol',
							width : 200,
							align : "center",
							sortable:true
						}
						],
//				autowidth:true,
				rowNum:10,//每一页的行数
				height: 'auto',
				width:1230,
				rowList:[10,20,30],
				pager: '#ParameterPager',
				sortname: 'ID',
				viewrecords: true,
				sortorder: "desc",
				multiselect: true,  //可多选，出现多选框 
			    multiselectWidth: 35, //设置多选列宽度 
				jsonReader: {//读取后端json数据的格式
					root: "dataList",//保存详细记录的名称
					total: "total",//总共有多少页
					page: "page",//当前是哪一页
					records: "records",//总共记录数
					repeatitems: false
				},
				caption: "参数管理"//表格名称
				
			});
	
	datagrid.jqGrid('filterToolbar',{searchOperators:true});
	datagrid.jqGrid('navGrid','#ParameterPager',{
		edit : false,
		add : false,
		search:false,
		del : false}).jqGrid('navButtonAdd',"#ParameterPager",{
				title:'添加',
				caption:"添加",
				id:"add_ParameterList",
				onClickButton : function addModal(){
					$("#addParameterForm").validate({
						debug:true,
						onsubmit:true,
						onfocusout:false,
						onkeyup:true,
						rules:{
							name:{
								required:true
							},
							display:{
								required:true
							},
							measureID:{
								required:true
							}
						},
						messages:{
							name:{
							required:"名称不能为空！",
							},
							display:{
							required:"中文名称不能为空！",
							},
							measureID:{
								required:"请选择单位！"
							}
						},
						submitHandler:function(){
							add_parameter();
						}
					});
					// 配置对话框
					
					$('#add_parameter_modal').modal();
				
				},
				position:"first"
			
		
			}).jqGrid('navButtonAdd',"#ParameterPager",{
				title:'删除',
				caption:"删除",	
				id:"delete_ParameterList",
				onClickButton:deleteParameter,
				position:"first"
			});
	
	function deleteParameter() {
		/*
		var gr = jQuery("#PhysicalList").jqGrid('getGridParam','selarrrow');
		if( gr != null ) jQuery("#PhysicalList").jqGrid('delGridRow',gr,{
												reloadAfterSubmit:false,
												caption:"删除记录",
												bSubmit:"确定",
												bCancel:"取消",
												url:"delPhysical.action"
									
												});
		else alert("Please Select Row to delete!");
		*/
		
	        var sels = $("#ParameterList").jqGrid('getGridParam','selarrrow'); 
	        if(sels==""){ 
	           //$().message("请选择要删除的项！"); 
	           alert("请选择要删除的项!");
	        }else{ 
	        	var selectedIDs={};
	        	$.each(sels,function(i,n){ 
                  if(sels[i]!=""){ 
                	  var rowData = $("#ParameterList").jqGrid("getRowData", sels[i]);
                	  selectedIDs["ids[" + i + "]"]=rowData.ID;
                  } 
	        	}); 

	           if(confirm("您是否确认删除？")){ 
	            $.ajax({ 
	              type: "POST", 
	              url: "delParameter.action", 
	              data: selectedIDs, 
	              beforeSend: function() { 
	                   $().message("正在请求..."); 
	              }, 
	              error:function(){ 
	                   $().message("请求失败..."); 
	              }, 
	              
	              success: function(msg){ 
	            	alert("删除成功！");
//	            	alert(msg);
					$("#ParameterList").trigger("reloadGrid");
	                   if(msg!=0){ 
	                       var arr = msg.split(','); 
	                       $.each(arr,function(i,n){ 
	                             if(arr[i]!=""){ 
	                                 $("#ParameterList").jqGrid('delRowData',n);  
	                             } 
	                       }); 
	                       $().message("已成功删除!"); 
	                   }else{ 
	                       $().message("操作失败！"); 
	                   } 
	              } 
	            }); 
	           } 
	        } 
			

	}

	
	
}//function结束
);//$()结束


function add_physical() {
	$.ajax({
		type : 'POST',
		url : 'addPhysical.action',
		data : {
			CName:$("#CName").val(),
			EName:$("#EName").val(),
			Description:$("#Description").val(),
			ISOBasicUnit:$("#ISOBasicUnit").val()
		},
		dataType:'json',
		complete : function(msg) {
			alert("添加成功！");
			$('#add_physical_modal').modal('hide');
			$("#PhysicalList").trigger("reloadGrid");
		},
		error:function(msg){
			alert("添加失败！");
			alter(msg);
			$('#add_physical_modal').modal('hide');
			$("#PhysicalList").trigger("reloadGrid");
		}
	});
	}

function add_measure() {

	$.ajax({
		type : 'POST',
		url : 'addMeasure.action',
		data : {
			mCName:$("#MCName").val(),
			mEName:$("#MEName").val(),
			Symbol:$("#Symbol").val(),
			RatioA:$("#RatioA").val(),
			RatioB:$("#RatioB").val(),
			PhysicalID:$("#PhysicalID").val(),
			StyleID:$("#StyleID").val()
		},
		dataType:'json',
		success : function(msg) {
			alert("添加成功！");
			$('#add_measure_modal').modal('hide');
			$("#MeasureList").trigger("reloadGrid");
		},
		error:function(msg){
			alter(msg);
			$('#add_measure_modal').modal('hide');
			$("#MeasureList").trigger("reloadGrid");
		}
	});
	}

function add_parameter() {

	$.ajax({
		type : 'POST',
		url : 'addParameter.action',
		data : {
			name:$("#name").val(),
			display:$("#display").val(),
			measureID:$("#measureID").val()
		},
		dataType:'json',
		success : function(data) {
			if(data.exist==true){
				alert(data.name+"已存在！");
			}else{
				alert("添加成功！");
				$('#add_parameter_modal').modal('hide');
				$("#ParameterList").trigger("reloadGrid");
			}
		},
		error:function(msg){
			alter(msg);
			$('#add_measure_modal').modal('hide');
			$("#ParameterList").trigger("reloadGrid");
		}
	});
	}
	
function loadPhysicalOptions(){
	$.ajax({
		url:'listPhysical.action',
		type:'post',
		data : {
			sidx: 'id',
			sord: "desc"
		},
		dataType:'json',
		success:function(data){
		//alert(data.dataList[0].CName);
			var items="";
			$.each(data.dataList,function(i,physical){
				items+= "<option value=\"" + physical.ID + "\">" + physical.CName + "</option>"; 
			});
			$("#PhysicalID").html(items);
		}
	});
	}

function loadPhysicalStyleOptions(){
	$.ajax({
		url:'listPhysicalStyle.action',
		type:'post',
		dataType:'json',
		success:function(data){
		//alert(data.dataList[0].CName);
			var items="";
			$.each(data.styleList,function(i,style){
				items+= "<option value=\"" + style.ID + "\">" + style.styleName + "</option>"; 
			});
			$("#StyleID").html(items);
		}
	});
	}

	
function loadMeasureOptions(){
	$.ajax({
		url:'listMeasure.action',
		type:'post',
		data : {
			sidx: 'mid',
			sord: "desc"
		},
		dataType:'json',
		success:function(data){
		//alert(data.dataList[0].CName);
			var items="";
			$.each(data.dataList,function(i,measure){
				items+= "<option value=\"" + measure.ID + "\">" + measure.CName + "</option>"; 
			});
			$("#measureID").html(items);
		}
	});
	}

