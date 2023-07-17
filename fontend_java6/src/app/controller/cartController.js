mainApp.controller("cartCtrl", function ($scope, $http, $rootScope, $location) {

    var CART = 'http://localhost:8080/api/product/cart';
    var UPDATE = 'http://localhost:8080/api/product/update-cart';
    var DELETE = 'http://localhost:8080/api/product/delete-cart/';
    var CLEAR = 'http://localhost:8080/api/product/clear-cart';
    var PAY = 'http://localhost:8080/api/pay';

    $scope.cart = [];
    $scope.totalItemInCart = 0;
    $scope.totalMoney = 0;
    $rootScope.getCart = function () {
        $http.get(CART).then(function (response) {
            $scope.cart = response.data;
            $scope.totalItemInCart = response.headers("X-Total-Item");
            $scope.totalMoney = response.headers("X-Total-Money");
        }, function () {
            alert("Lỗi server");
        })
    }

    $rootScope.getCart();

    $scope.updateCart = function (productId, quantity) {
        $http.post(UPDATE + "?productId=" + productId + "&quantity=" + quantity).then(function (response) {
            $scope.cart = response.data;
            $scope.totalItemInCart = response.headers("X-Total-Item");
            $scope.totalMoney = response.headers("X-Total-Money");
            location.reload();
        }, function () {
            alert("Lỗi server");
        })

    }

    $scope.deleteCart = function (productId) {
        $http.delete(DELETE + productId)
            .then(function (response) {
                $scope.cart = response.data;
                $scope.totalItemInCart = response.headers("X-Total-Item");
                $scope.totalMoney = response.headers("X-Total-Money");
                location.reload();
            }, function () {
                alert("Lỗi server");
            })
    }

    $scope.clearCart = function () {
        $http.get(CLEAR).then(function (response) {
            $scope.cart = response.data;
            $scope.totalItemInCart = response.headers("X-Total-Item");
            $scope.totalMoney = response.headers("X-Total-Money");
            location.reload();
        }, function () {
            alert("Lỗi server");
        })
    }


    //chuẩn, check lại phía spring
    $scope.pay = function () {
        if ($rootScope.authenticationToken != undefined) {
            var token = 'Bearer ' + $rootScope.authenticationToken.token;
            console.log(token)
            $http({
                method: 'GET',
                url: PAY,
                headers: {
                    'Authorization': token,
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                $scope.cart = response.data;
                $scope.totalItemInCart = response.headers("X-Total-Item");
                $scope.totalMoney = response.headers("X-Total-Money");
                location.reload();
            }, function () {
            })
        } else {
            $location.path("/login");
        }

    }


})