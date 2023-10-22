import React from "react";
import { useMemo } from 'react';
import mockData from "./mockData.json";
import { useTable, usePagination } from "react-table";

const COLUMNS = [
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
        accessor: 'lViewed',
        Header: 'Last Viewed',
        cell: (props) => <p>{props.getValue()}</p>
    },
]

export const FileTable = () => {
    const columns = useMemo(() => COLUMNS, [])
    const data = useMemo(() => mockData, [])
    
    const { 
        getTableProps, 
        getTableBodyProps, 
        headerGroups, 
        page, 
        previousPage, 
        nextPage, 
        prepareRow
    } = useTable (
        {
        columns,
        data
        },
        usePagination
    )

    return (
        <>
            <table className="bg-iso-offwhite border-solid rounded-md"{...getTableProps()}>
                <thead className="bg-iso-light-gray">
                    {headerGroups.map((headerGroup) => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map((column) => (
                            <th {...column.getHeaderProps()}>
                                {column.render('Header')}
                            </th>
                        ))}
                    </tr>
                    ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                    {page.map((row) => {
                        prepareRow(row)
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map( cell => {
                                    return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                                })}
                            </tr>
                        )
                    })}
                </tbody>
            </table>
            <div>
                <button onClick={() => previousPage()}>Previous</button>
                <button onClick={() => nextPage()}>Next</button>
            </div>
        </>
    )
}