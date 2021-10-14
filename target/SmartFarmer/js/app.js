let appNavBarLinks = {
    renderTo: "comp-topnav",
    links: [{
        label: "Employees",
        id: "employees",
        handler: function(){
            let me = this;

            AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
            AppComponents.htmlTable.render.apply(employeeComp);

        }
    },{
        label: "Activities",
        id: "activities",
        handler: function(){
            let me = this;
            AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
            AppComponents.htmlTable.render.apply(activitiesComp);
        }
    },{
        label: "Equipment",
        id: "equipment",
        handler: function(){
            let me = this;
            AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
            AppComponents.htmlTable.render.apply(equipmentComp);
        }
    },{
        label: "Fields",
        id: "field",
        handler: function(){
            let me = this;
            AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
            AppComponents.htmlTable.render.apply(fieldComp);
        }
    },
        {
            label: "Productions",
            id: "productions",
            handler: function(){
                let me = this;

                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(productionComp);

            }
        },{
            label: "Transactions",
            id: "transactions",
            handler: function(){
                let me = this;
                AppComponents.htmlToNavBar.changeStyle.call(appNavBarLinks, me.id);
                AppComponents.htmlTable.render.apply(transactionComp);
            }
        }
    ]
};

AppComponents.htmlToNavBar.render.call(appNavBarLinks);