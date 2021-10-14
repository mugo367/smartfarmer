var productionComp = {
    url: "./view-productions",
    method: "GET",
    tableTitle: 'Productions',
    renderTo: 'componentRender',
    columns: [{
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
                labelClass: "form-label"
            },{
                label: "Production Label",
                name: "productionLabel",
                id: "productionLabel",
                type: "text",
                divClass: "mb-3",
                inputClass: "form-control",
                labelClass: "form-label"
            },
                {
                    label: "Production Details",
                    name: "productionDetails",
                    id: "productionDetails",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },
                {
                    label: "Production Quantity",
                    name: "productionQuantity",
                    id: "productionQuantity",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                },
            ],
            selects : [
                {
                    labelTitle: "Unit",
                    id: "unit",
                    name: "unit",
                    labelClass: "form-label",
                    option:"Unit",
                    values: ["Bags", "Kgs"]
                },
                {
                    labelTitle: "Field",
                    id: "fieldName",
                    name: "fieldName",
                    labelClass: "form-label",
                    option:"Field",
                    values: []
                },
            ],
            buttons: [{
                btnDiv: "d-grid gap-2 col-6 mx-auto",
                type: 'submit',
                url: "./add-production",
                method: "POST",
                value: 'Save',
                id: 'addActivity',
                btnClass: "btn btn-success",
                showMsg: 'showErrorMsg',
                success: function(){
                    AppComponents.htmlTable.render.apply(productionComp);
                },
                failure: function(){
                    AppComponents.htmlTable.render.apply(productionComp);
                }
            }]
            });
        }
    }]
};