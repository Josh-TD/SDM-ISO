import React, {useEffect, useState} from 'react';
import axios from "axios";
import saveAs from 'file-saver';


const FileDownloadMult = ({fileNameArr}) => {
    console.log("fileNameArr: ", fileNameArr)
    useEffect(() => {
        let endpointUrl = "http://localhost:8080/api/sdmisofiles/download-zip?fileNames="
        fileNameArr.forEach(file => {
            endpointUrl += `FCTS_data/Attachments/${encodeURIComponent(file)},`
        });
        endpointUrl = endpointUrl.slice(0, -1)
        console.log("url: ", endpointUrl)
        axios.get(endpointUrl, {
            responseType: 'arraybuffer',
        })
        .then(response => {
            console.log("response: ", response)
            const blob = new Blob([response.data]);
            saveAs(blob, 'FCTS_data.zip')
            const file = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = file
            a.addEventListener('click', (e) => {
                setTimeout(() => URL.revokeObjectURL(a.href), 30 * 1000);
            });
            a.click()
        })
        .catch(error => {
            console.error('Error downloading the zip file:', error);
            window.alert(`Error downloading this zip: ${error}`);
        });
    }, [fileNameArr])
}

export default FileDownloadMult;