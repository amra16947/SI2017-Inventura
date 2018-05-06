import BaseService from './baseService';

export default class MyAccountService extends BaseService {
    current() {
        return super.get('myAccount');
    }

    update(data) {
        console.log(data);
        return super.post('myAccount', data);
    }
}