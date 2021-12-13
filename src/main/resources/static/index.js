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
                //для номера страницы пока что поле ввода
                pageNumber: $scope.filter ? $scope.filter.pageNumber : null
            }
        }).then(function (response){
            $scope.ProductList = response.data.content;
        });
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }


    $scope.loadProducts();
});