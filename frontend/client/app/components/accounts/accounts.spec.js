import AccountsModule from './accounts'
import AccountsController from './accounts.controller';
import AccountsComponent from './accounts.component';
import AccountsTemplate from './accounts.html';

describe('Accounts', () => {
  let $rootScope, makeController;

  beforeEach(window.module(AccountsModule));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new AccountsController();
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
      expect(AccountsTemplate).to.match(/{{\s?\$ctrl\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = AccountsComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(AccountsTemplate);
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(AccountsController);
      });
  });
});
