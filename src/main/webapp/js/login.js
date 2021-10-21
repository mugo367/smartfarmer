AppComponents.htmlForm.render.call({
    formTitle: 'Login Details',
    renderId: "componentRender",
    items: [{
        label: "Username",
        name: "username",
        id: "username",
        type: "text",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    },{
        label: "Password",
        name: "password",
        id: "password",
        type: "password",
        divClass: "mb-3",
        inputClass: "form-control",
        labelClass: "form-label"
    }],
    buttons: [{
        btnDiv: "d-grid gap-2 col-6 mx-auto",
        type: 'submit',
        url: "./login",
        method: "POST",
        value: 'Sign In',
        id: 'login',
        btnClass: "btn btn-success",
        showMsg: 'showErrorMsg'

    }]
});