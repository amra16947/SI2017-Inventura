import BaseService from './baseService';

export default class ItemService extends BaseService {
	all() {
		return super.get('items/all');
	}

    getPage(page) {
        return super.get('items/page/' + page);
    }

	create(item) {
		return super.post('items', item);
	}
    
    delete(id) {
		return super.delete('items/' + id);
    }

    update(id, item) {
        return super.post('items/' + id, item);
    }

    filterByName(name) {
        return super.get('items/filter-by/name?name=' + name);
    }

    find(id) {
        return super.get('items/' + id);
    }
}
