import LocationsModule from './locations'
import LocationsController from './locations.controller';
import LocationsComponent from './locations.component';
import LocationsTemplate from './locations.html';

describe('Locations', () => {
  let $rootScope, makeController;

  beforeEach(window.module(LocationsModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new LocationsController();
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
      expect(LocationsTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = LocationsComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(LocationsTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(LocationsController);
      });
  });
});
