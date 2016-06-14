var Leftpolys = function() {
	this.polys = new Array;
	this.polyGroups = new Array;
	this.connectionPoints = new Array;
	this.polys[0] = new Kinetic.Line({
		x : 5,
		y : 20,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#00D2FF',
		stroke : 'black',
		strokeWidth : 2,

		name : 'test1',
		closed : true
	});

	this.polys[1] = new Kinetic.Line({
		x : 5,
		y : 70,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#00FF00',
		stroke : 'black',
		strokeWidth : 2,
		name : 'test2',

		closed : true
	});

	this.polys[2] = new Kinetic.Line({
		x : 5,
		y : 120,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#FF0000',
		stroke : 'black',
		strokeWidth : 2,
		name : 'test3',
		closed : true
	});

	this.polys[3] = new Kinetic.Line({
		x : 5,
		y : 170,
		points : [ 0, 0, 90, 0, 90, 10, 0, 10 ],
		fill : '#44ffee',
		stroke : 'black',
		strokeWidth : 2,
		name : 'test4',
		closed : true
	});

	this.polys[4] = new Kinetic.Circle({
		x : 45,
		y : 220,
		radius : 15,
		fill : 'red',
		stroke : 'black',
		strokeWidth : 2,
		fillEnabled : true,
		name : 'test5',
		rotationDeg : -10
	});

	this.polys[5] = new Kinetic.Rect({
		x : 9,
		y : 270,
		width : 80,
		height : 30,
		cornerRadius : 10,
		scaleX : 1,
		scaleY : 1,
		RotationDeg : 0,
		name : 'test6',
		fill : 'blue'
	});
	this.init = function() {

		for ( var k in this.polys) {
			this.polyGroups[k] = new Kinetic.Group({
				x : this.polys[k].x(),
				y : this.polys[k].y(),

				draggable : true

			});
			var connPointsLeft = new Kinetic.Circle({
				x : 0,
				y : 5,

				radius : 5,
				fill : 'yellow',
				stroke : 'black',
				name : 'connPointsLeft',
				strokeWidth : 2
			});
			var connPointsRight = new Kinetic.Circle({
				x : 90,
				y : 5,
				name : 'connPointsRight',
				radius : 5,
				fill : 'yellow',
				stroke : 'black',
				strokeWidth : 2
			});

			this.polys[k].x(0);
			this.polys[k].y(0);
			this.polyGroups[k].add(this.polys[k]);
			this.polyGroups[k].add(connPointsLeft);
			this.polyGroups[k].add(connPointsRight);
			connPointsLeft.hide();
			connPointsRight.hide();
			this.polyGroups[k].dragBoundFunc(this.dragFun);

			this.polyGroups[k].on('click', this.clickFunc);
			// polys[k].on('dblclick', dbclickFun);

			this.polyGroups[k].on('dragend', this.cloneFun);
			this.polyGroups[k].on('mousedown touchstart', this.cloneFun2);
			this.polyGroups[k].on('mouseover', function() {
				document.body.style.cursor = 'pointer';
			});
			this.polyGroups[k].on('mouseout', function() {
				document.body.style.cursor = 'default';
			});
		}

	}
	/*
	 * 检查点是否在矩形区域里面
	 */

	// var platform=null;
	this.dragFun = function(pos) {

		if (checkPoint(pos, platform.centerlayer)) {
			if (platform.getConnShowed() == false) {
				showConnect(this);
				showALLConnPoints();
			}
			poss = checkConn(this);
			if (poss != null) {
				return {
					x : pos.x - poss.x,
					y : pos.y - poss.y
				};

			}
			return {
				x : pos.x,
				y : pos.y
			};
		}
		return {
			x : this.getAbsolutePosition().x,
			y : this.getAbsolutePosition().y
		};
	};

	this.cloneFun = function(e) {

		var userPos = platform.stage.getPointerPosition();
		if (checkPoint(userPos, platform.centerlayer))// 如果在中间画布上面

		{
			if (this.getParent() != platform.selectPainting.p) {

				this.x((this.x() - platform.selectPainting.mx)
						/ platform.selectPainting.scaleN);
				this.y((this.y() - platform.selectPainting.my)
						/ platform.selectPainting.scaleN);
				this.moveTo(platform.selectPainting.p);

			}

		} else {

			this.destroy();// 不在中间画布就摧毁

		}
		hideALLConnPoints();
		platform.centerlayer.draw(this);
		platform.stage.draw();
	};

	this.cloneFun2 = function(e) {

		if (e.type == 'mousedown'
				&& this.getLayer() != platform.selectPainting.p) {
			var cloneOfItem = this.clone();

			// cloneOfItem.off('mousedown touchstart');
			platform.leftlayer.add(cloneOfItem);

		}
		if (e.type == 'dragend') {

		}

	};

	this.dbclickFun = function(e) {
		if (e.type == 'dblclick') {
			alert('dblclick caidan');
		}

	};

	this.flag = 0;
	this.clickFunc = function(e) {
		// 当前位置弹出菜单（div）
		$("#contextmenu").css({
			top : this.getAbsolutePosition().y + 100,
			left : this.getAbsolutePosition().x + 90,

		}).show();
		var clickshape = e.target.getParent();
		var flagin = leftpoly.flag;// 当前序列
		leftpoly.flag++;
		/* 右键菜单处理 */
		$("#contextmenu a").click(
				function() {
					if (flagin != leftpoly.flag - 1) {
						return;
					}

					var text = $(this).text();
					if (text == '删除该节点') {

						clickshape.destroy();
						$("#contextmenu").hide();

						platform.selectPainting.p.draw();
					} else if (text == '更改颜色') {
						node.style.fillStyle = Math.floor(Math.random() * 250)
								+ "," + Math.floor(Math.random() * 250) + ","
								+ Math.floor(Math.random() * 250);
					} else if (text == '顺时针旋转90°') {
						clickshape.rotate(90);
						// centerlayer.draw(this);
						platform.selectPainting.p.draw();
					} else if (text == '逆时针旋转90°') {
						clickshape.rotate(-90);
						// centerlayer.draw(this);
						platform.selectPainting.p.draw();
					} else if (text == '放大') {
						clickshape.scale({
							x : clickshape.scaleX() * 2,
							y : clickshape.scaleY()
						});
						platform.selectPainting.p.draw();
					} else if (text == '缩小') {
						clickshape.scale({
							x : clickshape.scaleX() / 2,
							y : clickshape.scaleY()
						});
						platform.selectPainting.p.draw();
					}
					hideALLConnPoints();
					// $("#contextmenu").hide();
				});
	};

	showALLConnPoints = function() {
		points = platform.getAllChildren();
		for (i1 = 0; i1 < points.length; i1++) {
			showConnect(points[i1]);

		}
		platform.draw();
		platform.setConnShowed(true);
	}
	hideALLConnPoints = function() {
		points = platform.getAllChildren();
		for (i1 = 0; i1 < points.length; i1++) {
			hideConnection(points[i1]);

		}
		platform.draw();
		platform.setConnShowed(false);
	}
	showConnect = function(g) {

		tempArray = g.getChildren(function(node) {
			return node.getName() == 'connPointsLeft'
					|| node.getName() == 'connPointsRight'
		});
		tempArray[0].show();
		tempArray[1].show();
		g.draw();
	}
	hideConnection = function(g) {
		tempArray = g.getChildren(function(node) {
			return node.getName() == 'connPointsLeft'
					|| node.getName() == 'connPointsRight'
		});
		tempArray[0].hide();
		tempArray[1].hide();

	}
	checkConn = function(g) {
		tempArray = g.getChildren(function(node) {
			return node.getName() == 'connPointsLeft'
					|| node.getName() == 'connPointsRight'
		});
		points = platform.getAllChildren();
		for (li = 0; li < points.length; li++) {
			tempArray2 = points[li].getChildren(function(node) {
				return node.getName() == 'connPointsLeft'
						|| node.getName() == 'connPointsRight'
			});
			if (checkCircle(tempArray[0], tempArray2[1],
					tempArray[0].radius() * 2)) {
				return {
					g : points[li],
					right : tempArray2[1],
					left : 0,
					x : tempArray[0].getAbsolutePosition().x
							- tempArray2[1].getAbsolutePosition().x,
					y : tempArray[0].getAbsolutePosition().y
							- tempArray2[1].getAbsolutePosition().y,
				}
			}
			if (checkCircle(tempArray[1], tempArray2[0],
					tempArray[0].radius() * 2)) {
				return {
					g : points[li],
					right : 0,
					left : tempArray2[0],
					x : tempArray[1].getAbsolutePosition().x
							- tempArray2[0].getAbsolutePosition().x,
					y : tempArray[1].getAbsolutePosition().y
							- tempArray2[0].getAbsolutePosition().y,
				}
			}
		}
		return null;
	}
}