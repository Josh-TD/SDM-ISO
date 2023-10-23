import React from "react";

export const Range = ({ id, label, min, max, defaultValue, onChange }) => {
    return (
        <label>
            <input type="range" id={id} min={min} max={max} onChange={onChange} dir="rtl" defaultValue={defaultValue} />
            {label}
        </label>
    )
}