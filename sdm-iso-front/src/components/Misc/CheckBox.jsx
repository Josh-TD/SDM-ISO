import React from "react";

export const CheckBox = ({ id, label, defaultChecked, onChange, checked }) => {
    return (
        <label>
            <input
                type="checkbox"
                onChange={onChange}
                id={id}
                defaultChecked={defaultChecked}
                checked={checked}
            />
            {label}
        </label>
    )
}