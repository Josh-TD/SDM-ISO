import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import FileList, { FileListLayout } from '../FileList/FileList';
import AttachmentFileList from '../FileTable/AttachmentFileList';

export default function MainPage() {
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitleWithSearchBar />
      <main className="flex-grow overflow-visible">
        <FileList />
          <AttachmentFileList />
      </main>
    </div>
  )
};
