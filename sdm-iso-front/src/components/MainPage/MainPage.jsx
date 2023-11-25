import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import FileList from '../FileList/FileList';
import {UserButton } from "@clerk/clerk-react";

export default function MainPage() {
  return (
    <div className='flex flex-col h-screen'>
    <UserButton/>
      <Header />
      <PageTitleWithSearchBar />
      <main className="flex-grow overflow-visible">
        <FileList />
      </main>
    </div>
  )
};
