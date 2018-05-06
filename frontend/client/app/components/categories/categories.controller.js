class CategoriesController {
	static $inject = ['categoryService', 'swalService'];

	constructor(categoryService, swalService) {
		this.categoryService = categoryService;
        this.swalService = swalService;

        // Filters are disabled at first
        this.searchText = '';

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
        this.categoryService.all().then(response => {
            this.allCategories = response.data;

            this.setEmptyCategory();
            this.loadCategories(page);
        });
    }

    loadCategories(page = 1) {
        this.categoryService.getPage(page).then(response => {
            this.categories = response.data.content;
            this.number = response.data.number + 1;
            this.totalPages = response.data.totalPages;
        });
    }

    resetForm() {
        this.form.$setPristine();
        this.form.$setUntouched();
        this.form.$submitted = false;
        this.setEmptyCategory();
    }

    setEmptyCategory() {
        this.category = {
            id: null,
            name: '',
            parent: null
        };
    }

	saveCategory() {
        if (!this.form.$valid) {
            return;
        }

        this.category.parentId = this.category.parent ? this.category.parent.id : null;

        if (this.category.id) {
            this.updateCategory();
        } else {
            this.createCategory();
        }
	}

    createCategory() {
        this.categoryService.create(this.category).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Nova kategorija je uspješno kreirana.');
        }, error => {});
    }

    updateCategory() {
        this.categoryService.update(this.category.id, this.category).then(response => {
            this.refresh();
            this.closeModal();

            this.swalService.success('Izmjene su uspješno sačuvane.');
        }, error => {});
    }

    edit(id) {
        this.categoryService.find(id).then(response => {
            this.category = {
                id: response.data.id,
                name: response.data.name,
                parent: response.data.parent
            };

            this.openModal();
        });
    }

    delete(id) {
        this.swalService.areYouSure('Obrisana kategorija se ne može vratiti.', () => {
            this.categoryService.delete(id).then(response => {
                this.refresh();

                this.swalService.success('Kategorija je uspješno obrisana.');
            });
        });
    }

    closeModal() {
        $('#category-modal').modal('close');
    }

    openModal() {
        $('#category-modal').modal({
            complete: () => this.resetForm(),
            ready: (modal, trigger) => Materialize.updateTextFields()
        }).modal('open');

        $('#name').focus();
    }

    goto(newPage) {
        if (newPage > 0 && newPage <= this.totalPages) {
            this.loadCategories(newPage);
		}
    }

    filter() {
        this.categoryService.filterByName(this.searchText).then(response => {
            this.categories = response.data;
        });
    }

    getCategoryName(category) {
        let id = category ? category.id : null;

        let match = this.allCategories.find(cat => cat.id === id);

        return match ? match.name : null;
    }
}

export default CategoriesController;
