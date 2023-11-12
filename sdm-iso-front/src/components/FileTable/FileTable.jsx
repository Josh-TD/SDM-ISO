import React from "react";
import { useMemo } from 'react';
import mockData from "./mockData.json";
import { useTable, usePagination, useSortBy } from "react-table";
import Modal from "react-modal";
import "./FileTable.css"
import {FileViewer} from "../FileViewer/FileViewer";
import {CheckBox} from "../Misc/CheckBox"

const COLUMNS = [

    // Select is different than the rest as it contains checkboxes in its cells rather than data
    {
        id: 'select',
        Header: 'Select',
        cell: ({ row }) => (
            <CheckBox 
            id = {id}
            onChange={() => row.toggleRowSelected()}
            checked={row.isSelected}
            
            />
        ),
      },
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
    
    const { 
        getTableProps, 
        getTableBodyProps, 
        headerGroups, 
        rows,
        previousPage, 
        nextPage, 
        prepareRow
    } = useTable (
        {
        columns,
        data
        },
        useSortBy,
        usePagination
    )

    const [modalIsOpen, setIsOpen] = React.useState(false);
    const [selectedFileName, setSelectedFileName] = React.useState("");

    const openModal = (fileName) => {
        setSelectedFileName(fileName);
        setIsOpen(true);
    };

    return (
        <>
            <table className="bg-iso-offwhite w-full h-4/5" {...getTableProps()}>
                <thead className="bg-iso-light-gray">
                    {headerGroups.map((headerGroup) => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map((column) => (
                            <th {...column.getHeaderProps(
                                // if column id is equal to select then don't have sort by for that column
                                column.id !== 'select' ? column.getSortByToggleProps() : {}
                                )}>
                                {column.render('Header')}
                                <span className="inline-block">
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
                                onClick={() => openModal(row.original.fName)}
                                className={`cursor-pointer hover:bg-gray-200 ${rowClassName}`}
                            >
                                {row.cells.map( cell => {
                                    return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
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
            >
                <h2>File Name: {selectedFileName}</h2>
                <FileViewer filename="dummy" />
            </Modal>
            <div>
                <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={() => previousPage()}>Previous</button>
                <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={() => nextPage()}>Next</button>
            </div>
        </>
    )
}