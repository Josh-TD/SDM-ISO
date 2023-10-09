import React from "react";

export default function FileList() {
  return (
    <div className="grid grid-cols-[12%,88%] grid-rows-[5%,95%]  h-full">
      {/* Left 12% for filters */}
      <div className="bg-gray-200 col-start-1 row-span-2 p-4">
        {/* Filters */}
      </div>

      {/* Top 5% for various functions */}
      <div className="bg-blue-500 col-start-2 row-start-1 p-4">
        {/* Sort, Select Multiple, Download/View */}
      </div>

      {/* Right 88% for file list */}
      <div className="bg-iso-slate col-start-2 row-start-2 p-4">
        {/* File list */}
      </div>
    </div>
  )
};


export function FileListLayout() {
  return (
    <div className="grid grid-cols-[12%,88%] grid-rows-[7%,93%]  h-full">
      {/* Left 12% for filters */}
      <div className="bg-gray-200 col-start-1 row-span-2 p-3">
        <h5>Filters</h5>
      </div>

      {/* Top 5% for various functions */}
      <div className="bg-blue-500 col-start-2 row-start-1 p-1">
        <h5>Top Bar</h5>
      </div>

      {/* Right 88% for file list */}
      <div className="bg-iso-slate col-start-2 row-start-2 p-4">
        <h5>File List</h5>
      </div>
    </div>
  );
};