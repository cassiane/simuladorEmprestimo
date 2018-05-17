'use strict'

angular.module('demo.services', []).factory('UserService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};

			service.excluirCliente = function(id) {
			    var url = CONSTANTS.getExcluirCliente + id;
			    return $http.delete(url);
			}
			service.getAllClientes = function() {
				return $http.get(CONSTANTS.getAllClientes);
			}
			service.saveUser = function(clienteDto) {
				return $http.post(CONSTANTS.saveUser, clienteDto);
			}
			return service;
		} ]);