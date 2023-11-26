import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import FileList from '../FileList/FileList';
import {useState} from 'react';
import Popup from '../Misc/Popup';

export default function MainPage() {
  const [buttonPopup, setButtonPopup] = useState(false);
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitleWithSearchBar />
      <main className="flex-grow overflow-visible">
        <button onClick = {() => setButtonPopup(true)} >Advanced Search</button>
        <FileList />
      </main>
      <Popup trigger = {buttonPopup} setTrigger = {setButtonPopup}>
        <h1>Advanced Search</h1>
      </Popup>
    </div>
  )
};
