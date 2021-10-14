//equipment form
var equipmentComp = {
    url: "./view-equipments",
    method: "GET",
    tableTitle: 'Equipments',
    renderTo: 'componentRender',
    columns: [{
        header: "Equipment Label",
        dataIndex: "equipmentLabel",
    },{
        header: "Equipment Condition",
        dataIndex: "equipmentCondition",

    },{
        header: "Quantity",
        dataIndex: "equipmentQuantity",
    }],
    buttons: [{
        label: 'Add',
        id: 'addEquipment',
        handler: function(){
            AppComponents.htmlForm.render.call({

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
                buttons: [{
                    btnDiv: "d-grid gap-2 col-6 mx-auto",
                    type: 'submit',
                    url: "./add-equipment",
                    method: "POST",
                    value: 'Save',
                    id: 'addTransaction',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(equipmentComp);
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(equipmentComp);
                    }
                }]
            });
        }
    }]
};