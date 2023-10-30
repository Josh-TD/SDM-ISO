import React, { useState } from 'react';

const AttachmentFileViewer = ({ selectedFiles }) => {
    const [currentFileIndex, setCurrentFileIndex] = useState(0);

    const handleNext = () => {
        setCurrentFileIndex(prevIndex => prevIndex + 1);
    };

    const handlePrevious = () => {
        setCurrentFileIndex(prevIndex => prevIndex - 1);
    };

    const file = selectedFiles[currentFileIndex];

    return (
        <div>
            {file && (
                <div>
                    <h2>Viewing File: {file}</h2>
                    {/* Render file content here */}
                    <button onClick={handlePrevious} disabled={currentFileIndex === 0}>Previous</button>
                    <button onClick={handleNext} disabled={currentFileIndex === selectedFiles.length - 1}>Next</button>
                </div>
            )}
        </div>
    );
};

export default AttachmentFileViewer;