import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Mammoth from 'mammoth';
import parse from 'html-react-parser';
import read  from 'txt-reader';
import { read as readXlsx } from 'xlsx';
import saveAs from 'file-saver';
import("pdfjs-dist").then((pdfjsLib) => {
    pdfjsLib.getDocument();
});

const FileFetcher = ({ fileName }) => {
    const [fileData, setFileData] = useState(null);

    useEffect(() => {
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?fileName=${encodeURIComponent(fileName)}`;

        axios.get(endpointUrl, {
            responseType: 'arraybuffer',
        })
            .then(response => {
                const blob = new Blob([response.data]);

                // Determine file type from fileName
                const fileType = fileName.split('.').pop().toUpperCase();

                // Handle different file types
                if (['BMP', 'JPG'].includes(fileType)) {
                    const imageUrl = URL.createObjectURL(blob);
                    setFileData(<img src={imageUrl} alt={fileName} />);
                } else if (fileType === 'PDF') {
                    // Handle PDF files using pdfjs-dist library
                    const reader = new FileReader();
                    reader.onloadend = function () {
                        const typedarray = new Uint8Array(reader.result);
                        pdfjsLib.getDocument(typedarray).promise.then(pdf => {
                            pdf.getPage(1).then(page => {
                                const canvas = document.createElement('canvas');
                                const context = canvas.getContext('2d');
                                const viewport = page.getViewport({ scale: 1.5 });
                                canvas.height = viewport.height;
                                canvas.width = viewport.width;
                                page.render({ canvasContext: context, viewport: viewport }).promise.then(() => {
                                    setFileData(<img src={canvas.toDataURL()} alt={fileName} />);
                                });
                            });
                        });
                    };
                    reader.readAsArrayBuffer(blob);
                } else if (fileType === 'DOCX') {
                    // Handle DOCX files using mammoth-js library
                    Mammoth.extractRawText({ arrayBuffer: blob }).then(result => {
                        setFileData(<pre>{result.value}</pre>);
                    });
                } else if (fileType === 'HTML' || fileType === 'HTM') {
                    // Handle HTML files using html-react-parser library
                    const reader = new FileReader();
                    reader.onloadend = function () {
                        setFileData(parse(reader.result));
                    };
                    reader.readAsText(blob);
                } else if (fileType === 'TXT') {
                    // Handle TXT files using txt-reader library
                    read(blob).then(text => {
                        setFileData(<pre>{text}</pre>);
                    });
                } else if (['XLSX', 'XLSM'].includes(fileType)) {
                    // Handle XLSX/XLSM files using xlsx library
                    const reader = new FileReader();
                    reader.onloadend = function () {
                        const workbook = readXlsx(reader.result, { type: 'array' });
                        const sheet = workbook.Sheets[workbook.SheetNames[0]];
                        const sheetData = XLSX.utils.sheet_to_json(sheet, { header: 1 });
                        setFileData(
                            <table>
                                <tbody>
                                {sheetData.map((row, rowIndex) => (
                                    <tr key={rowIndex}>
                                        {row.map((cell, cellIndex) => (
                                            <td key={cellIndex}>{cell}</td>
                                        ))}
                                    </tr>
                                ))}
                                </tbody>
                            </table>
                        );
                    };
                    reader.readAsArrayBuffer(blob);
                } else {
                    // For unsupported file types, download the file
                    saveAs(blob, fileName);
                }
            })
            .catch(error => {
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