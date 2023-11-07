import { defaultAll } from "./common";

export const auctionTypesFilter = [
    {
      id: defaultAll,
      label: "All",
      default: true,
    },
    {
      id: "rca2",
      label: "RCA2",
      default: false,
    },
    {
      id: "ara",
      label: "ARA1/2/3",
      default: false,
    },
    {
      id: "mra",
      label: "MRA",
      default: false,
    },
    {
      id: "annual",
      label: "Annual",
      default: false,
    },
    {
      id: "monthly",
      label: "Monthly",
      default: false,
    },
  ];