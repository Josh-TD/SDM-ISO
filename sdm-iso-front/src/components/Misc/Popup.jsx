import React from 'react'
import "./Popup.css"

function Popup(props) {
  return (props.trigger) ? (
    <div className ="popup">
        <div className = "popup-inner">
            <button className="close-btn" onClick={() => props.setTrigger(false)}>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
              <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
            {props.children}
        </div>
    </div>
  ) : "";
}

export default Popup