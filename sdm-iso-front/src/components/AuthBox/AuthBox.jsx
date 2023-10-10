import React from 'react';

export function AuthBox() {
  return (
    <div class="min-w-screen min-h-0 h-full flex justify-center items-center">
      <div class="relative bg-iso-offwhite w-2/5 h-2/3 shadow">
        <span class="m-10 flex justify-center items-center font-semibold text-iso-dark-text">Please enter your email address and password.</span>
        <div className="w-full display-block">
          {/* Email Address*/}
          <div class="display-block mb-5 ">
            <label class="m-2 top-0 font-semibold text-iso-light-gray" for="email">Email Address:</label>
            <input class="shadow cursor-text">
            </input>
          </div>

          {/* Password*/}
          <div class="display-block">
            <label class="m-2 pr-4 font-semibold text-iso-light-gray" for="password">Password:</label>
            <input class="pl-4 shadow cursor-text">
            </input>
          </div>

          {/* container for the inline Keep Me Signed In and Sign In button */}

          <div class="display-block w-full min-h-">
            {/* Keep Signed in */}
            <div className="inline-flex items-center justify-between mx-3 float-left">
              <div className="pr-1">
                <input class="cursor-pointer" type="checkbox" id="staySignedIn" name="staySignedIn" value="staySignedIn"></input>
              </div>
              <label for="html" className="text-base font-semibold text-iso-secondary-text">Keep Me Signed In</label>
            </div>

            {/* Sign in */}
            <div class="display-inline-block float-right">
              <div class="display-block">
                <button class="bg-iso-medium-gray hover:bg-iso-link-blue text-white font-bold py-2 px-4 rounded cursor-pointer">Sign In</button>
              </div>
            </div>

            {/* Forgot Password */} {/* Have to make function here for forgotpassword*/}
            <div>
              <span className=' text-blue-500'>Forgot Password?</span>
            </div>
          </div>  

        </div>
      </div>
    </div>

  )};

export default AuthBox;
