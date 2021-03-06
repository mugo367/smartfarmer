AppComponents.htmlForm.render.call({
    formTitle: 'Login',
    renderId: "componentRender",
    items: [{
        label: "Username",
        name: "username",
        id: "username",
        type: "text",
        divClass: "mb-3",
        required: true,
        inputClass: "form-control",
        labelClass: "form-label"
    },{
        label: "Password",
        name: "password",
        id: "password",
        type: "password",
        divClass: "mb-3",
        inputClass: "form-control",
        required: true,
        labelClass: "form-label"
    }],
    buttons: [
        {
            btnDiv: "d-grid gap-2 col-6 mx-auto",
            type: 'button',
            url: "./register.jsp",
            value: 'Sign Up',
            id: 'register',
            btnClass: "btn btn-primary",
            handler: function(){
                location.href = "./register.jsp";
            }

        },{
            btnDiv: "d-grid gap-2 col-6 mx-auto",
            type: 'submit',
            url: "./login",
            method: "POST",
            value: 'Sign In',
            id: 'login',
            btnClass: "btn btn-success",
            showMsg: 'showErrorMsg',
            success: function(){
                swal("Done!", "Login was successfully", "success");
            },
            failure: function(){
                swal("Failed!", "An issue occured please try again", "error");
            }

    }, ]
});