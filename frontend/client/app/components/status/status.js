import angular from 'angular';
import uiRouter from 'angular-ui-router';
import statusComponent from './status.component';

let statusModule = angular.module('status', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('status', {
      url: '/statusi',
      component: 'status'
    });
})

.component('status', statusComponent)

.name;

export default statusModule;
