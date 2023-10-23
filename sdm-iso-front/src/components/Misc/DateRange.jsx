import React, { useState } from "react";
import { Range } from "./Range";

// not sure what to add for now
export const dateFilter = [
    {
        id: "all",
        label: "All time"
    },
    {
        id: "2y",
        label: "Past two years"
    },
    {
        id: "1y",
        label: "Past year"
    },
    {
        id: "6m",
        label: "Past six months"
    },
    {
        id: "3m",
        label: "Past three months"
    },
    {
        id: "1m",
        label: "Past month"
    },
    {
        id: "1w",
        label: "Past week"
    },
    {
        id: "1d",
        label: "Yesterday"
    },
];

export const DateRange = ({ id, onChange }) => {
    const defaultDate = dateFilter.length - 1; // All time

    const [selectedFilter, setSelectedFilter] = useState(defaultDate);

    // doing this so it looks better
    const reverseSlider = () => {
        return dateFilter.length - 1 - selectedFilter;
    }

    const localOnChange = (obj) => {
        const value = obj.target.value;
        setSelectedFilter(value);
        // make sure to use reversed value here
        onChange(dateFilter[reverseSlider()].id);
    };

    return (
        <Range id={id} label={dateFilter[reverseSlider()].label} onChange={localOnChange} min={0} max={dateFilter.length - 1} defaultValue={defaultDate} />
    )
};