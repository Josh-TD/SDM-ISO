import React, { useEffect, useState } from "react";
import axios from 'axios';

export function AdvancedSearch({onClosePopup, onAdvancedSearchPressed}) {
    const endpoint = "http://localhost:8080/api/v3/files/list";
    const pageNum = 0;
    const pageSize = 10;
    const sortBy = "createDate";
    const sortAsc = "false";

    const [data, setFileData] = useState(null)
    const [selectedCustomerName, setCustomerName] = useState('');
    const [selectedProposalName, setProposalName] = useState('');
    const [selectedProjectName, setProjectName] = useState('');
    const [selectedFileDescription, setFileDescription] = useState('');

    const [loading, setLoading] = useState(false);

    const fetchFiles = (selectedCustomerName, selectedProposalName, selectedProjectName, selectedFileDescription) => {
        var basic_url = endpoint + `?pageNum=${pageNum}&pageSize=${pageSize}&sortBy=${sortBy}&sortAsc=${sortAsc ? "true" : "false"}`;


        if (selectedCustomerName != ''){
            basic_url = basic_url + `${"&customerName=" + selectedCustomerName}`
        }

        if (selectedProposalName != ''){
            basic_url = basic_url + `${"&proposalName=" + selectedProposalName}`
        }

        if (selectedProjectName != ''){
            basic_url = basic_url + `${"&projectName=" + selectedProjectName}`
        }

        if (selectedFileDescription != ''){
            basic_url = basic_url + `${"&fileDescription=" + selectedFileDescription}`
        }

        axios.get(basic_url).then((res) => {
            onAdvancedSearchPressed(res.data.content)
        });
    };

    const performSearch = () => {
        setLoading(true);
        fetchFiles(selectedCustomerName, selectedProposalName, selectedProjectName, selectedFileDescription);
        onClosePopup();
        setTimeout(() => {setLoading(false)}, 10000);
    }

    return (
        <div className="w-full">
            <p className="text-xl pb-2 text-iso-dark-text">Advanced Search</p>
            <div className="w-full">
                <div className="flex justify-end py-2 pr-4">
                    <label className="text-iso-dim-gray justify-right font-regular pr-2" htmlFor="customer_name">Customer
                        Name:</label>
                    <input
                        className="placeholder:text-iso-light-gray placeholder:font-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2"
                        placeholder="Customer Name"
                        onChange={(e) => setCustomerName(e.target.value)}
                    />
                </div>
                <div className="flex justify-end py-2 pr-4">
                    <label className="text-iso-dim-gray justify-right font-regular pr-2" htmlFor="proposal_name">Proposal
                        Name:</label>
                    <input
                        className="placeholder:text-iso-light-gray placeholder:font-light text-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2"
                        placeholder="Proposal Name"
                        onChange={(e) => setProposalName(e.target.value)}
                    />
                </div>
                <div className="flex justify-end py-2 pr-4">
                    <label className="text-iso-dim-gray justify-right font-regular pr-2" htmlFor="project_name">Project
                        Name:</label>
                    <input
                        className="placeholder:text-iso-light-gray placeholder:font-light pl-2 border border-iso-border-light shadow cursor-text h-7 w-1/2"
                        placeholder="Project Name"
                        onChange={(e) => setProjectName(e.target.value)}
                    />
                </div>
                <div className="flex justify-end py-2 pr-4">
                    <label className="text-iso-dim-gray justify-right font-regular pr-2" htmlFor="file_name">File
                        Description:</label>
                    <input
                        className="placeholder:text-iso-light-gray placeholder:font-light border border-iso-border-light shadow cursor-text h-7 w-1/2 pl-2"
                        placeholder="File Description"
                        onChange={(e) => setFileDescription(e.target.value)}
                    />
                </div>
            </div>
            <div className="flex w-full justify-end">
                <button
                    className="mr-6 mt-2 bg-iso-light-slate hover:bg-iso-link-blue text-white font-semibold py-1 px-5 rounded cursor-pointer justify-self-end"
                    onClick={performSearch}
                >
                    Search
                </button>
            </div>
        </div>
    );
};