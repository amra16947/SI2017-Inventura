import angular from 'angular';
import uiRouter from 'angular-ui-router';
import accessLogsComponent from './accessLogs.component';

let accessLogsModule =  angular.module('accessLogs', [
    uiRouter
])
.config(($stateProvider) => {
    'ngInject';
    $stateProvider
        .state('accessLogs', {
            url: '/pristupni-logovi',
            component: 'accessLogs'
        }
    );
})
.component('accessLogs', accessLogsComponent)
.name;

export default accessLogsModule;