//equipment form
AppComponents.htmlForm.render.call({
    url: "./add-equipment",
    method: "POST",
    formTitle: 'Add New Equipment',
    renderId: "equipmentForm",
    items: [{
        label: "Equipment Label",
        name: "equipmentLabel",
        id: "equipmentLabel",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    }, {
        label: "Equipment Name",
        name: "equipmentName",
        id: "equipmentName",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    }, {
        label: "Equipment Quantity",
        name: "equipmentQuantity",
        id: "equipmentQuantity",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    }
    ],
    selects: [
        {
            labelTitle: "Equipment Condition",
            id: "equipmentCondition",
            name: "equipmentCondition",
            labelClass: "form-label",
            option: "Equipment Condition",
            values: ["Working", "NotWorking", "UnderMaintenance"]
        },
    ],
    submitBtn: {
        btnDiv: "d-grid gap-2 col-6 mx-auto",
        type: 'submit',
        value: 'Save',
        btnClass: "btn btn-success"
    }
});