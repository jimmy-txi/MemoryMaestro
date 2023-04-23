package memory;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccueilController implements Initializable {
	
	// GLOBAL VAR //
	public String mode;
	public String tailleGrille;
	public String type;
	public Stage fenetrePrincipale = null;
	// TYPE BUTTON //
	@FXML
	private Button butTriche;
	@FXML
	private Button butMulti;
	@FXML
	private Button butAleatoire;
	//  MODE BUTTON //
	@FXML 
	private Button butLettre;
	@FXML
	private Button butNumero;
	@FXML
	private Button butExtreme;
	// GRILLE BUTTON //
	@FXML
	private Button butGrille2x2;
	@FXML
	private Button butGrille2x3;
	@FXML
	private Button butGrille4x4;
	@FXML
	private Button butGrille6x4;
	// OTHER BUTTON //
	@FXML
	public Button butJouer;



	// GLOBAL FUNCTIONS //
	@FXML
	private void actionQuitter() {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Fermeture fenetre");
		confirm.setHeaderText("voullez vous quitter l'application ?");
		confirm.initOwner(fenetrePrincipale);
		ButtonType plustard = new ButtonType("Plus tard");
		ButtonType oui = new ButtonType("quitter");
		ButtonType non = new ButtonType("rester");
		confirm.getButtonTypes().setAll(plustard, oui, non);
		confirm.getDialogPane().getStylesheets().add(getClass().getResource("accueil.css").toExternalForm());
		Optional<ButtonType> rep = confirm.showAndWait();
		
		if (rep.isPresent()) {
			if (rep.get() == oui ) {
				this.fenetrePrincipale.close();
			}
			else if (rep.get() == non ) { 
				System.out.println("Sa continue la vie mon grand !");
			}
			else {
				System.out.println("j'ai rien reçu ouuuu ? ");
			}
		}
	}
	@FXML 
	private void actionJouer() {
		if ( this.mode != null && this.tailleGrille != null ) {
			System.out.println("Jouer en mode " + this.mode + " avec une grille de taille " + this.tailleGrille);
		}
		else {
			Alert creditAlert = new Alert(AlertType.ERROR);
			creditAlert.setTitle("Erreur");
			creditAlert.setHeaderText("Veuillez selectionner un mode et un type de grille");
			creditAlert.initOwner(this.fenetrePrincipale);
			creditAlert.showAndWait();
		}
	}



	// CONSTRUCTOR //
	public AccueilController() {
		System.out.println("Construction de acueill de mort");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialisation de PlusMoinsController");
	}
	


	// SETTER //


	public void setType(String str) {
		this.type = str;
		System.out.println("Type : " + str);
	}
	public void setMode(String mode) {
		this.mode = mode;
		System.out.println("Mode : " + mode);
	}
	public void setTailleGrille(String tailleGrille) {
		this.tailleGrille = tailleGrille;
		System.out.println("Taille de la grille : " + tailleGrille);
	}
	public void setFenetrePrincipale(Stage fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
		this.fenetrePrincipale.setOnCloseRequest(event -> actionQuitter());
	}
	


	// MODE FUNCTIONS //
	@FXML
	public void modeTriche(){
		setMode("triche");
		if ( !butTriche.getStyleClass().contains("button-selected") ) {
			butTriche.getStyleClass().add("button-selected");
		}
		butMulti.getStyleClass().remove("button-selected");
		butLettre.getStyleClass().remove("button-selected");
		butNumero.getStyleClass().remove("button-selected");
		butExtreme.getStyleClass().remove("button-selected");
	}
	@FXML
	public void modeLettre(){
		setMode("lettre");
		if ( !butLettre.getStyleClass().contains("button-selected") ) {
			butLettre.getStyleClass().add("button-selected");
		}
		butNumero.getStyleClass().remove("button-selected");
		butExtreme.getStyleClass().remove("button-selected");
	}
	@FXML
	public void modeNumero(){
		setMode("couleur");
		butLettre.getStyleClass().remove("button-selected");
		if ( !butNumero.getStyleClass().contains("button-selected") ) {
			butNumero.getStyleClass().add("button-selected");
		}
		butExtreme.getStyleClass().remove("button-selected");
	}
	@FXML
	public void modeExtreme(){
		setMode("extreme");
		butLettre.getStyleClass().remove("button-selected");
		butNumero.getStyleClass().remove("button-selected");
		if ( !butExtreme.getStyleClass().contains("button-selected") ) {
			butExtreme.getStyleClass().add("button-selected");
		}
	}



	// TYPE FUNCTIONS //
	@FXML
	public void typeMulti(){
		setType("multi");
		if ( !butMulti.getStyleClass().contains("button-selected") ) {
			butMulti.getStyleClass().add("button-selected");
		}
		butTriche.getStyleClass().remove("button-selected");
		butAleatoire.getStyleClass().remove("button-selected");
	}
	@FXML
	public void typeTriche(){
		setType("triche");
		if ( !butTriche.getStyleClass().contains("button-selected") ) {
			butTriche.getStyleClass().add("button-selected");
		}
		butMulti.getStyleClass().remove("button-selected");
		butAleatoire.getStyleClass().remove("button-selected");
	}
	@FXML 
	public void typeAleatoire() {
		setType("aleatoire");
		if ( !butAleatoire.getStyleClass().contains("button-selected") ) {
			butAleatoire.getStyleClass().add("button-selected");
		}
		butTriche.getStyleClass().remove("button-selected");
		butMulti.getStyleClass().remove("button-selected");
	}
	
	

	// GRID FUNCTIONS //
	@FXML
	public void butGrille2x2() {
		setTailleGrille("2x2");
		if ( !butGrille2x2.getStyleClass().contains("button-selected") ) {
			butGrille2x2.getStyleClass().add("button-selected");
		}
		butGrille2x3.getStyleClass().remove("button-selected");
		butGrille4x4.getStyleClass().remove("button-selected");
		butGrille6x4.getStyleClass().remove("button-selected");
	}
	@FXML
	public void butGrille2x3() {
		setTailleGrille("2x3");
		butGrille2x2.getStyleClass().remove("button-selected");
		if ( !butGrille2x3.getStyleClass().contains("button-selected") ) {
			butGrille2x3.getStyleClass().add("button-selected");
		}
		butGrille4x4.getStyleClass().remove("button-selected");
		butGrille6x4.getStyleClass().remove("button-selected");
	}
	@FXML
	public void butGrille4x4() {
		setTailleGrille("4x4");
		butGrille2x2.getStyleClass().remove("button-selected");
		butGrille2x3.getStyleClass().remove("button-selected");
		if ( !butGrille4x4.getStyleClass().contains("button-selected") ) {
			butGrille4x4.getStyleClass().add("button-selected");
		}
		butGrille6x4.getStyleClass().remove("button-selected");
	}
	@FXML
	public void butGrille6x4() {
		setTailleGrille("6x4");
		butGrille2x2.getStyleClass().remove("button-selected");
		butGrille2x3.getStyleClass().remove("button-selected");
		butGrille4x4.getStyleClass().remove("button-selected");
		if ( !butGrille6x4.getStyleClass().contains("button-selected") ) {
			butGrille6x4.getStyleClass().add("button-selected");
		}
	}

	
}
