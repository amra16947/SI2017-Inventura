export default class BaseService {
	static $inject = ['$http', 'ENV'];

	constructor($http, ENV) {
		this.$http = $http;
		this.ENV = ENV;
	}

	absUrl(url) {
		return this.ENV.API_URL + '/' + url;
	}

	get(url) {
		return this.$http.get(this.absUrl(url));
	}

	post(url, data) {
		return this.$http.post(this.absUrl(url), JSON.stringify(data));
	}

	delete(url) {
		return this.$http.delete(this.absUrl(url));
	}
}
