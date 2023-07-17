mainApp.controller("categoryCtrl", function ($scope, $http) {

    $scope.categoryList = [];

    $http.get("http://localhost:8080/api/category").then(function (response) {
        $scope.categoryList = response.data;
    }, function () {
        alert("Lỗi server");
    })

})