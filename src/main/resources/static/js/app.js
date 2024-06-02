import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
createApp({
       //VARIABLES REACTIVE
     data() {
       return {
            API_URL : 'http://localhost:8181/api/v1/users',
            ACTIONS:{
                ADD:'ADD',
                UPDATE:'UPDATE',
                DELETE:'DELETE',
                SEARCH:'SEARCH'
            },
            loading: false,
            userAction:'',
            noUserMessage: 'No users to display.',
            users: [],
            userForm:{},
            formDisabled: true,
            errors:{},
            classError:'border border-danger',
            displayAlertBox: false
       }
    },
    //METHODS
    methods: {
        //API Methods
        getUsersApi(){
            fetch(this.API_URL)
                .then((response) => response.json())
                .then((json) => { this.users = json });
        },
        searchUserApi(id){
            fetch(this.API_URL + '/user/'+id)
                .then((response) => response.json())
                .then((json) => {
                    this.userForm = json;
                });
        },
        updateUserApi(){
            const requestOptions = {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(this.userForm)
             };
            fetch(this.API_URL + '/user/'+this.userForm.id, requestOptions)
                .then((response) => {
                   this.displayAlertBox = true;
                   this.formDisabled = true;
                });
        },
        deleteUserApi(){
            fetch(this.API_URL + '/user/'+this.userForm.id, { method: 'DELETE' })
                .then((response) => {
                    this.userForm = {};
                    this.displayAlertBox = true;
                    this.getUsersApi();
                });
        },
        //Button methods
        closeAlertBoxBtn(){
            this.userAction = '';
            this.displayAlertBox = false;
        },
        addUserBtn(){
            //this.userAction = this.ACTIONS.ADD;
            //this.userForm = {};
            alert('Not yet implemented');
        },
        searchUserBtn(id) {
            this.userAction = this.ACTIONS.SEARCH;
            this.searchUserApi(id);
        },
        editUserBtn() {
            this.formDisabled = false;
            this.userAction = this.ACTIONS.UPDATE;
        },
        deleteUserBtn() {
            this.userAction = this.ACTIONS.DELETE;
        },
        cancelEditUserBtn(){
            this.formDisabled = true;
            this.userAction = '';
        },
        submit(){
            if(this.userAction === this.ACTIONS.ADD){
                this.addUserApi();
            } else if(this.userAction === this.ACTIONS.UPDATE) {
                this.updateUserApi()
            } else if(this.userAction === this.ACTIONS.DELETE) {
                this.deleteUserApi();
            }
        }
    },
    //COMPUTED
    computed:{
        openForm(){
            return (this.userAction === this.ACTIONS.ADD) || (JSON.stringify(this.userForm) != JSON.stringify({})) ;
        },
        confirmBoxMessage() {
            if(this.userAction === this.ACTIONS.UPDATE) {
               return 'Update the user: '+this.userForm.fullName+'?';
            } else if(this.userAction === this.ACTIONS.DELETE){
               return 'Delete the user: '+this.userForm.fullName+'?';
            }
        },
        openAlertBox(){
            return (this.userAction != '' && this.displayAlertBox);
        },
        alertMessage() {
            if(this.userAction === this.ACTIONS.UPDATE) {
                return 'User updated successfully.';
            } else if(this.userAction === this.ACTIONS.DELETE){
                return 'User deleted successfully.';
            } else if(this.userAction === this.ACTIONS.ADD){
                 return 'User add successfully.';
            }
        },
        formNotValid(){
            var isValid =
                this.errors.firstname
                && this.errors.lastname
                && this.errors.mail
                && this.errors.birthday;
            console.log('Form:', isValid);
            return !isValid;
        }
    },
    //WATCH
    watch:{
        'userForm.firstname'(newValue){
            this.errors.firstnameClass = '';
            if(!newValue){
                this.errors.firstnameClass = this.classError;
            }
        },
        'userForm.lastname'(newValue){
            this.errors.lastnameClass = '';
            if(!newValue){
                this.errors.lastnameClass = this.classError;
            }
        },
        'userForm.mail'(newValue){
             this.errors.mailClass = '';
             if(!newValue){
                this.errors.mailClass = this.classError;
             }
        },
        'userForm.birthday'(newValue){
            this.errors.birthdayClass = '';
            if(!newValue){
                this.errors.birthdayClass = this.classError;
            }
        }
    },
    //HOOK LIFE CYCLE
    beforeCreate() {
        this.loading = true;
    },
    created() {
        this.getUsersApi();
    },
    mounted() {
        this.loading = false;
    }
}).mount('#app')