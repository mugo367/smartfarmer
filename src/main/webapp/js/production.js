var productionComp = {
    url: "./view-productions",
    method: "GET",
    tableTitle: 'Productions',
    renderTo: 'componentRender',
    id:'productionTable',
    columns: [ {
        id:true,
        dataIndex: "id",
    },{
        header: "Production Label",
        dataIndex: "productionLabel",
    },{
        header: "Date",
        dataIndex: "productionDate",

    },{
        header: "Field",
        dataIndex: "fieldName",
    },
    {
        header: "Quantity",
        dataIndex: "productionQuantity",
    },
        {
            header: "Unit",
            dataIndex: "unit",
        },
    {
        header: "Details",
        dataIndex: "productionDetails",
    }
        ],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        class: 'btn btn-dark',
        handler: function(){
            AppComponents.htmlForm.render.call({
            formTitle: 'Add New Production',
            renderId: "productionForm",
            items: [{
                label: "Production Date",
                name: "productionDate",
                id: "productionDate",
                type: "date",
                divClass: "mb-3",
                inputClass: "form-control",
                required: true,
                labelClass: "form-label"
            },{
                label: "Production Label",
                name: "productionLabel",
                id: "productionLabel",
                type: "text",
                divClass: "mb-3",
                inputClass: "form-control",
                required: true,
                labelClass: "form-label"
            },{
                label: "Field Name",
                name: "fieldId",
                id: "fieldId",
                type: "select",
                labelClass: "form-label",
                divClass: "mb-3",
                required: true,
                select: {
                    url: './view-fields',
                    optionMap:{value: 'id', display: 'fieldName'}
                }
            },
                {
                    label: "Production Details",
                    name: "productionDetails",
                    id: "productionDetails",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                },
                {
                    label: "Production Quantity",
                    name: "productionQuantity",
                    id: "productionQuantity",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                },
                {
                    label: "Unit",
                    id: "unitStr",
                    name: "unitStr",
                    labelClass: "form-label",
                    option:"Unit",
                    type: "radio",
                    divClass: "mb-3",
                    required: true,
                    options:{
                        data:[{id:'Kgs', type:'Kgs'},{id:'Bags', type:'Bags'}],
                        optionMap: {value:'id', display:'type'}
                    }
                },
            ],
            buttons: [ {
                type: 'cancel',
                value: 'Cancel',
                id: 'cancel',
                btnClass: "btn btn-success",
                handler: function(){
                    AppComponents.htmlTable.render.apply(productionComp);
                }
            },{
                type: 'submit',
                url: "./add-production",
                method: "POST",
                value: 'Save',
                id: 'addActivity',
                btnClass: "btn btn-success",
                showMsg: 'showErrorMsg',
                success: function(){
                    AppComponents.htmlTable.render.apply(productionComp);
                    swal("Done!", "Record was added successfully", "success");
                },
                failure: function(){
                    AppComponents.htmlTable.render.apply(productionComp);
                    swal("Failed!", "An issue occured please try again", "error");
                }
            }]
            });
        }
    },
        {
            label: 'Delete',
            id: 'deleteProduction',
            method: 'DELETE',
            class:'btn btn-danger',
            url: './delete-production',
            handler: function(){
                AppComponents.htmlTable.render.apply(productionComp);
            }
        },
        {
            label: 'Refresh',
            id: 'refresh',
            class:'btn btn-primary',
            handler: function(){
                AppComponents.htmlTable.render.apply(productionComp);
            }
        },]
};