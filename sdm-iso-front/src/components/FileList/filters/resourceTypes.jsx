import { defaultAll } from "./common";

export const resourceTypesFilter = [
    {
        id: defaultAll,
        label: "All",
        default: true,
    },
    {
        id: "gen",
        label: "Generator",
        default: false,
    },
    {
        id: "dr",
        label: "Demand Resource",
        default: false,
    },
    {
        id: "import",
        label: "Import",
        default: false,
    },
];