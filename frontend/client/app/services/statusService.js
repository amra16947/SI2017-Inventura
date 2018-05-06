import BaseService from './baseService';

export default class StatusService extends BaseService {
	all() {
		return super.get('status/all');
	}

	create(status) {
		return super.post('status', status);
	}

    getPage(page) {
        return super.get('status/page/' + page);
    }

    delete(id) {
		return super.delete('status/' + id);
	}

    find(id) {
        return super.get('status/' + id);
    }

    update(id, status) {
        return super.post('status/' + id, status);
    }

    filterByName(name) {
        return super.get('status/filter-by/name?name=' + name);
    }
}
