import React from "react";

const FileList = () => {
  return (
    <div className="grid grid-rows-[0%,95%] grid-cols-[12%,88%] h-full">
      {/* Top 5% for functions */}
      <div className="bg-blue-500 row-span-1 col-span-2 p-4">
        {/* Sort, Select Multiple, Download/View */}
      </div>

      {/* Left 12% for filters */}
      <div className="bg-gray-200 row-span-2 p-4">
        {/* Filters */}
      </div>

      {/* Right 88% for file list */}
      <div className="bg-gray-300 row-span-2 col-span-2 p-4">
        {/* File list */}
      </div>
    </div>
  );

};
export default FileList;



export const FileListLayout = () => {
  return (
    <div className="grid grid-rows-[0%,95%] grid-cols-[12%,88%] h-full">
      {/* Top 5% for functions */}
      <div className="bg-blue-500 row-span-1 col-span-2 p-4">
        {/* Sort, Select Multiple, Download/View */}
      </div>

      {/* Left 12% for filters */}
      <div className="bg-gray-200 row-span-2 p-4">
        {/* Filters */}
      </div>

      {/* Right 88% for file list */}
      <div className="bg-gray-300 row-span-2 col-span-2 p-4">
        {/* File list */}
      </div>
    </div>
  );
};