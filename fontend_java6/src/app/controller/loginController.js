mainApp.controller("loginCtrl", function ($scope, $http, $location, $rootScope) {

    var LOGIN = 'http://localhost:8080/api/auth/authenticate'

    $scope.credentials = {};
    $rootScope.authenticationToken = {};

    $scope.login = function () {
        $http(
            {
                method: 'POST',
                url: LOGIN,
                data: $scope.credentials
            }
        ).then(function (response) {
            $rootScope.authenticationToken = response.data;
            $location.path("/product");
        }, function () {
            document.getElementById("confirmationModal").style.display = "block";
        }
        )
    };

    $scope.closeModal = function () {
        document.getElementById("confirmationModal").style.display = "none";
    }
})