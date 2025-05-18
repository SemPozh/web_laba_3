"use strict";

const canvas = document.getElementById("figureCanvas");
canvas.width = document.getElementById("frame").offsetWidth;
canvas.height = document.getElementById("frame").offsetHeight;
const intervalSize = canvas.height / 10;
const ctx = canvas.getContext("2d");
const rNotSelectedError = document.getElementById("rNotSelectedError");
const incorrectFormError = document.getElementById("Incorrect");
const submitButton = document.getElementById("form:submitButton");
const yInput = document.getElementById("form:y-inp");
const rRadioButtons = document.querySelectorAll("input[type='radio']");

const hiddenX = document.getElementById("form:hiddenX");
const hiddenY = document.getElementById("form:hiddenY");
const hiddenR = document.getElementById("form:hiddenR");
const hiddenByClick = document.getElementById("form:hiddenByClick");
const dataTableShots = document.querySelectorAll("#results tbody tr");

hiddenByClick.checked = false;
drawCoordinateLines();
rRadioButtons.forEach(radio=>{
    radio.addEventListener("change", ()=>{

        if (radio.checked){
            hiddenR.value = radio.value;
            drawArea(radio.value);
            let shots_with_r = parseShotsWithR(Number(radio.value));
            shots_with_r.forEach(shot=>{
                drawPoint(shot.x, shot.y, shot.result);
            });
        }
    });
    if (radio.checked){
        hiddenR.value = radio.value;
        drawArea(radio.value);
        let shots_with_r = parseShotsWithR(Number(radio.value));
        shots_with_r.forEach(shot=>{
            drawPoint(shot.x, shot.y, shot.result);
        });
    }
});

yInput.addEventListener("input", ()=>{
   validateYInput();
   if (checkY(yInput.value)){
       submitButton.disabled = false;
       hiddenY.value = yInput.value.slice(0, Math.min(6, yInput.value.length));
   } else {
       submitButton.disabled = true;
   }
});
canvas.addEventListener("click", (e) => {
    const rect = canvas.getBoundingClientRect();
    let x = e.clientX - rect.left;
    let y = e.clientY - rect.top;
    let xMapped = (x - canvas.width / 2) / intervalSize;
    let yMapped = (canvas.height / 2 - y) / intervalSize;
    let current_radius = document.querySelectorAll("input[type='radio']:checked");
    if (current_radius.length!==0) {
        incorrectFormError.style.display = "none";
        rNotSelectedError.style.display = "none";

        hiddenX.value = xMapped;
        hiddenY.value = yMapped;
        hiddenR.value = current_radius[0].value;
        hiddenByClick.checked = true;
        submitButton.click();
    } else {
        incorrectFormError.style.display = "none";
        rNotSelectedError.style.display = "block";
    }
});

function parseShotsWithR(radius){
    let shots_with_r = [];
    dataTableShots.forEach(shot => {
        let data = shot.getElementsByTagName("td");
        let x = data[0].textContent;
        let y = data[1].textContent;
        let r = data[2].textContent;
        let result = data[3].textContent;
        if (Number(r)===radius){
            shots_with_r.push({"x":Number(x), "y":Number(y), "r":Number(r), "result":result.trim()==="true"});
        }
    });
    return shots_with_r;
}
