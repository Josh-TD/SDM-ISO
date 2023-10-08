import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import MainPage from './components/MainPage/MainPage'
import './index.css';

function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage/>}/>
        </Routes>      
      </BrowserRouter>
    )
  }
  
  export default App