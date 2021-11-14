var salariesComp = {
    url: "./view-salaries",
    method: "GET",
    tableTitle: 'Salaries And Remunerations',
    renderTo: 'componentRender',
    id:'activityTable',
    columns: [
        {
            id:true,
            dataIndex: "id",
        },{
            header: "Name",
            dataIndex: "employeeName",
        },{
            header: "Salary",
            dataIndex: "salary",
        },
        {
            header: "Additional Information",
            dataIndex: "info",
        }
        ],
    buttons: [{
        label: 'Add',
        class: 'btn btn-dark',
        id: 'addRecord',
        handler: function(){
            AppComponents.htmlForm.render.call({
                formTitle: 'Add New Record',
                items: [{
                    label: "Employee Name",
                    name: "employeeId",
                    id: "employeeId",
                    type: "select",
                    labelClass: "form-label",
                    divClass: "mb-3",
                    required: true,
                    select: {
                        url: './view-employees',
                        optionMap:{value: 'id', display: 'employeeName'}
                    }
                } ,{
                    label: "Salary",
                    name: "salary",
                    id: "salary",
                    type: "number",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }, {
                    label: "Additional Information",
                    name: "info",
                    id: "info",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }],
                buttons: [
                    {
                        type: 'cancel',
                        value: 'Cancel',
                        id: 'cancel',
                        btnClass: "btn btn-success",
                        handler: function(){
                            AppComponents.htmlTable.render.apply(salariesComp);
                        }
                    },{
                        type: 'submit',
                        url: "./add-salary",
                        method: "POST",
                        value: 'Save',
                        id: 'add-salary',
                        btnClass: "btn btn-success",
                        showMsg: 'showErrorMsg',
                        success: function(){
                            AppComponents.htmlTable.render.apply(salariesComp);
                            swal("Done!", "Record was added successfully", "success");
                        },
                        failure: function(){
                            AppComponents.htmlTable.render.apply(salariesComp);
                            swal("Failed!", "An issue occured please try again", "error");
                        }
                    }]
            });
        }
    }, {
        label: 'Delete',
        id: 'deleteSalary',
        url: './delete-salary',
        class:'btn btn-danger',
        method: 'DELETE',
        handler: function(){
            AppComponents.htmlTable.render.apply(salariesComp);
        }
    }]
};