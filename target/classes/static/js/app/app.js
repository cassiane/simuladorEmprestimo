'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers', 'demo.services' ]);
demoApp.constant("CONSTANTS", {
	getAllClientes : "/api/cliente",
	saveUser : "/api/cliente",
	getExcluirCliente : "/api/cliente/",
	simularEmprestimo : "/api/cliente/simular-emprestimo"
});