import React, { useState, useEffect } from 'react';
import axios from 'axios';
import parse from 'html-react-parser';
import FileViewer from "react-file-viewer";


const FileFetcher = ({ fileName }) => {
    const [fileData, setFileData] = useState(null);

    useEffect(() => {
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?fileName=${encodeURIComponent(
            fileName
        )}`;

        axios
            .get(endpointUrl, {
                responseType: 'arraybuffer',
            })
            .then((response) => {
                const blob = new Blob([response.data]);

                // Determine file type from fileName
                const fileType = fileName.split('.').pop().toUpperCase();

                // IMAGES WORKING
                if (['BMP', 'JPG'].includes(fileType)) {
                    const imageUrl = URL.createObjectURL(blob);
                    setFileData(<img src={imageUrl} alt={fileName} />);

                // PDFS WORKING
                } else if (fileType === 'PDF') {
                    const pdfBlob = new Blob([response.data], {type: 'application/pdf'});
                    const pdfURL = URL.createObjectURL(pdfBlob);
                    setFileData(
                        <iframe
                            src={pdfURL}
                            width="100%"
                            height="500px"
                            style={{ border: 'none' }}
                        />
                    );
                // TODO
                } else if (fileType === 'DOCX') {
                    setFileData(<FileViewer
                        fileType={'pdf'}
                        filePath={'https://www.africau.edu/images/default/sample.pdf'}
                    />)
               // TODO
                } else if (fileType === 'HTML' || fileType === 'HTM') {
                    // Handle HTML files using html-react-parser library
                    const reader = new FileReader();
                    reader.onloadend = function () {
                        setFileData(parse(reader.result));
                    };
                    reader.readAsText(blob);

                // TODO: TEXT Files = giving up
                } else if (fileType === 'TXT') {
                    setFileData(
                        <h2> At this time, our application does not support .txt file viewing.</h2>

                    );
                // TODO: EXCEL SHEETS = giving up
                } else if (['XLSX', 'XLSM'].includes(fileType)) {
                    setFileData(
                        <h2> At this time, our application does not support Excel file viewing.</h2>
                    );
                }
            })
            .catch((error) => {
                console.error('Error fetching file:', error);
            });

        return () => {
            if (fileData && fileData.type === 'img') {
                URL.revokeObjectURL(fileData.props.src);
            }
        };
    }, [fileName]);

    return <div>{fileData ? fileData : <p>Loading...</p>}</div>;
};

export default FileFetcher;