import React, { useEffect, useState } from "react";
import { useMemo } from 'react';
import mockData from "./mockData.json";
import { useTable, usePagination, useSortBy, useRowSelect } from "react-table";
import Modal from "react-modal";
import "./FileTable.css"
import {FileViewer} from "../FileViewer/FileViewer";
import {FileTableCheckbox} from "./FileTableCheckbox"

const COLUMNS = [

    // Select is different than the rest as it contains checkboxes in its cells rather than data
    {
        accessor: 'fName',
        Header: 'File Name',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'pName',
        Header: 'Project Name',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'cName',
        Header: 'Customer',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'cDateTime',
        Header: 'Date Created',
        cell: (props) => <p>{props.getValue()}</p>
    },
    {
        accessor: 'fDescript',
        Header: 'Descriptions',
        cell: (props) => <p>{props.getValue()}</p>
    },
]

Modal.setAppElement("#root");

export const FileTable = () => {
    const columns = useMemo(() => COLUMNS, [])
    const data = useMemo(() => mockData, [])
    let [ test, setTest ] = useState([])
    useEffect(() => {
        fetch('http://localhost:8080/api/v3/files/list?pageNum=0&pageSize=10&sortBy=createDate&sortAsc=false', {method:"GET"})
            .then(response => response.json())
            .then((data) => {
                console.log(data);
                setTest(data);
            })
            .catch(error => console.error(error));
    }, [])
    
    const { 
        getTableProps, 
        getTableBodyProps, 
        headerGroups, 
        rows,
        prepareRow,
        selectedFlatRows
    } = useTable (
        {
        columns,
        data
        },
        useSortBy,
        useRowSelect,
        (hooks) => {
            hooks.visibleColumns.push(columns => [
                {
                id: 'select',
                Header: ({ getToggleAllRowsSelectedProps }) => (
                    <FileTableCheckbox {...getToggleAllRowsSelectedProps()} />
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

    return (
        <>
            <div className="bg-white col-start-2 row-start-1 flex items-center justify-start">
                <div className="inline-flex items-center justify-between mx-3">
                    <div className="pr-1">
                        <input type="checkbox" id="selectAll" name="selectAll" onClick={() => {handleSelectAll}}></input>
                    </div>
                    <label for="html" className="text-base font-semibold text-iso-secondary-text">Select All</label>
                </div>
                <div className="flex items-center justify-between mx-3">
                    <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">Download</div>
                    <div className="text-base font-semibold text-iso-secondary-text">&nbsp;|&nbsp;</div>
                    <div className="text-base font-semibold text-iso-secondary-text cursor-pointer">View</div>
                </div>
            </div>
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
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                                    </svg>
                                    : 
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                                    </svg>
                                  ) :
                                    // removes icon for select row
                                    column.id == 'select' ? <></> : 
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15L12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
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
                                            openModal(row.original.fName)
                                        }
                                    }    
                                }
                                className={`cursor-pointer hover:bg-gray-200 ${rowClassName}`}
                            >
                                {row.cells.map( cell => {
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
                preventScroll={true}
            >
                <FileViewer filename={selectedFileName} />
            </Modal>
            <div>
                <button className="bg-iso-offwhite p-1 border-solid border-2">Previous</button>
                <button className="bg-iso-offwhite p-1 border-solid border-2">Next</button>
            </div>
            <pre>
                <code>
                {/* {JSON.stringify(
                    {
                    selectedRows: selectedFlatRows.map(row => row.original)
                    },
                    null,
                    2
                )} */}
                </code>
            </pre>
        </>
    )
}