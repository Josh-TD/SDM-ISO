import React, { useEffect, useLayoutEffect, useState } from "react";
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

export const FileTable = ({ data, fetchFunction, pageNum }) => {
    const columns = useMemo(() => COLUMNS, [])
    let [ page, setPage ] = useState(0)
    let [pageSize ,setPageSize] = useState(10)
    let [sortBy, setSortBy] = useState('createDate')
    let [sortAsc, setSortAsc] = useState(true)
    let [sortState, setSortState] = useState(0) //0 is none, 1 is asc, 2 is desc

    useEffect( () => {
        setPage(0)
    }, [pageNum])
    useEffect(() => {
        // fetchFiles(0,10,'createDate',true)
        fetchFunction(page,pageSize,sortBy,sortAsc)
    }, [page, sortAsc])

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
        useRowSelect,
        (hooks) => {
            hooks.visibleColumns.push(columns => [
                {
                    id: 'select',
                    Header: ({ getToggleAllRowsSelectedProps }) => (
                        <div className="flex items-center w-28">
                            <span className="text-base">Select All </span>
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
        selectedFiles = selectedFlatRows.map(row => row.original.fileName);
        setSelectedFiles(selectedFiles);

        if (selectedFlatRows.length == 1) {
            console.log("one file only: ", selectedFiles[0])
            download = true;
            setDownload(true);
        } else if (selectedFlatRows.length > 1) {
            downloadMult = true;
            setDownloadMult(true);
            console.log("selected file names: ", selectedFiles)
        }
        
        console.log("Number of selected rows: ", selectedFlatRows.length)
        console.log("Downloading files")
    }
    const handleSortHeader = (columnID) => {
        console.log(`ColumnID: ${columnID}`)
        if(columnID === 'fileCreateDate') {
            setSortBy('createDate')
        } else {
            setSortBy(columnID);
        }
        sortAsc === true ? setSortAsc(false) : setSortAsc(true)
    };

    return (
        <>
            <div className="bg-white col-start-2 row-start-1 flex items-center justify-start">
                <div className="flex items-center justify-between mx-3">
                    <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">
                        <button onClick={handleDownload}>
                            <span>Download</span>
                            {download && <FileDownloader fileName={selectedFiles[0]} />}
                            {downloadMult && <FileDownloadMult fileNameArr={selectedFiles}/>}
                        </button>
                    </div>
                </div>
            </div>

            {data.content.length > 0 ? (
            <React.Fragment>
                <table className="bg-iso-offwhite w-full h-4/5" {...getTableProps()}>
                    <thead className="bg-iso-offwhite h-12">
                        {headerGroups.map((headerGroup) => (
                        <tr {...headerGroup.getHeaderGroupProps()} className="items-center">
                            {headerGroup.headers.map((column) => (
                                <th {...column.getHeaderProps()} onClick={() => {column.id !== 'select' && column.id !== 'fileDescription' ? handleSortHeader(column.id) : {}}} className="p-2 place-items-center">
                                    {column.render('Header')}
                                    <span className="inline-block relative top-1.5">
                                        {
                                            column.id === 'select' || column.id === 'fileDescription' ? <></> : (
                                                <>
                                                    {column.id === 'fileCreateDate' ? (
                                                        sortBy !== 'createDate' ? (
                                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                                <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                                                            </svg>
                                                        ) : (
                                                        sortAsc !== false ? (
                                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                                <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                                                            </svg>
                                                        ) : (
                                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                                <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                                                            </svg>
                                                        )
                                                    )) : (
                                                    sortBy !== column.id ? (
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                            <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                                                        </svg>
                                                    ) : (
                                                    sortAsc !== false ? (
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                            <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                                                        </svg>
                                                    ) : (
                                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                            <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                                                        </svg>
                                                    )))}
                                                </>
                                            )
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