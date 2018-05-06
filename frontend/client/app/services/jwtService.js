export default class JwtService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
    }

    saveToken(jwt){
        let user = JSON.parse(this.$window.localStorage.getItem('user'));
        //do ovoga ne bi ni trebalo doci
        if (!user) {
            user = {};
        }
        user.jwt = jwt;
        this.$window.localStorage.setItem('user', JSON.stringify(user));
    }

    getToken(){
        let user = JSON.parse(this.$window.localStorage.getItem('user'));
        return user != null ? user.jwt : null;
    }

    destroyToken(){
        let user = JSON.parse(this.$window.localStorage.getItem('user'));
        //do ovoga ne bi ni trebalo doci
        if (!user) {
            user = {};
        }
        user.jwt = null;
        this.$window.localStorage.setItem('user', JSON.stringify(user));
    }
}