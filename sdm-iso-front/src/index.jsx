import React from 'react';
import ReactDOM from "react-dom/client";
import reportWebVitals from './reportWebVitals';
import App from './App';
import { ClerkProvider,
    SignedIn,
    SignedOut,
    RedirectToSignIn,
    SignIn,
    SignUp} from "@clerk/clerk-react";
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import MainPage from './components/MainPage/MainPage'
 
const clerkPubKey = 'pk_test_Y29taWMtbG9vbi0yOC5jbGVyay5hY2NvdW50cy5kZXYk';

if (!clerkPubKey) {
  throw new Error("Missing Publishable Key")
}



const root = ReactDOM.createRoot(document.getElementById('root'));

const ClerkWithRoutes = ()=> {
    const navigate = useNavigate();
    return (
        <ClerkProvider
            publishableKey={clerkPubKey}
            navigate={(to) => navigate(to)}
        >
            <Routes>
            <Route path="/" element={<App />} />
            <Route
                path="/sign-in/*"
                element={<SignIn redirectUrl={'/protected'} routing="path" path="/sign-in" />}
            />
            <Route
                path="/sign-up/*"
                element={<SignUp redirectUrl={'/protected'}routing="path" path="/sign-up" />}
            />
            <Route
          path="/protected"
          element={
          <>
            <SignedIn>
              <MainPage />
            </SignedIn>
            <SignedOut>
              <RedirectToSignIn />
            </SignedOut>
          </>
          }
        />
            </Routes>
        </ClerkProvider>
    );
}
root.render(
    <React.StrictMode>
    <BrowserRouter>
        <ClerkWithRoutes />
    </BrowserRouter>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
