var transactionComp ={
    url: "./view-transactions",
    method: "GET",
    tableTitle: 'Transactions',
    id:'transactionTable',
    renderTo: 'componentRender',
    rowId:'transactionLabel',
    columns: [ {
        id:true,
        dataIndex: "id",
    },{
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
    buttons: [
        {
        label: 'Add',
        id: 'addTransaction',
            class: 'btn btn-dark',
        handler: function (){
            AppComponents.htmlForm.render.call({

                formTitle: 'Add New Transaction',
                renderId: "transactionForm",
                items: [
                    {
                        label: "Transaction Type",
                        id: "transactionTypeStr",
                        name: "transactionTypeStr",
                        labelClass: "form-label",
                        option:"Transaction Type",
                        type: "radio",
                        divClass: "mb-3",
                        required: true,
                        options:{
                            data:[{id:'Income', type:'Income'},{id:'Expense', type:'Expense'}],
                            optionMap: {value:'id', display:'type'}
                        }
                    },{
                        label: "Transaction Date",
                        name: "transactionDate",
                        id: "transactionDate",
                        type: "date",
                        divClass: "mb-3",
                        inputClass: "form-control",
                        required: true,
                        labelClass: "form-label"
                    },{
                        label: "Transaction Label",
                        name: "transactionLabel",
                        id: "transactionLabel",
                        type: "text",
                        divClass: "mb-3",
                        required: true,
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },{
                        label: "Cost Per Unit",
                        name: "costPerUnit",
                        id: "costPerUnit",
                        type: "text",
                        required: true,
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
                        required: true,
                        inputClass: "form-control",
                        labelClass: "form-label"
                    },
                    {
                        label: "Transaction Details",
                        name: "transactionDetails",
                        id: "transactionDetails",
                        type: "text",
                        divClass: "mb-3",
                        required: true,
                        inputClass: "form-control",
                        labelClass: "form-label"
                    }
                ],
                buttons:[ {
                    type: 'cancel',
                    value: 'Cancel',
                    id: 'cancel',
                    btnClass: "btn btn-success",
                    handler: function(){
                        AppComponents.htmlTable.render.apply(transactionComp);
                    }
                }, {
                    type: 'submit',
                    url: "./add-transaction",
                    method: "POST",
                    value: 'Save',
                    id: 'addTransaction',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(transactionComp);
                        swal("Done!", "Record was added successfully", "success");
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(transactionComp);
                        swal("Failed!", "An issue occured please try again", "error");
                    }
                }

                ]
            });
        }
        },
        {
            label: 'Delete',
            id: 'deleteTransactions',
            method: 'DELETE',
            class:'btn btn-danger',
            url: './delete-transaction',
            handler: function(){

                AppComponents.htmlTable.render.apply(transactionComp);
            }
        },
        {
            label: 'Refresh',
            id: 'refresh',
            class:'btn btn-primary',
            handler: function(){
                AppComponents.htmlTable.render.apply(salariesComp);
            }
        }]
};