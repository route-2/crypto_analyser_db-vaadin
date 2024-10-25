package crypto_analyser_db.crypto.controller;


import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CryptoCategoryController {

    private final CryptoCategoryService categoryService;

    public CryptoCategoryController(CryptoCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<CryptoCategory>> getAllCategories() {
        List<CryptoCategory> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CryptoCategory> getCategoryById(@PathVariable Long id) {
        Optional<CryptoCategory> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

 // Get category by name
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CryptoCategory> getCategoryByName(@PathVariable String categoryName) {
        CryptoCategory category = categoryService.getCategoryByName(categoryName); // Change the method to return a single category
        
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    // Add a new category
    @PostMapping
    public ResponseEntity<CryptoCategory> addCategory(@RequestBody CryptoCategory category) {
        CryptoCategory savedCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<CryptoCategory> updateCategory(@PathVariable Long id, @RequestBody CryptoCategory category) {
        try {
            CryptoCategory updatedCategory = categoryService.updateCategory(id, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
