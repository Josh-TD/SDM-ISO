import React from 'react';

export function AuthBox() {
  return (
    <div class="min-w-screen min-h-0 h-full flex justify-center items-center">
      <div class="relative bg-iso-offwhite w-2/5 h-2/3 shadow">
        <span class="m-10 flex justify-center items-center font-semibold text-iso-dark-text">Please enter your email address and password.</span>
        <div className="w-full display-block items-center">
          {/* Email Address*/}
          <div class="mb-5">
            <label class="m-2 top-0 font-semibold text-iso-light-gray justify-right" for="email">Email Address:</label>
            <input class="shadow cursor-text h-7">
            </input>
          </div>

          {/* Password*/}
          <div class="justify-center">
            <label class="m-2 pr-4 font-semibold text-iso-light-gray justify-right" for="password">Password:</label>
            <input class="pl-4 shadow cursor-text h-7">
            </input>
          </div>

          {/* container for the inline Keep Me Signed In and Sign In button */}
          <div class="display-block w-full m-5">
            <div className="inline-flex items-center justify-between mx-3">
              <div className="pr-1">
                <input class="cursor-pointer" type="checkbox" id="staySignedIn" name="staySignedIn" value="staySignedIn"></input>
              </div>
              <label for="html" className="text-base font-semibold text-iso-light-gray">Keep Me Signed In</label>
            </div>

              <div class = "inline-flex m-5">
                <button class="bg-iso-medium-gray hover:bg-iso-link-blue text-white font-bold py-2 px-4 rounded cursor-pointer float-right">Sign In</button>
              </div>
          </div> 

          <div class="w-full items-right">
            <a class="text-iso-link-blue">Forgot your password?</a>
          </div> 
 
        </div>
      </div>
    </div>

  )};

export default AuthBox;
