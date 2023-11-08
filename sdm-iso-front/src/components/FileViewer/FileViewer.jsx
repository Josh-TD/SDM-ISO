import React from 'react';


// for displaying functions, we should display from storage if possible
// so we have to save it into storage first, not sure how to do for now
const displayIFrame = (path) => {
    return (
        <iframe className="h-full w-full" src={path} />
    )
}

// it is not good to categorize images with their extension names
// some links are very weird, such as this
// https://www.startpage.com/av/proxy-image?piurl=https%3A%2F%2Fpicfiles.alphacoders.com%2F311%2F311202.jpg&sp=1699237939Tdc224389a92e59722c3c8a935aa4d865ac14fc60fea81981aba59456fe28ea46
// let's hope that we don't need to do it
const fileCategory = {
    "image": {
        fn: displayIFrame,
        ext: ["png", "jpeg", "jpg", "bmp", "tiff"]
    },
    "document": {
        fn: null,
        ext: ["doc", "docx"],
    },
    "spreadsheet": {
        fn: null,
        ext: ["xls", "xlsx", "xlsm"],
    },
    "pdf": {
        fn: displayIFrame,
        ext: ["pdf"],
    },
    "text": {
        fn: null,
        ext: ["txt"],
    },
    "markup": {
        fn: null,
        ext: ["htm", "html", "md"],
    },
    "archive": {
        fn: null,
        ext: ["zip", "rar", "7z"],
    }
};

const categorizeFile = (filename) => {
    let split = filename.split(".");
    let ext = split[split.length - 1];

    for (let [key, value] of Object.entries(fileCategory)) {
        for (let known of value.ext) {
            if (known === ext) {
                return [key, value.fn];
            }
        }
    }

    return undefined;
}

// dummy function when we do fetching for real
// should return the path to file i think
const getFile = (filename) => { };

export const FileViewer = ({ filename }) => {
    const sample_pdf = "https://www.africau.edu/images/default/sample.pdf";
    const sample_img = "https://t4.ftcdn.net/jpg/00/97/58/97/360_F_97589769_t45CqXyzjz0KXwoBZT9PRaWGHRk5hQqQ.jpg";

    const _filename = Math.random() > 0.5 ? sample_img : sample_pdf;

    // path to download, preferrable we have it in localstorage
    const filepath = "";

    let [_type, fn] = categorizeFile(_filename);

    // TODO fix hyperlink style because it is not underlined and not blue
    return (
        <div className='flex flex-col h-full w-full'>
            <u><a href={filepath}>{filename}</a></u>
            {fn(_filename)}
        </div>
    )
}