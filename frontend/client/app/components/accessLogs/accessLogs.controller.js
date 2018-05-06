class AccessLogsController {
    static $inject = ["accessLogService"];

    constructor(accessLogService) {
        this.accessLogService = accessLogService;
        this.loadAccessLogs();
        this.searchedText = "";
    }

    loadAccessLogs(page = 1) {
        this.accessLogService.getPage(page).then((response) => {
            this.accessLogs = response.data.content;
            this.number = response.data.number+1;
			this.totalPages = response.data.totalPages;
        });
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadAccessLogs(newPage);
        }
    }

    filter() {
        if(this.searchedText) {
            this.accessLogService.getByFilter(this.searchedText).then((response) => {
                this.accessLogs = response.data;
            });
        }
        else {
            this.loadAccessLogs();
        }

    }
}

export default AccessLogsController;