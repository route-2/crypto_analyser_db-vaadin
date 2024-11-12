package crypto_analyser_db.crypto.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import crypto_analyser_db.crypto.models.CryptoModel;
import crypto_analyser_db.crypto.services.CryptoService;
import crypto_analyser_db.crypto.services.CryptoCategoryService; // Import the CryptoCategoryService
import jakarta.annotation.security.PermitAll;

import java.util.List;

@Route(value = "", layout = MainLayout.class)
@PermitAll
public class CryptoDataView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private final CryptoService cryptoService;
    private final CryptoDataForm cryptoDataForm;
    private final Grid<CryptoModel> cryptoGrid;

    public CryptoDataView(CryptoService cryptoService, CryptoCategoryService categoryService) { // Accept categoryService in the constructor
        this.cryptoService = cryptoService;
        this.cryptoDataForm = new CryptoDataForm(cryptoService, categoryService); // Pass both services
        this.cryptoGrid = new Grid<>(CryptoModel.class);

        // Configure the grid
        cryptoGrid.setColumns("name", "symbol", "price", "oneHourChange", "twentyFourHourChange", "marketCap", "volume");
        cryptoGrid.setItems(getCryptoList());

        // Add listeners to the grid
        cryptoGrid.asSingleSelect().addValueChangeListener(event -> {
            CryptoModel selectedCrypto = event.getValue();
            if (selectedCrypto != null) {
                cryptoDataForm.setCrypto(selectedCrypto);
            }
        });

        // Add components to the layout
        Button refreshButton = new Button("Refresh", event -> refreshGrid());

        add(refreshButton, cryptoDataForm, cryptoGrid);
    }

    private List<CryptoModel> getCryptoList() {
    	 List<CryptoModel> cryptos = cryptoService.getAllCryptos();
    	 if (cryptos.isEmpty()) {
    	        System.out.println("No data found");
    	        Notification.show("No data found");
    	    }
    	cryptos.forEach(crypto -> System.out.println(crypto.getName()));
        return cryptoService.getAllCryptos(); 
        }

    private void refreshGrid() {
    	
        cryptoGrid.setItems(getCryptoList());
        Notification.show("Crypto data refreshed");
    }
}
