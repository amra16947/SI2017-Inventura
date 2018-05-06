class DoAuditController {
    static $inject = ['$stateParams', 'auditService', 'auditItemService', 'statusService', 'swalService'];

    constructor($stateParams, auditService, auditItemService, statusService, swalService) {
        this.name = 'doAudit';

        this.auditID = $stateParams.auditID;

        this.auditService = auditService;
        this.auditItemService = auditItemService;
        this.statusService = statusService;
        this.swalService = swalService;

        this.load();
    }

    load() {
        this.loadAudit();
        this.loadStatuses();
    }

    loadAudit() {
        this.auditService.find(this.auditID).then(response => {
            this.audit = response.data;
        });
    }

    loadStatuses() {
        this.statusService.all().then(response => {
            this.statuses = response.data;
        });
    }

    setPresent(auditItem) {
        this.auditItemService.setPresent(auditItem.id, auditItem.present).then(response => {}, error => {
            auditItem.present = !auditItem.present;
        });
    }

    setSkuCorrect(auditItem) {
        this.auditItemService.setSkuCorrect(auditItem.id, auditItem.skuCorrect).then(response => {}, error => {
            auditItem.skuCorrect = !auditItem.skuCorrect;
        });
    }

    setStatus(auditItem) {
        let statusId = auditItem.status ? auditItem.status.id : null;

        this.auditItemService.setStatus(auditItem.id, statusId).then(response => {}, error => {
            this.swalService.error(error.data.message.string);
        });
    }

    finalize() {
        this.swalService.areYouSure('Nakon što se zaključi, inventura se više ne može mijenjati.', () => {
            this.auditService.finalize(this.audit.id).then(response => {
                this.audit.finished = true;

                this.swalService.success('Inventura je uspješno zaključena.');
            }, error => {
                this.swalService.error('Došlo je do greške prilikom zaključenja inventure.');
            })
        });
    }
}

export default DoAuditController;
