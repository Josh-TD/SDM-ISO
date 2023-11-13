import React from "react";
import SearchBar from "../SearchBar/SearchBar";
import SearchDropdown from "../SearchDropdown/SearchDropdown";

export function PageTitle() {
  return (
    <div className="bg-iso-offwhite w-full">
      <div className="container pl-10 flex items-center justify-between w-full font-light text-iso-slate">
        <div className="grow py-4 text-5xl">SDM File Manager</div>
      </div> 
    </div> 
  )
};

export function PageTitleWithSearchBar() {
  return (
    <div className="bg-iso-offwhite w-full">
      <div className="pr-10 pl-10 flex items-center justify-between w-full font-light text-iso-slate">
        <div className="grow py-4 text-5xl">SDM File Manager</div>
        <SearchDropdown />
        <SearchBar />
      </div> 
    </div> 
  )
};