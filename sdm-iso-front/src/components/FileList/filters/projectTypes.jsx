import { defaultAll } from "./common";

export const projectTypesFilter = [
    {
        id: 'new_gen',
        label: "New Generation",
        default: false,
    },
    {
        id: 'new_import',
        label: "New Import",
        default: false,
    },
    {
        id: 'increase_above',
        label: "Increase above Threshold",
        default: false,
    },
    {
        id: 'repower',
        label: "Repowering",
        default: false,
    },
    {
        id: 'env_comp',
        label: "Environmental Upgrade",
        default: false,
    },
    {
        id: 'incremental',
        label: "Incremental Capacity",
        default: false,
    },
    {
        id: 'derating',
        label: "Reestablishment",
        default: false,
    },
    {
        id: 'sig_inc',
        label: "Significant Increase",
        default: false,
    },
    {
        id: 'new_dr',
        label: "New Demand Resource",
        default: false,
    },
    {
        id: 'existing_dr',
        label: "Increase of Demand Resource",
        default: false,
    },
];