class LocationTypesController {
    static $inject = ['locationTypeService', 'swalService'];

    constructor(locationTypeService, swalService) {
        this.locationTypeService = locationTypeService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.setEmptyLocationType();
        this.loadLocationTypes();
    }

    setEmptyLocationType() {
        this.locationType = {
            id: null,
            name: '',
            description: ''
        };
    }

    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.loadLocationTypes();
        }
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyLocationType();
    }

    loadLocationTypes(page = 1) {
        this.locationTypeService.getPage(page).then(response => {
            this.locationTypes = response.data.content;
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    saveLocationType() {
        if (!this.form.$valid) {
            return;
        }

        if (this.locationType.id) {
            this.updateLocationType();
        } else {
            this.createLocationType();
        }
    }

    createLocationType() {
        this.locationTypeService.create(this.locationType).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Novi tip lokacije je uspješno kreiran.');
        }, error => {});
    }

    updateLocationType() {
        this.locationTypeService.update(this.locationType.id, this.locationType).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadLocationTypes(newPage);
        }
    }

    edit(id) {
        this.locationTypeService.find(id).then(response => {
            this.locationType = {
                id: response.data.id,
                name: response.data.name,
                description: response.data.description
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisani tip lokacije se ne može vratiti.', () => {
            this.locationTypeService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Tip lokacije je uspješno obrisan.');
            });
        });
    }

    closeModal() {
        $('#locationTypes-modal').modal('close');
    }

    openModal() {
        $('#locationTypes-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

        $('#name').focus();
    }

    filter() {
        this.locationTypeService.filterByName(this.searchText).then(response => {
            this.locationTypes = response.data;
        });
    }
}

export default LocationTypesController;
