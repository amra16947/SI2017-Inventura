import template from './items.html';
import controller from './items.controller';
import './items.scss';

let itemsComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller
};

export default itemsComponent;
