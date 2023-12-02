import React, { useRef, useState, useEffect } from 'react';
import useAuth from '../Security/hooks/useAuth';
import axios from '../api/axios';
import { Link, useNavigate } from 'react-router-dom';

import { Icon } from 'react-icons-kit';
import { eyeOff } from 'react-icons-kit/feather/eyeOff';
import { eye } from 'react-icons-kit/feather/eye';

const LOGIN_URL = "/api/auth/authenticate";

export function AuthBox(props) {
    const{ setAuth } = useAuth();

    const navigate = useNavigate();

    const emailRef = useRef();
    const errRef = useRef();

    // State variables
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    useEffect(() => {
        emailRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('')
    }, [email, password])

    // Function to toggle password visibility
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(LOGIN_URL, 
                JSON.stringify({email, password}),
                {
                    headers: {'Content-Type': 'application/json'},
                    withCredentials: true
                }
            );
            const accessToken = response?.data?.accessToken;
            const role = response?.data?.role;
            setAuth({ email, password, role, accessToken });
            setEmail('');
            setPassword('');
            navigate("/", { replace: true });
        } catch (err) {
            if(!err?.response){
                setErrMsg('No Server Response');
            } else if(err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if(err.response?.status === 401) {
                setErrMsg(err.response.data.error);
            } else {
                setErrMsg("Login Failed");
            }
            errRef.current.focus();
        }

        
    }

    return (
        <div className="min-w-screen min-h-0 h-full flex justify-center items-center">
            <div className="relative bg-iso-offwhite w-2/5 h-2/3 shadow">
                <span className="m-10 flex justify-center items-center text-xl text-iso-slate font-semibold">
                    Please enter your email address and password.
                </span>
                <p ref={errRef} className={errMsg ? "bg-red-100 border border-red-400 text-red-700 px-4 py-4 mb-4 rounded-md" : "offscreen"} aria-live="assertive">{errMsg}</p>
                <form className="w-full display-block text-center pr-7" onSubmit={handleSubmit}>
                    {/* Email Address*/}
                    <div className="mb-5">
                        <label className="m-2 top-0 text-iso-dim-gray justify-right font-regular" htmlFor="email">
                            Email Address:
                        </label>
                        <input
                            className="placeholder-color-iso-light-gray shadow cursor-text h-7 w-1/2 pl-2"
                            type="text"
                            id="email"
                            ref={emailRef}
                            autoComplete="off"
                            placeholder="Email Address"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    {/* Password*/}
                    <div>
                        <label className="m-2 pl-12 text-iso-dim-gray justify-right font-regular" htmlFor="password">
                            Password:
                        </label>
                        <input
                            className="placeholder-color-iso-light-gray pl-2 shadow cursor-text h-7 w-1/2"
                            type={showPassword ? 'text' : 'password'}
                            id="password"
                            autoComplete="off"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <Icon icon={showPassword ? eye : eyeOff} onClick={togglePasswordVisibility} className="ml-2 cursor-pointer" />
                    </div>

                    {/* container for the inline Keep Me Signed In and Sign In button */}
                    <div className="display-block w-full m-5">
                        <div className="inline-flex text-center justify-between mx-3">
                            <div className="pr-1">
                                <input
                                    className="cursor-pointer"
                                    type="checkbox"
                                    id="staySignedIn"
                                    name="staySignedIn"
                                    value="staySignedIn"
                                />
                            </div>
                            <label htmlFor="html" className="text-base font-regular text-iso-medium-gray pr-10">
                                Keep Me Signed In
                            </label>
                        </div>

                        <div className="inline-flex mt-5 pl-10 items-right">
                            <button
                                className="bg-iso-light-slate hover:bg-iso-link-blue text-white font-semibold py-2 px-4 rounded cursor-pointer float-right"
                            >
                                Sign In
                            </button>
                        </div>
                    </div>

                    <div className="w-full text-right p-7">
                        <a className="text-iso-link-blue font-regular">Forgot your password?</a>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AuthBox;
