
let vm = new Vue ({
    el: "#password-form",



    data : {
        email: "efthimis.vafidis@gmail.com",
        password: "password",
        passwordConfirm : "password",
        errors: [],
        bearerToken: "",
        requestInit: {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            redirect: 'follow',
            referrerPolicy: 'no-referrer',

        }
    },
    methods : {
        onClick : function (e) {

            this.errors.length = 0;

            if (this.password === this.passwordConfirm) {

                this.fetchData();
            }
            else
                this.bearerToken = ""
                this.errors.push("Passwords should match")

            e.preventDefault();
        },
        fetchData : function (){
            this.requestInit.body = JSON.stringify({username: this.email,password: this.password});
            fetch("http://localhost:8080/login", this.requestInit)
                .then((response) => {
                    return response.headers.get("Authorization")
                })
                .then((data) => {
                    this.bearerToken = data
                    this.errors = ""
                })
                .catch((err) => {
                    console.log(err)
                });
        }
    }

})


