import angular from 'angular';
import Home from './home/home';
import Accounts from './accounts/accounts';
import Locations from './locations/locations';
import LocationTypes from './locationTypes/locationTypes';
import Categories from './categories/categories';
import Status from './status/status';
import Items from './items/items';
import Login from './login/login';
import Navbar from './navbar/navbar';
import MyAccount from './myAccount/myAccount';
import AccessLogs from './accessLogs/accessLogs';
import DoAudit from './doAudit/doAudit';

let Components = angular.module('app.components', [
  Home,
  Accounts,
  Locations,
  Categories,
  Status,
  LocationTypes,
  Items,
  Navbar,
  Login,
  MyAccount,
  AccessLogs,
  DoAudit
])
.name;

export default Components;
