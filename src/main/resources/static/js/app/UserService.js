'use strict'

angular.module('demo.services', []).factory('UserService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
//			service.getUserById = function(userId) {
//				var url = CONSTANTS.getUserByIdUrl + userId;
//				return $http.get(url);
//			}
			service.getAllClientes = function() {
				return $http.get(CONSTANTS.getAllClientes);
			}
			service.saveUser = function(clienteDto) {
				return $http.post(CONSTANTS.saveUser, clienteDto);
			}
			return service;
		} ]);