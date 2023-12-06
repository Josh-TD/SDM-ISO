import React, {useEffect, useState} from 'react';
import axios from "axios";
import saveAs from 'file-saver';

const FileDownloader = ({fileName}) => {
    const [fileData, setFileData] = useState(null);
    useEffect(() => {
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?filePath=${encodeURIComponent(fileName)}`;
        axios.get(endpointUrl, {
            responseType: 'arraybuffer',
        })
        .then(response => {
            const blob = new Blob([response.data]);
            saveAs(blob, fileName)
            const file = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = file
            a.addEventListener('click', (e) => {
                setTimeout(() => URL.revokeObjectURL(a.href), 30 * 1000);
            });
            a.click()
        })
        .catch(error => {
            console.error('Error downloading this file:', error);
            window.alert("Error downloading this file: \{error}", error);
        });
    }, [fileName]);
};

export default FileDownloader;