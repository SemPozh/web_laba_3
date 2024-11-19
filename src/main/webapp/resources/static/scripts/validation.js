"use strict";

function validateYInput(){
    if (yInput.value === ""){
        yInput.classList.remove("incorrect");
        return
    }

    if (checkY(yInput.value)){
        yInput.classList.remove("incorrect");
    } else {
        yInput.classList.add("incorrect");
    }
}
function checkR(rString){
    const regex = /^(1|1\.5|2|2\.5|3)$/;
    // Matches a number in set 1, 1.5, 2, 2.5, 3
    // and may have an optional decimal part.
    // Examples: "1.2", "2.1", "4", "3.123", "2.1234567890123456" (invalid)
    // Examples: "2", "1.5" (valid)
    return regex.test(rString)
}

function checkY(yString){
    const regex = /^(([0-2](\.\d+)?)|(-[0-4](\.\d+)?))$/;
    // Matches a number between -5 and 3, not inclusive,
    // and may have an optional decimal part.
    // Examples: "0", "1.421412", "2", "-4.9999", "-3.1", "2.1234567890123456" (valid)
    // Examples: "3", "-5.12345678901234567" (invalid)
    return regex.test(yString);
}