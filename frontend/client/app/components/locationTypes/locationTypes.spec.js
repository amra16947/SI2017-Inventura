import LocationTypesModule from './locationTypes'
import LocationTypesController from './locationTypes.controller';
import LocationTypesComponent from './locationTypes.component';
import LocationTypesTemplate from './locationTypes.html';

describe('LocationTypes', () => {
  let $rootScope, makeController;

  beforeEach(window.module(LocationTypesModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new LocationTypesController();
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
      expect(LocationTypesTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = LocationTypesComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(LocationTypesTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(LocationTypesController);
      });
  });
});
