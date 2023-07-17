mainApp.controller("productCtrl", function ($scope, $http, $routeParams, $rootScope) {

    var PRODUCTBYCATEGORYAPI = 'http://localhost:8080/api/product/product-by-category';
    var PRODUCTAPI = 'http://localhost:8080/api/product';
    var ADDTOCART = 'http://localhost:8080/api/product/add-to-cart';
    var categoryId = $routeParams.categoryId;

    $scope.productList = [];
    $scope.pagination = {
        totalPages: 0,
        currentPage: 0
    }

    // auto get products or products by category
    if (categoryId != undefined) {
        $http.get(PRODUCTBYCATEGORYAPI + "?categoryId=" + categoryId).
            then(function (response) {
                $scope.productList = response.data;
                $scope.pagination.totalPages = response.headers("X-Total-Page");
            }, function () {
                alert("Lỗi server");
            })
    } else {
        $http.get(PRODUCTAPI).then(function (response) {
            $scope.productList = response.data;
            $scope.pagination.totalPages = response.headers("X-Total-Page");
        }, function () {
            alert("Lỗi server");
        })
    }

    //get a list of pages
    $scope.getNumberRange = function () {
        var range = [];
        for (var i = 0; i < $scope.pagination.totalPages; i++) {
            range.push(i);
        }
        return range;
    };

    //add product to cart
    $scope.addToCart = function (productId) {
        $http.get(ADDTOCART + "/" + productId).then(function () {
            $rootScope.getCart();
            location.reload();
        }, function () {
            alert("Lỗi server");
        })
    }

    //get page
    $scope.getPage = function (indexPage) {
        if (indexPage < 0 || indexPage > $scope.pagination.totalPages - 1) {
            return false;
        } else {
            if ($routeParams.categoryId != undefined) {
                $http.get(PRODUCTBYCATEGORYAPI + "?indexPage=" + indexPage + "&categoryId=" + categoryId).
                    then(function (response) {
                        $scope.pagination.currentPage = indexPage;
                        $scope.productList = response.data;
                    }, function () {
                        alert("Lỗi server");
                    })
            } else {
                $http.get(PRODUCTAPI + "?indexPage=" + indexPage).
                    then(function (response) {
                        $scope.pagination.currentPage = indexPage;
                        $scope.productList = response.data;
                    }, function () {
                        alert("Lỗi server");
                    })
            }
        }
    }
})

mainApp.controller("productDetail", function ($scope, $http, $routeParams) {

    $scope.product = {};

    //get product detail
    $http.get("http://localhost:8080/api/product/" + $routeParams.id).
        then(function (response) {
            $scope.product = response.data;
        }, function () {
            alert("Không thể lấy chi tiết");
        })
})
// mainApp.controller("productCreate", function ($scope, $http, $routeParams) {
//     $scope.product = {};
//     $scope.create = function () {
//         $http.post("http://localhost:8080/api/product/" + $routeParams.id).then(function (response) {
//             $scope.product = response.data;
//         }, function () {
//             alert("Lỗi server");
//         })

//     }
// })
// mainApp.controller("productUpdate", function ($scope, $http, $routeParams) {
//     $scope.product = {};
//     $scope.update = function () {
//         $http.put("http://localhost:8080/api/product/" + $routeParams.id).then(function (response) {
//             $scope.product = response.data;
//         }, function () {
//             alert("Lỗi server");
//         })

//     }
// })
