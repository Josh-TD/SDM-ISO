import React from 'react';
import FileFetcher from "./FileFetcher";
import { IoMdExit } from "react-icons/io";
import {useState} from "react";
import FileDownloader from "./FileDownloader";


export const FileRender = ({filename, closeModal}) => {
    const [exit, setExit] = useState(false);
    const exitHandler = () => {
        closeModal()
        console.log("closing modal")
    }
    let [download, setDownload] = useState(false)
    const downloadHandler = () => {
        download = true
        setDownload(true)
        console.log("user is requesting download")
    }
    
    return (
    <div className='flex flex-col h-full w-full'> 
        <nav className='top-part mb-4'>
            <div class="flex flex-wrap justify-between items-center mx-auto">
                <div class="flex items-center ">
                    <h2 class="text-xl font-bold mb-2"> {filename} </h2>
                    {/* Download Button */}
                    <button onClick={downloadHandler}
                        className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-2 rounded inline-flex items-center ml-4">
                        <svg className="fill-current w-4 h-4 ml-1 mr-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                            <path d="M13 8V2H7v6H2l8 8 8-8h-5zM0 18h20v2H0v-2z"/>
                        </svg>
                        <span>Download</span>
                        {download && <FileDownloader fileName={filename} />}
                    </button>
                </div>
                <div className='flex-exit flex justify-end'>
                    {/* Exit Button */}
                    <button onClick={exitHandler} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        <IoMdExit />
                    </button>
                </div>
            </div>
        </nav>

        <div className="mb-4"></div>
        {/* Render File With FileFetcher Component */}
        <FileFetcher fileName={fileName} />
        
    </div>
    )
}


// Commented code for having the download button at the bottom
/*
<button onClick={downloadHandler}
className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center">
<svg className="fill-current w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
    <path d="M13 8V2H7v6H2l8 8 8-8h-5zM0 18h20v2H0v-2z"/>
</svg>
<span>Download</span>
{/*<FileDownloader fileName={filename}/>}
</button>
*/