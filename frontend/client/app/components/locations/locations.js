import angular from 'angular';
import uiRouter from 'angular-ui-router';
import locationsComponent from './locations.component';

let locationsModule = angular.module('locations', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('locations', {
      url: '/lokacije',
      component: 'locations'
    });
})

.component('locations', locationsComponent)

.name;

export default locationsModule;
