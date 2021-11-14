var activitiesComp = {
    url: "./view-activities",
    method: "GET",
    tableTitle: 'Activities',
    renderTo: 'componentRender',
    id:'activityTable',
    columns: [
        {
        id:true,
        dataIndex: "id",
    },{
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
        class: 'btn btn-dark',
        id: 'addActivity',
        handler: function(){
            AppComponents.htmlForm.render.call({
                formTitle: 'Add New Activity',
                items: [{
                    label: "Activity Label",
                    name: "activityLabel",
                    id: "activityLabel",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }, {
                    label: "Activity Name",
                    name: "activityName",
                    id: "activityName",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }, {
                    label: "Activity Description",
                    name: "activityDescription",
                    id: "activityDescription",
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
                            AppComponents.htmlTable.render.apply(activitiesComp);
                        }
                    },{
                    type: 'submit',
                    url: "./add-activity",
                    method: "POST",
                    value: 'Save',
                    id: 'addActivity',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(activitiesComp);
                        swal("Done!", "Record was added successfully", "success");
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(activitiesComp);
                        swal("Failed!", "An issue occured please try again", "error");
                    }
                }]
            });
        }
    }, {
        label: 'Delete',
        id: 'deleteActivity',
        url: './delete-activity',
        class:'btn btn-danger',
        method: 'DELETE',
        handler: function(){
            AppComponents.htmlTable.render.apply(activitiesComp);
        }
    },{
        label: 'Refresh',
        id: 'refresh',
        class:'btn btn-primary',
        handler: function(){
            AppComponents.htmlTable.render.apply(activitiesComp);
        }
    },]
};