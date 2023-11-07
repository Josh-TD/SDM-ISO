import { defaultAll } from "./common";

export const auctionTypesFilter = [
    {
      id: defaultAll,
      label: "All",
      default: true,
    },
    {
      id: "fca",
      label: "FCA",
      default: false,
    },
    {
      id: "other",
      label: "Other",
      default: false,
    }
  ];