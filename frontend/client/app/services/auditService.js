import BaseService from './baseService';

export default class AuditService extends BaseService {
	all() {
		return super.get('audits/all');
	}

    find(id) {
        return super.get('audits/' + id);
    }

    finalize(id) {
        return super.post('audits/' + id + '/finalize');
    }

    create(audit) {
        return super.post('audits', audit);
    }

    delete(id) {
        return super.delete('audits/' + id);
    }
}
