import React, { useState, useEffect } from 'react';
import axios from 'axios';
import parse from 'html-react-parser';
import mammoth from 'mammoth';
import {ExcelRenderer} from 'react-excel-renderer';
import ExcelTable from "./ExcelRenderTable";
import MsgReader from '@kenjiuno/msgreader'

const FileFetcher = ({ fileName }) => {
    const [fileData, setFileData] = useState(null);
    const [excelData, setExcelData] = useState(null);
    // TODO: For some reason the filename being passed in is "undefined"
    console.log("This is FileFetcher, the file you want is this: " + fileName)
    useEffect(() => {

        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?filePath=FCTS_data/Attachments/${encodeURIComponent(fileName)}`;

        const fileType = fileName.split('.').pop().toUpperCase();
        axios
            .get(endpointUrl, {
                responseType: 'arraybuffer'
            })
            .then(async (response) => {
                const arrayBuffer = response.data
                const blob = new Blob([arrayBuffer]);
                if (['BMP', 'JPG'].includes(fileType)) {
                    const imageUrl = URL.createObjectURL(blob);
                    setFileData(<img src={imageUrl} id={fileName} alt={fileName}/>);
                } else if (fileType === 'PDF') {
                    const pdfBlob = new Blob([response.data], {type: 'application/pdf'});
                    const pdfURL = URL.createObjectURL(pdfBlob);
                    setFileData(<iframe src={pdfURL} width="100%" height="500px" id={fileName} style={{border: 'none'}}/>);
                } else if (['TXT', 'HTML', 'HTM'].includes(fileType)) {
                    const reader = new FileReader();
                    reader.onloadend = function () {setFileData(parse(reader.result));};
                    reader.readAsText(blob);
                } else if (fileType === 'DOCX') {
                    const result = await mammoth.extractRawText({arrayBuffer});
                    setFileData(parse(result.value));
                } else if (['XLSX', 'XLSM'].includes(fileType)) {
                    ExcelRenderer(blob, (err, resp) => {
                        setExcelData({cols: resp.cols,rows: resp.rows,});
                    });
                }
                else if (fileType == 'MSG') {
                    const msgBody = new MsgReader(arrayBuffer).getFileData().body;
                    setFileData(<div id={fileName}>{msgBody}</div>);
                }
                else if (fileType == 'DOC') {
                    // TODO: Render .doc files
                    // 1) Render arrayBuffer (the file blob contents as text)
                    // 2) Convert .doc file to .docx for viewing
                    // 3) Display not supported message at this time like so:
                    setFileData(<h2>At this type we do not support rendering .doc files.</h2>);
                }
            })
            .catch((error) => {
                console.error('Error fetching file:', error);
            });
    }, [fileName]);
    return <div>{fileData || (excelData && <ExcelTable excelData={excelData}/>) || <p>Loading...</p>}</div>;

};

export default FileFetcher;