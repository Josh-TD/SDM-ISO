import { React, useEffect, useState } from 'react';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom'
import MainPage from './components/MainPage/MainPage'
import LoginPage from './components/LoginPage/LoginPage'
import './index.css';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [rootPage, setRootPage] = useState(undefined);

  useEffect(() => {
    const authenticate = () => { setIsAuthenticated(!isAuthenticated) };

    if (isAuthenticated) {
      setRootPage(<MainPage />)
    } else {
      setRootPage(<LoginPage authenticateFn={authenticate} />)
    }
  }, [isAuthenticated])

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={rootPage} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App