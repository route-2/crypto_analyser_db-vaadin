
package crypto_analyser_db.crypto.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import crypto_analyser_db.crypto.models.CryptoModel;
import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;
import crypto_analyser_db.crypto.services.CryptoService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CryptoDataForm extends FormLayout {

    private static final long serialVersionUID = 1L;
    private final TextField nameField = new TextField("Name");
    private final TextField symbolField = new TextField("Symbol");
    private final NumberField priceField = new NumberField("Price");
    private final NumberField oneHourChangeField = new NumberField("1 Hour Change");
    private final NumberField twentyFourHourChangeField = new NumberField("24 Hour Change");
    private final NumberField marketCapField = new NumberField("Market Cap");
    private final NumberField volumeField = new NumberField("Volume");
    private final ComboBox<String> categoryField = new ComboBox<>("Category"); 

    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");

    private final CryptoService cryptoService;
    private final CryptoCategoryService categoryService;
    private CryptoModel currentCrypto;

    @Autowired
    public CryptoDataForm(CryptoService cryptoService, CryptoCategoryService categoryService) {
        this.cryptoService = cryptoService;
        this.categoryService = categoryService;

        // Set validators
        priceField.addValueChangeListener(event -> {
            if (event.getValue() != null && event.getValue() < 0) {
                Notification.show("Price must be a positive number");
                priceField.setValue(0.0); // Reset to a valid value
            }
        });

        categoryField.setItems(getCategoryNames());
        saveButton.addClickListener(event -> save());
        deleteButton.addClickListener(event -> delete());
        updateButton.addClickListener(event -> update());
        

        add(nameField, symbolField, priceField, oneHourChangeField, twentyFourHourChangeField,
            marketCapField, volumeField, categoryField, saveButton, deleteButton);
        
        // Create a horizontal layout for buttons
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, deleteButton, updateButton);
        buttonLayout.setSpacing(true); // Add spacing between buttons

        // Add the button layout to the form
        add(buttonLayout);
    }

    private List<String> getCategoryNames() {
        // Fetch all category names from the service
        return categoryService.getAllCategories()
                              .stream()
                              .map(CryptoCategory::getCategoryName)
                              .toList();
    }

    public void setCrypto(CryptoModel crypto) {
        this.currentCrypto = crypto;
        if (crypto != null) {
            nameField.setValue(crypto.getName());
            symbolField.setValue(crypto.getSymbol());
            priceField.setValue(crypto.getPrice());
            oneHourChangeField.setValue(crypto.getOneHourChange());
            twentyFourHourChangeField.setValue(crypto.getTwentyFourHourChange());
            marketCapField.setValue((double) crypto.getMarketCap());
            volumeField.setValue((double) crypto.getVolume());
            categoryField.setValue(crypto.getCategory().getCategoryName()); // Set category name
        } else {
            clearForm();
        }
    }

    private void clearForm() {
        nameField.clear();
        symbolField.clear();
        priceField.clear();
        oneHourChangeField.clear();
        twentyFourHourChangeField.clear();
        marketCapField.clear();
        volumeField.clear();
        categoryField.clear();
        currentCrypto = null;
    }

    private void save() {
        if (nameField.isEmpty() || symbolField.isEmpty() || priceField.isEmpty()) {
            Notification.show("Please fill in all required fields", 3000, Notification.Position.MIDDLE);
            return;
        }

        // If currentCrypto is null, we are creating a new entry
        if (currentCrypto == null) {
            currentCrypto = new CryptoModel(); // Use default constructor
        }

        // Fetch category by name from the ComboBox
        String categoryName = categoryField.getValue();
        CryptoCategory category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            Notification.show("Category not found: " + categoryName, 3000, Notification.Position.MIDDLE);
            return;  // Stop the save operation if category is not found
        }

        // Set properties of the currentCrypto object
        currentCrypto.setName(nameField.getValue());
        currentCrypto.setSymbol(symbolField.getValue());
        currentCrypto.setPrice(priceField.getValue());
        currentCrypto.setOneHourChange(oneHourChangeField.getValue());
        currentCrypto.setTwentyFourHourChange(twentyFourHourChangeField.getValue());
        currentCrypto.setMarketCap(marketCapField.getValue().intValue());
        currentCrypto.setVolume(volumeField.getValue().longValue());
        currentCrypto.setCategory(category);
        currentCrypto.setCategoryId(category.getId()); 

        // Save the crypto data and get the result
        boolean isSaved = cryptoService.addCrypto(currentCrypto); 
        if (isSaved) {
            Notification.show("Crypto data saved");
            clearForm(); // Optionally clear the form after successful save
        } else {
            Notification.show("Failed to save crypto data");
        }
    }


    private void delete() {
        if (currentCrypto != null) {
            Long id = currentCrypto.getId(); // Use the ID from the currentCrypto
            boolean isDeleted = cryptoService.deleteCrypto(id);
            if (isDeleted) {
                Notification.show("Crypto data deleted");
                clearForm();
            } else {
                Notification.show("Failed to delete crypto data");
            }
        } else {
            Notification.show("No crypto selected to delete");
        }
    }
    
    
    private void update() {
        if (currentCrypto == null) {
            Notification.show("No crypto selected to update", 3000, Notification.Position.MIDDLE);
            return;
        }

        // Update properties of the currentCrypto object with the current form values
        currentCrypto.setName(nameField.getValue());
        currentCrypto.setSymbol(symbolField.getValue());
        currentCrypto.setPrice(priceField.getValue());
        currentCrypto.setOneHourChange(oneHourChangeField.getValue());
        currentCrypto.setTwentyFourHourChange(twentyFourHourChangeField.getValue());
        currentCrypto.setMarketCap(marketCapField.getValue().intValue());
        currentCrypto.setVolume(volumeField.getValue().longValue());

        // Fetch and set the category
        String categoryName = categoryField.getValue();
        CryptoCategory category = categoryService.getCategoryByName(categoryName);
        if (category != null) {
            currentCrypto.setCategory(category);
            currentCrypto.setCategoryId(category.getId()); 
        } else {
            Notification.show("Category not found: " + categoryName, 3000, Notification.Position.MIDDLE);
            return; // Stop the update operation if category is not found
        }

        // Call the service to update the crypto entry in the database
        boolean isUpdated = cryptoService.updateCrypto(currentCrypto.getId(), currentCrypto); // Pass the ID and the model
        if (isUpdated) {
            Notification.show("Crypto data updated");
            clearForm(); // Optionally clear the form after successful update
        } else {
            Notification.show("Failed to update crypto data");
        }
    }


}
