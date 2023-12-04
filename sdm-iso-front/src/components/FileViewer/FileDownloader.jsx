import React, {useEffect, useState} from 'react';
import axios from "axios";
import saveAs from 'file-saver';

const FileDownloader = ({fileName}) => {
    const [fileData, setFileData] = useState(null);
    useEffect(() => {
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?fileName=${encodeURIComponent(fileName)}`;
        axios.get(endpointUrl, {
            responseType: 'arraybuffer',
        })
        .then(response => {
            const blob = new Blob([response.data]);
            saveAs(blob, fileName);
        })
        .catch(error => {
            console.error('Error downloading this file:', error);
        });
    }, [fileName]);
};

export default FileDownloader;