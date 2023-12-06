import React from "react";

export function ResponseParser(newData, updateData) {
    console.log("yoo man you've had a long day but you're doing so good - peace out man")
    console.log(newData)
    console.log("go catch some waves the surf tide is quite high broski in the trenches fr fr")
    console.log("ResponseParser received data:", newData);
    updateData(newData);
}