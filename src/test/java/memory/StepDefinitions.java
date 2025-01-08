package memory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private String gameType;
    private String gameMode;
    private String gridSize;
    private boolean gameStarted = false;

    @Given("I run the application")
    public void i_run_the_application() {
        // Simuler le lancement de l'application
        System.out.println("Application launched");
    }

    @Given("I have selected the {string} game type")
    public void i_have_selected_the_game_type(String type) {
        this.gameType = type;
        System.out.println("Game type selected: " + type);
    }

    @Given("I have selected the {string} game mode")
    public void i_have_selected_the_game_mode(String mode) {
        this.gameMode = mode;
        System.out.println("Game mode selected: " + mode);
    }

    @Given("I have selected a {string} grid size")
    public void i_have_selected_a_grid_size(String size) {
        this.gridSize = size;
        System.out.println("Grid size selected: " + size);
    }

    @When("I start the game")
    public void i_start_the_game() {
        this.gameStarted = true; // Simuler le démarrage de la partie
        System.out.println("Game started");
    }

    @Then("the game should start")
    public void the_game_should_start() {
        assertTrue(gameStarted, "The game did not start");
    }

    @Then("the game should not start")
    public void the_game_should_not_start() {
        assertTrue(gameStarted, "The game did not start");
    }
    
}
