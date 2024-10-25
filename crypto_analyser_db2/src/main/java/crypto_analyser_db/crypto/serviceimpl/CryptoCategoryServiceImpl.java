package crypto_analyser_db.crypto.serviceimpl;

import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.repository.CryptoCategoryRepository;
import crypto_analyser_db.crypto.services.CryptoCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The CryptoCategoryServiceImpl class implements the CryptoCategoryService interface.
 * This service provides methods for managing cryptocurrency categories, including 
 * retrieving, adding, updating, and deleting categories.
 */
@Service
public  class CryptoCategoryServiceImpl implements CryptoCategoryService {

    private final CryptoCategoryRepository categoryRepository;

    /**
     * Constructs a CryptoCategoryServiceImpl with the specified category repository.
     * 
     * @param categoryRepository the repository for accessing crypto categories
     */
    public CryptoCategoryServiceImpl(CryptoCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    

    /**
     * Retrieves all cryptocurrency categories.
     * 
     * @return a list of all CryptoCategory objects
     */
    @Override
    public List<CryptoCategory> getAllCategories() {
        return (List<CryptoCategory>) categoryRepository.findAll();
    }

    /**
     * Retrieves a cryptocurrency category by its unique identifier.
     * 
     * @param id the unique identifier of the category
     * @return an Optional containing the found CryptoCategory, or an empty Optional if not found
     */
    @Override
    public Optional<CryptoCategory> getCategoryById(Long id) {
        return categoryRepository.findById( id);
    }

    /**
     * Retrieves a cryptocurrency category by its name.
     * 
     * @param categoryName the name of the category
     * @return an Optional containing the found CryptoCategory, or an empty Optional if not found
     */
    @Override
    public CryptoCategory getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    /**
     * Adds a new cryptocurrency category.
     * 
     * @param category the CryptoCategory object to be added
     * @return the added CryptoCategory object
     */
    @Override
    public CryptoCategory addCategory(CryptoCategory category) {
        return categoryRepository.save(category);
    }

    /**
     * Updates an existing cryptocurrency category.
     * 
     * @param category the CryptoCategory object containing updated information, including its unique identifier
     * @return the updated CryptoCategory object
     * @throws RuntimeException if the category with the specified id is not found
     */
 
    @Override
    public CryptoCategory updateCategory(Long id, CryptoCategory category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id); // Set the ID to ensure the correct category is updated
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Category with id " + id + " not found.");
    }



    /**
     * Deletes a cryptocurrency category by its unique identifier.
     * 
     * @param id the unique identifier of the category to be deleted
     * @throws RuntimeException if the category with the specified id is not found
     */
    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category with id " + id + " not found.");
        }
    }

    @Override
    @Transactional
    public void save(CryptoCategory category) {
        // Ensure the category is not null
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        
        // Save the category to the database
        categoryRepository.save(category);
    }

   

	
}
