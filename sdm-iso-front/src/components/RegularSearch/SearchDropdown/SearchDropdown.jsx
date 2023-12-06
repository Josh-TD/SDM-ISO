import React from 'react';

export default function SearchDropdown() {
    return (
        <div className="flex items-center w-49 space-x-2">
          <label htmlFor="search-by" className="text-sm font-light text-gray-600 whitespace-nowrap">Search by</label>
          <select id="search-by" name="search-by" className="block w-full mt-1 py-2 px-3 border focus-ring bg-white focus:border-blue-300">
            <option value="select">Select...</option>
            <option value="file-name">File Name</option>
            <option value="customer-name">Customer Name</option>
            <option value="proposal-name">Proposal Name</option>
            <option value="resource-name">Resource Name</option>
            <option value="resource-name">File Description</option>
          </select>
        </div>
    )
}
