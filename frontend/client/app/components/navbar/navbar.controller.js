class NavbarController {
    static $inject = ['sessionService', '$state'];

    constructor(sessionService, $state) {
        this.name = 'navbar';

        this.sessionService = sessionService;
        this.$state = $state;
    }

    logout() {
        this.sessionService.destroySession();
        this.$state.go('login');
    }
}

export default NavbarController;
