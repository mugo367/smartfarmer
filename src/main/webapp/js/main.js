let AppComponents = {
    htmlForm:{
        render: function(){
            let me = this;

            let formToRender = '<div class ="container"><h2>' + me.formTitle + '</h2>';

            formToRender += '<form>';

            me.items.forEach(formItem=>{
                if (formItem.type === 'select'){

                    formToRender +='<div class = "'+formItem.divClass+'"> <label  class = "'+formItem.labelClass+'" for="' + formItem.id +'">' + formItem.label + ':</label><br>'
                        +'<select name="' + formItem.name + '" id="' + formItem.id + '" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" >'
                    let selectOptions;

                    if (formItem.select && formItem.select.data){
                        selectOptions = formItem.select.data;

                    }else if (formItem.select && formItem.select.url){
                        function getSelectOptions(reqRes){
                             selectOptions = reqRes;
                            selectOptions = selectOptions.list;
                        }
                        ajax('GET', formItem.select.url, getSelectOptions);
                    }

                    selectOptions.forEach(option =>{
                        formToRender += '<option value="' + option[formItem.select.optionMap.value]+ '">' + option[formItem.select.optionMap.display] + '</option>';
                    })

                    formToRender += '</select></div>';

                }else if (formItem.type === 'radio'){
                    formToRender +='<div class = "'+formItem.divClass+'"> <label  class = "'+formItem.labelClass+'" for="' + formItem.id +'">' + formItem.label + ':</label> &nbsp;&nbsp;&nbsp;'

                    let selectOptions;
                    if (formItem.options && formItem.options.data){
                        selectOptions = formItem.options.data;

                    }else if (formItem.options && formItem.options.options){

                        ajax('GET', formItem.select.url, getSelectOptions);

                    }

                    selectOptions.forEach(option =>{
                        formToRender += '<div class="form-check form-check-inline"> <input class="form-check-input" type="radio" name="'+formItem.name+'" id="'+formItem.id+'" value="' + option[formItem.options.optionMap.value]+ '">' +
                                        '  <label class="form-check-label" for="'+formItem.id+'">'+option[formItem.options.optionMap.display]+'</label></div>';
                       })
                    formToRender += '</div>';
                }
                else{
                    formToRender += '<div class = "'+formItem.divClass+'"> ';
                    formToRender += '<label  class = "'+formItem.labelClass+'" for="' + formItem.id +'">' + formItem.label + ':</label><br>'
                        +'<input class="'+formItem.inputClass+'" type="' + formItem.type + '" id="' + formItem.id +'" name="' + formItem.name + '"><br></div>';
                }

            });

            formToRender += '<div class = "d-grid gap-2 d-md-flex justify-content-md-end"> ';
            me.buttons.forEach(btn=>{
                formToRender += '<button class ="'+btn.btnClass+'" id="' + btn.id + '" type="' + btn.type + '">' + btn.value + '</button>';
            });
            formToRender += '</div></form>';

            document.getElementById('componentRender').innerHTML = formToRender;

            console.log(formToRender);

            me.buttons.forEach(btn=>{
                if(btn.type==="cancel"){
                    document.getElementById(btn.id).addEventListener("click", btn.handler);
                }else if (btn.type==="submit"){
                    document.getElementById(btn.id).addEventListener("click",  event=>{
                        event.preventDefault();

                        me.url = btn.url;
                        me.method = btn.method;
                        me.showMsg = btn.showMsg;
                        me.success = btn.success; // will execute if saving is success
                        me.failure = btn.failure; //will execute if saving is failure

                        AppComponents.htmlForm.submit.apply(me);

                    });
                }
            });
        },
        submit: function(){

            let me = this;
            let formData = '';
            let submitForm = true;

            //loop through the form to be submitted and collect the values while populating the submitForm variable
            me.items.forEach(field=>{
                let fieldVal = document.getElementById(field.id).value;
                formData += encodeURIComponent(field.name) + '=' + encodeURIComponent(fieldVal) + '&';
            });

            if (!submitForm){
                document.getElementById(me.showMsg).innerHTML = 'Please Enter All Required Fields(*)';
                return;
            }

            function submit(reqRes) {
                if (reqRes.loginError)
                    document.getElementById(me.showMsg).innerHTML = reqRes.loginErrorMsg;
                else if (reqRes.redirectPage)
                    location.href = reqRes.redirectPage;
                else if (reqRes.success)
                    me.success();
                else if (reqRes.failure)
                    me.failure();
            }

            ajax(me.method, me.url, submit, formData);

        }
    },

    htmlTable: {
        render: function(){

            let me = this;
            let tableToRender = '<div class="card mb-4"> <div class="card-header">'
                + '<h3>'+me.tableTitle+ '</h3> </div> <div class="card-body"> <div class="d-grid gap-2 d-md-block">'

            me.buttons.forEach(btn=>{
                tableToRender += '<button  type="button" class="btn btn-dark" id="' + btn.id + '">' + btn.label + '</button> &nbsp;&nbsp;';
            });

            tableToRender +='</div> </div> <table id="'+me.id+'" class="table table-striped table-hover">';

            let tableColGroup = '<colgroup>';
            let tableHeaders = '<thead><tr>';
            tableColGroup += '<col span="1" style="width: 3%">';
            tableHeaders += '<th></th>';

            me.columns.forEach(col=>{
                tableHeaders += '<th>' + col.header + '</th>';

            });

            tableColGroup += '</colgroup>';
            tableHeaders += '</tr></thead>';
            tableToRender += tableColGroup + tableHeaders;
            tableToRender += '<tbody>';

            function getData(reqRes){
                reqRes.list.forEach(row=>{
                    tableToRender += '<tr><td><input type="checkbox" name="row-check" class="checkAll" />&nbsp;</td>';
                    me.columns.forEach(col=>{
                        tableToRender += '<td>' + row[col.dataIndex] + '</td>'
                    });
                });
                tableToRender += '</tbody></div>'
                document.getElementById(me.renderTo).innerHTML = tableToRender;
            }

            ajax(me.method, me.url, getData);

            me.buttons.forEach(btn=>{
                document.getElementById(btn.id).addEventListener("click", btn.handler);
            });
        }

    },

    htmlToNavBar: {

        render: function(){
            let me = this;

            let topNavToolBar =
                '    <div class="container-fluid">' +
                '        <a class="navbar-brand" href="#">Farm Management System</a>' +
                '            <ul class="nav">';
            me.links.forEach(link=>{
                    topNavToolBar += '<li class="nav-item">'
                    topNavToolBar += '<a class="nav-link active" id="' + link.id + '" href="#">' + link.label + '</a>';
                    topNavToolBar +='</li>';
            });
                 topNavToolBar+= '</ul> </div>';

            let userSessionData = AppComponents.htmlToNavBar.loadSessionData(me.userDataLink);

            document.getElementById(me.renderTo).innerHTML = topNavToolBar;

            me.links.forEach(link=>{
                document.getElementById(link.id).addEventListener("click", link.handler);
            });
            document.getElementById('userSessionData').innerHTML ='<div class="small">Logged in as:</div>' + userSessionData.username ;
        },

       changeStyle: function(linkId){
            let me = this;
            console.log(me);
            console.log(linkId);

            me.links.forEach(link=>{
                if (link.id === linkId){
                    document.getElementById(linkId).classList.add("active");

                }else{
                    document.getElementById(linkId).classList.remove("active");

                }
            });
        },

        loadSessionData: function(userDataLink){
            let userSessionData = {};

            function getUserSessionData(reqRes){
                reqRes.list.forEach(row=>{
                    userSessionData = row;
                });
            }

            ajax('GET', userDataLink, getUserSessionData);

            return userSessionData;
        }
    }
};

function ajax(method, url, Function, submitData) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.onreadystatechange = function () {
        if (ajaxReq.readyState === XMLHttpRequest.DONE) {
            if (ajaxReq.status === 200) {
                let reqRes = eval('(' + ajaxReq.responseText + ')');
                Function(reqRes);
            }
        }
    }

    ajaxReq.open(method, url, false);
    ajaxReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    if (method === 'POST'){
        ajaxReq.send(submitData);
    }
    else{
        ajaxReq.send();
    }
}