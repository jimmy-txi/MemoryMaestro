package memory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import io.cucumber.java.en.*;
import memory.om.Jeu;
import memory.om.Reponse;

public class MemoryAppSteps {
    private Jeu game;
    private Reponse response;

    @Given("I create a game with {int} pairs")
    public void iCreateAGameWithXPairs(int numPairs) {
        game = new Jeu(numPairs, true);
    }

    @Then("the total number of cards should be {int}")
    public void theTotalNumberOfCardsShouldBe(int numCards) {
        assertEquals(numCards, game.getNbCartes());
    }

    @Then("no card should be found")
    public void noCardShouldBeFound() {
        assertEquals(0, game.getNbCartesTrouvees());
    }

    @Then("the game should not be finished")
    public void theGameShouldNotBeFinished() {
        assertFalse(game.isPartieTerminee());
    }

    @When("I play the first card number {int}")
    public void iPlayTheFirstCardNumber(int cardNumber) {
        System.out.println(cardNumber);
        response = game.jouer(cardNumber);
        System.out.println(response);
    }

    @When("I play the second card with the same number {int}")
    public void iPlayTheSecondCardWithTheSameNumber(int cardNumber) {
        System.out.println(cardNumber);
        response = game.jouer(cardNumber);
        System.out.println(response);
    }

    @Then("I should have found a pair")
    public void iShouldHaveFoundAPair() {
        // Check that the response is "GAGNE" when the cards form a pair
        assertEquals(Reponse.GAGNE, response);
    }

    @Then("the number of moves played should be {int}")
    public void theNumberOfMovesPlayedShouldBe(int numMoves) {
        assertEquals(numMoves, game.getNbCoupsJoues());
    }

    @When("I play an invalid card with the number {int}")
    public void iPlayAnInvalidCardWithTheNumber(int cardNumber) {
        response = game.jouer(cardNumber);
    }

    @Then("an error should be returned")
    public void anErrorShouldBeReturned() {
        assertEquals(Reponse.ERREUR, response);
    }
}
