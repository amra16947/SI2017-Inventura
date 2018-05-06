import BaseService from './baseService';

export default class AccountService extends BaseService {
	all() {
		return super.get('accounts/all');
	}

	create(account) {
		return super.post('accounts', account);
	}

	getPage(page) {
		return super.get('accounts/page/' + page);
	}

	delete(id) {
		return super.delete('accounts/' + id);
	}

	login(user) {
		return super.post('login', user);
	}

	find(id) {
		return super.get('accounts/' + id);
	}

	update(id, account) {
		return super.post('accounts/' + id, account);
	}

	filterByEmail(email) {
		return super.get('accounts/filter-by/email?email=' + email);
	}
}
