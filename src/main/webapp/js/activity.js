AppComponents.htmlTable.render.apply({
    url: "./view-activities",
    method: "GET",
    tableTitle: 'Activities',
    renderTo: 'componentRender',
    columns: [{
        header: "Activity Label",
        dataIndex: "activityLabel",
    },{
        header: "Name",
        dataIndex: "activityName",

    },{
        header: "Description",
        dataIndex: "activityDescription",
    }],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function(){
            AppComponents.htmlForm.render.call({
                url: "./add-activity",
                method: "POST",
                formTitle: 'Add New Activity',
                renderId: "activityForm",
                items: [{
                    label: "Activity Label",
                    name: "activityLabel",
                    id: "activityLabel",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }, {
                    label: "Activity Name",
                    name: "activityName",
                    id: "activityName",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }, {
                    label: "Activity Description",
                    name: "activityDescription",
                    id: "activityDescription",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }],
                selects: {},
                submitBtn: {
                    btnDiv: "d-grid gap-2 col-6 mx-auto",
                    type: 'submit',
                    value: 'Save',
                    btnClass: "btn btn-success"
                }
            });
        }
    }]
});