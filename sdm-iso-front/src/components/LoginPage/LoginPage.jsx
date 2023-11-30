import React, { useRef } from 'react';
import Header from '../Header/Header';
import AuthBox from '../AuthBox/AuthBox';
import { PageTitle } from '../PageTitle/PageTitle';

export default function LoginPage (props) {
  return (
    <div className='flex flex-col h-screen'>
      <Header />
      <PageTitle /> 
      <main className="flex-grow overflow-visible">
        <div className="justify-center text-center">
          <div className="w-2/3">
            <h1 className="text-4xl mt-10 mb-3 pl-4 pb-1 w-min-min text-center text-iso-slate align-baseline font-light">Sign in</h1>
          </div>
          <AuthBox authenticateFn={props.authenticateFn} />
        </div>
      </main>
    </div>
  );
};
