import React from "react";
import SearchBar from "../SearchBar/SearchBar";

export function PageTitle() {
  return (
    <div className="bg-iso-offwhite w-full">
      <div className="container pl-10 flex items-center justify-between w-full">
        <div className="grow py-4 text-3xl">
          SDM File Manager
        </div>
      </div> 
    </div> 
  )
};

export function PageTitleWithSearchBar() {
  return (
    <div className="bg-iso-offwhite w-full">
      <div className="container pl-10 flex items-center justify-between w-full ">
        <div className="grow py-4 text-3xl">
          SDM File Manager
        </div>
        <SearchBar />
      </div> 
    </div> 
  )
};