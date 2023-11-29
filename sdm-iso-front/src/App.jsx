import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import MainPage from './components/MainPage/MainPage'
import LoginPage from './components/LoginPage/LoginPage'
import './index.css';
import "@fontsource/open-sans";
import "@fontsource/open-sans/300.css";
import "@fontsource/open-sans/400.css";
import "@fontsource/open-sans/700.css";
import { AuthProvider, useAuth } from './components/AuthContext/AuthContext';

function NoMatch() {
  return (
    <div style={{ padding: 20 }}>
      <h2>404: Page Not Found</h2>
      <p>URL does not lead anywhere.</p>
    </div>
  );
}

const PrivateRoute = ({ element: Element, ...rest }) => {
  const { isAuthenticated } = useAuth();

  return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
};

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<LoginPage />}/>
          <Route exact path='/' element={<PrivateRoute/>}>
            <Route exact path='/' element={<MainPage/>}/>
          </Route>
          <Route path="*" element={<NoMatch/>} />
        </Routes>
      </Router>
    </AuthProvider>
  )
}

export default App;