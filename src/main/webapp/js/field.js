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
    submitBtn: {
        btnDiv:"d-grid gap-2 col-6 mx-auto",
        type: 'submit',
        value: 'Save',
        btnClass:"btn btn-success"
    }
});