import React from 'react'

export function AdvancedSearch(){
  return (
        <div className="w-full">
          <h1>Advanced Search</h1>
          <div>
            <label className="m-2 pt-4 pl-8 text-iso-dim-gray justify-right font-regular" for="file_name">File Name:</label>
            <input className="placeholder-color-iso-light-gray shadow cursor-text h-7 w-1/2 pl-2" placeholder="File Name"/>
          </div>

          <div>
            <label className="m-2 pt-4 pl-8 text-iso-dim-gray justify-right font-regular" for="customer_name">Customer Name:</label>
            <input className="placeholder-color-iso-light-gray pl-2 shadow cursor-text h-7 w-1/2" placeholder="Customer Name"/>
          </div>

          <div>
            <label className="m-2 pt-4 pl-8 text-iso-dim-gray justify-right font-regular" for="proposal_name">Proposal Name:</label>
            <input className="placeholder-color-iso-light-gray pl-2 shadow cursor-text h-7 w-1/2" placeholder="Proposal Name"/>
          </div>

          <div>
            <label className="m-2 pt-4 pl-8 text-iso-dim-gray justify-right font-regular" for="project_name">Project Name:</label>
            <input className="placeholder-color-iso-light-gray pl-2 shadow cursor-text h-7 w-1/2" placeholder="Project Name"/>
          </div>

          <div className = "inline-flex mt-5 pl-10 items-right">
            <button className="bg-iso-light-slate hover:bg-iso-link-blue text-white font-semibold py-2 px-4 rounded cursor-pointer float-right">Search</button>
          </div>
        </div>
  )
};

export default AdvancedSearch;