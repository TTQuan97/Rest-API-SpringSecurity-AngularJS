mainApp.config(function ($routeProvider) {

    $routeProvider.
        when("/login",
            {
                templateUrl: "/src/views/account/login.html",
                controller: "loginCtrl"
            }
        ).
        when("/cart",
            {
                templateUrl: "/src/views/cart/detail.html",
                controller: "cartCtrl"
            }
        ).
        when("/product:categoryId?",
            {
                templateUrl: "/src/views/product/list.html",
                controller: "productCtrl"
            }
        ).
        when("/detail-product:id",
            {
                templateUrl: "/src/views/product/detail.html",
                controller: "productDetail"
            }
        ).
        otherwise("/product",
            {
                templateUrl: "/src/views/product/list.html",
                controller: "productGetAll"
            }
        );

});