var fieldComp = {
    url: "./view-fields",
    method: "GET",
    tableTitle: 'Fields',
    renderTo: 'componentRender',
    columns: [{
        header: "Field Label",
        dataIndex: "fieldLabel",
    },{
        header: "Name",
        dataIndex: "fieldName",

    },{
        header: "Size",
        dataIndex: "fieldSize",
    },{
            header: "Status",
            dataIndex: "fieldStatus",
    }],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function(){
        AppComponents.htmlForm.render.call({
            url: "./add-field",
            method: "POST",
            formTitle: 'Add New Field',
            renderId: "fieldForm",
            items: [{
                label: "Field Label",
                name: "fieldLabel",
                id: "fieldLabel",
                type: "text",
                divClass: "mb-3",
                inputClass: "form-control",
                labelClass: "form-label"
            },{
                label: "Field Name",
                name: "fieldName",
                id: "fieldName",
                type: "text",
                divClass: "mb-3",
                inputClass: "form-control",
                labelClass: "form-label"
            },{
                label: "Field Size",
                name: "fieldSize",
                id: "fieldSize",
                type: "text",
                divClass: "mb-3",
                inputClass: "form-control",
                labelClass: "form-label"
            }
            ],
            selects : [
                {
                    labelTitle: "Field Status",
                    id: "fieldStatus",
                    name: "fieldStatus",
                    labelClass: "form-label",
                    option:"Field Status",
                    values: ["Planted", "NotPlanted"]
                },
            ],
            buttons: [{
                btnDiv: "d-grid gap-2 col-6 mx-auto",
                type: 'submit',
                url: "./add-field",
                method: "POST",
                value: 'Save',
                id: 'addActivity',
                btnClass: "btn btn-success",
                showMsg: 'showErrorMsg',
                success: function(){
                    AppComponents.htmlTable.render.apply(fieldComp);
                },
                failure: function(){
                    AppComponents.htmlTable.render.apply(fieldComp);
                }
            }]
        });
        }
    }]
};