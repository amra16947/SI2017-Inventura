import ItemsModule from './items'
import ItemsController from './items.controller';
import ItemsComponent from './items.component';
import ItemsTemplate from './items.html';

describe('Items', () => {
  let $rootScope, makeController;

  beforeEach(window.module(ItemsModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new ItemsController();
    };
  }));

  describe('Module', () => {
    // top-level specs: i.e., routes, injection, naming
  });

  describe('Controller', () => {
    // controller specs
    it('has a name property [REMOVE]', () => { // erase if removing this.name from the controller
      let controller = makeController();
      expect(controller).to.have.property('name');
    });
  });

  describe('Template', () => {
    // template specs
    // tip: use regex to ensure correct bindings are used e.g., {{  }}
    it('has name in template [REMOVE]', () => {
      expect(ItemsTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = ItemsComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(ItemsTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(ItemsController);
      });
  });
});
