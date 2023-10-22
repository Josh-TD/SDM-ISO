import React from "react";
import { useMemo } from 'react';
import mockData from "./mockData.json";
import { useTable, usePagination, useSortBy } from "react-table";

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
        useSortBy,
        usePagination
    )

    return (
        <>
            <table className="bg-iso-offwhite border-solid rounded-md"{...getTableProps()}>
                <thead className="bg-iso-light-gray">
                    {headerGroups.map((headerGroup) => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {headerGroup.headers.map((column) => (
                            <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                                {column.render('Header')}
                                <span className="display: inline-block">
                                {column.isSorted ? (column.isSortedDesc ? 
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 15.75l7.5-7.5 7.5 7.5" />
                                    </svg>
                                    : 
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 8.25l-7.5 7.5-7.5-7.5" />
                                    </svg>
                                  ) : 
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
                <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={() => previousPage()}>Previous</button>
                <button className="bg-iso-offwhite p-1 border-solid border-2" onClick={() => nextPage()}>Next</button>
            </div>
        </>
    )
}