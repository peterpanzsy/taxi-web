var Platform=function(){
	this.stage = new Kinetic.Stage({
        container: 'container',
        width: 1020,
        height: 800
      });
    
	this.leftlayer   = new Kinetic.Layer({
	    x:0,
	    y:0,
	    id: 'leftlayer',
	    width:100,
	    height:800
	});
	this.centerlayer   = new Kinetic.Layer({
	    x:100,
	    y:40,
	    id: 'centerlayer',
	    width:920,
	    height:500
//	    width:8,
//	    height:8
	});
	this.gridlayer  = new Kinetic.Layer({
	    x:100,
	    y:this.centerlayer.y(),
	    id: 'gridlayer',
	    width:920,
	    height:500
	});
	
	this.paintingGroup = new Kinetic.Group({
		x : 100,
		y : 0,
		id : 'painting',
		width : 2000,
		height : 1000,
		fill : '#ff33ee',
		draggable : true
	});
	this.tablayer=new Kinetic.Layer({
		x:100,
		y:2,
		id:'tablayer',
		width:850,
		height:50,
		draggable:false,
	});
	
	
	this.rectBackground = new Kinetic.Rect({
	   x: 0,
	   y: 0,
	   height: this.stage.attrs.height,
	   width: this.stage.attrs.width,
	   fill: 'transparent',
	   draggable: false,
	   name: 'rectBackground'
	 });
	this.rectBackgroundLeft = new Kinetic.Rect({
	   x: 0,
	   y: 0,
	   height: 800,
	   width: 100,
	   fill: 'transparent',
	   draggable: false,
	   stroke:'black',
	   name: 'rectBackgroundLeft'
	 });
	this.rectBackgroundCenter = new Kinetic.Rect({
	   x: 10,
	   y: 0,
	   height: 500,
	   width: 900,
	   fill: 'transparent',
	   draggable: false,
	   stroke:'black',
	   name: 'rectBackgroundCenter'
	 });
   

		
	this.selectPainting=null;	
	this.bgGroup;
	this.bgRect=null;
	this.bgGridRect=null;
   
	this.init=function(){
		this.leftlayer.on('mouseover', function() {
			document.body.style.cursor = 'pointer';
		});
		this.leftlayer.on('mouseout', function() {
			document.body.style.cursor = 'default';
		});
		this.centerlayer.on('click',function(e){//如果点击在中央层的背景上则隐藏点击菜单
			if(e.target.name()==='rectBackgroundCenter')
			$('#contextmenu').hide();
		});
	    bgGroup.add(bgRect);
	    
	    this.gridlayer.add(bgGroup);
		this.leftlayer.add(this.rectBackgroundLeft);
		this.centerlayer.add(this.rectBackgroundCenter);
		this.stage.add(this.gridlayer);
		this.stage.add(this.leftlayer);
		
		this.stage.add(this.centerlayer);
		this.drawGrid();
		this.showGrid();
		//stage.add(tablayer);
		//this.stage.add(painting);
	}
	
	
	this.addLeft=function(left){
		left.platform=this;
		for (var k in left.polyGroups)
		{this.leftlayer.add(left.polyGroups[k]);}
		this.leftlayer.draw();
	}

	this.getAllChildren=function(){
		return this.selectPainting.p.getChildren();
	}
	this.showGrid=function(){
		if(document.getElementById("gridCheckbox").checked==true){
			bgGroup.show();
			this.gridlayer.draw();
		}else{
			bgGroup.hide();
			this.gridlayer.draw();
		}
	}
	var gridEle = 9;
//	gridlayer=centerlayer;
	bgGroup = new Kinetic.Group();
	   //主编辑区域背景框
	bgRect = new Kinetic.Rect( {
            x : 12,
            y : this.gridlayer.y()+1,
//            fill : "#eee",
            fill: 'transparent',
            draggable : false,
//            width : gridlayer.width()-100,
//            height :gridlayer.height()-100,
            width:8000,
            height:8000,
//            shadow : {
//                    color : 'black',
//                    blur : 8,
//                    offset : [ 2, 2 ],
//                    opacity : 0.6
//            }
    });


	  //主编辑区域网格框
    bgGridRect = new Kinetic.Rect( {
            x : bgRect.x(),
            y : 0,
            fill : "#fff",
//            fill: 'transparent',
            draggable : false,
            width :900,
            height :500
    });
	this.drawGrid=function(){


	    bgGroup.add(bgGridRect);

	    //网格的行数列数
	    var rows = bgGridRect.getHeight() / gridEle + 2;
	    var cols = bgGridRect.getWidth() / gridEle - 2;

	    //绘制网格
	    for ( var i = 0; i <= rows; i++) {
	            var color = "#f3f3f3";
	            var line = new Kinetic.Line( {
	                    points : [ bgGridRect.getX(),
	                                    bgGridRect.getY() + (i * gridEle) + 0.5,
	                                    bgGridRect.getX() + bgGridRect.getWidth(),
	                                    bgGridRect.getY() + (i * gridEle) + 0.5 ],
	                    stroke : color,
	                    strokeWidth : 1,
	                    lineCap : "butt",
	                    lineJoin : "butt"
	            });
	            bgGroup.add(line);
	    }

	    for ( var i = 0; i <= cols; i++) {
	            var color = "#f3f3f3";
	            if (i % 4 == 0) {
	                    color = "#ddd";
	            }
	            var line = new Kinetic.Line( {
	                    points : [ bgGridRect.getX() + (i * gridEle) + 0.5,
	                                    bgGridRect.getY(),
	                                    bgGridRect.getX() + (i * gridEle) + 0.5,
	                                    bgGridRect.getY() + bgGridRect.getHeight() ],
	                    stroke : color,
	                    strokeWidth : 1,
	                    lineCap : "butt",
	                    lineJoin : "butt"
	            });
	            bgGroup.add(line);
	    }
	    
	    for ( var i = 0; i <= rows; i++) {
	            if (i % 4 == 0) {
	                    var line = new Kinetic.Line( {
	                            points : [ bgGridRect.getX(),
	                                            bgGridRect.getY() + (i * gridEle) + 0.5,
	                                            bgGridRect.getX() + bgGridRect.getWidth(),
	                                            bgGridRect.getY() + (i * gridEle) + 0.5 ],
	                            stroke : "#ddd",
	                            strokeWidth : 1,
	                            lineCap : "butt",
	                            lineJoin : "butt"
	                    });
	                    bgGroup.add(line);
	            }
	    }
	    this.gridlayer.draw();

	}


	this.addPainting=function(paintFrame){
		this.stage.add(paintFrame.p);
		this.selectPainting=paintFrame;
		paintFrame.update();
	}

	this.scaleCenter=function(s) {
		
		this.selectPainting.scaleN=this.selectPainting.scaleN*s;
		this.selectPainting.p.scaleX(this.selectPainting.p.scaleX() * s);
		this.selectPainting.p.scaleY(this.selectPainting.p.scaleY() * s);
		this.selectPainting.p.draw();	
		
	}
	this.paintingArray=new Array;
	this.addLoadPainting=function(newone) {
		paintings=new Paintings();
		paintings.init()
		paintings.p=newone;
		this.addPainting(paintings);
		this.paintingArray.push(paintings);
		this.selectPainting.showSelected();
		return this.paintingArray.indexOf(paintings);
	}
	this.showPainting=function(index){
		this.selectPainting=this.paintingArray[index];
		this.selectPainting.showSelected();
		this.stage.draw();
	}

	this.draw=function(){
		this.selectPainting.p.draw();
	}
	this.getConnShowed=function(){
		return this.showed;
	}
	this.setConnShowed=function(bool){
		this.showed=bool;
	}
};
	