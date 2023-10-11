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
        <div className="justify-center items-center">
          <div className="w-2/5"> 
            <h1 className="text-4xl mt-10 mb-3 w-min-min text-center align-baseline">Sign in</h1>
          </div>
          <AuthBox />
        </div>
      </main>
    </div>
  );
};
