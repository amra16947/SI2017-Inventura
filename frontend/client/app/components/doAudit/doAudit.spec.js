import DoAuditModule from './doAudit'
import DoAuditController from './doAudit.controller';
import DoAuditComponent from './doAudit.component';
import DoAuditTemplate from './doAudit.html';

describe('DoAudit', () => {
  let $rootScope, makeController;

  beforeEach(window.module(DoAuditModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new DoAuditController();
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
      expect(DoAuditTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = DoAuditComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(DoAuditTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(DoAuditController);
      });
  });
});
