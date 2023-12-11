import React, { useState } from "react";

export default function SearchBar({onSearchPressed}) {
  const [input, setInput] = useState('');
  const [selectedParam, setSelectedParam] = useState('');
  
  const handleChange = (e) => {
    console.log("the input is " + e.target.value);
    setInput(e.target.value);
  };

  const constructEndpoint = (selectedParam, input) => {
    let parameterURL = ''
    console.log("selected param = " + selectedParam);
    if (selectedParam == "customer-name"){
      parameterURL = parameterURL + `${"&customerName=" + input}`
    }
    if (selectedParam == "proposal-name"){
      parameterURL = parameterURL + `${"&proposalName=" + input}`
    }
    if (selectedParam == "project-name"){
      parameterURL = parameterURL + `${"&projectName=" + input}`
    }
    if (selectedParam == "file-description"){
      parameterURL = parameterURL + `${"&fileDescription=" + input}`
    }
    console.log("the endpoint has been created: " + parameterURL);
    onSearchPressed(parameterURL)
};

  const performSearch = () => {
    console.log("performing the search");
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
      />
      <svg 
        className="w-6 h-6 mr-4 cursor-pointer"
        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" onClick={performSearch} aria-label="search submit button"
        >
        <path strokeLinecap="round" strokeLinejoin="round" d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" 
      />
      </svg>
    </div>
  )
};