class StatusController {
	static $inject = ['statusService', 'swalService'];

	constructor(statusService, swalService) {
        this.statusService = statusService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.loadStatus();
        this.setEmptyStatus();
	}

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.loadStatus();
        }
    }

    loadStatus(page = 1) {
        this.statusService.getPage(page).then((response) => {
            this.allStatus = response.data.content;
            this.number = response.data.number+1;
            this.totalPages = response.data.totalPages;
        });
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyStatus();
    }

    setEmptyStatus() {
        this.status = {
            id: null,
            name: '',
        };
    }

    saveStatus() {
        if (!this.form.$valid) {
            return;
        }

        if (this.status.id) {
            this.updateStatus();
        } else {
            this.createStatus();
        }
    }
 
    createStatus() {
        this.statusService.create(this.status).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Novi status je uspješno kreiran.');
        }, error => {});
    }

    updateStatus() {
        this.statusService.update(this.status.id, this.status).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    edit(id) {
        this.statusService.find(id).then(response => {
            this.status = {
                id: response.data.id,
                name: response.data.name,
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisani status se ne može vratiti.', () => {
            this.statusService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Status je uspješno obrisan.');
            });
        });
    }

    closeModal() {
        $('#status-modal').modal('close');
    }

    openModal() {
        $('#status-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

        $('#name').focus();
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadStatus(newPage);
        }
    }

    filter() {
        this.statusService.filterByName(this.searchText).then(response => {
            this.allStatus = response.data;
        });
    }
}

export default StatusController;
