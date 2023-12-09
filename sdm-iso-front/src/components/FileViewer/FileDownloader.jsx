import React, {useEffect, useState} from 'react';
import axios from "axios";
import saveAs from 'file-saver';

const FileDownloader = ({fileName}) => {
    const [fileData, setFileData] = useState(null);
    useEffect(() => {
<<<<<<< HEAD
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?fileName=${encodeURIComponent(fileName)}`;
=======
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?filePath=FCTS_data/Attachments/${encodeURIComponent(fileName)}`;
>>>>>>> 0f488af2d71aee5e4c71daa35cb9dff7a514b5f6
        axios.get(endpointUrl, {
            responseType: 'arraybuffer',
        })
        .then(response => {
            const blob = new Blob([response.data]);
<<<<<<< HEAD
            saveAs(blob, fileName);
        })
        .catch(error => {
            console.error('Error downloading this file:', error);
=======
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
>>>>>>> 0f488af2d71aee5e4c71daa35cb9dff7a514b5f6
        });
    }, [fileName]);
};

export default FileDownloader;