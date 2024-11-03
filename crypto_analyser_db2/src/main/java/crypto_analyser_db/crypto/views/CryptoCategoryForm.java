package crypto_analyser_db.crypto.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;


public class CryptoCategoryForm extends FormLayout {

    private static final long serialVersionUID = 1L;
    private final CryptoCategoryService categoryService;
    private final CryptoCategoryView cryptoCategoryView; // Reference to parent view
    private final TextField name = new TextField("Category Name");
    private CryptoCategory category;

    public CryptoCategoryForm(CryptoCategoryService categoryService, CryptoCategoryView cryptoCategoryView) {
        this.categoryService = categoryService;
        this.cryptoCategoryView = cryptoCategoryView; // Initialize the reference to parent view
        add(name, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        Button save = new Button("Save", event -> save());
        Button delete = new Button("Delete", event -> delete());
        Button update = new Button("Update", event -> update());
        return new HorizontalLayout(save, delete,update);
    }

    public void setCategory(CryptoCategory category) {
        this.category = category;
        if (category != null) {
            name.setValue(category.getCategoryName());
        } else {
            name.clear();
        }
    }

    private void save() {
        if (category == null) {
            category = new CryptoCategory();
        }
        category.setCategoryName(name.getValue());
        categoryService.save(category);
        Notification.show("Category saved", 3000, Notification.Position.TOP_CENTER);
        cryptoCategoryView.refreshGrid(); // Refresh grid in parent view after saving
        setCategory(null); // Clear the form after saving
    }

    private void delete() {
        String categoryName = name.getValue().trim(); // Get category name from text field
        if (!categoryName.isEmpty()) {
            try {
                categoryService.deleteCategoryByName(categoryName);
                Notification.show("Category deleted: " + categoryName, 3000, Notification.Position.TOP_CENTER);
                cryptoCategoryView.refreshGrid(); // Refresh grid in parent view after deletion
                name.clear(); // Clear the field after deletion
            } catch (Exception e) {
                Notification.show("Error deleting category: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
            }
        } else {
            Notification.show("Please enter a category name to delete", 3000, Notification.Position.TOP_CENTER);
        }
    }
    
    private void update() {
        String existingCategoryName = name.getValue().trim();

        if (!existingCategoryName.isEmpty() && category != null && category.getCategoryName().equals(existingCategoryName)) {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Update Category");

            TextField newCategoryField = new TextField("New Category Name");
            Button confirmUpdate = new Button("Update", event -> {
                String newCategoryName = newCategoryField.getValue().trim();
                if (!newCategoryName.isEmpty()) {
                    try {
                        CryptoCategory updatedCategory = new CryptoCategory(newCategoryName);
                        cryptoCategoryView.getCategoryService().updateCategory(category.getId(), updatedCategory); // Update using the service

                        Notification.show("Category updated from: " + existingCategoryName + " to: " + newCategoryName, 3000, Notification.Position.TOP_CENTER);
                        cryptoCategoryView.refreshGrid();
                        setCategory(null);
                        dialog.close();
                    } catch (Exception e) {
                        Notification.show("Error updating category: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
                    }
                } else {
                    Notification.show("Please enter a valid new category name.", 3000, Notification.Position.TOP_CENTER);
                }
            });

            dialog.add(newCategoryField, confirmUpdate);
            dialog.open();
        } else {
            Notification.show("Please select a category and enter the existing category name to update.", 3000, Notification.Position.TOP_CENTER);
        }
    }



}
