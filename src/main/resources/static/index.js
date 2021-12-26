angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                minCost: $scope.filter ? $scope.filter.minCost : null,
                maxCost: $scope.filter ? $scope.filter.maxCost : null,
                titlePart: $scope.filter ? $scope.filter.titlePart : null,
                pageNumber: $scope.filter ? $scope.filter.pageNumber : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
        });
    };

    // не уверен в том, что запрос для добавления продукта в корзину по REST'у должен быть GET.
    // Мб это какой-нибудь патч, который апдейтит корзину?
    $scope.addToCart = function (id) {
        $http.get(contextPath + '/cart/add/' + id)
            .then(function (response) {
                $scope.loadProductsInCart();
            });
    }

    $scope.loadProductsInCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.ProductsListInCart = response.data;
            });
    }

    $scope.deleteProductInCart = function (id) {
        $http.delete(contextPath + '/cart/' + id)
            .then(function (response) {
               $scope.loadProductsInCart();
            });
    }

    $scope.loadProductsInCart();
    $scope.loadProducts();

});