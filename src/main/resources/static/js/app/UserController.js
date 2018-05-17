'use strict'

var module = angular.module('demo.controllers', []);
module.controller("UserController", [ "$scope", "UserService",
		function($scope, UserService) {
            $scope.limpar = function() {
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
            }

            $scope.buscarTodosClientes = function() {
                UserService.getAllClientes().then(function(value) {
                    $scope.allClientes = value.data;
                }, function(reason) {
                    console.log("Erro ao pesquisar todos os clientes: " + reason);
                }, function(value) {
                    console.log("Nenhum callback para pesquisa de todos os clientes: " + value);
                });
            };

            $scope.excluirCliente = function() {
                UserService.excluirCliente($scope.id).then(function() {
                    $scope.limpar();
                    $scope.buscarTodosClientes();
                }, function error(response) {
                      if (response.status == 409) {
                          $scope.errorMessage = response.data.message;
                      } else if (response.status == 400) {
                          response.data.errors.forEach(function(el) {
                              $scope.errorMessage = el;
                          });
                      } else {
                          $scope.errorMessage = 'Erro excluindo cliente.';
                      }
                      $scope.message = '';
                  }, function(value) {
                    console.log("Nenhum callback para exclusão de cliente: " + value);
                  });
            }

			$scope.saveUser = function() {
				UserService.saveUser($scope.clienteDto).then(function() {
					console.log("Cliente salvo com sucesso");
					$scope.limpar();
                    $scope.buscarTodosClientes();
				}, function error(response) {
                    if (response.status == 409) {
                        $scope.errorMessage = response.data.message;
                    } else if (response.status == 400) {
                        response.data.errors.forEach(function(el) {
                            $scope.errorMessage = el;
                        });
                    } else {
                        $scope.errorMessage = 'Erro salvando cliente.';
                    }
                    $scope.message = '';
                }, function(value) {
					console.log("Nenhum callback para salvamento de cliente: " + value);
				});
			}

            $scope.buscarTodosClientes();
			$scope.limpar();
		} ]);