package quantran.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quantran.demo.service.CategoryService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }
}
