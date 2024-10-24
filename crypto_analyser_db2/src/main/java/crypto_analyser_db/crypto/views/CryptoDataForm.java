
package crypto_analyser_db.crypto.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import crypto_analyser_db.crypto.models.CryptoModel;
import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;
import crypto_analyser_db.crypto.services.CryptoService;

import java.util.List;

public class CryptoDataForm extends FormLayout {

    private static final long serialVersionUID = 1L;
    private final TextField nameField = new TextField("Name");
    private final TextField symbolField = new TextField("Symbol");
    private final NumberField priceField = new NumberField("Price");
    private final NumberField oneHourChangeField = new NumberField("1 Hour Change");
    private final NumberField twentyFourHourChangeField = new NumberField("24 Hour Change");
    private final NumberField marketCapField = new NumberField("Market Cap");
    private final NumberField volumeField = new NumberField("Volume");
    private final ComboBox<CryptoCategory> categoryField = new ComboBox<>("Category");

    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");

    private CryptoService cryptoService;
    private CryptoCategoryService categoryService;
    private CryptoModel currentCrypto;

    public CryptoDataForm(CryptoService cryptoService, CryptoCategoryService categoryService) {
        this.cryptoService = cryptoService;
        this.categoryService = categoryService;

        // Setting up required fields
        priceField.setRequiredIndicatorVisible(true);
        oneHourChangeField.setRequiredIndicatorVisible(true);
        twentyFourHourChangeField.setRequiredIndicatorVisible(true);
        marketCapField.setRequiredIndicatorVisible(true);
        volumeField.setRequiredIndicatorVisible(true);

        // Set validators using listeners
        priceField.addValueChangeListener(event -> {
            if (event.getValue() != null && event.getValue() < 0) {
                Notification.show("Price must be a positive number");
                priceField.setValue(0.0); // Reset to a valid value
            }
        });

        marketCapField.addValueChangeListener(event -> {
            if (event.getValue() != null && event.getValue() < 0) {
                Notification.show("Market Cap must be a positive number");
                marketCapField.setValue((double) 0L); // Reset to a valid value
            }
        });

        volumeField.addValueChangeListener(event -> {
            if (event.getValue() != null && event.getValue() < 0) {
                Notification.show("Volume must be a positive number");
                volumeField.setValue((double) 0L); // Reset to a valid value
            }
        });

        categoryField.setItems(getCategories());
        categoryField.setItemLabelGenerator(CryptoCategory::getCategoryName);

        saveButton.addClickListener(event -> save());
        deleteButton.addClickListener(event -> delete());

        add(nameField, symbolField, priceField, oneHourChangeField, twentyFourHourChangeField,
            marketCapField, volumeField, categoryField, saveButton, deleteButton);
    }

    private List<CryptoCategory> getCategories() {
        return categoryService.getAllCategories();
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
            categoryField.setValue(crypto.getCategory()); // Set the selected category
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
        if (currentCrypto == null) {
            currentCrypto = new CryptoModel();
        }
        currentCrypto.setName(nameField.getValue());
        currentCrypto.setSymbol(symbolField.getValue());
        currentCrypto.setPrice(priceField.getValue());
        currentCrypto.setOneHourChange(oneHourChangeField.getValue());
        currentCrypto.setTwentyFourHourChange(twentyFourHourChangeField.getValue());
        currentCrypto.setMarketCap(marketCapField.getValue().intValue());
        currentCrypto.setVolume(volumeField.getValue().longValue());
        currentCrypto.setCategory(categoryField.getValue());

        cryptoService.addCrypto(currentCrypto);
        Notification.show("Crypto data saved");
        clearForm();
    }

    private void delete() {
        if (currentCrypto != null) {
            Long rank = (long) currentCrypto.getRank(); // Convert int to Long
            boolean isDeleted = cryptoService.deleteCrypto(rank);
            if (isDeleted) {
                Notification.show("Crypto data deleted");
                clearForm();
            } else {
                Notification.show("Failed to delete crypto data");
            }
        }
    }
}
