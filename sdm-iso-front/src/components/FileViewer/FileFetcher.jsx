import React, { useState, useEffect } from 'react';
import axios from 'axios';
import parse from 'html-react-parser';
import mammoth from 'mammoth';
import ExcelRenderer from 'react-excel-renderer';
import ExcelTable from "./ExcelRenderTable";
import MsgReader from '@kenjiuno/msgreader'

const FileFetcher = ({ fileName }) => {
    const [fileData, setFileData] = useState(null);
    const [excelData, setExcelData] = useState(null);

    useEffect(() => {

        // TODO: Fixing .DOCX vs .docx filetype bug
        // for some reason converting other .docx files to .DOCX before the call is
        // made works, but then for g_gravityworks_proposal.DOCX it doesn't work, response 500
        // but calling g_gravityworks_proposal.docx does work, so not sure what is going on there
        if (fileName.toLowerCase().endsWith('.docx')) {
            fileName = fileName.slice(0, fileName.length - 5) + ".DOCX";
        }

        // TODO: Access file from FCTS_data/Attachments/{fileName} once we are ready
        // We can modify the endpointURL parameter to do this like so:
        // http://localhost:8080/api/sdmisofiles/viewordownload?filePath=FCTS_data/Attachments/${encodeURIComponent(fileName)}
        const endpointUrl = `http://localhost:8080/api/sdmisofiles/viewordownload?filePath=${encodeURIComponent(fileName)}`;

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
                    setFileData(<img src={imageUrl} alt={fileName}/>);
                } else if (fileType === 'PDF') {
                    const pdfBlob = new Blob([response.data], {type: 'application/pdf'});
                    const pdfURL = URL.createObjectURL(pdfBlob);
                    setFileData(<iframe src={pdfURL} width="100%" height="500px" style={{border: 'none'}}/>);
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
                    setFileData(<div>{msgBody}</div>);
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