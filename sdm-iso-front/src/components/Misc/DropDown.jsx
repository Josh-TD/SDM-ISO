import React, { useState } from "react";
import "./DropDown.css"; 

export const DropDown = ({ label, defaultHidden, children }) => {
    const [hidden, setHidden] = useState(defaultHidden);
    const toggleHidden = () => { setHidden(!hidden); };

    return (
        <div className="border-y-4 border-iso-border-light pl-4 pr-6">
            <div className="flex items-center justify-between grow text-base font-semibold text-iso-secondary-text cursor-pointer my-4" onClick={toggleHidden}>
                {label}
                { hidden ? 
                    <svg
                        className="w-6 h-6 mr-3 cursor-pointer"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                    </svg>
                :
                    <svg 
                        className="w-6 h-6 mr-3 cursor-pointer"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                    </svg>

                }
            </div>
            <div className={`my-4 transition-max-height duration-300 ease-in-out overflow-hidden ${hidden ? 'max-h-0 overflow-hidden' : 'max-h-96'}`}>
                
                {children}
            </div>

            {/* This is the original code without the smoother transitions if code breaks can revert to this */}
            {/* {!hidden &&
                <div className="my-4">
                    {children}
                </div>} */}
        </div>
    );
};