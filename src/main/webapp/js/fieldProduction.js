var fieldProductionComp = {
    url: "./view-fieldProduction",
    method: "GET",
    tableTitle: 'Started Productions',
    renderTo: 'componentRender',
    class: 'btn btn-dark',
    id:'production',
    columns: [
        {
            id:true,
            dataIndex: "id",
        },{
            header: "Field Name",
            dataIndex: "fieldName",
        },{
            header: "Start Date",
            dataIndex: "startDate",
        },
        {
            header: "Crop Planted",
            dataIndex: "crop",
        },
        {
            header: "Additional Information",
            dataIndex: "description",
        }],
    buttons: [{
        label: 'Add',
        id: 'startProduction',
        class: 'btn btn-dark',
        handler: function(){
            AppComponents.htmlForm.render.call({
                formTitle: 'Start New Production',
                items: [
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
                        label: "Start Date",
                        name: "startDate",
                        id: "startDate",
                        type: "Date",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Crop Planted",
                        name: "crop",
                        id: "crop",
                        type: "text",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Additional Information",
                        name: "description",
                        id: "description",
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
                            AppComponents.htmlTable.render.apply(fieldProductionComp);
                        }
                    },{
                        type: 'submit',
                        url: "./add-fieldProduction",
                        method: "POST",
                        value: 'Save',
                        id: 'addProduction',
                        btnClass: "btn btn-success",
                        showMsg: 'showErrorMsg',
                        required: true,
                        success: function(){
                            AppComponents.htmlTable.render.apply(fieldProductionComp);
                            swal("Done!", "Record was added successfully", "success");
                        },
                        failure: function(){
                            AppComponents.htmlTable.render.apply(fieldProductionComp);
                            swal("Failed!", "An issue occured please try again", "error");
                        }
                    }]
            });
        }
    }, {
        label: 'Delete',
        id: 'deleteFieldProduction',
        url: './delete-fieldProduction',
        class:'btn btn-danger',
        method: 'DELETE',
        handler: function(){
            AppComponents.htmlTable.render.apply(fieldProductionComp);
        }
    },{
        label: 'Refresh',
        id: 'refresh',
        class:'btn btn-primary',
        handler: function(){
            AppComponents.htmlTable.render.apply(fieldProductionComp);
        }
    },]
};