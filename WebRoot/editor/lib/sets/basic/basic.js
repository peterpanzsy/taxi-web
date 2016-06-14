"use strict";

/**Figure set declaration*/
figureSets["basic"] = {
    name: 'Basic',
    description : 'A basic set of figures',
    figures: [
        {figureFunction: "line1", image: "1.png"},
        {figureFunction: "line2", image: "2.png"},
        {figureFunction: "line3", image: "3.png"},
        {figureFunction: "line4", image: "4.png"}
    ]
};

var figure_defaultFigureSegmentSize = 70;
var figure_defaultFigureSegmentShortSize = 40;
var figure_defaultFigureRadiusSize = 35;
var figure_defaultFigureParalelsOffsetSize = 40;
var figure_defaultFigureCorner = 10

var figure_defaultFigureCornerRoundness = 8;

var figure_defaultXCoordonate = 0;
var figure_defaultYCoordonate = 0;

var figure_defaultFigureTextSize = 12;
var figure_defaultFigureTextStr = "Text";
var figure_defaultFigureTextFont = "Arial";
var figure_defaultStrokeStyle = "#000000";
var figure_defaultFillStyle = "#ffffff";
var figure_defaultFillTextStyle = "#000000";


function figure_line1(x,y)
{    

  var rect = new Kinetic.Rect({
    x: 239,
    y: 75,
    width: 100,
    height: 50,
    fill: 'blue',
    stroke: 'black',
    strokeWidth: 4
  });

  // add the shape to the layer
  layer.add(rect);

  // add the layer to the stage
  stage.add(layer);
  
   
}

function figure_line2(x,y)
{   
  var rect = new Kinetic.Rect({
    x: x,
    y: y,
    width: 100,
    height: 50,
    fill: 'green',
    stroke: 'black',
    strokeWidth: 4
  });

  // add the shape to the layer
  layer.add(rect);

  // add the layer to the stage
  stage.add(layer);
  
   
}

function figure_line3(x,y)
{ 

  var rect = new Kinetic.Rect({
    x: x,
    y: y,
    width: 100,
    height: 50,
    fill: 'green',
    stroke: 'black',
    strokeWidth: 4
  });

  // add the shape to the layer
  layer.add(rect);

  // add the layer to the stage
  stage.add(layer);
  
   
}


function figure_line4(x,y)
{  

  var rect = new Kinetic.Rect({
    x: x,
    y: y,
    width: 100,
    height: 50,
    fill: 'green',
    stroke: 'black',
    strokeWidth: 4
  });

  // add the shape to the layer
  layer.add(rect);

  // add the layer to the stage
  stage.add(layer);
  
   
}