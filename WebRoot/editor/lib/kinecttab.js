var TabTools=function (){
	/*
	 * 图形项目列表
	 */
	this.datagrid = jQuery("#GUIProList")
	.jqGrid(
			{
				url : "listGUIPro.action",// 后端的数据交互程序，改为你的
				datatype : "json",// 前后交互的格式是json数据
				mtype : 'POST',// 交互的方式是发送httpget请求						
				colNames : [ '编号', '项目名称', '作者','描述','添加时间','操作'],// 表格的列名
				colModel : [
						{
							name : 'id',
							index : 'ID',
							width : 50,
							align : "center",
							sortable:true,
							sorttype:'int'
						},// 每一列的具体信息，index是索引名，当需要排序时，会传这个参数给后端
						{
							name : 'proname',
							index : 'proname',
							width : 100,
							align : "center",
							sortable:true
						},
						{
							name:'author',
							index:'author',
							width:100,
							align:'center',
							sortable:true
						},
						{
							name:'description',
							index:'description',
							width:200,
							align:'center',
							sortable:false
						},
						{
							name:'addTime',
							index:'addTime',
							width:100,
							align:'center',
							sortable:true
						},
						{
							name : 'load',
							index : 'load',
							width : 100,
							align : "center",
							formatter : function(value, grid, rows,
									state) {
//								alert(rows.id);
								return "<a href=\"javascript:void(0)\" style=\"color:#798991\" onclick=\"tabtools.load('"
										+ rows.id + "')\">加载</a>"
							}
						}					
	
						],
//				autowidth:true,
				rowNum:10,//每一页的行数
				height: 'auto',
				width:1230,
				rowList:[10,20,30],
				pager: '#GUIProPager',
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
				caption: "模型列表"//表格名称       
				
			});

	this.listGUIProGrid=function(){
//		$("#GUIProList").empty();
		/*
		 * 图形项目列表
		 */
		$('#GUIProList').trigger("reloadGrid");
		
		$('#listGUIPro_modal').modal();
	}

	this.createNewModal=function(){
		newone=new Paintings();
		platform.addPainting(newone.init());
//		paintingArray.add(selectedPainting);
		// 配置对话框
		loadAuthorOptions();//加载作者选项
		$('#add_GUI_modal').modal();
		$("#addGUIForm").validate({
			debug:true,
			onsubmit:true,
			onfocusout: function(element) { $(element).valid(); },
		    onkeyup: function(element) { $(element).valid(); },
			rules:{
				proname:{
					required:true
				},
				authorID:{
					required:true
				},
				type:{
					digits:true                 
				}
			},
			messages:{
				proname:{
					required:"名称不能为空！",
				},							
				authorID:{
					required:"请选择作者！"
				},
				type:{
					digits:"请输入整数", 
				}
			},
			submitHandler:function(){
				createNew();
			}
		});
			
	}
	/*
	 * 添加图形项目
	 */
	var createNew=function() {
//		if($('#selectedID').val()==''){
			jsondata=platform.selectPainting.p.toJSON();
			$.ajax({
				type : 'POST',
				url : 'addGUIPro.action',
				data : {
					description:$("#description").val(),
					name:$('#proname').val(),
					data:jsondata,
					authorID:$("#authorID").val(),
					type:$('#type').val()
				},
				success : function(data) {
					alert('图形化项目新建成功!');
					$('#add_GUI_modal').modal('hide');
					tabtools.load(data.ID);
					//$("#AlgorithmList").trigger("reloadGrid");		
				},
				error:function(msg){
					alert(msg);
					$('#add_GUI_modal').modal('hide');
					//$("#AlgorithmList").trigger("reloadGrid");
					
				}
			});

	}

	this.save=function() {
		//selectPainting=Kinetic.Node.create(jsondata, 'container')
		var selectedID=$('#selectedID').val();
		if(selectedID==''){
			createNewModal();
		}else{
			updateGUI();
		}
		
	}
	function updateGUI(){
		jsondata=platform.selectPainting.p.toJSON();
		console.log(jsondata);
		$.ajax({
			type : 'POST',
			url : 'updateGUIPro.action',
			data : {			
				data:jsondata,
				ID:$('#selectedID').val(),
			},
			success : function(data) {
				alert('图形化保存成功!');
		
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
	this.switchTab=function(selectedID) {



				platform.showPainting(selectedID);
		
	}
	this.load=function(selectedID) {

//		selectedID=1;
		$.ajax({
			type : 'POST',
			url : 'viewGUIPro.action',
			data : {
				
				ID:selectedID
				
			},
			success : function(data) {
				alert('图形化载入成功!');
				$('#selectedID').val(selectedID);
				//data=jQuery.parseJSON(data);
				$('#listGUIPro_modal').modal('hide');
				try{
					saveData=data['dataView']['JSONData'];
				}catch(err){
					alert('该项目模型为空！');
				}
				//alert(saveData);
				//console.log(saveData['JSONData']);
				newone=Kinetic.Node.create(saveData);
				index=platform.addLoadPainting(newone);				
				createTab(data['dataView']['proname'],index);
				//createNewTab(data['dataView']['proname']);
				platform.stage.draw();
						
			},
			error:function(msg){
				alert(msg);
			}
		});
	}
	var tabX=50;
	var tabY=50;
	var position;
	 
	function createTab(proname,paintingIndex){
		$("#paintingTabs").children().removeClass("active");
		var tabItem="<li class=\"tab active\"> <a href=\"javascript:void(0)\" onclick=\"tabtools.showPainting(this,'"+ paintingIndex + "')\">"+proname+"</a></li>";
		$("#paintingTabs").append(tabItem);
	}
	this.showPainting=function(obj,paintingIndex){
		$("#paintingTabs").children().removeClass("active");
		$(obj).parent().addClass("active");
		platform.showPainting(paintingIndex);
	}


	/*
	 * 加载作者下拉列表
	 */
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
}