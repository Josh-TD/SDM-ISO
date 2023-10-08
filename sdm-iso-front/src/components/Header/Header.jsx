import React from 'react';

const Header = () => {
  return (
    <div className="bg-white w-full p-4">
      <div className="container mx-auto flex items-center justify-between">
        {/* Logo on the left with 50px left padding */}
        <div className="flex items-center">
          <div className="p-2">
            <img src="iso_logo.jpg" alt="Logo" className="w-300 h-135" />
          </div>
          <h1 className="text-3xl font-bold underline">
            Hello world!
          </h1>
        </div>
      </div>
    </div>
  );
};

export default Header;
