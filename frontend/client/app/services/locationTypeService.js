import BaseService from './baseService';

export default class LocationTypeService extends BaseService {
	all() {
		return super.get('locationTypes/all');
	}

	create(locType) {
		return super.post('locationTypes', locType);
	}

	getPage(page) {
		return super.get('locationTypes/page/'+page);
	}

	delete(id) {
		return super.delete('locationTypes/' + id);
	}

	find(id) {
        return super.get('locationTypes/' + id);
    }

    update(id, locationType) {
        return super.post('locationTypes/' + id, locationType);
    }

    filterByName(name) {
        return super.get('locationTypes/filter-by/name?name=' + name);
    }
}
