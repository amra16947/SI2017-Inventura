import angular from 'angular';
import uiRouter from 'angular-ui-router';
import locationTypesComponent from './locationTypes.component';

let locationTypesModule = angular.module('locationTypes', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('locationTypes', {
      url: '/tipovi-lokacija',
      component: 'locationTypes'
    });
})

.component('locationTypes', locationTypesComponent)

.name;

export default locationTypesModule;
 //