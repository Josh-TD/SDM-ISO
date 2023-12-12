import React, { useState } from 'react';

const Search = ({onSearchPressed}) => {
    const [searchText, setSearchText] = useState('');
    const [selectedOption, setSelectedOption] = useState('');

    const constructEndpoint = (searchText, selectedOption) => {
        let parameterURL = ''
        if (selectedOption !== '') {
            if (selectedOption === 'Customer Name'){
                parameterURL = parameterURL + `${"&customerName=" + searchText}`
            }
            if (selectedOption === 'Proposal Name'){
                parameterURL = parameterURL + `${"&proposalName=" + searchText}`
            }
            if (selectedOption === 'Project Name'){
                parameterURL = parameterURL + `${"&projectName=" + searchText}`
            }
            if (selectedOption === 'File Description'){
                parameterURL = parameterURL + `${"&fileDescription=" + searchText}`
            }
        }
        onSearchPressed(parameterURL)
    };

    const performSearch = () => {
        constructEndpoint(searchText, selectedOption);
    }

    return (
    <div className="flex items-center w-49 space-x-2">
        <label htmlFor="search-by" className="text-sm font-light text-gray-600 whitespace-nowrap">Search by</label>
        <select
            id="search-by"
            name="search-by"
            onChange={(e) => setSelectedOption(e.target.value)}
            value={selectedOption}
            className="block w-full mt-1 py-2 px-3 border focus-ring bg-white focus:border-blue-300">
            <option value="select">Select...</option>
            <option value="Customer Name">Customer Name</option>
            <option value="Proposal Name">Proposal Name</option>
            <option value="Project Name">Project Name</option>
            <option value="File Description">File Description</option>
        </select>
        <div className='mr-4 flex items-center justify-between bg-white border-2 border-iso-border-light'>
            <input
                className='py-2 pl-4 mr-3'
                type="text"
                aria-label="search input form"
                placeholder="SEARCH"
                value={searchText}
                onChange={(e) => setSearchText(e.target.value)}/>
            <svg className="w-6 h-6 mr-4 cursor-pointer"/>
            <svg
                className="w-6 h-6 mr-4 cursor-pointer"
                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" onClick={performSearch} aria-label="search submit button">
                <path strokeLinecap="round" strokeLinejoin="round" d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"/>
            </svg>
        </div>
    </div>
    );
};

export default Search;