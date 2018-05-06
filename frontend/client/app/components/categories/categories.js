import angular from 'angular';
import uiRouter from 'angular-ui-router';
import categoriesComponent from './categories.component';

let categoriesModule = angular.module('categories', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('categories', {
      url: '/kategorije',
      component: 'categories'
    });
})

.component('categories', categoriesComponent)

.name;

export default categoriesModule;
//com