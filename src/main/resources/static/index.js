angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';


    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                minCost: $scope.costFilter ? $scope.costFilter.minCost : null,
                maxCost: $scope.costFilter ? $scope.costFilter.maxCost : null,
                pageNumber: $scope.page ? $scope.page.pageNumber : null
            }
        }).then(function (response){
            $scope.ProductList = response.data;
        });
    };

    /*
    Почему в таком варианте функция не работает?
    В моем понимании, при открытии сайта она должна выполняться следующим образом:
    т.к. в ней есть RequestParam, то они могут быть указаны, но они помечены в контроллере как не обязательные
    и если их не указать, то должны подставиться дефолтные значения и уже по ним отобразится список продуктов.

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                minCost: $scope.costFilter.minCost,
                maxCost: $scope.costFilter.maxCost
            }
        }).then(function (response){
            $scope.ProductList = response.data;
        });
    };
    */

    $scope.deleteProduct = function (id) {
        $http.get(contextPath + '/products/delete/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }


    $scope.loadProducts();
});