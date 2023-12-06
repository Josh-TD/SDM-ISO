import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import Modal from "react-modal";
import axios from "axios";

import { FileTable } from "../FileTable/FileTable";
import { CheckBoxes } from "../Misc/CheckBoxes";
import { DropDown } from "../Misc/DropDown";
import { FileViewer } from "../FileViewer/FileViewer";

import { fileTypesFilter } from "./filters/fileTypes";
import { resourceTypesFilter } from "./filters/resourceTypes";
import { auctionTypesFilter } from "./filters/auctionTypes";
import { projectTypesFilter } from "./filters/projectTypes";
import { CreatedDateSlider, getFilterDateFormat } from "./filters/date";

// in the future, this file  list should also takes in how many files to display
// there are three types of data that can be passed into FileList
// one is when the user applies filters, the second is when they search normally, and third when they do adv search
// in main there are callback functions which will execute when the user does something so only then
// will the fileList recieve one or multiple of these parameters which we can handle in useEffect
export const FileList = ({filterData, searchData, advancedSearchData}) => {
  // hardcoded endpoints, use .env file?
  const _endpoint = "http://localhost:8080/api";
  const endpoint = _endpoint + "/v3/files/list";
  // these are the things that should pass into this file list in the future
  const pageNum = 0;
  const pageSize = 10;
  const sortBy = "createDate";
  const sortAsc = "false";

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

  const [selectedFileTypes, setSelectedFileTypes] = useState([]);
  const toggleFileTypes = (obj) => { return checkBoxesToggling(selectedFileTypes, setSelectedFileTypes)(obj) };

  const [selectedResourceTypes, setSelectedResourceTypes] = useState([]);
  const toggleResouceTypes = (obj) => { return checkBoxesToggling(selectedResourceTypes, setSelectedResourceTypes)(obj) };

  const [selectedDateRange, setSelectedDateRange] = useState("all");

  const [selectedAuctionTypes, setSelectedAuctionTypes] = useState([]);
  const toggleAuctionTypes = (obj) => { return checkBoxesToggling(selectedAuctionTypes, setSelectedAuctionTypes)(obj) };

  const [selectedProjectTypes, setSelectedProjectTypes] = useState([]);
  const toggleProjectTypes = (obj) => { return checkBoxesToggling(selectedProjectTypes, setSelectedProjectTypes)(obj) };

  const [auctionDateStart, setAuctionDateStart] = useState(new Date());
  const [auctionDateEnd, setAuctionDateEnd] = useState(new Date());
  const [auctionDateAny, setAuctionDateAny] = useState(true);

  const [data, setData] = useState(null);

  // TODO: Maybe abstract this since the if-else's are redundant
  // default to fetch data by itself, maybe done in outer layer if possible
  useEffect(() => {
    if (filterData) {
      // when the user wants to search something normally
      console.log("This is filtering reporting for duty")
      setData(filterData)
    }
    else if (searchData) {
      // when the user wants to search something normally
      console.log("This is regular search reporting for duty")
      setData(searchData)
    }
    else if (advancedSearchData != null) {
      console.log("This is advanced search reporting for duty")
      console.log(advancedSearchData)
      setData(advancedSearchData)
    }
    // TODO: Add in the cases when there are a combination of these data inputs
        // when the user wants to search and filter or filter and advanced search, etc.
    else {
      // this is the default call that shows files initally before
      // the user interacts with the application
      fetchFiles();
    }
  }, [filterData, searchData, advancedSearchData]);

  const fetchFiles = () => {
    // not enough contents in the entry so we need more for proper filtering
    const basic_url = endpoint + `?pageNum=${pageNum}&pageSize=${pageSize}&sortBy=${sortBy}&sortAsc=${sortAsc ? "true" : "false"}`;

    const isoDate = getFilterDateFormat(selectedDateRange);
    const javaDate = isoDate.substring(0, isoDate.length - 5);

    const full_url = basic_url
      + `${selectedProjectTypes.reduce((acc, e) => acc + "&projectTypes=" + e, "")}`
      + `${selectedResourceTypes.reduce((acc, e) => acc + "&resourceTypes=" + e, "")}`
      // there is no auction type??
      // + `${auctionTypesFilter.reduce((acc, e) => acc + "&auctionTypes=" + e, "")}`
      + `${selectedFileTypes.reduce((acc, e) => acc + "&fileTypes=" + e, "")}`
      + `&createdSince=${javaDate}`
      ;

    axios.get(full_url).then((res) => {
      setData(
        res.data.content
      );
    })
  };

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
            <button className="bg-iso-light-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 rounded cursor-pointer float-right" onClick={() => { fetchFiles() }}>Apply Filters</button>
            <button className="bg-iso-light-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 rounded cursor-pointer float-right">Clear Filters</button>
          </div>

        </div>
      </div>
      {/* File List */}
      <div className="bg-white col-start-2 row-start-1 p-4">
        {/* File list */}
        <div className="width: 100% height: 100%">
          {data && <FileTable data={data}/>}
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