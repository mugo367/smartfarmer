var fieldComp = {
    url: "./view-fields",
    method: "GET",
    tableTitle: 'Fields',
    renderTo: 'componentRender',
    id:'fieldTable',
    columns: [ {
        id:true,
        dataIndex: "id",
    },
        {
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
            },
                {
                    label: "Field Status",
                    id: "fieldStatusStr",
                    name: "fieldStatusStr",
                    labelClass: "form-label",
                    option:"Field Status",
                    type: "radio",
                    divClass: "mb-3",
                    options:{
                        data:[{id:'Planted', status:'Planted'},{id:'NotPlanted', status:'NotPlanted'}],
                        optionMap: {value:'id', display:'status'}
                    }
                },
            ],
            buttons: [
                {
                    btnDiv: "d-grid gap-2 d-md-flex justify-content-md-end",
                    type: 'cancel',
                    value: 'Cancel',
                    id: 'cancel',
                    btnClass: "btn btn-success",
                    handler: function(){
                        AppComponents.htmlTable.render.apply(fieldComp);
                    }
                },{
                btnDiv: "d-grid gap-2 d-md-flex justify-content-md-end",
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
    },
        {
            label: 'Delete',
            id: 'deleteFields',
            url: './delete-field',
            method: 'DELETE',
            handler: function(){
                AppComponents.htmlTable.render.apply(fieldComp);
            }
        }
    ]
};