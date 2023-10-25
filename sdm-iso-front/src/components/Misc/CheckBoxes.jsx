import React from "react";
import { CheckBox } from "./CheckBox";

export const CheckBoxes = ({ array, onChange }) => {
    return (
        <fieldset>
            {array.map(obj => {
                return <div key={obj.id}>
                    <CheckBox id={obj.id} label={obj.label} defaultChecked={obj.default} onChange={onChange} />
                    <br />
                </div>
            })}
        </fieldset>
    )
}