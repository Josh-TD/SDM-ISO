import React from 'react'

export function AdvancedSearch(){
  return (
        <div className="w-full">
          <p className="text-xl pb-2 text-iso-dark-text">Advanced Search</p>
          <div className="flex justify-end py-2 pr-4">
            <label className="text-iso-dim-gray justify-right font-regular pr-2" for="file_name">File Name:</label>
            <input className="placeholder:text-iso-light-gray placeholder:font-light border border-iso-border-light shadow cursor-text h-7 w-1/2 pl-2" placeholder="File Name"/>
          </div>

          <div className="flex justify-end py-2 pr-4">
            <label className="text-iso-dim-gray justify-right font-regular pr-2" for="customer_name">Customer Name:</label>
            <input className="placeholder:text-iso-light-gray placeholder:font-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2" placeholder="Customer Name"/>
          </div>

          <div className="flex justify-end py-2 pr-4">
            <label className="text-iso-dim-gray justify-right font-regular pr-2" for="proposal_name">Proposal Name:</label>
            <input className="placeholder:text-iso-light-gray placeholder:font-light text-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2" placeholder="Proposal Name"/>
          </div>

          <div className="flex justify-end py-2 pr-4">
            <label className="text-iso-dim-gray justify-right font-regular pr-2" for="project_name">Project Name:</label>
            <input className="placeholder:text-iso-light-gray placeholder:font-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2" placeholder="Project Name"/>
          </div>

          <div className = "flex w-full justify-end">
            <button className="mr-6 mt-2 bg-iso-light-slate hover:bg-iso-link-blue text-white font-semibold py-1 px-5 rounded cursor-pointer justify-self-end">Search</button>
          </div>
        </div>
  )
};

export default AdvancedSearch;