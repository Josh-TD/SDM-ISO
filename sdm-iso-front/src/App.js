import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import MainPage from './components/MainPage/MainPage'
import LoginPage from './components/LoginPage/LoginPage'
import './index.css';

function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage/>}/>
          <Route path="/login" element={<LoginPage/>}/>
        </Routes>      
      </BrowserRouter>
    )
  }
  
  export default App