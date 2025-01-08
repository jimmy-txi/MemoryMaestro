package memory;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import javafx.stage.Stage;
import memory.MemoryApp;

import org.testfx.framework.junit5.ApplicationTest;

public class MemoryStepDefinitions extends ApplicationTest {

    private MemoryApp memoryApp;

    @Override
    public void start(Stage stage) throws Exception {
        memoryApp = new MemoryApp();
        memoryApp.start(stage);
    }

    @Given("je suis sur l'écran d'Accueil")
    public void jeSuisSurLEcranDAccueil() {
        Platform.runLater(() -> {
            assertNotNull(lookup("#butJouer"), "L'écran d'Accueil ne s'est pas chargé correctement.");
        });
    }

    @When("je sélectionne un mode de jeu")
    public void jeSelectionneUnModeDeJeu() {
        Platform.runLater(() -> {
            clickOn("#gameModeDropdown"); // Remplacez par l'ID ou le sélecteur de contrôle réel.
            clickOn("Classique");         // Remplacez par l'option réelle.
        });
    }

    @When("je sélectionne une taille de grille")
    public void jeSelectionneUneTailleDeGrille() {
        Platform.runLater(() -> {
            clickOn("#gridSizeDropdown"); // Remplacez par l'ID ou le sélecteur de contrôle réel.
            clickOn("4x4");               // Remplacez par l'option réelle.
        });
    }

    @When("je sélectionne un type de jeu")
    public void jeSelectionneUnTypeDeJeu() {
        Platform.runLater(() -> {
            clickOn("#gameTypeDropdown"); // Remplacez par l'ID ou le sélecteur de contrôle réel.
            clickOn("Animaux");           // Remplacez par l'option réelle.
        });
    }

    @When("j'appuie sur le bouton {string}")
    public void jAppuieSurLeBouton(String nomBouton) {
        Platform.runLater(() -> {
            clickOn(nomBouton);
        });
    }

    @Then("l'écran de Grille doit s'afficher")
    public void lEcranDeGrilleDoitSAfficher() {
        Platform.runLater(() -> {
            assertNotNull(lookup("#grillePane"), "L'écran de Grille ne s'est pas chargé correctement.");
        });
    }
}
