class HomeController {
    static $inject = ['auditService', 'locationService', 'swalService', 'reportService'];
    
    constructor(auditService, locationService, swalService, reportService) {
        this.auditService = auditService;
        this.locationService = locationService;
        this.swalService = swalService;
        this.reportService = reportService;

        this.setEmptyAudit();
        this.load();
    }

    load() {
        this.locationService.all().then(response => {
            this.locations = response.data;

            this.loadAudits();
        });
    }

    loadAudits() {
        this.auditService.all().then(response => {
            this.audits = response.data;
        });
    }

    generateReport(auditID) {
        this.reportService.generateAuditReport(auditID);
    }

    openModal() {
        $('#create-audit-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');
    }

    closeModal() {
        $('#create-audit-modal').modal('close');
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyAudit();
    }

    setEmptyAudit() {
        this.audit = {
            name: '',
            location: null
        };
    }

    createAudit() {
        if (!this.form.$valid) {
            return;
        }

        this.audit.locationId = this.audit.location.id;

        this.auditService.create(this.audit).then(response => {
            this.closeModal();
            this.load();

            this.swalService.success('Nova inventura je uspješno kreirana.');
        }, error => {
            this.swalService.error(error.data.message.string);
        });
    }

    delete(id) {
        this.swalService.areYouSure('Sve informacije prikupljene tokom inventure će biti obrisane.', () => {
            this.auditService.delete(id).then(response => {
                this.load();

                this.swalService.success('Inventura je uspješno obrisana.');
            });
        });
    }
}

export default HomeController;
