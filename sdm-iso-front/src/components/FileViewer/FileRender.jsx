import React from 'react';
import FileFetcher from "./FileFetcher";
import { IoMdExit } from "react-icons/io";
import {useState} from "react";
import FileDownloader from "./FileDownloader";

export const FileRender = ({fileName }) => {
    const [exit, setExit] = useState(false);
    const exitHandler = () => {
        setExit(true)
        console.log("closing modal")
    }
    const [download, setDownload] = useState(false)
    const downloadHandler = () => {
        setDownload(true)
        console.log("user is requesting download")
    }

    return (
    <div className='flex flex-col h-full w-full'>
        <h2> {fileName} </h2>

        {/* Exit Button */}
        <button onClick={exitHandler} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            <IoMdExit />
        </button>

        {/* Render File With FileFetcher Component */}
        <FileFetcher fileName={fileName} />

        {/* Download Button */}
        <button onClick={downloadHandler}
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center">
            <svg className="fill-current w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                <path d="M13 8V2H7v6H2l8 8 8-8h-5zM0 18h20v2H0v-2z"/>
            </svg>
            <span>Download</span>
            {/*<FileDownloader fileName={fileName}/>*/}
        </button>
        </div>
    )
}