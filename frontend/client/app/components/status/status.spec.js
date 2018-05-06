import StatusModule from './status'
import StatusController from './status.controller';
import StatusComponent from './status.component';
import StatusTemplate from './status.html';

describe('Status', () => {
  let $rootScope, makeController;

  beforeEach(window.module(StatusModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new StatusController();
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
      expect(StatusTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Status', () => {
      // component/directive specs
      let component = StatusComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(StatusTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(StatusController);
      });
  });
});
