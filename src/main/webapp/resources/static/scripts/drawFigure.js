"use strict";
function drawArea(r) {
    clearCanvas();
// rectangle
    ctx.fillStyle = "#2130ff";
    ctx.fillRect(canvas.width/2, canvas.height/2, intervalSize*r, intervalSize*(r/2));


// circle-sector
    ctx.beginPath()
    ctx.moveTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(canvas.width/2, canvas.height/2 - intervalSize*(r/2));
    ctx.moveTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(canvas.width/2-intervalSize*(r/2), canvas.height/2);
    ctx.arc(canvas.width/2, canvas.height/2, intervalSize*(r/2), Math.PI, -Math.PI/2, false);
    ctx.closePath();
    ctx.fill();


// triangle
    ctx.beginPath()
    ctx.moveTo(canvas.width/2 - intervalSize*r ,canvas.height/2);
    ctx.lineTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(canvas.width/2, canvas.height/2+intervalSize*r);
    ctx.closePath();
    ctx.fill();
//
    drawCoordinateLines();
}

function clearCanvas(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

function drawCoordinateLines(){
// draw coordinates plane
    ctx.beginPath()
    ctx.fillStyle = "#000";

    ctx.moveTo(canvas.width/2, 0);
    ctx.lineTo(canvas.width/2, canvas.height);


    ctx.moveTo(0, canvas.height/2);
    ctx.lineTo(canvas.width, canvas.height/2);


    ctx.moveTo(canvas.width/2, 0);
    ctx.lineTo(canvas.width/2+10, 15);


    ctx.moveTo(canvas.width/2, 0);
    ctx.lineTo(canvas.width/2-10, 15);


    ctx.moveTo(canvas.width, canvas.height/2);
    ctx.lineTo(canvas.width-15, canvas.height/2+10);


    ctx.moveTo(canvas.width, canvas.height/2);
    ctx.lineTo(canvas.width-15, canvas.height/2-10);

    for (let i=-4; i<=4; i++){
        if (i === 0){
            continue
        }
        ctx.moveTo(canvas.width/2-7, canvas.height/2 + intervalSize*i);
        ctx.lineTo(canvas.width/2+7, canvas.height/2 + intervalSize*i);

        ctx.moveTo(canvas.width/2 + intervalSize*i, canvas.height/2-7);
        ctx.lineTo(canvas.width/2 + intervalSize*i, canvas.height/2+7);
    }

    // text
    ctx.font = "18px Arial";
    for (let i=-4; i<=4; i++){
        if (i === 0){
            continue;
        }
        if (i < 0){
            ctx.fillText(i.toString(), canvas.width/2 + intervalSize*i-11, canvas.height/2-10);
            ctx.fillText(i.toString(), canvas.width/2-25, canvas.height/2 - intervalSize*i+7);
        } else {
            ctx.fillText(i.toString(), canvas.width/2 + intervalSize*i-6, canvas.height/2-10);
            ctx.fillText(i.toString(), canvas.width/2-20, canvas.height/2 - intervalSize*i+7);
        }
    }
    ctx.closePath();
    ctx.stroke();
}

function drawPoint(x, y, result){
    console.log(x)
    console.log(y)
    console.log(result);
    if (result){
        ctx.fillStyle = "#2fc908";
    } else {
        ctx.fillStyle = "#ff0000";
    }
    ctx.beginPath();
    ctx.arc(canvas.width/2 + x*intervalSize, canvas.height/2 - y*intervalSize, 3, 0, Math.PI * 2);
    ctx.closePath();
    ctx.fill();
}