import angular from 'angular';
import uiRouter from 'angular-ui-router';
import myAccountComponent from './myAccount.component';

let myAccountModule = angular.module('myAccount', [
  uiRouter
])

.config($stateProvider => {
  'ngInject';

  $stateProvider
    .state('myAccount', {
      url: '/moj-racun',
      component: 'myAccount'
    });
})

.component('myAccount', myAccountComponent)

.name;

export default myAccountModule;
