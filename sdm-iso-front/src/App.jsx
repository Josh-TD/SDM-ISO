import React from 'react';
import {  Route, Routes } from 'react-router-dom';
import RequireAuth from './components/RequireAuth/RequireAuth'; 

import MainPage from './components/MainPage/MainPage'
import LoginPage from './components/LoginPage/LoginPage'
import './index.css';
import "@fontsource/open-sans";
import "@fontsource/open-sans/300.css";
import "@fontsource/open-sans/400.css";
import "@fontsource/open-sans/700.css";


function App() {
  return (
    <Routes>
        <Route>
            {/* public */}
            <Route path="/login" element={<LoginPage />}/>

            {/* protected */}
            <Route element={<RequireAuth allowedRoles={["USER"]}/>}>
                <Route path="/" element={<MainPage />} />
            </Route>

            {/* catch all */}
            {/* <Route path="*" element={<Missing />} /> */}
        </Route>
    </Routes>
  )
}

export default App;