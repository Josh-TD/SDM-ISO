import React from "react";
import { useMemo } from 'react';
import mockData from "./mockData.json";
import { useTable } from "react-table";

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
        accessor: 'fDescript',
        Header: 'Description',
        cell: (props) => <p>{props.getValue()}</p>
    },
]

export const FileTable = () => {
    const columns = useMemo(() => COLUMNS, [])
    const data = useMemo(() => mockData, [])
    
    const tableInstance = useTable({
        columns,
        data
    })

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow} = tableInstance

    return (
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
                {rows.map((row) => {
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
    )
}