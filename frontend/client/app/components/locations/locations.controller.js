class LocationsController {
	static $inject = ['locationService', 'locationTypeService', 'swalService'];

	constructor(locationService, locationTypeService, swalService) {
		this.locationService = locationService;
		this.locationTypeService = locationTypeService;
		this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.setEmptyLocation();
        this.load();
	}

    load(page = 1) {
        Promise.all([this.loadLocationTypes(), this.loadAllLocations()]).then(([locTypesRes, locRes]) => {
            this.locationTypes = locTypesRes.data;
            this.allLocations = locRes.data;

            this.loadLocations(page);
        });
    }

	refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.load();
        }
    }

    saveLocation() {
    	if (!this.form.$valid) {
            return;
        }

        if (this.location.parent) {
            this.location.parentId = this.location.parent.id;
        }

        this.location.typeId = this.location.type.id;

        if (this.location.id) {
            this.updateLocation();
        } else {
            this.createLocation();
        }
    }
	
	loadLocations(page = 1) {
		this.locationService.getPage(page).then(response => {
			this.locations = response.data.content;
			this.number = response.data.number + 1;
			this.totalPages = response.data.totalPages;
		});
	}

	loadAllLocations() {
        return this.locationService.all();
    }
	
	loadLocationTypes() {
		return this.locationTypeService.all();
	}

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyLocation();
    }

    setEmptyLocation() {
		this.location = {
			name: '', 
			parent: null, 
			type: null
        };
	}

	createLocation() {
        this.locationService.create(this.location).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Nova lokacija je uspješno kreirana.');
        }, error => {});
    }

    updateLocation() {
        this.locationService.update(this.location.id, this.location).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    edit(id) {
        this.locationService.find(id).then(response => {
            this.location = {
                id: response.data.id,
                name: response.data.name,
                parent: response.data.parent,
                type: response.data.type
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana lokacija se ne može vratiti.', () => {
            this.locationService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Lokacija je uspješno obrisana.');
            });
        });
    }

    closeModal() {
        $('#location-modal').modal('close');
    }

    openModal() {
        $('#location-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

        $('#name').focus();
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.load(newPage);
		}
    }

    filter() {
        this.locationService.filterByName(this.searchText).then(response => {
            this.locations = response.data;
        });
    } 
}

export default LocationsController;
