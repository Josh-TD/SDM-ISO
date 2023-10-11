import React from 'react';

export function AuthBox() {
  return (
    <div class="min-w-screen min-h-0 h-full flex justify-center items-center">
      <div class="relative bg-iso-offwhite w-2/5 h-2/3 shadow">
        <span class="m-10 flex justify-center items-center font-semibold text-iso-dark-text">Please enter your email address and password.</span>
        <div className="w-full display-block text-center pr-7">
          {/* Email Address*/}
          <div className="mb-5">
            <label className="m-2 top-0 font-semibold text-iso-light-gray justify-right" for="email">Email Address:</label>
            <input className="shadow cursor-text h-7 w-1/2">
            </input>
          </div>

          {/* Password*/}
          <div>
            <label className="m-2 pl-8 font-semibold text-iso-light-gray justify-right" for="password">Password:</label>
            <input className="pl-4 shadow cursor-text h-7 w-1/2">
            </input>
          </div>

          {/* container for the inline Keep Me Signed In and Sign In button */}
          <div className="display-block w-full m-5">
            <div className="inline-flex text-center justify-between mx-3">
              <div className="pr-1">
                <input className="cursor-pointer" type="checkbox" id="staySignedIn" name="staySignedIn" value="staySignedIn"></input>
              </div>
              <label for="html" className="text-base font-semibold text-iso-light-gray pr-10">Keep Me Signed In</label>
            </div>

              <div className = "inline-flex mt-5 pl-10 items-right">
                <button className="bg-iso-medium-gray hover:bg-iso-link-blue text-white font-bold py-2 px-4 rounded cursor-pointer float-right">Sign In</button>
              </div>
          </div> 

          <div className="w-full text-right p-7">
            <a className="text-iso-link-blue">Forgot your password?</a>
          </div> 
 
        </div>
      </div>
    </div>

  )};

export default AuthBox;
