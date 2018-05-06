class MyAccountController {
    static $inject = ['myAccountService', 'swalService'];

    constructor(myAccountService, swalService) {
        this.name = 'myAccount';

        this.myAccountService = myAccountService;
        this.swalService = swalService;

        this.loadMyAccount();
    }

    loadMyAccount() {
        this.myAccountService.current().then(response => {
            this.myAccount = response.data;

            Materialize.updateTextFields();
        });
    }

    save() {
        if (!this.form.$valid) {
            return;
        }

        let data = {
            newPassword: this.myAccount.newPassword,
            currentPassword: this.myAccount.currentPassword
        };

        this.myAccountService.update(data).then(response => {
            this.swalService.success('Novi podaci koje ste unijeli su sačuvani.');
        });
    }
}

export default MyAccountController;
