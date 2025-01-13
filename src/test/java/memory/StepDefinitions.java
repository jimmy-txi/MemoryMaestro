package memory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class StepDefinitions {

    private String gameType;
    private String gameMode;
    private String gridSize;
    private boolean gameStarted = false;

    private AccueilController accueilCtrl;

    @Given("I run the application")
    public void i_run_the_application() {
        accueilCtrl = new AccueilController();
        System.out.println("Application started");
    }

    @Given("I have selected the {string} game type")
    public void i_have_selected_the_game_type(String type) {
        accueilCtrl.setType(type);
    }

    @Given("I have selected the {string} game mode")
    public void i_have_selected_the_game_mode(String mode) {
        accueilCtrl.setMode(mode);
    }

    @Given("I have selected a {string} grid size")
    public void i_have_selected_a_grid_size(String size) {
        accueilCtrl.setTailleGrille(size);
    }

    @When("I start the game")
    public void i_start_the_game() {
        this.gameStarted = false;   

        // array of correct values
        String[] gameModes = {"letter", "number", "extreme"};
        String[] gameTypes = {"cheat", "random", "2player"};
        String[] gridSizes = {"4x4", "6x6", "6x4"};

        if ( accueilCtrl.mode != null && accueilCtrl.tailleGrille != null && accueilCtrl.type != null) {
            // check if the names are correct with an contains
            if (Arrays.asList(gameModes).contains(accueilCtrl.mode) && Arrays.asList(gameTypes).contains(accueilCtrl.type) && Arrays.asList(gridSizes).contains(accueilCtrl.tailleGrille)) {
                this.gameStarted = true;
                System.out.println("mode:" + accueilCtrl.mode + " grille:" + accueilCtrl.tailleGrille + " type:" + accueilCtrl.type);
            }
            System.out.println("mode:" + accueilCtrl.mode + " grille:" + accueilCtrl.tailleGrille + " type:" + accueilCtrl.type);
        }
        else{
            this.gameStarted = false;
            System.out.println("Option + " + accueilCtrl.mode + " " + accueilCtrl.tailleGrille + " " + accueilCtrl.type);
        }
    }

    @Then("the game should start")
    public void the_game_should_start() {
        assertTrue(gameStarted, "The game did not start");
    }

    @Then("the game should not start")
    public void the_game_should_not_start() {
        assertFalse(gameStarted, "The game did not start");
    }
    
}
