import React from "react";
import { CheckBox } from "./CheckBox";

export const CheckBoxes = ({ array, onChange, selectedElem }) => {

    const checkElem = (id) => {
        if (typeof(id) == 'string'){
            return selectedElem.includes(id);
        }
        return id.reduce((acc,e) => selectedElem.includes(e) && acc, true)
    };
    return (
        <fieldset>
            {array.map(obj => (
                <div key={obj.id}>
                    <CheckBox id={obj.id} label={obj.label} defaultChecked={obj.default} onChange={onChange} checked={selectedElem ? checkElem(obj.id) : false} />
                    <br />
                </div>
            ))}
      </fieldset>
    )
}