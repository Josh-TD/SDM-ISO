import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import { FileList } from '../FileList/FileList';

import {UserButton } from "@clerk/clerk-react";
import {useState} from 'react';
import Popup from '../Misc/Popup';
import { AdvancedSearch } from '../AdvancedSearch/AdvancedSearch';

export default function MainPage() {
  const [buttonPopup, setButtonPopup] = useState(false);
  return (
    <div className='flex flex-col h-screen'>
    <UserButton/>
      <Header />
      <PageTitleWithSearchBar />
      <button onClick = {() => setButtonPopup(true)} className="place-self-end mr-14 text-sm hover:text-iso-link-blue">Advanced Search</button>
      <main className="flex-grow overflow-visible">
        <FileList />
      </main>
      <Popup trigger = {buttonPopup} setTrigger = {setButtonPopup}>
        <AdvancedSearch/>
      </Popup>
    </div>
  )
};
