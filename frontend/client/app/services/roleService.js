import BaseService from './baseService';

export default class RoleService extends BaseService {
    all() {
        return super.get('roles/all');
    }
}