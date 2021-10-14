var employeeComp = {
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
        },


    ],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function (){
            AppComponents.htmlForm.render.call({
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
                }, {
                        labelTitle: "Employee Gender",
                        id: "employeeGender",
                        name: "employeeGender",
                        type: "radio",
                        labelClass: "form-label",
                        divClass: "mb-3",
                        options:{
                            data:[{id:'Male', gender:'Male'},{id:'Female', gender:'Female'}],
                            optionMap: {value:'id', display:'gender'}
                        }
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
                    },
                    {
                        labelTitle: "Employee Designation",
                        id: "employeeDesignation",
                        name: "employeeDesignation",
                        type: "select",
                        labelClass: "form-label",
                        divClass: "mb-3",
                        select: {
                            data:[{
                                id:'Manager', designation:'Manager'},
                                {id:'Farmhand', designation:'Farmhand'},
                                {id:'Accountant', designation:'Accountant'},
                                {id:'Stockman', designation:'Stockman'}
                            ],
                            optionMap:{value: 'id', display: 'designation'}
                        }
                    },
                    {
                        labelTitle: "Employee Type",
                        id: "employeeType",
                        name: "employeeType",
                        type: "select",
                        labelClass: "form-label",
                        divClass: "mb-3",
                        select: {
                            data:[{
                                id:'FullTime', type:'FullTime'},
                                {id:'Casual', type:'Casual'},
                                {id:'Contract', type:'Contract'}
                            ],
                            optionMap:{value: 'id', display: 'type'}
                        }
                    }],
                buttons: [{
                    btnDiv:"d-grid gap-2 d-md-flex justify-content-md-end",
                    type: 'submit',
                    value: 'Save',
                    btnClass:"btn btn-success btn-lg",
                    id: 'addemployee',
                    url: "./add-employee",
                    method: "POST",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(employeeComp);
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(employeeComp);
                    }
                }]
            });
        }
    },
        {
            label: 'Delete',
            id: 'deleteActivity'
        }]
};