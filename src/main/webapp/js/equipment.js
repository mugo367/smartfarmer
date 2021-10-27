//equipment form
var equipmentComp = {
    url: "./view-equipments",
    method: "GET",
    tableTitle: 'Equipments',
    renderTo: 'componentRender',
    id:'equipmentTable',
    columns: [{
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
                    ,
                    {
                        label: "Employee Condition",
                        id: "equipmentConditionStr",
                        name: "equipmentConditionStr",
                        type: "select",
                        labelClass: "form-label",
                        divClass: "mb-3",
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
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(equipmentComp);
                    }
                }]
            });
        }
    },
        {
            label: 'Delete',
            id: 'deleteEquipment',
            handler: function(){
                //Reference the Table.
                let tableRef = document.getElementById(equipmentComp.id);
                //Reference the CheckBoxes in Table.
                let checkBoxes = tableRef.getElementsByTagName("INPUT");

                let checkedEquipments = [];
                //Loop through the CheckBoxes.
                for (let i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].checked) {
                        let row = checkBoxes[i].parentNode.parentNode;
                        checkedEquipments.push(row.cells[1].innerHTML);
                    }
                }
                console.log(checkedEquipments)
                //make ajax request to delete record
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (xhr.readyState === XMLHttpRequest.DONE){
                        if (xhr.status === 200){
                            console.log(xhr.responseText);
                        }
                    }
                }

                console.log(JSON.stringify(checkedEquipments))

                xhr.open("DELETE", "./delete-equipment", false);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("equipmentLabels="+JSON.stringify(checkedEquipments));

                AppComponents.htmlTable.render.apply(equipmentComp);
            }
        }]
};