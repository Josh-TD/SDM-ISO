import { defaultAll } from "./common";

export const fileTypesFilter = [
    {
      id: defaultAll,
      label: "All",
      default: true,
    },
    {
      id: "doc",
      label: "DOC/DOCX",
      default: false,
    },
    {
      id: "pdf",
      label: "PDF",
      default: false,
    },
    {
      id: "html",
      label: "HTML",
      default: false,
    },
    {
      id: "xls",
      label: "XLS/XLSX",
      default: false,
    },
    {
      id: "zip",
      label: "ZIP",
      default: false,
    },
  ];