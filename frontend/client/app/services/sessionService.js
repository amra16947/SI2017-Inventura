export default class SessionService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
        this.currentUser = JSON.parse($window.localStorage.getItem('user'));
    }

    startSession(authServerResponse){
        this.$window.localStorage.setItem('user', JSON.stringify(authServerResponse));
        this.currentUser = authServerResponse;
    }

    userInfo(){
        return this.currentUser;
    }

    destroySession(){
        this.$window.localStorage.setItem('user', null);
        this.currentUser = null;
    }

    isUserLoggedIn(){
        return this.currentUser == null ? false : true;
    }

    currentRole() {
        return this.currentUser == null ? "" : this.currentUser.role;
    }

    refreshRole(newRole) {
        this.currentUser.role = newRole;
        this.$window.localStorage.setItem('user', JSON.stringify(this.currentUser));
    }
}