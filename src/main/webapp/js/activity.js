var activitiesComp = {
    url: "./view-activities",
    method: "GET",
    tableTitle: 'Activities',
    renderTo: 'componentRender',
    id:'activityTable',
    columns: [
        {
        id:true,
        dataIndex: "id",
    },{
        header: "Activity Label",
        dataIndex: "activityLabel",
    },{
        header: "Name",
        dataIndex: "activityName",

    },{
        header: "Description",
        dataIndex: "activityDescription",
    }],
    buttons: [{
        label: 'Add',
        id: 'addActivity',
        handler: function(){
            AppComponents.htmlForm.render.call({
                formTitle: 'Add New Activity',
                items: [{
                    label: "Activity Label",
                    name: "activityLabel",
                    id: "activityLabel",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }, {
                    label: "Activity Name",
                    name: "activityName",
                    id: "activityName",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }, {
                    label: "Activity Description",
                    name: "activityDescription",
                    id: "activityDescription",
                    type: "text",
                    divClass: "mb-3",
                    inputClass: "form-control",
                    labelClass: "form-label"
                }],
                buttons: [
                    {
                        type: 'cancel',
                        value: 'Cancel',
                        id: 'cancel',
                        btnClass: "btn btn-success",
                        handler: function(){
                            AppComponents.htmlTable.render.apply(activitiesComp);
                        }
                    },{
                    type: 'submit',
                    url: "./add-activity",
                    method: "POST",
                    value: 'Save',
                    id: 'addActivity',
                    btnClass: "btn btn-success",
                    showMsg: 'showErrorMsg',
                    success: function(){
                        AppComponents.htmlTable.render.apply(activitiesComp);
                    },
                    failure: function(){
                        AppComponents.htmlTable.render.apply(activitiesComp);
                    }
                }]
            });
        }
    }, {
        label: 'Delete',
        id: 'deleteActivity',
        handler: function(){
            //Reference the Table.
            let tableRef = document.getElementById(activitiesComp.id);
            //Reference the CheckBoxes in Table.
            let checkBox = tableRef.getElementsByTagName("INPUT");

                if (checkBox.checked) {
                    var iddd = checkBox.id;
                    console.log(iddd)
                }

                console.log(id)
            //make ajax request to delete record
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (xhr.readyState ===XMLHttpRequest.DONE){
                    if (xhr.status === 200){
                        console.log(xhr.responseText);
                    }
                }
            }


            xhr.open("DELETE", "./delete-activity", false);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

            AppComponents.htmlTable.render.apply(activitiesComp);
        }
    }]
};