import React, { useState } from "react";
import DatePicker from "react-datepicker";
import Modal from "react-modal";

import { FileTable } from "../FileTable/FileTable";
import { CheckBox } from "../Misc/CheckBox";
import { CheckBoxes } from "../Misc/CheckBoxes";
import { DropDown } from "../Misc/DropDown";
import { FileViewer } from "../FileViewer/FileViewer";

import { defaultAll } from "./filters/common";
import { fileTypesFilter } from "./filters/fileTypes";
import { resourceTypesFilter } from "./filters/resourceTypes";
import { auctionTypesFilter } from "./filters/auctionTypes";
import { projectTypesFilter } from "./filters/projectTypes";
import { CreatedDateSlider } from "./filters/date";

export default function FileList() {

  // doing this so if we want we can do this inline instead of making it a new function
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

  const [selectedFileTypes, setSelectedFileTypes] = useState([defaultAll]);
  const toggleFileTypes = (obj) => { return checkBoxesToggling(selectedFileTypes, setSelectedFileTypes)(obj) };

  const [selectedResourceTypes, setSelectedResourceTypes] = useState([defaultAll]);
  const toggleResouceTypes = (obj) => { return checkBoxesToggling(selectedResourceTypes, setSelectedResourceTypes)(obj) };

  const [selectedDateRange, setSelectedDateRange] = useState(defaultAll);

  const [selectedAuctionTypes, setSelectedAuctionTypes] = useState([defaultAll]);
  const toggleAuctionTypes = (obj) => { return checkBoxesToggling(selectedAuctionTypes, setSelectedAuctionTypes)(obj) };

  const [selectedProjectTypes, setSelectedProjectTypes] = useState([defaultAll]);
  const toggleProjectTypes = (obj) => { return checkBoxesToggling(selectedProjectTypes, setSelectedProjectTypes)(obj) };

  const [auctionDateStart, setAuctionDateStart] = useState(new Date());
  const [auctionDateEnd, setAuctionDateEnd] = useState(new Date());
  const [auctionDateAny, setAuctionDateAny] = useState(true);

  const [modalIsOpen, setIsOpen] = useState(false);
  const modalStyles = {
    content: {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      marginRight: '-50%',
      transform: 'translate(-50%, -50%)',
    },
  };

  return (
    <div className="grid grid-cols-[15%,85%] grid-rows-[7%,93%] h-full">
      <Modal
        isOpen={modalIsOpen}
        onAfterOpen={() => setIsOpen(true)}
        onRequestClose={() => setIsOpen(false)}
        style={modalStyles}
        contentLabel="Example Modal"
      >
        <FileViewer filename="dummy" />

      </Modal>
      <div className="col-start-1 row-span-2 pr-1">
        <div className="bg-white container w-full flex flex-col shadow-[10px_0px_8px_-8px_#a0aec0]">

          <div className="text-base font-semibold text-iso-secondary-text pl-4 pt-1 pb-16">Filtered by:</div>

          <DropDown label="Project Type" defaultHidden={true}>
            <CheckBoxes array={projectTypesFilter} onChange={toggleProjectTypes} />
          </DropDown>

          <DropDown label="Resource Type" defaultHidden={true}>
            <CheckBoxes array={resourceTypesFilter} onChange={toggleResouceTypes} />
          </DropDown>

          <DropDown label="Auction Type" defaultHidden={true}>
            <CheckBoxes array={auctionTypesFilter} onChange={toggleAuctionTypes} />
          </DropDown>

          <DropDown label="Auction Period" defaultHidden={true}>
            <legend>Start date:</legend>
            <DatePicker selected={auctionDateStart} onChange={(date) => { setAuctionDateStart(date); setAuctionDateAny(false) }} />
            <legend>End date:</legend>
            <DatePicker selected={auctionDateEnd} onChange={(date) => { setAuctionDateEnd(date); setAuctionDateAny(false) }} />
          </DropDown>

          <DropDown label="Created Date" defaultHidden={true}>
            <CreatedDateSlider id="hello" onChange={setSelectedDateRange} />
          </DropDown>

          <DropDown label="File Type" defaultHidden={true}>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} />
          </DropDown>

          <div className="inline-flex mt-5 pl-5 items-left">
            <button className="bg-iso-light-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 mr-2 rounded cursor-pointer float-right">Apply Filters</button>
            <button className="bg-iso-light-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 rounded cursor-pointer float-right">Clear Filters</button>

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
            <input type="checkbox" id="selectAll" name="selectAll" value="selectAll"></input>
          </div>
          <label for="html" className="text-base font-semibold text-iso-secondary-text">Select All</label>
        </div>

        {/* Top bar item */}
        <div className="flex items-center justify-between mx-3">
          <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">Download</div>
          <div className="text-base font-semibold text-iso-secondary-text">&nbsp;|&nbsp;</div>
          <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">View</div>
        </div>
      </div>

      {/* File List */}
      <div className="bg-white col-start-2 row-start-2 p-4">
        {/* File list */}
        <div className="width: 100% height: 100%">
          <FileTable />
        </div>
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