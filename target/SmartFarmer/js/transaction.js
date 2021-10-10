//transaction form
AppComponents.htmlForm.render.call({
    url: "./add-transaction",
    method: "POST",
    formTitle: 'Add New Transaction',
    renderId: "transactionForm",
    items: [{
        label: "Transaction Date",
        name: "transactionDate",
        id: "transactionDate",
        type: "date",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    },{
        label: "Transaction Label",
        name: "transactionLabel",
        id: "transactionLabel",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    },{
        label: "Cost Per Unit",
        name: "costPerUnit",
        id: "costPerUnit",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    },
        {
            label: "UnitS",
            name: "units",
            id: "units",
            type: "text",
            divClass: "mb-3",
            inputClass: "form-control",
            labelClass: "form-label"
        },
        {
            label: "Transaction Details",
            name: "transactionDetails",
            id: "transactionDetails",
            type: "text",
            divClass: "mb-3",
            inputClass: "form-control",
            labelClass: "form-label"
        }
    ],
    selects : [
        {
            labelTitle: "Transaction Type",
            id: "transactionType",
            name: "transactionType",
            labelClass: "form-label",
            option:"Transaction Type",
            values: ["Income", "Expense"]
        },
    ],
    submitBtn: {
        btnDiv:"d-grid gap-2 col-6 mx-auto",
        type: 'submit',
        value: 'Save',
        btnClass:"btn btn-success"
    }
});