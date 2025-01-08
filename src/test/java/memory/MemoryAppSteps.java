package memory;

import static org.junit.Assert.*;

import io.cucumber.java.en.*;
import memory.om.Jeu;
import memory.om.Reponse;

public class MemoryAppSteps {
    private Jeu jeu;
    private Reponse reponse;

    @Given("je crée un jeu avec {int} paires")
    public void jeCreeUnJeuAvecXPaires(int nbPaires) {
        jeu = new Jeu(nbPaires);
    }

    @Then("le nombre total de cartes doit être {int}")
    public void leNombreTotalDeCartesDoitEtre(int nbCartes) {
        assertEquals(nbCartes, jeu.getNbCartes());
    }

    @Then("aucune carte ne doit être trouvée")
    public void aucuneCarteNeDoitEtreTrouvee() {
        assertEquals(0, jeu.getNbCartesTrouvees());
    }

    @Then("la partie ne doit pas être terminée")
    public void laPartieNeDoitPasEtreTerminee() {
        assertFalse(jeu.isPartieTerminee());
    }

    @When("je joue la première carte numéro {int}")
    public void jeJoueLaPremiereCarteNumero(int numCarte) {
        reponse = jeu.jouer(numCarte);
    }

    @When("je joue la seconde carte avec le même numéro {int}")
    public void jeJoueLaSecondeCarteAvecLeMemeNumero(int numCarte) {
        reponse = jeu.jouer(numCarte);
    }

    @Then("je dois avoir trouvé une paire")
    public void jeDoisAvoirTrouveUnePaire() {
        assertEquals(Reponse.GAGNE, reponse);
    }

    @Then("le nombre de coups joués doit être {int}")
    public void leNombreDeCoupsJouesDoitEtre(int nbCoups) {
        assertEquals(nbCoups, jeu.getNbCoupsJoues());
    }

    @When("je joue une carte invalide avec le numéro {int}")
    public void jeJoueUneCarteInvalideAvecLeNumero(int numCarte) {
        reponse = jeu.jouer(numCarte);
    }

    @Then("une erreur doit être retournée")
    public void uneErreurDoitEtreRetournee() {
        assertEquals(Reponse.ERREUR, reponse);
    }
}

