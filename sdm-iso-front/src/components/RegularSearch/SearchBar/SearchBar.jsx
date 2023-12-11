import React, { useState } from "react";

export default function SearchBar({onSearchPressed}) {
  const [input, setInput] = useState('');
  const [selectedParam, setSelectedParam] = useState('');
  
  const handleChange = (e) => {
    setInput(e.target.value);
  };

  const constructEndpoint = (selectedParam, input) => {
    let parameterURL = ''
    if (selectedParam.equals("customer-name")){
      parameterURL = parameterURL + `${"&customerName=" + input}`
    }
    if (selectedParam.equals("proposal-name")){
      parameterURL = parameterURL + `${"&proposalName=" + input}`
    }
    if (selectedParam.equals("project-name")){
      parameterURL = parameterURL + `${"&projectName=" + input}`
    }
    if (selectedParam.equals("file-description")){
      parameterURL = parameterURL + `${"&fileDescription=" + input}`
    }
    onSearchPressed(parameterURL)
};

  const performSearch = () => {
    constructEndpoint(selectedParam, input);
  }

  return(
    <div className='mr-4 flex items-center justify-between bg-white border-2 border-iso-border-light'>
      <input
        className='py-2 pl-4 mr-3'
        type="text"
        aria-label="search input form"
        placeholder="SEARCH"
        value={input}
        onChange={handleChange}
      />
      <svg 
        className="w-6 h-6 mr-4 cursor-pointer"
        aria-label="search submit button"
        onClick={performSearch}
      />
      <svg 
        className="w-6 h-6 mr-4 cursor-pointer"
        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor">
        <path strokeLinecap="round" strokeLinejoin="round" d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" 
      />
      </svg>
    </div>
  )
};