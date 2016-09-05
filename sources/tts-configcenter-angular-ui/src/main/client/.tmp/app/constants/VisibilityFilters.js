"use strict";

var _visibilityFilters;

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function showAll() {
  return true;
}

function showCompleted(todo) {
  return todo.completed;
}

function showActive(todo) {
  return !todo.completed;
}

var visibilityFilters = (_visibilityFilters = {}, _defineProperty(_visibilityFilters, SHOW_ALL, { filter: showAll, type: SHOW_ALL }), _defineProperty(_visibilityFilters, SHOW_COMPLETED, { filter: showCompleted, type: SHOW_COMPLETED }), _defineProperty(_visibilityFilters, SHOW_ACTIVE, { filter: showActive, type: SHOW_ACTIVE }), _visibilityFilters);