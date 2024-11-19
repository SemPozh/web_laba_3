"use strict";
const canvas = document.getElementById("clockCanvas");
canvas.width = document.getElementById("clockWrapper").offsetWidth;
canvas.height = document.getElementById("clockWrapper").offsetHeight;
const ctx = canvas.getContext("2d");

setTime();
setInterval(setTime, 6000);

function drawClock(){
    ctx.fillStyle = "#fff";
    // main background
    ctx.beginPath();
    ctx.arc(canvas.width/2, canvas.height/2, canvas.width/2, 0, Math.PI * 2);
    ctx.closePath();
    ctx.fill();

    // rim
    ctx.strokeStyle = "#000";
    ctx.lineWidth = 15;
    ctx.beginPath();
    // ctx.arc(canvas.width/2, canvas.height/2, canvas.width/2, 0, Math.PI*2);
    ctx.arc(canvas.width/2, canvas.height/2, canvas.width/2-7.5, 0, Math.PI*2);
    ctx.closePath();
    ctx.stroke();

    // dashes
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(canvas.width/2,20);
    ctx.lineTo(canvas.width/2,40);

    ctx.moveTo(canvas.width-20, canvas.height/2);
    ctx.lineTo(canvas.width-40, canvas.height/2);

    ctx.moveTo(canvas.width/2, canvas.height-20);
    ctx.lineTo(canvas.width/2, canvas.height-40);

    ctx.moveTo(20, canvas.height/2);
    ctx.lineTo(40, canvas.height/2);

    let x1_1 = Math.sqrt(canvas.width*canvas.width/16 - 15*canvas.width/4 + 225/4)-2.5;
    let y1_1 = x1_1*Math.sqrt(3);
    let x2_1 = x1_1-10;
    let y2_1 = x2_1*Math.sqrt(3);
    ctx.moveTo(x1_1+canvas.height/2, canvas.height/2 - y1_1);
    ctx.lineTo(x2_1+canvas.width/2, canvas.height/2 - y2_1);

    let x1_2 = Math.sqrt(3*canvas.width*canvas.width/16 - 45*canvas.width/4 + 3*225/4)-2.5*Math.sqrt(3);
    let y1_2 = x1_2 * Math.sqrt(3)/3;
    let x2_2 = x1_2 - 10*Math.sqrt(3);
    let y2_2 = x2_2*Math.sqrt(3)/3;
    ctx.moveTo(x1_2+canvas.height/2, canvas.height/2 - y1_2);
    ctx.lineTo(x2_2+canvas.width/2, canvas.height/2 - y2_2);

    let x1_4 = x1_2;
    let y1_4 = -y1_2;
    let x2_4 = x2_2;
    let y2_4 = -y2_2;
    ctx.moveTo(x1_4+canvas.height/2, canvas.height/2 - y1_4);
    ctx.lineTo(x2_4+canvas.width/2, canvas.height/2 - y2_4);

    let x1_5 = x1_1;
    let y1_5 = -y1_1;
    let x2_5 = x2_1;
    let y2_5 = -y2_1;
    ctx.moveTo(x1_5+canvas.height/2, canvas.height/2 - y1_5);
    ctx.lineTo(x2_5+canvas.width/2, canvas.height/2 - y2_5);

    let x1_7 = -x1_1;
    let y1_7 = -y1_1;
    let x2_7 = -x2_1;
    let y2_7 = -y2_1;
    ctx.moveTo(x1_7+canvas.height/2, canvas.height/2 - y1_7);
    ctx.lineTo(x2_7+canvas.width/2, canvas.height/2 - y2_7);

    let x1_8 = -x1_2;
    let y1_8 = -y1_2;
    let x2_8 = -x2_2;
    let y2_8 = -y2_2;
    ctx.moveTo(x1_8+canvas.height/2, canvas.height/2 - y1_8);
    ctx.lineTo(x2_8+canvas.width/2, canvas.height/2 - y2_8);

    let x1_10 = -x1_2;
    let y1_10 = y1_2;
    let x2_10 = -x2_2;
    let y2_10 = y2_2;
    ctx.moveTo(x1_10+canvas.height/2, canvas.height/2 - y1_10);
    ctx.lineTo(x2_10+canvas.width/2, canvas.height/2 - y2_10);

    let x1_11 = -x1_1;
    let y1_11 = y1_1;
    let x2_11 = -x2_1;
    let y2_11 = y2_1;
    ctx.moveTo(x1_11+canvas.height/2, canvas.height/2 - y1_11);
    ctx.lineTo(x2_11+canvas.width/2, canvas.height/2 - y2_11);
    ctx.closePath();
    ctx.stroke();

    ctx.fillStyle="#000";
    ctx.beginPath();
    ctx.arc(canvas.width/2, canvas.width/2, 4, 0, Math.PI * 2);
    ctx.closePath();
    ctx.fill();
}

function drawArrows(hours, minutes, seconds){
    // draw hour-arrow
    ctx.lineWidth = 3;
    ctx.beginPath();
    let hour_x = canvas.width/2 + Math.cos(Math.PI/180 * (90-30*hours))*canvas.width/2*0.6;
    let hour_y = canvas.width/2 - Math.sin(Math.PI/180 * (90-30*hours))*canvas.width/2*0.6;
    ctx.moveTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(hour_x, hour_y);
    ctx.closePath();
    ctx.stroke();

    //draw minute-arrow
    ctx.lineWidth = 2;
    ctx.beginPath();
    let minute_x = canvas.width/2 + Math.cos(Math.PI/180 * (90-6*minutes))*canvas.width/2*0.8;
    let minute_y = canvas.width/2 - Math.sin(Math.PI/180 * (90-6*minutes))*canvas.width/2*0.8;
    ctx.moveTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(minute_x, minute_y);
    ctx.closePath();
    ctx.stroke();

    // draw second-arrow
    ctx.lineWidth = 1;
    ctx.strokeStyle = "red";
    ctx.beginPath();
    let second_x = canvas.width/2 + Math.cos(Math.PI/180 * (90-6*seconds))*canvas.width/2*0.8;
    let second_y = canvas.width/2 - Math.sin(Math.PI/180 * (90-6*seconds))*canvas.width/2*0.8;
    ctx.moveTo(canvas.width/2, canvas.height/2);
    ctx.lineTo(second_x, second_y);
    ctx.closePath();
    ctx.stroke();

}

function setTime(){
    const now = new Date();

    const hours = now.getHours()%12;   // Получаем часы
    const minutes = now.getMinutes(); // Получаем минуты
    const seconds = now.getSeconds(); // Получаем секунды

    clearCanvas();
    drawClock();
    drawArrows(hours, minutes, seconds);
}

function clearCanvas(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}