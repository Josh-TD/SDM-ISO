import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import FileList, { FileListLayout } from '../FileList/FileList';


const MainPage = () => {
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitleWithSearchBar />
      <main className="flex-grow overflow-visible">
        <FileListLayout />
      </main>
    </div>
  );
};

export default MainPage;
