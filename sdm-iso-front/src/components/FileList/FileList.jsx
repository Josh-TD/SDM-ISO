import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import Modal from "react-modal";
import axios from "axios";

import { FileTable } from "../FileTable/FileTable";
import { CheckBoxes } from "../Misc/CheckBoxes";
import { DropDown } from "../Misc/DropDown";

import { fileTypesFilter } from "./filters/fileTypes";
import { resourceTypesFilter } from "./filters/resourceTypes";
import { auctionTypesFilter } from "./filters/auctionTypes";
import { projectTypesFilter } from "./filters/projectTypes";

// in the future, this file  list should also takes in how many files to display

export function FileList({ searchParameters, advancedSearchParameters }) {
  // hardcoded endpoints, use .env file?
  const endpoint = "http://localhost:8080/api/v3/files/list";
  // these are the things that should pass into this file list in the future
  const pageNum = 0;
  const pageSize = 10;
  const sortBy = "createDate";
  const sortAsc = "false";

  // doing this so if we want we can do this inline instead of making it a new function
  const checkBoxesToggling = (get, set) => {
    return function (obj) {
      const id = obj.target.id.split(",");

      let res = get;

      id.forEach(e => {
        if (get.includes(e)) {
          res = res.filter(curr => curr !== e);
        } else {
          res = res.concat(e);
        }
      });

      set(res);
    };
  }

  const [selectedFileTypes, setSelectedFileTypes] = useState([]);
  const toggleFileTypes = (obj) => { return checkBoxesToggling(selectedFileTypes, setSelectedFileTypes)(obj) };

  const [selectedResourceTypes, setSelectedResourceTypes] = useState([]);
  const toggleResourceTypes = (obj) => { return checkBoxesToggling(selectedResourceTypes, setSelectedResourceTypes)(obj) };


  const [selectedCreatedDate, setSelectedCreatedDate] = useState(new Date())
  const [createdDateAny, setCreatedDateAny] = useState(true)

  const [selectedAuctionTypes, setSelectedAuctionTypes] = useState([]);
  const toggleAuctionTypes = (obj) => { return checkBoxesToggling(selectedAuctionTypes, setSelectedAuctionTypes)(obj) };

  const [selectedProjectTypes, setSelectedProjectTypes] = useState([]);
  const toggleProjectTypes = (obj) => { return checkBoxesToggling(selectedProjectTypes, setSelectedProjectTypes)(obj) };

  const [auctionDateStart, setAuctionDateStart] = useState(new Date());
  const [auctionDateEnd, setAuctionDateEnd] = useState(new Date());
  const [auctionDateAny, setAuctionDateAny] = useState(true);

  const [selectedCommitPeriod, setSelectedCommitPeriod] = useState(new String())

  const [data, setData] = useState(null);
  const [filters, usingFilters] = useState(false);

  const [currPage, setCurrPage] = useState(0)


  const [searchCurrParams, setSearchCurrParams] = useState(searchParameters);
  const [advancedSearchCurrParams, setAdvancedSearchCurrParams] = useState(advancedSearchParameters);
  const [appliedFilters, setAppliedFilters] = useState("");

  useEffect(() => {
    setAdvancedSearchCurrParams(advancedSearchParameters);
    setSearchCurrParams(searchParameters);
    console.log("param changed")
    console.log(advancedSearchParameters);
  }, [searchParameters, advancedSearchParameters])


  useEffect(() => {
    console.log('updating table')
    console.log(advancedSearchCurrParams)
    fetchFiles(0, 10, 'createDate', true);
  }, [searchParameters, advancedSearchParameters]);

  const onApplyFilters = () => {
    setCurrPage(currPage + 1)
    usingFilters(true)
    fetchFiles(0, 10, 'createDate', true)
    updateAppliedFilters()
  }
  const updateAppliedFilters = () => {
    const appliedFiltersArray = []
    if (selectedProjectTypes.length > 0) {
      appliedFiltersArray.push(`Project Type(s): ${selectedProjectTypes.join(', ')} `);
    }
    if (selectedResourceTypes.length > 0) {
      appliedFiltersArray.push(`Resource Type(s): ${selectedResourceTypes.join(', ')} `);
    }
    if (selectedAuctionTypes.length > 0) {
      appliedFiltersArray.push(`Auction Type(s): ${selectedAuctionTypes.join(', ')} `);
    }
    console.log(selectedCommitPeriod)
    if (selectedCommitPeriod.length > 0) {
      appliedFiltersArray.push(`Commitment  Period: ${selectedCommitPeriod} `);
    }
    if (selectedFileTypes.length > 0) {
      appliedFiltersArray.push(`File Type(s): ${selectedFileTypes.join(', ')} `);
    }
    setAppliedFilters(appliedFiltersArray)
    //console.log(appliedFiltersArray)
  }

  // resets states of checkboxes 
  const resetCheckboxStates = () => {
    setSelectedFileTypes([]);
    setSelectedResourceTypes([]);
    setSelectedProjectTypes([]);
    setSelectedAuctionTypes([]);
    setAdvancedSearchCurrParams("");
    setSearchCurrParams("");

  };

  const dateObjectToJavaDate = (date_obj) => {
    const binding = date_obj.toISOString();
    return binding.substring(0, binding.length - 5)
  }

  // default is fetchFiles(0,10,'createDate',true)
  const fetchFiles = (pageNum, pageSize, sortBy, sortAsc) => {
    const basic_url = endpoint + `?pageNum=${pageNum}&pageSize=${pageSize}&sortBy=${sortBy}&sortAsc=${sortAsc ? "true" : "false"}`;

    const createdSinceJava = dateObjectToJavaDate(selectedCreatedDate);
    const auctionDateStartJava = dateObjectToJavaDate(auctionDateStart);
    const auctionDateEndJava = dateObjectToJavaDate(auctionDateEnd);

    let full_url = basic_url
      + `${selectedProjectTypes.reduce((acc, e) => acc + "&projectTypes=" + e, "")}`
      + `${selectedResourceTypes.reduce((acc, e) => acc + "&resourceTypes=" + e, "")}`
      + `${selectedAuctionTypes.reduce((acc, e) => acc + "&auctionTypes=" + e, "")}`
      + `${selectedFileTypes.reduce((acc, e) => acc + "&fileTypes=" + e, "")}`
      ;

    if (!createdDateAny) {
      full_url += "&createdSince=" + createdSinceJava;
    }

    if (selectedCommitPeriod.length != 0) {
      full_url += "&commitPeriodDesc=" + selectedCommitPeriod;
    }

    if (!auctionDateAny) {
      full_url += "&aucBeginDate=" + auctionDateStartJava;
      full_url += "&aucEndDate=" + auctionDateEndJava;
    }

    if (searchCurrParams != null) {
      console.log("im search")
      full_url = full_url + searchCurrParams;
    }
    if (advancedSearchCurrParams != null) {
      console.log("im advanced")
      full_url = full_url + advancedSearchCurrParams;
    }
    console.log(full_url)

    axios.get(full_url).then((res) => {
      setData(
        res.data
      );
    }).catch(err => {
      if (err.response && err.response.status === 404) {
        setData(undefined);
      } else {
        console.log(err);
      }
    })
  };

  const fetchUnfiltered = () => {
    setAppliedFilters([])
    resetCheckboxStates();
    setCurrPage(currPage + 1);
    setCreatedDateAny(true);
    setSelectedCreatedDate(new Date());
    setAuctionDateStart(new Date());
    setAuctionDateEnd(new Date());
    setSelectedCommitPeriod("");
    setAuctionDateAny(true);

    const basic_url = endpoint + `?pageNum=${pageNum}&pageSize=${pageSize}&sortBy=${sortBy}&sortAsc=${sortAsc ? "true" : "false"}`;
    axios.get(basic_url).then((res) => {
      setData(
        res.data
      );
    })
  }

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

      </Modal>
      <div className="col-start-1 row-span-2 pr-1">
        <div className="bg-white container w-full flex flex-col shadow-[10px_0px_8px_-8px_#a0aec0]">

          <div className="text-base font-semibold text-iso-secondary-text pl-4 pt-1 pb-16">Filtered by:
            <p className="text-sm text-gray-600">{appliedFilters}</p>
          </div>

          <DropDown label="Project Type" defaultHidden={true}>
            <CheckBoxes array={projectTypesFilter} onChange={toggleProjectTypes} selectedElem={selectedProjectTypes} />
          </DropDown>

          <DropDown label="Resource Type" defaultHidden={true}>
            <CheckBoxes array={resourceTypesFilter} onChange={toggleResourceTypes} selectedElem={selectedResourceTypes} />
          </DropDown>

          <DropDown label="Auction Type" defaultHidden={true}>
            <CheckBoxes array={auctionTypesFilter} onChange={toggleAuctionTypes} selectedElem={selectedAuctionTypes} />
          </DropDown>

          <DropDown label="Auction Period" defaultHidden={true}>
            <legend>Start date:</legend>
            <DatePicker selected={auctionDateStart} onChange={(date) => { setAuctionDateStart(date); setAuctionDateAny(false) }} />
            <legend>End date:</legend>
            <DatePicker selected={auctionDateEnd} onChange={(date) => { setAuctionDateEnd(date); setAuctionDateAny(false) }} />
          </DropDown>

          <DropDown label="Commitment Period" defaultHidden={true}>
            <select value={selectedCommitPeriod} id="commitment-period" onChange={(e) => setSelectedCommitPeriod(e.target.value)} className="block w-full mt-1 py-2 px-3 border focus-ring bg-white focus:border-blue-300">
              <option value="select">Select...</option>
              <option value="2010-11">2010-11</option>
              <option value="2012-13">2012-13</option>
              <option value="2014-15">2014-15</option>
              <option value="2016-17">2016-17</option>
              <option value="2018-19">2018-19</option>
              <option value="2020-21">2020-21</option>
              <option value="2022-23">2022-23</option>
            </select>
          </DropDown>

          <DropDown label="Created Date" defaultHidden={true}>
            <DatePicker selected={selectedCreatedDate} onChange={(date) => { setSelectedCreatedDate(date); setCreatedDateAny(false) }} />
          </DropDown>

          <DropDown label="File Type" defaultHidden={true}>
            <CheckBoxes array={fileTypesFilter} onChange={toggleFileTypes} selectedElem={selectedFileTypes} />
          </DropDown>

          <div className="inline-flex mt-5 pl-5 mb-5 items-left">
            <button className="bg-iso-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 mx-1 rounded cursor-pointer float-right" onClick={() => { onApplyFilters() }}>Apply Filters</button>
            <button className="bg-iso-slate hover:bg-iso-link-blue text-white text-sm font-semibold py-2 px-4 mx-1 rounded cursor-pointer float-right" onClick={() => { fetchUnfiltered() }}>Clear Filters</button>
          </div>
        </div>
      </div>
      {/* File List */}
      <div className="bg-white col-start-2 row-start-1 p-4">
        {/* File list */}
        <div className="width: 100% height: 100%">
          {data ? <FileTable data={data} fetchFunction={fetchFiles} pageNum={currPage} /> : <b className="text-center text-4xl">No files to display.</b>}
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