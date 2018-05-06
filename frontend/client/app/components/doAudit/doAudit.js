import angular from 'angular';
import uiRouter from 'angular-ui-router';
import doAuditComponent from './doAudit.component';

let doAuditModule = angular.module('doAudit', [
  uiRouter
])

.config($stateProvider => {
    'ngInject';

    $stateProvider
        .state('doAudit', {
            url: '/obavljanje-inventure/{auditID:int}',
            component: 'doAudit'
        });
})

.component('doAudit', doAuditComponent)

.name;

export default doAuditModule;
