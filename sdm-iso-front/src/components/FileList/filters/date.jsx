import React, { useEffect, useState } from "react";

// not sure what to add for now
export const createdDateSliderFilters = [
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

export const getFilterDateFormat = (date_id) => {
    const days_to_msec = (days) => days * 24 * 60 * 60 * 1000;

    if (date_id === "all") {
        const d = new Date(0);
        return d.toISOString();
    } else if (date_id === "2y") {
        const d = new Date(new Date().getTime() - days_to_msec(365 * 2));
        return d.toISOString();
    } else if (date_id === "1y") {
        const d = new Date(new Date().getTime() - days_to_msec(365));
        return d.toISOString();
    } else if (date_id === "6m") {
        const d = new Date(new Date().getTime() - days_to_msec(30 * 6));
        return d.toISOString();
    } else if (date_id === "3m") {
        const d = new Date(new Date().getTime() - days_to_msec(30 * 3));
        return d.toISOString();
    } else if (date_id === "1m") {
        const d = new Date(new Date().getTime() - days_to_msec(30));
        return d.toISOString();
    } else if (date_id === "1w") {
        const d = new Date(new Date().getTime() - days_to_msec(7));
        return d.toISOString();
    } else if (date_id === "1d") {
        const d = new Date(new Date().getTime() - days_to_msec(1));
        return d.toISOString();
    }
}

export const CreatedDateSlider = ({ id, onChange }) => {
    const defaultDate = createdDateSliderFilters.length - 1; // All time

    const [selectedFilter, setSelectedFilter] = useState(defaultDate);

    // doing this so it looks better
    const reverseSlider = () => {
        return createdDateSliderFilters.length - 1 - selectedFilter;
    }

    const localOnChange = (obj) => {
        setSelectedFilter(obj.target.value);
    };

    // due to async
    useEffect(() => {
        onChange(createdDateSliderFilters[reverseSlider()].id);
    }, [selectedFilter])

    return (
        <div>
            <legend>{createdDateSliderFilters[reverseSlider()].label}</legend>
            <input className="w-full" id={id} type="range" onChange={localOnChange} min={0} max={createdDateSliderFilters.length - 1} defaultValue={defaultDate} dir="rtl" />
        </div>
    )
};