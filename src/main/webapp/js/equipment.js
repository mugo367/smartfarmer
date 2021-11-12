//equipment form
var equipmentComp = {
    url: "./view-equipments",
    method: "GET",
    tableTitle: 'Equipments',
    renderTo: 'componentRender',
    id:'equipmentTable',
    columns: [ {
        id:true,
        dataIndex: "id",
    },{
        header: "Equipment Label",
        dataIndex: "equipmentLabel",
    },
        {
            header: "Equipment Name",
            dataIndex: "equipmentName",
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
        class: 'btn btn-dark',
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
                    required: true,
                    labelClass: "form-label"
                }, {
                    label: "Equipment Name",
                    name: "equipmentName",
                    id: "equipmentName",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }, {
                    label: "Equipment Quantity",
                    name: "equipmentQuantity",
                    id: "equipmentQuantity",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    required: true,
                    labelClass: "form-label"
                }
                    ,
                    {
                        label: "Employee Condition",
                        id: "equipmentConditionStr",
                        name: "equipmentConditionStr",
                        type: "select",
                        labelClass: "form-label",
                        divClass: "mb-3",
                        required: true,
                        select: {
                            data:[{
                                id:'Working', condition:'Working'},
                                {id:'NotWorking', condition:'Working'},
                                {id:'UnderMaintenance', condition:'Working'}
                            ],
                            optionMap:{value: 'id', display: 'condition'}
                        }
                    },
                ],
                selects: [
                    {
                        label: "Equipment Condition",
                        id: "equipmentCondition",
                        name: "equipmentCondition",
                        labelClass: "form-label",
                        option: "Equipment Condition",
                        required: true,
                        values: ["Working", "NotWorking", "UnderMaintenance"]
                    },
                ],
                buttons: [
                    {
                        type: 'cancel',
                        value: 'Cancel',
                        id: 'cancel',
                        btnClass: "btn btn-success",
                        handler: function(){
                            AppComponents.htmlTable.render.apply(equipmentComp);
                        }
                    },{
                    type: 'submit',
                    url: "./add-equipment",
                    method: "POST",
                    value: 'Save',
                    id: 'addTransaction',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(equipmentComp);
                        swal("Done!", "Record was added successfully", "success");
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(equipmentComp);
                        swal("Failed!", "An issue occured please try again", "error");
                    }
                }]
            });
        }
    },
        {
            label: 'Delete',
            id: 'deleteEquipment',
            method:'DELETE',
            class:'btn btn-danger',
            url: './delete-equipment',
            handler: function(){


                AppComponents.htmlTable.render.apply(equipmentComp);
            }
        }]
};