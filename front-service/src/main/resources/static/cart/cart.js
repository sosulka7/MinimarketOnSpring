angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';


    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    };

    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    // $scope.changeQuantity = function (productId, delta) {
    //     $http({
    //         url: contextPath + 'api/v1/cart/change_quantity',
    //         method: 'GET',
    //         params: {
    //             productId: productId,
    //             delta: delta
    //         }
    //     }).then(function (response) {
    //         $scope.loadCart();
    //     });
    // };

    $scope.checkOut = function () {
        $http.post('http://localhost:5555/core/api/v1/orders', $scope.orderDetails)
            .then(function (response) {
                $scope.loadCart();
                $scope.orderDetails = null;
            });
    };

    $scope.loadCart();
});









