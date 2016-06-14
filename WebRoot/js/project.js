
$(
	

	
	function() {
		
		
		$("li>input").blur(function() {
			alert("test");
			value=$(this).val();
			ID=$(this).siblings('input').val();
			alert(value+ID);
			$.ajax({
				type : 'POST',
				url : 'updateProInputs.action',
				data : {
					value:value,
					ID:ID
				},
				success : function(data) {
					alert('参数保存成功!');
			
					//$('#addAlgorithmInput_modal').modal('hide');
					//$("#AlgorithmInputList").trigger("reloadGrid");			
				},
				error:function(msg){
					alert(msg);
					//$('#addAlgorithmInput_modal').modal('hide');
					//$("#AlgorithmList").trigger("reloadGrid");
				}
			});
		});
		
	/*
	 * 工程管理列表
	 */
	var datagrid = jQuery("#ProjectList")
	.jqGrid(
			{
				url : "listAlgPro.action",// 后端的数据交互程序，改为你的
				datatype : "json",// 前后交互的格式是json数据
				mtype : 'POST',// 交互的方式是发送httpget请求						
				colNames : [ '编号', '名称', '描述','作者','添加时间','最后运行时间','历史运行次数','输入参数设置','算法选择','运行','输出结果','查看运行历史'],// 表格的列名
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
							name : 'name',
							index : 'name',
							width : 100,
							align : "center",
							sortable:true
						},
						{
							name : 'description',
							index : 'Description',
							width : 100,
							align : "center",
							sortable:true
						},
						{
							name : 'author',
							index : 'Author',
							width : 100,
							align : "center",
							sortable:true
						},
						{
							name : 'addTimes',
							index : 'AddTimes',
							width : 100,
							align : "center",
							sortable:true
						},
						{
							name:'lastCalcTimes',
							index:'LastCalcTimes',
							width:100,
							align:'center',
							sortable:true
						},
						{
							name:'calcHisNum',
							index:'CalcHisNum',
							width:100,
							align:'center',
							sortable:true
						},
						{				
							name : 'input',
							index : 'input',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.ID);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"setInput('"
										+ rows.ID + "')\">输入参数设置</a>"
							}
						},
						{				
							name : 'select_algorithm',
							index : 'select_algorithm',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.ID);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"selectAlgorithm('"
										+ rows.ID + "')\">算法选择</a>"
							}
						},
						{				
							name : 'execute',
							index : 'execute',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.ID);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"runAlg('"
										+ rows.ID + "')\">运行</a>"
							}
						},
						{				
							name : 'output',
							index : 'output',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.ID);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"viewOutput('"
										+ rows.ID + "')\">输出结果</a>"
							}
						},
						{				
							name : 'calcHistory',
							index : 'calcHistory',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.ID);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"viewCalcHistory('"
										+ rows.ID + "')\">查看运行历史</a>"
							}
						}
						],
//				autowidth:true,
				rowNum:10,//每一页的行数
				height: 'auto',
				width:1254,
				rowList:[10,20,30],
				pager: '#ProjectPager',
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
				caption: "工程管理"//表格名称
				
			});

//	datagrid.jqGrid('filterToolbar',{searchOperators:true});
	datagrid.jqGrid('navGrid','#ProjectPager',{
		edit : false,
		add : false,
		search:false,
		del : false}).jqGrid('navButtonAdd',"#ProjectPager",{
				title:'添加',
				caption:"添加",
				id:"add_ProjectList",
				onClickButton : function addModal(){
						// 配置对话框
						loadAuthorOptions();//加载作者选项		
						$('#add_project_modal').modal();
						$("#addProjectForm").validate({
							debug:true,
							onsubmit:true,
							onfocusout:false,
							onkeyup:true,
							rules:{
								name:{
									required:true
								},
								authorID:{
									required:true
								}
							},
							messages:{
								name:{
									required:"名称不能为空！"
								},
								authorID:{
									required:"请选择作者！"
								}
							},
							submitHandler:function(){
								add_project();
							}
						});
					
						
				},
				position:"first"
			
		
			}).jqGrid('navButtonAdd',"#ProjectPager",{
				title:'删除',
				caption:"删除",	
				id:"delete_ProjectList",
				onClickButton:deleteProject,
				position:"first"
			});
	
}//function结束
);//$()结束

/*
 * 添加项目
 */
function add_project() {

	$.ajax({
		type : 'POST',
		url : 'addAlgPro.action',
		data : {
			name:$("#name").val(),
			Description : $("#Description").val(),
			authorID:$("#authorID").val()
		},
		success : function(data) {
			alert('工程添加成功！');
			$('#add_project_modal').modal('hide');
			$("#ProjectList").trigger("reloadGrid");			
		},
		error:function(msg){
			alter(msg);
			$('#add_project_modal').modal('hide');
			$("#ProjectList").trigger("reloadGrid");
		}
	});
	}
/*
 * 删除项目
 */
function deleteProject() {
    var sels = $("#ProjectList").jqGrid('getGridParam','selarrrow'); 
    if(sels==""){ 
       //$().message("请选择要删除的项！"); 
       alert("请选择要删除的项!");
    }else{ 
    	var selectedIDs={};
    	$.each(sels,function(i,n){ 
          if(sels[i]!=""){ 
        	  var rowData = $("#ProjectList").jqGrid("getRowData", sels[i]);
        	  selectedIDs["ids[" + i + "]"]=rowData.ID;
//        	  alert(rowData.ID);
          } 
    	}); 

       if(confirm("您是否确认删除？")){ 
        $.ajax({ 
          type: "POST", 
          url: "delAlgPro.action", 
          data: selectedIDs, 
          beforeSend: function() { 
               $().message("正在请求..."); 
          }, 
          error:function(){ 
               $().message("请求失败..."); 
          }, 
          
          success: function(msg){ 
        	alert("删除成功！");
			$("#ProjectList").trigger("reloadGrid");
               if(msg!=0){ 
                   var arr = msg.split(','); 
                   $.each(arr,function(i,n){ 
                         if(arr[i]!=""){ 
                             $("#ProjectList").jqGrid('delRowData',n);  
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
function viewCalcHistory(proID){
	$("#historyTr").nextAll().remove();
	$("#historyTr").show();	
	$.ajax({
		type:"post",
		url:"Calclist.action",
		data:{
			proID:proID,
			sidx:"ID",
			sord:"asc"
		},
		success:function(data){
		
			var tr=$("#historyTr");
			$.each(data.dataList,function(index,row){
				var clonedTr=tr.clone();
				var _index=index;
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):$(this).html(row.ID);break;
						case(1):$(this).html(row.algorithm_name);break;
						case(2):$(this).html(row.calc_re);break;
						case(3):$(this).html(row.startTimeS);break;
						case(4):$(this).html(row.endTimeS);break;
					
					}//end switch
				});//end children.each
				clonedTr.insertAfter(tr);		
			});//end $each
			$("#historyTr").hide();
			$("#calcHistoryTable").show();					
		}
	});
	$('#view_calchistory_modal').modal();
}
/*
 * 计算历史查看页面中的查看输入输出列表功能
 */
function viewHisInputOutput(obj){
	var calcID=$(obj).parent().parent().children().first().text();
	$("#hisInputTr").nextAll().remove();
	$("#hisOutputTr").nextAll().remove();
	$("#hisInputTr").show();
	$("#hisOutputTr").show();	
	$.ajax({
		url:"Calcinputlist.action",
		type:"post",
		dataType:'json',
		data:{
			ID:calcID			
				
		},
		success:function(data){
			var tr=$("#hisInputTr");
			$.each(data.inputList,function(index,row){
				var clonedTr=tr.clone();
				var _index=index;
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):$(this).html(row.id);break;
						case(1):$(this).html(row.par_display+"("+row.par_Name+")");break;
						case(2):$(this).html(row.par_value);break;
						case(3):$(this).html(row.symbol);break;
					}//end switch
				});//end children.each
				clonedTr.insertAfter(tr);		
			});//end $each
			$("#hisInputTr").hide();
			$("#hisInputTable").show();	
			
		}
	});
	$.ajax({
		url:"Calcoutputlist.action",
		type:"post",
		dataType:'json',
		data:{
			ID:calcID
		},
		success:function(data){
			var tr=$("#hisOutputTr");
			$.each(data.outputList,function(index,row){
				var clonedTr=tr.clone();
				var _index=index;
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):$(this).html(row.id);break;
						case(1):$(this).html(row.par_display+"("+row.par_Name+")");break;
						case(2):$(this).html(row.par_value);break;
						case(3):$(this).html(row.symbol);break;
					}//end switch
				});//end children.each
				clonedTr.insertAfter(tr);		
			});//end $each
			$("#hisOutputTr").hide();
			$("#hisOutputTable").show();	
		}
	});
	$("#view_input_output_modal").modal();
	$("#view_input_output_modal").css("z-index","9999");
	$("#view_calchistory_modal").css("z-index","9998");
}
function viewOutput(proID){
	$("#outputID").text("");
	$("#outputName").text("");
	$("#outputValue").text("");
	$("#outputSymbol").text("");
	$.ajax({
		url:"listProOutputs.action",
		type:"post",
		dataType:'json',
		data:{
			pro_id:proID,
			sidx: 'id',
			sord: "desc"
				
		},
		success:function(data){
			$("#outputID").text(data.dataList[0].ID);
			$("#outputName").text(data.dataList[0].display+"("+data.dataList[0].name+")");
			$("#outputValue").text(data.dataList[0].value);
			$("#outputSymbol").text(data.dataList[0].mess);
		}
	});
	$("#view_output_modal").modal();
}
function runAlg(proID){
	$.ajax({
		url:'runAlgPro.action',
		type:'post',
		dataTpye:'json',
		data:{
			ID:proID
		},
		beforeSend: function() { 
              $().message("正在请求..."); 
         }, 
        error:function(){ 
              $().message("请求失败..."); 
         }, 
         
		success:function(data){
			if(data.exeSuccess==true){
				alert("执行成功！");
			}else{
				alert("执行失败！");
			}
		}
		
	});
}
/*
 * 算法选择
 */
function selectAlgorithm(proID){

	$('#select_algorithm_modal').modal();
	$('#proID2').val(proID);
	loadAlgorithmOptions();
	$("#selectAlgorithmForm").validate({
		debug:true,
		onsubmit:true,
		onfocusout:false,
		onkeyup:true,
		rules:{
			algorithmID:{
				required:true
			}
		},
		messages:{
			algorithmID:{
				required:"请选择算法！"
			}
		},
		submitHandler:function(){
			save_algorithm();
		}
	});
	$.ajax({
		url:'searchProAlg.action',
		type:'post',
		dataType:'json',
		data:{
			ID:proID
		},
		success:function(data){
			viewAlgorithmDetail(data.algID);
		}
	});
	
	
}
/*
 * 加载算法下拉列表
 */
function loadAlgorithmOptions(){
	$.ajax({
		url:'listAlgorithmsCycle.action',
		type:'post',
		dataType:'json',
		data : {
			sidx: 'id',
			sord: "desc"
		},
		success:function(data){
			var items="";
			$.each(data.dataList,function(i,algorithm){
				items+= "<option value=\"" + algorithm.ID + "\">" + algorithm.name+" </option>"; 
			});
			$("#algorithmID").html(items);			
		}
	});
	}

/*
 * 选择算法
 */
function save_algorithm() {
	$.ajax({
		type : 'POST',
		url : 'selectAlg.action',
		data : {
			ID:$("#proID2").val(),
			algID:$("#algorithmID").val()			
		},
		success : function(data) {
			 viewAlgorithmDetail(data.algID);
		},
		error:function(msg){
			alert(msg);
		}
	});
	}

function viewAlgorithmDetail(rowId){
	$("#inputTr").nextAll().remove();
	$("#inputTr").show();	
	$("#algOutputTr").nextAll().remove();
	$("#algOutputTr").show();	
	$.ajax({
		type:"post",
		url:"viewAlgorithmDetail.action",
		data:{
			ID:rowId,
			sidx:"ID",
			sord:"asc"
		},
		success:function(data){
			$("#AlgID").text(data.algorithm.ID);
			$("#AlgName").text(data.algorithm.name);
			$("#AlgDes").text(data.algorithm.description);
			$("#AlgAddDate").text(data.algorithm.addDates);
			$("#AlgLastUpdateDate").text(data.algorithm.lastUpdateDates);
			$("#AlgAuthor").text(data.algorithm.authorName);

			var tr=$("#inputTr");
			$.each(data.inputList,function(index,row){
				var clonedTr=tr.clone();
				var _index=index;
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):$(this).html(row.ID);break;
						case(1):$(this).html(row.display);break;
						case(2):$(this).html(row.symbol);break;
					
					}//end switch
				});//end children.each
				clonedTr.insertAfter(tr);		
			});//end $each
			$("#inputTr").hide();
			$("#inputTable").show();			
			
			var tr=$("#algOutputTr");
			$.each(data.outputList,function(index,row){
				var clonedTr=tr.clone();
				var _index=index;
				clonedTr.children("td").each(function(inner_index){
					switch(inner_index){
						case(0):$(this).html(row.ID);break;
						case(1):$(this).html(row.display);break;
						case(2):$(this).html(row.symbol);break;
					
					}//end switch
				});//end children.each
				clonedTr.insertAfter(tr);		
			});//end $each
			$("#algOutputTr").hide();
			$("#algOutputTable").show();			
			
		}
	});
}

/*
 * 输入设置
 */
function setInput(proID){
	$("#ItemInputList").empty();
	$('#proID').val(proID);
	loadParameterOptions();
	loadInputOptions(proID);
	$("#addProInputForm").validate({
		debug:true,
		onsubmit:true,
		onfocusout:false,
		onkeyup:true,
		rules:{
			addParameterID:{
				required:true
			},
			addInputValue:{
				required:true
			}
		},
		messages:{
			addParameterID:{
				required:"请选择输入参数！"
			},
			addInputValue:{
				required:"参数值不能为空！"
			}
		},
		submitHandler:function(){
			add_proInput();
		}
	});
	$('#setProjectInput_modal').modal();
}
/*
 * 加载参数下拉列表
 */
function loadParameterOptions(){
	$.ajax({
		url:'listParameter.action',
		type:'post',
		dataType:'json',
		data : {
			sidx: 'id',
			sord: "desc"
		},
		success:function(data){
			var items="";
			$.each(data.dataList,function(i,parameter){
				items+= "<option value=\"" + parameter.ID + "\">" + parameter.display+" &nbsp;&nbsp"+parameter.measureSymbol + " </option>"; 
				
//				$("#measureSymbol").html(parameter.measureSymbol);
			});
			$("#addParameterID").html(items);
			
		}
	});
	}


//function loadInputOptions(proId){
//	$.ajax({
//		url:'listProInputs.action',
//		type:'post',
//		dataType:'json',
//		data : {
//			pro_id:proId,
//			sidx: 'id',
//			sord: "desc"
//		},
//		success:function(data){
//			var items="";
//			$.each(data.dataList,function(i,proInput){
//				items+= "<option value=\"" + proInput.ID + "\">" + proInput.display+" &nbsp;&nbsp"+proInput.mess + "</option>"; 
////				$("#measureSymbol").html(parameter.measureSymbol);
//			});
//			$("#modifyInputID").html(items);
//			showValue($("#modifyInputID").val());
//		}
//	});
//	}
/*
 * 加载已有的项目输入列表
 */
function loadInputOptions(proId){
	$.ajax({
		url:'listProInputs.action',
		type:'post',
		dataType:'json',
		data : {
			pro_id:proId,
			sidx: 'id',
			sord: "desc"
		},
		success:function(data){
			var items="";
			$.each(data.dataList,function(i,proInput){
				items+="<input style='display:none;' value="+proInput.ID+"'/><li><label class='control-label'>"+proInput.display+"</label>&nbsp;&nbsp;" +
						"<input  style='width:60px;vertical-align:middle;' class='valuechange' id='modifyInputValue' name='modifyInputValue' value="+proInput.value+" '/> &nbsp;&nbsp;" +
								"<span>"+proInput.mess+"</span>&nbsp;&nbsp;<button type='button' onclick='updateInputItem(this,"+proInput.ID+");' title='保存'>&radic;</button>" +
										"&nbsp;&nbsp;<button type='button' onclick='deleteInputItem(this,"+proInput.ID+");' title='删除'>×</button></li>";
				//items+= "<option value=\"" + proInput.ID + "\">" + proInput.display+" &nbsp;&nbsp"+proInput.mess + "</option>"; 			
			});
			$("#ItemInputList").append(items);
//			showValue($("#modifyInputID").val());
		}
	});
	}
/*
 * 保存工程输入项
 */
function updateInputItem(obj,id){
	value=$(obj).prev().prev().val();
	ID=id;
	$.ajax({
		type : 'POST',
		url : 'updateProInputs.action',
		data : {
			value:value,
			ID:ID
		},
		success : function(data) {
			alert('参数保存成功!');
	
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmInputList").trigger("reloadGrid");			
		},
		error:function(msg){
			alert(msg);
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmList").trigger("reloadGrid");
		}
	});
}
/*
 * 删除工程输入项
 */
function deleteInputItem(obj,id){
	 
	$.ajax({
		url:'delProInputs.action',
		type:'post',
		dataType:'json',
		data:{
			ID:id
		},
		success:function(data){
			alert("删除成功！");
//			$(obj).parent().replaceWith(""); 
			$(obj).parent().remove();
		}
	});
	}

/*
 * 添加项目输入
 */
function add_proInput() {
	$.ajax({
		type : 'POST',
		url : 'addProInputs.action',
		data : {
			pro_id:$("#proID").val(),
			param_id:$("#addParameterID").val(),
			value:$("#addInputValue").val()
		},
		success : function(data) {
			alert('参数保存成功!');
			items="<input style='display:none;' value="+data.ID+"'/><li><label class='control-label'>"+data.param.display+"</label>" +
					"&nbsp;&nbsp;<input  style='width:60px;vertical-align:middle;' class='valuechange' name='modifyInputValue' value="+data.value+" '/> &nbsp;&nbsp;" +
							"<span>"+data.param.measureSymbol+"</span>&nbsp;&nbsp;" +
									"<button type='button' onclick='updateInputItem(this,"+data.ID+");' title='保存'>&radic;</button>&nbsp;&nbsp;" +
											"<button type='button' onclick='deleteInputItem(this,"+data.ID+");' title='删除'>×</button></li>";
			$("#ItemInputList").append(items);
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmInputList").trigger("reloadGrid");			
		},
		error:function(msg){
			alert(msg);
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmList").trigger("reloadGrid");
		}
	});
	}
	
function modify_proInput() {
	$.ajax({
		type : 'POST',
		url : 'updateProInputs.action',
		data : {
			ID:$("#modifyInputID").val(),			
			value:$("#modifyInputValue").val()
		},
		success : function(data) {
			alert('参数保存成功!');
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmInputList").trigger("reloadGrid");			
		},
		error:function(msg){
			alter(msg);
			//$('#addAlgorithmInput_modal').modal('hide');
			//$("#AlgorithmList").trigger("reloadGrid");
		}
	});
	}
	
function loadAuthorOptions(){
	$.ajax({
		url:'listUser.action',
		type:'post',
		dataType:'json',
		success:function(data){
			var items="";
			$.each(data.dataList,function(i,user){
				items+= "<option value=\"" + user.userid + "\">" + user.username + "</option>"; 
			});
			$("#authorID").html(items);
		}
	});
	}

$(function(){
	
})
