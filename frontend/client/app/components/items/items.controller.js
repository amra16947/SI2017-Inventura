class ItemsController {
    static $inject = ['itemService', 'locationService' , 'categoryService', 'swalService'];
    
    constructor(itemService, locationService, categoryService, swalService) {
        this.itemService = itemService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

        this.setEmptyItem();
        this.load();
    }
    
    refresh() {
        if (this.searchText) {
            this.filter();
        } else {
            this.load();
        }
    }

    load(page = 1) {
        Promise.all([this.loadCategories(), this.loadLocations()]).then(([cat, loc]) => {
            this.categories = cat.data;
            this.locations = loc.data;

            this.loadItems(page);
        });
    }

    loadLocations() {
		return this.locationService.all();
    }
    
    loadCategories() {
        return this.categoryService.all();
    }

    loadItems(page = 1) {
        this.itemService.getPage(page).then(response => {
            this.items = response.data.content;  
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadItems(newPage);
		}
    }

    saveItem() {
        if (!this.form.$valid) {
            return;
        }

        this.item.categoryID = this.item.category.id;
        this.item.locationID = this.item.location.id;

        if (this.item.id) {
            this.updateItem();
        } else {
            this.createItem();
        }
    }
    
    createItem() {
        this.itemService.create(this.item).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Nova inventurna stavka je uspješno kreirana.');
        }, error => {});
    }

     updateItem() {
        this.itemService.update(this.item.id, this.item).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {
        });
    }

    setEmptyItem() {
        this.item = {
            skuNumber: '',
            name: '',
            purchasedBy: '',
            personResponsible: '',
            value: '',
            dateOfPurchase: '',
            unitOfMeasurement: '',
            category: null,
            location: null
        };
    }

    edit(id) {
        this.itemService.find(id).then(response => {
            this.item = {
                id: response.data.id,
                skuNumber: response.data.skuNumber,
                name: response.data.name,
                purchasedBy: response.data.purchasedBy,
                personResponsible: response.data.personResponsible,
                value: response.data.value,
                dateOfPurchase: response.data.dateOfPurchase,
                unitOfMeasurement: response.data.unitOfMeasurement,
                category: response.data.category,
                location: response.data.location
            };

            this.openModal();
        });
    }

    filter() {
        this.itemService.filterByName(this.searchText).then(response => {
            this.items = response.data;
        });
    }
    
    delete(id) {
        this.swalService.areYouSure('Obrisana inventurna stavka se ne može vratiti.', () => {
			this.itemService.delete(id).then(response => {
                this.refresh();
                this.swalService.success('Inventurna stavka je uspješno obrisana.');
			});
		});
    }   

    closeModal() {
        $('#item-modal').modal('close');
    }

    openModal() {
        $('#item-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

        $('#name').focus();
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyItem();
    }
}

export default ItemsController;