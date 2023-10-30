import React from 'react';
import Header from '../Header/Header';
import { PageTitleWithSearchBar } from '../PageTitle/PageTitle';
import FileList, { FileListLayout } from '../FileList/FileList';
import AttachmentFileList from '../AttachmentFileList';
import AttachmentFileViewer from '../AttachmentFileViewer';

export default function MainPage() {
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitleWithSearchBar />
      <main className="flex-grow overflow-visible">
        <FileList />
      </main>
        <AttachmentFileList> This is the list of files </AttachmentFileList>
        <AttachmentFileViewer> This is the File Viewer </AttachmentFileViewer>
    </div>
  )
};
