import React from 'react';

export default function Header() {
  return (
    <div className="bg-white w-full">
      {/* Icon */}
      <div className="container pl-12  flex items-center justify-between">
        <div className="p-2">
          <img src="iso_logo.jpg" alt="ISO New England Logo" className="w-300 h-135" />
        </div>
      </div>

      {/* slate line across screen */}
      <div className="h-12 bg-iso-slate flex items-center justify-center w-full">
        {/* We can put elements in here if we want, for example a logout button */}
      </div>
    </div>
  )
};
