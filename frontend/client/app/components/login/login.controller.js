class LoginController {
    static $inject = ["accountService", "$state", "sessionService"];

    constructor(accountService, $state, sessionService) {
        this.accountService = accountService;
        this.$state = $state;
        this.sessionService = sessionService;
        this.error = "";
        this.user = {};
    }

    login() {
        if (!this.form.$valid) {
    		return;
    	}
        this.accountService.login(this.user).then((response) => {
            this.loginSuccess(response.data);
        }, (error) => {});
    }

    loginSuccess(responseContent){
        this.sessionService.startSession(responseContent);
        this.$state.go("home");
    }

}

export default LoginController;