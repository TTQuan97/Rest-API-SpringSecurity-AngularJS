package quantran.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quantran.demo.entity.Product;
import quantran.demo.service.CartService;
import quantran.demo.service.ProductService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        exposedHeaders = {"X-Total-Page", "X-Total-Item", "X-Total-Money"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.OPTIONS})
public class ProductController {

    private final ProductService productService;

    private final CartService cartService;

    @GetMapping("/api/product")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer indexPage) {
        Page<Product> page = productService.getAll(indexPage);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Page", String.valueOf(page.getTotalPages()));
        return ResponseEntity.ok().headers(httpHeaders).body(page.getContent());
    }

    @GetMapping("/api/product/product-by-category")
    public ResponseEntity<?> getAllByCategory(@RequestParam(defaultValue = "0") Integer indexPage,
                                              @RequestParam(defaultValue = "") String categoryId) {
        Page<Product> page = productService.getAllByCategory(categoryId, indexPage);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Page", String.valueOf(page.getTotalPages()));
        return ResponseEntity.ok().headers(httpHeaders).body(page.getContent());
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/api/product/add-to-cart/{id}")
    public ResponseEntity<?> addToCart(@PathVariable Integer id) {
        cartService.add(id);
        return ResponseEntity.ok(cartService.countItem());
    }

    @GetMapping("/api/product/cart")
    public ResponseEntity<?> getCart() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Item", String.valueOf(cartService.countItem()));
        httpHeaders.add("X-Total-Money", String.valueOf(cartService.countTotalMonney()));
        return ResponseEntity.ok().headers(httpHeaders).body(cartService.getCart());
    }

    @PostMapping("/api/product/update-cart")
    public ResponseEntity<?> updateCart(@RequestParam Integer productId,
                                        @RequestParam(defaultValue = "1") Integer quantity) {
        cartService.update(productId, quantity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Item", String.valueOf(cartService.countItem()));
        httpHeaders.add("X-Total-Money", String.valueOf(cartService.countTotalMonney()));
        return ResponseEntity.ok().headers(httpHeaders).body(cartService.getCart());
    }

    @DeleteMapping("/api/product/delete-cart/{productId}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer productId) {
        cartService.delete(productId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Item", String.valueOf(cartService.countItem()));
        httpHeaders.add("X-Total-Money", String.valueOf(cartService.countTotalMonney()));
        return ResponseEntity.ok().headers(httpHeaders).body(cartService.getCart());
    }

    @GetMapping("/api/product/clear-cart")
    public ResponseEntity<?> clearCart() {
        cartService.clear();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Item", String.valueOf(cartService.countItem()));
        httpHeaders.add("X-Total-Money", String.valueOf(cartService.countTotalMonney()));
        return ResponseEntity.ok().headers(httpHeaders).body(cartService.getCart());
    }

    @GetMapping("/api/pay")
    public ResponseEntity<?> pay() {
        cartService.clear();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Item", String.valueOf(cartService.countItem()));
        httpHeaders.add("X-Total-Money", String.valueOf(cartService.countTotalMonney()));
        return ResponseEntity.ok().headers(httpHeaders).body(cartService.getCart());
    }
}
