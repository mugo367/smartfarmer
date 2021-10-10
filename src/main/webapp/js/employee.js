AppComponents.htmlTable.render.apply({
    url: "./view-employees",
    method: "GET",
    tableTitle: 'Employees',
    renderTo: 'componentRender',
    columns: [{
        header: "Employee Number",
        dataIndex: "employeeNumber",
    },{
        header: "Name",
        dataIndex: "employeeName",

    },{
        header: "ID Number",
        dataIndex: "idNumber",
    },
    {
        header: "Gender",
        dataIndex: "employeeGender",
    },
    {
        header: "Email",
        dataIndex: "employeeEmail",

    },
    {
        header: "Contact",
        dataIndex: "employeeContact",
    },
    {
        header: "Emergency Contact",
        dataIndex: "employeeEmergencyContact",
    },
    {
        header: "Date of Employment",
        dataIndex: "employeeDateOfEmp",

    },
    {
        header: "Designation",
        dataIndex: "employeeDesignation",
    },
        {
            header: "Type",
            dataIndex: "employeeType",
        }

    ],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function (){
            AppComponents.htmlForm.render.call({
                url: "./add-employee",
                method: "POST",
                formTitle: 'Add New Employee',
                renderId: "employeeForm",
                items: [{
                    label: "Employee Number",
                    name: "employeeNumber",
                    id: "employeeNumber",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },{
                    label: "Employee Name",
                    name: "employeeName",
                    id: "employeeName",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },{
                    label: "ID Number",
                    name: "idNumber",
                    id: "idNumber",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },
                    {
                        label: "Employee Email",
                        name: "employeeEmail",
                        id: "employeeEmail",
                        type: "text",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Employee Contact",
                        name: "employeeContact",
                        id: "employeeContact",
                        type: "text",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Employee Emergency Contact",
                        name: "employeeEmergencyContact",
                        id: "employeeEmergencyContact",
                        type: "text",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Date of Employment",
                        name: "employeeDateOfEmp",
                        id: "employeeDateOfEmp",
                        type: "Date",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    }],
                selects : [
                    {
                        labelTitle: "Employee Gender",
                        id: "employeeGender",
                        name: "employeeGender",
                        labelClass: "form-label",
                        values: ["Male", "Female"]
                    },
                    {
                        labelTitle: "Employee Designation",
                        id: "employeeDesignation",
                        name: "employeeDesignation",
                        labelClass: "form-label",
                        values: ["Manager", "Farmhand", "Accountant", "Stockman"]
                    },
                    {
                        labelTitle: "Employee Type",
                        id: "employeeType",
                        name: "employeeType",
                        labelClass: "form-label",
                        values: ["FullTime", "Casual", "Contract"]
                    },

                ],
                submitBtn: {
                    btnDiv:"d-grid gap-2 col-6 mx-auto",
                    type: 'submit',
                    value: 'Save',
                    btnClass:"btn btn-success"
                }
            });
        }
    }]
});