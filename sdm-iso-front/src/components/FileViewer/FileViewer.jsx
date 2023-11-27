import React from 'react';
import FileFetcher from "./FileFetcher";

export const FileViewer = ({ filename }) => {

    return (
        <div className='flex flex-col h-full w-full'>
            <h2> {filename} </h2>
            {/*add closing button for popup*/}
            <button style = {{'text-align': 'right'}}> Exit </button>
            <FileFetcher fileName={filename} />
        </div>
    )
}