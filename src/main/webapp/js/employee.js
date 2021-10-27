var employeeComp = {
    url: "./view-employees",
    method: "GET",
    tableTitle: 'Employees',
    renderTo: 'componentRender',
    id:'employeeTable',
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
                        label: "Employee Gender",
                        id: "employeeGenderStr",
                        name: "employeeGenderStr",
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
                        label: "Employee Designation",
                        id: "employeeDesignationStr",
                        name: "employeeDesignationStr",
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
                        label: "Employee Type",
                        id: "employeeTypeStr",
                        name: "employeeTypeStr",
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
                buttons: [
                    {

                        type: 'cancel',
                        value: 'Cancel',
                        id: 'cancel',
                        btnClass: "btn btn-success",
                        handler: function(){
                            AppComponents.htmlTable.render.apply(employeeComp);
                        }
                    },{

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
            id: 'deleteEmployees',
            handler: function(){
                //Reference the Table.
                let tableRef = document.getElementById(employeeComp.id);
                //Reference the CheckBoxes in Table.
                let checkBoxes = tableRef.getElementsByTagName("INPUT");

                let checkedEmployees = [];
                //Loop through the CheckBoxes.
                for (let i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].checked) {
                        let row = checkBoxes[i].parentNode.parentNode;
                        checkedEmployees.push(row.cells[1].innerHTML);
                    }
                }
                console.log(checkedEmployees)
                //make ajax request to delete record
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (xhr.readyState === XMLHttpRequest.DONE){
                        if (xhr.status === 200){
                            console.log(xhr.responseText);
                        }
                    }
                }

                console.log(JSON.stringify(checkedEmployees))

                xhr.open("DELETE", "./delete-employee", false);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("employeeNumbers="+JSON.stringify(checkedEmployees));

                AppComponents.htmlTable.render.apply(employeeComp);
            }
        }]
};