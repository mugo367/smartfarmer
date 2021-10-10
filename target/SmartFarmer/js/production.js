AppComponents.htmlForm.render.call({
    url: "./add-production",
    method: "POST",
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
    submitBtn: {
        btnDiv:"d-grid gap-2 col-6 mx-auto",
        type: 'submit',
        value: 'Save',
        btnClass:"btn btn-success"
    }
});