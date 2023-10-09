import React from 'react';
import Header from '../Header/Header';
import AuthBox from '../AuthBox/AuthBox';
import { PageTitle } from '../PageTitle/PageTitle';

export default function LoginPage () {
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitle /> 
      <main className="flex-grow overflow-visible">
        <h1>Sign in</h1>
        <AuthBox />
      </main>
    </div>
  );
};
