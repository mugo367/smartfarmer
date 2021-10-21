var fieldComp = {
    url: "./view-fields",
    method: "GET",
    tableTitle: 'Fields',
    renderTo: 'componentRender',
    id:'fieldTable',
    columns: [{
        header: "Field Label",
        dataIndex: "fieldLabel",
    },{
        header: "Name",
        dataIndex: "fieldName",

    },{
        header: "Size",
        dataIndex: "fieldSize",
    },{
            header: "Status",
            dataIndex: "fieldStatus",
    }],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function(){
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
            },
                {
                    labelTitle: "Field Status",
                    id: "fieldStatus",
                    name: "fieldStatus",
                    labelClass: "form-label",
                    option:"Field Status",
                    type: "radio",
                    divClass: "mb-3",
                    options:{
                        data:[{id:'Planted', status:'Planted'},{id:'NotPlanted', status:'NotPlanted'}],
                        optionMap: {value:'id', display:'status'}
                    }
                },
            ],
            buttons: [{
                btnDiv: "d-grid gap-2 col-6 mx-auto",
                type: 'submit',
                url: "./add-field",
                method: "POST",
                value: 'Save',
                id: 'addActivity',
                btnClass: "btn btn-success",
                showMsg: 'showErrorMsg',
                success: function(){
                    AppComponents.htmlTable.render.apply(fieldComp);
                },
                failure: function(){
                    AppComponents.htmlTable.render.apply(fieldComp);
                }
            }]
        });
        }
    },
        {
            label: 'Delete',
            id: 'deleteFields',
            handler: function(){
                //Reference the Table.
                let tableRef = document.getElementById(fieldComp.id);
                //Reference the CheckBoxes in Table.
                let checkBoxes = tableRef.getElementsByTagName("INPUT");

                let checkedFields = [];
                //Loop through the CheckBoxes.
                for (let i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].checked) {
                        let row = checkBoxes[i].parentNode.parentNode;
                        checkedFields.push(row.cells[1].innerHTML);
                    }
                }
                //make ajax request to delete record
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (xhr.readyState === XMLHttpRequest.DONE){
                        if (xhr.status === 200){
                            console.log(xhr.responseText);
                        }
                    }
                }

                console.log(JSON.stringify(checkedFields))

                xhr.open("DELETE", "./delete-field", false);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("employeeNumbers="+JSON.stringify(checkedFields));

                AppComponents.htmlTable.render.apply(fieldComp);
            }
        }
    ]
};