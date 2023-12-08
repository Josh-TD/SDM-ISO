const ExcelTable = ({ excelData }) => (
    <table className="ExcelTable2007">
        <thead className="heading">
        <tr>
            {excelData.cols.map((col, index) => (
                <th key={index}>{typeof col === 'object' ? col.name : col}</th>
            ))}
        </tr>
        </thead>
        <tbody>
        {excelData.rows.map((row, rowIndex) => (
            <tr key={rowIndex}>
                {row.map((cell, cellIndex) => (
                    <td key={cellIndex}>{typeof cell === 'object' ? cell.name : cell}</td>
                ))}
            </tr>
        ))}
        </tbody>
    </table>
);

export default ExcelTable;