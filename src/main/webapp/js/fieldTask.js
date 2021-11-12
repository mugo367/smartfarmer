var tasksComp = {
    url: "./view-tasks",
    method: "GET",
    tableTitle: 'Scheduled Activities',
    renderTo: 'componentRender',
    class: 'btn btn-dark',
    id:'taskTable',
    columns: [
        {
            id:true,
            dataIndex: "id",
        },{
            header: "Field Name",
            dataIndex: "fieldName",
        },{
            header: "Activity Name",
            dataIndex: "activityName",

        },{
            header: "Start Date",
            dataIndex: "startDate",
        },
        {
            header: "End Date",
            dataIndex: "endDate",
        },
        {
            header: "Description",
            dataIndex: "activityDescription",
        }],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        class: 'btn btn-dark',
        handler: function(){
            AppComponents.htmlForm.render.call({
                formTitle: 'Add New Task',
                items: [
                    {
                        label: "Activity Name",
                        name: "activityId",
                        id: "activityId",
                        type: "select",
                        labelClass: "form-label",
                        divClass: "mb-3",
                        select: {
                            url: './view-activities',
                            optionMap:{value: 'id', display: 'activityName'}
                        }
                    },
                    {
                    label: "Field Name",
                    name: "fieldId",
                    id: "fieldId",
                    type: "select",
                    labelClass: "form-label",
                    divClass: "mb-3",
                    select: {
                        url: './view-fields',
                        optionMap:{value: 'id', display: 'fieldName'}
                    }
                }, {
                    label: "Employee incharge",
                    name: "employeeId",
                    id: "employeeId",
                    type: "select",
                    labelClass: "form-label",
                    divClass: "mb-3",
                    select: {
                        url: './view-employees',
                        optionMap:{value: 'id', display: 'employeeName'}
                    }
                },{
                    label: "Start Date",
                    name: "startDate",
                    id: "startDate",
                    type: "Date",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },
                {
                    label: "End Date",
                    name: "endDate",
                    id: "endDate",
                    type: "Date",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },
                {
                    label: "Activity Description",
                    name: "activityDescription",
                    id: "activityDescription",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }],
                buttons: [
                    {
                        type: 'cancel',
                        value: 'Cancel',
                        id: 'cancel',
                        btnClass: "btn btn-success",
                        handler: function(){
                            AppComponents.htmlTable.render.apply(tasksComp);
                        }
                    },{
                        type: 'submit',
                        url: "./add-task",
                        method: "POST",
                        value: 'Save',
                        id: 'addActivity',
                        btnClass: "btn btn-success",
                        showMsg: 'showErrorMsg',
                        required: true,
                        success: function(){
                            AppComponents.htmlTable.render.apply(tasksComp);
                            swal("Done!", "Record was added successfully", "success");
                        },
                        failure: function(){
                            AppComponents.htmlTable.render.apply(tasksComp);
                            swal("Failed!", "An issue occured please try again", "error");
                        }
                    }]
            });
        }
    }, {
        label: 'Delete',
        id: 'deleteActivity',
        url: './delete-task',
        class:'btn btn-danger',
        method: 'DELETE',
        handler: function(){
            AppComponents.htmlTable.render.apply(tasksComp);
        }
    }]
};