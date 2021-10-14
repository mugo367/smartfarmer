var transactionComp ={
    url: "./view-transactions",
    method: "GET",
    tableTitle: 'Transactions',
    renderTo: 'componentRender',
    columns: [{
        header: "Transaction Date",
        dataIndex: "transactionDate",
    },{
        header: "Type",
        dataIndex: "transactionType",

    },{
        header: "Transaction Label",
        dataIndex: "transactionLabel",
    },
        {
            header: "Transaction Cost",
            dataIndex: "transactionCost",
        },
        {
            header: "Details",
            dataIndex: "transactionDetails",

        }
    ],
    buttons: [{
        label: 'Add',
        id: 'addTransaction',
        handler: function (){
            AppComponents.htmlForm.render.call({

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
                buttons:[{
                    btnDiv: "d-grid gap-2 col-6 mx-auto",
                    type: 'submit',
                    url: "./add-transaction",
                    method: "POST",
                    value: 'Save',
                    id: 'addTransaction',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(transactionComp);
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(transactionComp);
                    }
                }]
            }) ;
        }
    }]
};