package crypto_analyser_db.crypto.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import crypto_analyser_db.crypto.services.SecurityService;
import crypto_analyser_db.crypto.views.CryptoDataView;
import crypto_analyser_db.crypto.views.CryptoCategoryView;

/**
 * The main layout for the Crypto Analyzer application.
 */
public class MainLayout extends AppLayout {

    private static final long serialVersionUID = -5291741451913578403L;

    private final SecurityService securityService;

    /**
     * Constructor for MainLayout.
     * 
     * @param securityService the security service used for authentication and logout
     */
    @Autowired
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    /**
     * Creates the application header with a title, toggle button, and logout button.
     */
    private void createHeader() {
        H1 logo = new H1("Crypto Analyzer");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM
        );

        // Retrieve the authenticated user's name and create a logout button
        String username = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + username, e -> securityService.logout());

        // Set up the layout for the header
        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM
        );

        addToNavbar(header);
    }

    /**
     * Creates the application drawer with navigation links to different views.
     */
    private void createDrawer() {
        RouterLink cryptoDataLink = new RouterLink("Crypto Data", CryptoDataView.class);
        cryptoDataLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink cryptoCategoryLink = new RouterLink("Crypto Categories", CryptoCategoryView.class);

        addToDrawer(new VerticalLayout(cryptoDataLink, cryptoCategoryLink));
    }
}
