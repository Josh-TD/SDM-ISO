import React from "react";

export const CheckBox = ({ id, label, defaultChecked, onChange }) => {
    return (
        <label>
            <input
                type="checkbox"
                onChange={onChange}
                id={id}
                defaultChecked={defaultChecked}
            />
            {label}
        </label>
    )
}