import React from 'react';

export default function SearchDropdown(setSelectedParam) {
  const handleSelectChange = (e) => {
    setSelectedParam(e.target.value);
  };
    return (
        <div className="flex items-center w-49 space-x-2">
          <label htmlFor="search-by" className="text-sm font-light text-gray-600 whitespace-nowrap">Search by</label>
          <select 
          id="search-by" 
          name="search-by" 
          onChange={handleSelectChange}
          className="block w-full mt-1 py-2 px-3 border focus-ring bg-white focus:border-blue-300">
            <option value="select">Select...</option>
            <option value="customer-name">Customer Name</option>
            <option value="proposal-name">Proposal Name</option>
            <option value="project-name">Project Name</option>
            <option value="file-description">File Description</option>
          </select>
        </div>
    )
}
