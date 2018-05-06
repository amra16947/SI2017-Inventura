import angular from 'angular';
import uiRouter from 'angular-ui-router';
import accountsComponent from './accounts.component';

let accountsModule = angular.module('accounts', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('accounts', {
      url: '/racuni',
      component: 'accounts'
    });
})

.component('accounts', accountsComponent)

.name;

export default accountsModule;
