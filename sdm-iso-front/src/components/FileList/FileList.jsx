import React, { useState } from "react";
import { FileTable } from "../FileTable/FileTable";
import { CheckBoxes } from "../Misc/CheckBoxes";
import { DateRange, dateFilter } from "../Misc/DateRange";

export default function FileList() {
  const defaultAll = "all";
  const checkBoxesToggling = (get, set) => {
    return function (obj) {
      const id = obj.target.id;

      if (get.includes(id)) {
        set(get.filter(curr => curr !== id));
      } else {
        set(get.concat(id));
      }
    };
  }

  const fileTypesFilter = [
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
  const [selectedFileTypes, setSelectedFileTypes] = useState([defaultAll]);
  const toggleFileTypes = (obj) => { return checkBoxesToggling(selectedFileTypes, setSelectedFileTypes)(obj) };

  const resourceTypesFilter = [
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
  const [selectedResourceTypes, setSelectedResourceTypes] = useState([defaultAll]);
  const toggleResouceTypes = (obj) => { return checkBoxesToggling(selectedResourceTypes, setSelectedResourceTypes)(obj) };

  const [selectedDateRange, setSelectedDateRange] = useState(defaultAll);

    const auctionTypeFilter = [
    {
      id: 'fca',
      label: "fca",
      default: true,
    },
    {
      id: "other",
      label: "Other",
      default: false,
    }
  ];



  return (
    <div className="grid grid-cols-[15%,85%] grid-rows-[7%,93%] h-full">
      <div className="col-start-1 row-span-2 pr-1">
        <div className="bg-white container w-full flex flex-col shadow-[10px_0px_8px_-8px_#a0aec0]">

          <div className="text-base font-semibold text-iso-secondary-text pl-4 pt-1 pb-16">Filtered by:</div>

          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">Date</div>
            <DateRange id="hello" onChange={setSelectedDateRange} />
          </div>

          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">Resource Type</div>
            <CheckBoxes array={resourceTypesFilter} onChange={toggleResouceTypes} />
          </div>

          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">FileType</div>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} />
          </div>

          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">AuctionType</div>
            <CheckBoxes array={fileTypesFilter} onChange={auctionTypeFilter} />
          </div>
          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">AuctionPeriod</div>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} />
          </div>
          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">CreateDate</div>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} />
          </div>
          <div className="flex items-center justify-between border-y-4 border-iso-border-light">
            <div className="grow text-base font-semibold text-iso-secondary-text pl-4 pr-6 my-4 cursor-pointer">ProjectType</div>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} />
          </div>
        </div>
      </div>

      {/* Top Bar */}
      <div className="bg-white col-start-2 row-start-1 flex items-center justify-start">

        {/* Top bar item */}
        {/* <div className="flex items-center justify-between mx-3">
          <div className="text-base font-semibold text-iso-secondary-text">Sort:&nbsp;</div>
          <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">Default</div>
          <svg 
            className="w-5 h-5 cursor-pointer pt-1"
            xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
            <path fillRule="evenodd" d="M5.23 7.21a.75.75 0 011.06.02L10 11.168l3.71-3.938a.75.75 0 111.08 1.04l-4.25 4.5a.75.75 0 01-1.08 0l-4.25-4.5a.75.75 0 01.02-1.06z" clipRule="evenodd" />
          </svg>
        </div> */}

        {/* Top bar item */}
        <div className="inline-flex items-center justify-between mx-3">
          <div className="pr-1">
            <input type="checkbox" id="selectMultiple" name="selectMultiple" value="selectMultiple"></input>
          </div>
          <label for="html" className="text-base font-semibold text-iso-secondary-text">Select Multiple</label>
        </div>

        {/* Top bar item */}
        <div className="flex items-center justify-between mx-3">
          <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">Download</div>
          <div className="text-base font-semibold text-iso-secondary-text">&nbsp;|&nbsp;</div>
          <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">View</div>
        </div>

      </div>

      {/* File List */}
      <div className="bg-iso-slate col-start-2 row-start-2 p-4">
        {/* File list */}
        <FileTable />
      </div>
    </div>
  )
};


export function FileListLayout() {
  return (
    <div className="grid grid-cols-[15%,85%] grid-rows-[5%,95%] h-full">
      {/* Left 20% for filters */}
      <div className="bg-gray-200 col-start-1 row-span-2 pr-1">
        <div className="bg-white container w-full flex flex-col shadow-[10px_0px_8px_-8px_#a0aec0]">
          <h5>Filters</h5>
        </div>
      </div>

      {/* Top 5% for various functions */}
      <div className="bg-blue-500 col-start-2 row-start-1 p-4">
        <h5>Top Bar</h5>
      </div>

      {/* Right 80% for file list */}
      <div className="bg-iso-slate col-start-2 row-start-2 p-4">
        <h5>File List</h5>
      </div>
    </div>
  );
};