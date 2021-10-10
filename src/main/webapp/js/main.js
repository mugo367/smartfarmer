var AppComponents = {
    htmlForm:{
        render: function(){
            let me = this;

            var formToRender = '<h2>' + me.formTitle + '</h2>';

            formToRender += '<form action="' + me.url + '" method="' + me.method + '">';

            me.items.forEach(formItem=>{
                formToRender += '<div class = "'+formItem.divClass+'"> ';
                formToRender += '<label  class = "'+formItem.labelClass+'" for="' + formItem.id +'">' + formItem.label + ':</label><br>'
                    +'<input class="'+formItem.inputClass+'" type="' + formItem.type + '" id="' + formItem.id +'" name="' + formItem.name + '"><br></div>';
            });

            if (Object.keys(me.selects).length !== 0 )
            {
                me.selects.forEach(select=> {
                    formToRender +='<label  class = "'+select.labelClass+'" for="' + select.id +'">' + select.labelTitle + ':</label><br>'
                        +'<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id = "'+select.id+'" name ="'+select.name+'">'
                    select.values.forEach(value=> {
                        formToRender += '<option value="' + value + '">' + value + '</option>'
                    });
                    formToRender += '</select>';
                });
            }
            formToRender += '<div class = "'+me.submitBtn.btnDiv+'"> ';

            formToRender += '<input class ="'+me.submitBtn.btnClass+'" type="' + me.submitBtn.type + '" value="' + me.submitBtn.value + '"></div></form>';


        console.log(formToRender);
        }
    },

    htmlTable: {
        render: function(){

            let me = this;
            let tableToRender = '<div class="card mb-4">'
                +'<div class="card-header">'

                + '<h3>'+me.tableTitle+ '</h3>'
                +'</div>'
                +'<div class="card-body">'
                + '<div class="d-grid gap-2 d-md-block">'
                    me.buttons.forEach(btn=>{
                        tableToRender += '<button type="button" class="btn btn-dark position-relative" id="' + btn.id + '">' + btn.label + '</button><br/>';
                    });
            tableToRender += '</div> </div> <div class="card-footer"></div><table class="table table-success table-striped">';

            let tableColGroup = '<colgroup>';
            let tableHeaders = '<thead><tr>';

            me.columns.forEach(col=>{
                tableColGroup += '<col span="' + (col.span?col.span: 1) + '">';
                tableHeaders += '<th>' + col.header + '</th>';

            });
            tableColGroup += '</colgroup>';
            tableHeaders += '</tr></thead>';

            tableToRender += tableColGroup + tableHeaders;

            tableToRender += '<tbody>';

            var ajaxReq = new XMLHttpRequest();
            ajaxReq.onreadystatechange = function(){
                if (ajaxReq.readyState == XMLHttpRequest.DONE){
                    if (ajaxReq.status == 200){
                        let reqRes = eval('(' + ajaxReq.responseText + ')');
                        reqRes.list.forEach(row=>{
                            tableToRender += '<tr>';
                            me.columns.forEach(col=>{
                                tableToRender += '<td>' + row[col.dataIndex] + '</td>';

                            });

                        });

                        tableToRender += '</tbody></div></div>'
                        document.getElementById(me.renderTo).innerHTML = tableToRender;
                    }
                }
            }

            ajaxReq.open(me.method, me.url, false);
            ajaxReq.send();

        }
    }

};




