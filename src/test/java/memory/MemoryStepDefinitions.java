package memory;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.stage.Stage;
import memory.MemoryApp;

import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

public class MemoryStepDefinitions extends ApplicationTest {

    private MemoryApp memoryApp;
    private Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MemoryApp::new);
        FxToolkit.setupStage(s -> {
            this.stage = s;
            stage.show();
        });
        waitForFxEvents();
    }

    @Given("je suis sur l'\u00e9cran d'Accueil")
    public void jeSuisSurLEcranDAccueil() {
        Platform.runLater(() -> {
            assertNotNull(lookup("#butJouer").query(), "L'\u00e9cran d'Accueil ne s'est pas charg\u00e9 correctement.");
        });
        waitForFxEvents();
    }

    @When("je s\u00e9lectionne un mode de jeu")
    public void jeSelectionneUnModeDeJeu() {
        Platform.runLater(() -> {
            clickOn("#gameModeDropdown");
            clickOn("Classique");
        });
        waitForFxEvents();
    }

    @When("je s\u00e9lectionne une taille de grille")
    public void jeSelectionneUneTailleDeGrille() {
        Platform.runLater(() -> {
            clickOn("#gridSizeDropdown");
            clickOn("4x4");
        });
        waitForFxEvents();
    }

    @When("je s\u00e9lectionne un type de jeu")
    public void jeSelectionneUnTypeDeJeu() {
        Platform.runLater(() -> {
            clickOn("#gameTypeDropdown");
            clickOn("Animaux");
        });
        waitForFxEvents();
    }

    @When("j'appuie sur le bouton {string}")
    public void jAppuieSurLeBouton(String nomBouton) {
        Platform.runLater(() -> {
            clickOn("#" + nomBouton);
        });
        waitForFxEvents();
    }

    @Then("l'\u00e9cran de Grille doit s'afficher")
    public void lEcranDeGrilleDoitSAfficher() {
        Platform.runLater(() -> {
            assertNotNull(lookup("#grillePane").query(), "L'\u00e9cran de Grille ne s'est pas charg\u00e9 correctement.");
        });
        waitForFxEvents();
    }

    // Méthode utilitaire pour attendre la fin des événements JavaFX
    private void waitForFxEvents() {
        try {
            FxToolkit.toolkitContext().getPrimaryStageFuture().get();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'attente des événements JavaFX.", e);
        }
    }
}
