'use strict'

var module = angular.module('demo.controllers', []);
module.controller("UserController", [ "$scope", "UserService",
		function($scope, UserService) {
             UserService.getAllClientes().then(function(value) {
                $scope.allClientes = value.data;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });

			$scope.clienteDto = {
				id : null,
				nome : null,
			    sobrenome: null,
			    rendimentoMensal: null,
			    risco: null,
			    enderecoDto : {
			        id: null,
			        pais: null,
			        estado: null,
			        cidade: null,
			        bairro: null,
			        logradouro: null,
			        numero: null,
			        apartamento: null,
			        cep: null
			    }
			};

//			UserService.getUserById(1).then(function(value) {
               //				console.log(value.data);
               //			}, function(reason) {
               //				console.log("error occured");
               //			}, function(value) {
               //				console.log("no callback");
               //			});


			$scope.saveUser = function() {
				UserService.saveUser($scope.clienteDto).then(function() {
					console.log("works");
					$scope.clienteDto = {
                        id : null,
                        nome : null,
                        sobrenome: null,
                        rendimentoMensal: null,
                        risco: null,
                        enderecoDto : {
                            id: null,
                            pais: null,
                            estado: null,
                            cidade: null,
                            bairro: null,
                            logradouro: null,
                            numero: null,
                            apartamento: null,
                            cep: null
                        }
                    };
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);