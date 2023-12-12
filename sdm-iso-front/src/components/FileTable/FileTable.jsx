import React, { useEffect, useState } from "react";
import { useMemo } from 'react';
import { useTable, usePagination, useSortBy, useRowSelect } from "react-table";
import Modal from "react-modal";
import "./FileTable.css"
import {FileTableCheckbox} from "./FileTableCheckbox"
import {FileRender} from "../FileViewer/FileRender";
import FileDownloader from "../FileViewer/FileDownloader";
import FileDownloadMult from "../FileViewer/FileDownloadMult";

const COLUMNS = [

    // Select is different than the rest as it contains checkboxes in its cells rather than data
    {
        accessor: 'fileName',
        Header: 'File Name',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'projectName',
        Header: 'Project Name',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'customerName',
        Header: 'Customer',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'fileCreateDate',
        Header: 'Date Created',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'fileDescription',
        Header: 'Descriptions',
        cell: (props) => <p>{props.getValue()}</p>
    },
]

Modal.setAppElement("#root");

export const FileTable = ({ data, fetchFunction }) => {
    const columns = useMemo(() => COLUMNS, [])
    let [ page, setPage ] = useState(0)

    useEffect(() => {
        fetchFunction(page)
    }, [page])

    function handleNextClick() {
        setPage(page => page + 1)
        console.log('next')
    }
    function handlePrevClick() {
        setPage(page => page - 1)
        console.log('prev')
    }

    const { 
        getTableProps, 
        getTableBodyProps, 
        headerGroups, 
        rows,
        prepareRow,
        selectedFlatRows
    } = useTable(
        {
            columns,
            data: data?.content
        },
        useSortBy,
        useRowSelect,
        (hooks) => {
            hooks.visibleColumns.push(columns => [
                {
                    id: 'select',
                    Header: ({ getToggleAllRowsSelectedProps }) => (
                        <div className="flex mt-3 items-center justify-center w-28">
                            <span className="text-base pr-1">Select All </span>
                            <FileTableCheckbox {...getToggleAllRowsSelectedProps()} />
                        </div>
                    ),
                    Cell: ({ row }) => <FileTableCheckbox {...row.getToggleRowSelectedProps()} />
                },
                ...columns
            ])
        }
    )

    const [modalIsOpen, setIsOpen] = React.useState(false);
    const [selectedFileName, setSelectedFileName] = React.useState("");

    const openModal = (fileName) => {
        setSelectedFileName(fileName);
        setIsOpen(true);
    };

    let [selectedFiles, setSelectedFiles] = React.useState([])
    let [downloadMult, setDownloadMult] = useState(false)
    let [download, setDownload] = useState(false)
    const handleDownload = () => {
        // Here is the handle download
        // selectedFlatRows is like any other array, just console.log it to see updates
        if (selectedFlatRows.length == 1) {
            download = true;
            setDownload(true);
        } else if (selectedFlatRows.length > 1) {
            selectedFiles = selectedFlatRows.map(row => row.original.fileName);
            setSelectedFiles(selectedFiles);
            downloadMult = true;
            setDownloadMult(true);
            console.log("selected file names: ", selectedFiles)
        }
        console.log("Number of selected rows: ", selectedFlatRows.length)
        console.log("Downloading files")
    }

    return (
        <>
            <div className="bg-white col-start-2 row-start-1 flex items-center justify-start">
                <div className="flex items-center justify-between mx-3">
                    <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">
                        <button onClick={handleDownload}>
                            <span>Download</span>
                            {download && <FileDownloader fileName={selectedFileName} />}
                            {downloadMult && <FileDownloadMult fileNameArr={selectedFiles}/>}
                        </button>
                    </div>
                    <div className="text-base font-semibold text-iso-secondary-text">&nbsp;|&nbsp;</div>
                    <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">View</div>
                </div>
            </div>

            {data.content.length > 0 ? (
            <React.Fragment>
                <table className="bg-iso-offwhite w-full h-4/5" {...getTableProps()}>
                    <thead className="bg-iso-offwhite h-12">
                        {headerGroups.map((headerGroup) => (

                        <tr {...headerGroup.getHeaderGroupProps()} className="items-center">
                            {headerGroup.headers.map((column) => (
                                <th {...column.getHeaderProps(
                                    // if column id is equal to select then don't have sort by for that column
                                    column.id !== 'select' ? column.getSortByToggleProps() : {}
                                    )} className="p-2 place-items-center">
                                    {column.render('Header')}
                                    <span className="inline-block relative top-1.5">
                                        {column.isSorted ? (column.isSortedDesc ?
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                                            </svg>
                                            :
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                                            </svg>
                                        ) :
                                            // removes icon for select row
                                            column.id == 'select' ? <></> :
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                    <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                                                </svg>
                                        }
                                    </span>
                                </th>
                            ))}
                        </tr>
                    ))}

                </thead>
                <tbody {...getTableBodyProps()} className="text-center">
                    {rows.map((row, index) => {
                        prepareRow(row)
                        const rowClassName = index % 2 === 0 ? "table-row-even" : "table-row-odd";
                        return (
                            <tr {...row.getRowProps()}
                                onClick={(e) => {
                                    if (!e.target.closest('input[type="checkbox"]')) {
                                        openModal(row.original.fileName)
                                    }
                                }
                                }
                                className={`cursor-pointer hover:bg-gray-200 ${rowClassName}`}
                            >
                                {row.cells.map(cell => {
                                    return <td {...cell.getCellProps()} className="p-2">{cell.render('Cell')}</td>
                                })}
                            </tr>
                        )
                    })}
                </tbody>
            </table>
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={() => setIsOpen(false)}
                contentLabel="File Modal"
                preventScroll={true}>
                <FileRender filename={selectedFileName} closeModal={() => setIsOpen(false)}/>
            </Modal>
            <div>
                {
                    data.first != true && (
                        <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={handlePrevClick}>Previous</button>
                    )
                }
                {
                    data.last != true && (
                        <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={handleNextClick}>Next</button>
                    )
                }
            </div>
            </React.Fragment>
                ) : (
                    <p>Loading...</p>
                )
            }
        </>
    )
}