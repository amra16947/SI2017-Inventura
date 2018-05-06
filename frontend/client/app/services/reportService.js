export default class ReportService {
	static $inject = ['$http', 'ENV'];

	constructor($http, ENV) {
		this.$http = $http;
		this.ENV = ENV;
		this.reportsPath = '/reports';
	}

	generateAuditReport(id) {
		return this.$http.get(this.ENV.API_URL + this.reportsPath + '/audit/' + id, {
			responseType: 'arraybuffer'
		}).then(response => {
			// zaobilaznica (workaround) za preuzimanje pdf dokumenta
			let file = new Blob([response.data], {type: 'application/pdf'});
       		let fileURL = window.URL.createObjectURL(file);

      		let  linkElement = document.createElement('a');

      		linkElement.setAttribute('href', fileURL);
      		linkElement.setAttribute("download", "inventura-izvjestaj");
     		linkElement.click();
		}); 
	}
}
