var centerlayer;
var painting;
var paintingGroup;
var poly = new Array;
var mx=100;
var my=0;
var scaleN=1;
function initLight() {
	var stage = new Kinetic.Stage({
		container : 'container',
		width : 1020,
		height : 800
	});

	var leftlayer = new Kinetic.Layer({
		x : 0,
		y : 0,
		id : 'leftlayer',
		width : 100,
		height : 800
	});
	centerlayer = new Kinetic.Layer({
		x : 100,
		y : 0,
		id : 'centerlayer',
		width : 920,
		height : 800
	});
	paintingGroup = new Kinetic.Group({
		x : 100,
		y : 0,
		id : 'painting',
		width : 2000,
		height : 1000,
		fill : '#ff33ee',
		draggable : true
	});
	painting = new Kinetic.Layer({
		x : 100,
		y : 0,
		id : 'painting',
		width : 3000,
		height : 2000,
		fill : '#ff33ee',
		draggable : true
	});
	
	var rectBackground = new Kinetic.Rect({
		x : 0,
		y : 0,
		height : stage.attrs.height,
		width : stage.attrs.width,
		fill : 'transparent',
		draggable : false,
		name : 'rectBackground'
	});
	var rectBackgroundLeft = new Kinetic.Rect({
		x : 0,
		y : 0,
		height : 800,
		width : 100,
		fill : 'transparent',
		draggable : false,
		stroke : 'black',
		name : 'rectBackgroundLeft'
	});
	var rectBackgroundCenter = new Kinetic.Rect({
		x : 10,
		y : 0,
		height : 800,
		width : 900,
		fill : 'transparent',
		draggable : false,
		stroke : 'black',
		name : 'rectBackgroundCenter'
	});

	poly[0] = new Kinetic.Line({
		x : 5,
		y : 20,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#00D2FF',
		stroke : 'black',
		strokeWidth : 2,
		draggable : true,
		closed : true
	});

	poly[1] = new Kinetic.Line({
		x : 5,
		y : 70,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#00FF00',
		stroke : 'black',
		strokeWidth : 2,
		draggable : true,
		closed : true
	});

	poly[2] = new Kinetic.Line({
		x : 5,
		y : 120,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#FF0000',
		stroke : 'black',
		strokeWidth : 2,
		draggable : true,
		closed : true
	});

	poly[3] = new Kinetic.Line({
		x : 5,
		y : 170,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,

		draggable : true,
		closed : true
	});
	var S = new Kinetic.Line({
		x : 0,
		y : 0,
		points : [ 0, 0, 20, 0, 10, 15],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,

		draggable : true,
		closed : true
	});
	poly[3] = new Kinetic.Line({
		x : 5,
		y : 170,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,

		draggable : true,
		closed : true
	});
	poly[3] = new Kinetic.Line({
		x : 5,
		y : 170,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,

		draggable : true,
		closed : true
	});
	poly[3] = new Kinetic.Line({
		x : 5,
		y : 170,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,

		draggable : true,
		closed : true
	});
	leftlayer.add(rectBackgroundLeft);
	centerlayer.add(rectBackgroundCenter);

	function checkPoint(pos, rect) {
		var size = rect.size();
		if (pos.x > rect.x() && pos.y > rect.y()
				&& (pos.x < rect.x() + size.width)
				&& (pos.y < rect.y() + size.height)) {
			return true;
		}
		return false;
	}
	var dragFun = function(pos) {

		if (checkPoint(pos, centerlayer)) {

			return {
				x : pos.x,
				y : pos.y
			}
		}
		return {
			x : this.getAbsolutePosition().x,
			y : this.getAbsolutePosition().y
		};
	};
	var cloneFun = function(e) {
		var userPos = stage.getPointerPosition();
		if (checkPoint(userPos, centerlayer))

		{
			if (this.getParent() != painting) {
				this.x((this.x() - mx)/scaleN);
				this.y((this.y()-my)/scaleN);
				this.moveTo(painting);
			
			}

		} else {
			this.destroy();
		}
		centerlayer.draw(this);
		stage.draw();
	}
	var dbclickFun = function(e) {
		if (e.type == 'dblclick') {
			alert('dblclick caidan');

		}

	};
	var clickFun = function(e) {
		if (e.type == 'click') {
			//alert('dblclick caidan');
			
		}

	};
	var cloneFun2 = function(e) {
		if (e.type == 'mousedown' && this.getLayer() != painting) {
			var cloneOfItem = this.clone();

			// cloneOfItem.off('mousedown touchstart');
			leftlayer.add(cloneOfItem);

		}
		if (e.type == 'dragend') {

		}

	};

	for ( var k in poly) {
		poly[k].dragBoundFunc(dragFun);
		poly[k].on('click', clickFun);
		poly[k].on('dblclick', dbclickFun);
		leftlayer.add(poly[k]);
		poly[k].on('dragend', cloneFun);
		poly[k].on('mousedown touchstart', cloneFun2);
	}
    
	
	
	 var updateBackgroundPos = function() {
	        var x =(hscroll.getPosition().x - 20)/(centerlayer.getWidth() - 90-110);
	        var y =(vscroll.getPosition().y - 20)/(centerlayer.getHeight() -90-110);
	        var px=painting.getWidth();
	        var py=painting.getHeight();
	        mx=100-(px-centerlayer.getWidth())*x;
	        my=0-(py-centerlayer.getHeight())*y;
	        painting.x(mx);
	        
	        painting.y(my);
	        var childs=painting.getChildren();
	        	 
	        
	        for (var ich in childs){
	        /*	
	        	if(checkPoint(childs[ich].getAbsolutePosition(),centerlayer)){
	        		childs[ich].show();
	        	}
	        	else{
	        		childs[ich].hide();
	        	}*/
	        	
	        }
	        painting.draw();
	      };
	

	     
	      var areas = new Kinetic.Group();
	      var scrollbars = new Kinetic.Group();
	      var container = stage.getContainer();

	      /*
	       * horizontal scrollbars
	       */
	      var hscrollArea = new Kinetic.Rect({
	        x: 20,
	        y: centerlayer.getHeight() - 30,
	        width: centerlayer.getWidth() - 70,
	        height: 20,
	        fill: 'black',
	        opacity: 0.3
	      });

	      var hscroll = new Kinetic.Rect({
	        x: 20,
	        y: centerlayer.getHeight() - 30,
	        width: 130,
	        height: 20,
	        fill: '#9f005b',
	        draggable: true,
	        dragBoundFunc: function(pos) {
	          var newX = pos.x;
	          if(newX < centerlayer.x()+20) {
	            newX = centerlayer.x()+20;
	          }
	          else if(newX > centerlayer.getWidth()-80 ) {
	            newX = centerlayer.getWidth() -80;
	          }
	          return {
	            x: newX,
	            y: this.getAbsolutePosition().y
	          }
	        },
	        opacity: 0.9,
	        stroke: 'black',
	        strokeWidth: 1
	      });

	      /*
	       * vertical scrollbars
	       */
	      var vscrollArea = new Kinetic.Rect({
	        x: centerlayer.getWidth() - 40,
	        y: 20,
	        width: 20,
	        height: centerlayer.getHeight() - 70,
	        fill: 'black',
	        opacity: 0.3
	      });

	      var vscroll = new Kinetic.Rect({
	        x: centerlayer.getWidth() - 40,
	        y: 20,
	        width: 20,
	        height: 130,
	        fill: '#9f005b',
	        draggable: true,
	        dragBoundFunc: function(pos) {
	          var newY = pos.y;
	          if(newY < 20) {
	            newY = 20;
	          }
	          else if(newY > centerlayer.getHeight() - 110-70) {
	            newY = centerlayer.getHeight() - 110-70;
	          }
	          return {
	            x: this.getAbsolutePosition().x,
	            y: newY
	          }
	        },
	        opacity: 0.9,
	        stroke: 'black',
	        strokeWidth: 1
	      });

	      /*
	       * scrollbars
	       */
	      scrollbars.on('mouseover', function() {
	        document.body.style.cursor = 'pointer';
	      });
	      scrollbars.on('mouseout', function() {
	        document.body.style.cursor = 'default';
	      });

	      hscroll.on('dragmove', updateBackgroundPos);
	      vscroll.on('dragmove', updateBackgroundPos);

	      areas.add(hscrollArea);
	      areas.add(vscrollArea);
	      scrollbars.add(hscroll);
	      scrollbars.add(vscroll);
	      centerlayer.add(areas);
	      centerlayer.add(scrollbars);
	     
	
	
	
	
	// group.setZIndex(-1);
	painting.on('mouseover', function() {
		document.body.style.cursor = 'pointer';
	});
	painting.on('mouseout', function() {
		document.body.style.cursor = 'default';
	});
	leftlayer.on('mouseover', function() {
		document.body.style.cursor = 'pointer';
	});
	leftlayer.on('mouseout', function() {
		document.body.style.cursor = 'default';
	});
	stage.add(leftlayer);
	stage.add(centerlayer);
	stage.add(painting);

}
function scaleCenter(s) {
	scaleN=scaleN*s;
	painting.scaleX(painting.scaleX() * s);
	painting.scaleY(painting.scaleY() * s);
	painting.draw();
}
