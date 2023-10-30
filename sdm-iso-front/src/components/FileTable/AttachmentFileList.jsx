import React, { useState, useEffect } from 'react';
import axios from 'axios';
const AttachmentFileList = () => {
    const [files, setFiles] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/files/list')
            .then(response => {
                setFiles(response.data);
            })
            .catch(error => {
                console.error('Error fetching files: ', error);
            });
    }, []);

    const downloadFile = (fileName) => {
        axios.get(`http://localhost:8080/api/files/download/${fileName}`, {
            responseType: 'blob'
        })
            .then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', fileName);
                document.body.appendChild(link);
                link.click();
            })
            .catch(error => {
                console.error('Error downloading file: ', error);
            });
    };

    return (
        <div>
            <h2>File List</h2>
            <table>
                <thead>
                <tr>
                    <th>File Name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {files.map(fileName => (
                    <tr key={fileName}>
                        <td>{fileName}</td>
                        <td>
                            <button onClick={() => downloadFile(fileName)}>Download</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AttachmentFileList;