import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import { FileList } from '../FileList/FileList';

import {UserButton } from "@clerk/clerk-react";
import {useState} from 'react';
import Popup from '../Misc/Popup';
import { AdvancedSearch } from '../AdvancedSearch/AdvancedSearch';
import SearchBar from "../RegularSearch/SearchBar/SearchBar";

export default function MainPage() {
  const [buttonPopup, setButtonPopup] = useState(false);

  const [search, usingSearch] = useState(false);
  const [searchParameters, setSearchParameters] = useState(null)

  const [advancedSearch, usingAdvancedSearch] = useState(false);
  const [advancedSearchParameters, setAdvancedSearchParameters] = useState(null)

  const saveSearchParameters = (parameterURL) => {
    usingSearch(true)
    setSearchParameters(parameterURL);
  };

  const saveAdvancedSearchParameters = (parameterURL) => {
    usingAdvancedSearch(true)
    setAdvancedSearchParameters(parameterURL);
  };

  return (
    <div className='flex flex-col h-screen'>
    <UserButton/>
      <Header />
      <PageTitleWithSearchBar />
      <button onClick = {() => setButtonPopup(true)} className="place-self-end mr-14 text-sm hover:text-iso-link-blue">Advanced Search</button>
      <main className="flex-grow overflow-visible">
        {advancedSearch ? (<FileList advancedSearchParameters={advancedSearchParameters} />) :
              search ? (<FileList searchParameters={searchParameters} />) :
                  ( <FileList />)}
      </main>
      <Popup trigger = {buttonPopup} setTrigger = {setButtonPopup}>
        <AdvancedSearch onClosePopup = {() => setButtonPopup(false)}
                        onAdvancedSearchPressed={saveAdvancedSearchParameters}/>
      </Popup>
    </div>
  )
};
