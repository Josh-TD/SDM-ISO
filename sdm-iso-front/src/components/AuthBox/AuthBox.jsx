import React from 'react';

export function AuthBox() {
  return (
    <div class="min-w-screen min-h-0 h-full flex justify-center items-center">
    <div class="relative bg-iso-offwhite w-2/5 h-2/3 shadow">
        <span class="m-10 flex justify-center items-center font-semibold text-iso-dark-text">Please enter your email address and password.</span>
        <div className="w-full flex items-center justify-center ">

          {/* Email Address*/}
          <div>
            <label>Email Address:</label>
            <input>
            </input>
          </div>
    
          {/* Password*/}
          <div>
            <label>Password:</label>
            <input>
            </input>
          </div>
            
        </div>
      </div>
    </div>

  )};

export default AuthBox;
