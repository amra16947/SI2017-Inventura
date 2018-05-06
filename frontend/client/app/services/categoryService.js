import BaseService from './baseService';

export default class CategoryService extends BaseService {
	all() {
		return super.get('categories/all');
	}

	create(category) {
		return super.post('categories', category);
	}

    getPage(page) {
        return super.get('categories/page/' + page);
    }

    delete(id) {
		return super.delete('categories/' + id);
	}

    find(id) {
        return super.get('categories/' + id);
    }

    update(id, category) {
        return super.post('categories/' + id, category);
    }

    filterByName(name) {
        return super.get('categories/filter-by/name?name=' + name);
    }
}
