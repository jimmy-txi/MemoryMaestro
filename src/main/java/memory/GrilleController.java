package memory;



import java.beans.EventHandler;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import memory.om.Jeu;
import memory.om.Reponse;


public class GrilleController implements Initializable {
	
	// LABEL INFO //
	@FXML 
	Label labScore;
	@FXML
	Label labTemps;
	@FXML
	Label labJoueur;
	
	// GLOBAL VAR //
	@FXML
	private int nbJoueurs;
	public GridPane grille;
	private String type;
	private String mode;
	private String grilleTaille;
	public Stage fenetrePrincipale = null;
	private Jeu jeu;
	private Button premiereCarte = null; 
	public int grilleLong;
	public int grilleLarg;
	
	// ALPHABETT MODE VAR //
	
	ArrayList<Character> alphabet = new ArrayList<Character>(); // generated by chatbot
	private int asciiValue = 97; // Code ASCII pour la lettre 'a'
	
	
	// PROPERTY VAR //
	private IntegerProperty nbEssais =  new SimpleIntegerProperty(0);
	private IntegerProperty temps = new SimpleIntegerProperty(0);
	private IntegerProperty joueurPlaying = new SimpleIntegerProperty(1);

	private ArrayList<IntegerProperty> listNbPoints = new ArrayList<IntegerProperty>();



	// TIMER VAR //
	private Timeline timeline;
    private Duration timeElapsed = Duration.ZERO;
    private KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
        timeElapsed = timeElapsed.add(Duration.millis(10));
        updateTimeLabel();
    });


	// BUTTON VAR //
	@FXML
	public Button butQuitter;
	private ArrayList<Button> listButtons = new ArrayList<Button>(); // list of all buttons
	private ArrayList<Button> lockedButtons = new ArrayList<Button>(); // list of all buttons locked
	


	// TIMER FUNCTION //

	
	private String getFormattedElapsedTime() {
		int minutes = (int) (timeElapsed.toSeconds() / 60);
		int seconds = (int) (timeElapsed.toSeconds() % 60);
		int millis = (int) (timeElapsed.toMillis() % 1000 / 10);
		return String.format("%02d:%02d:%02d", minutes, seconds, millis);
	}

	private void updateTimeLabel() {
        int minutes = (int) (timeElapsed.toSeconds() / 60);
        int seconds = (int) (timeElapsed.toSeconds() % 60);
        int millis = (int) (timeElapsed.toMillis() % 1000 / 10);
        String time = String.format("%02d:%02d:%02d", minutes, seconds, millis);
        labTemps.setText("Temps: "+time);
    }


    public void startTimer() {
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stopTimer() {
		timeline.setCycleCount(0); 	
        timeline.stop();
    }


	// SETTER FUNCTION //
	public void setPlayerCount(int nbJoueurs) {
		for (int i = 0; i < nbJoueurs; i++) {
			listNbPoints.add(new SimpleIntegerProperty(0));
		}
		this.joueurPlaying.set(1);
		labJoueur.setText("Joueur: 1");
		this.nbJoueurs = nbJoueurs;
	}
	public void addEssais(int joueur, boolean multi ) {
		// use arraylist listessais
		joueur = joueur - 1;
		if (multi) {
			listNbPoints.get(joueur).set(listNbPoints.get(joueur).get()+1);
		}
		else {
			listNbPoints.get(0).set(listNbPoints.get(0).get()+1);
		}
	}

	public void setFenetrePrincipale(Stage fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
	}
	public void setGrilleSize(String grilleTaille) {
		butQuitter.getStyleClass().add("button-quit");
		String[] parts = grilleTaille.split("x");
		this.grilleLong = Integer.parseInt(parts[0]);
		this.grilleLarg = Integer.parseInt(parts[1]);
	}



	// GETTER FUNCTION //
	public int getGrileLong() {
		return this.grilleLong;
	}
	public int getGrilleLarg() {
		return this.grilleLarg;
	}
	


	// GENERAL FUNCTION //
	public void initGrille( ) {
		int count = 0;
		for (int i = 0; i < getGrileLong(); i++) {
			for (int j = 0; j < getGrilleLarg(); j++) {
				Button button = new Button();
				button.setId(""+count);
				button.setAlignment(Pos.CENTER);
				button.setPrefSize(this.grille.getWidth() / getGrilleLarg(), this.grille.getHeight() / getGrileLong());
				button.setBorder(null);
				button.setFont(new Font(23));
				if (this.mode == "lettre") {
					button.setText(""+alphabet.get(jeu.getCarteValeur(Integer.parseInt(button.getId()))));
				}
				else {
					button.setText(""+jeu.getCarteValeur(Integer.parseInt(button.getId())));
				}
				listButtons.add(button);
				button.setOnAction(evente -> {
					onClick(evente);
				});
				count++;
				this.grille.add(button, i, j);
			}
		}
		displayAll();
		LockAllButton();
		int viewTime = this.mode == "extreme" ? 8000 : 2000;
		new Thread(()-> {
			try {
				Thread.sleep(viewTime);
			} catch (InterruptedException e) {}
			Platform.runLater(() -> {
				HideAll();
				unlockAllButton();
			});
		}).start();
		
	}
	public void lancerJeu(String type, String mode, String grilleTaille) {
		if (type =="triche") {
			
			this.jeu = new Jeu( (this.grilleLarg*this.grilleLong)/2 ,true);
		}
		else {
			this.jeu = new Jeu( (this.grilleLarg*this.grilleLong)/2 ,false);
		}

		this.type = type;
		this.mode = mode;
		this.grilleTaille = grilleTaille;
		initGrille();
		if (type == "multi") {
			setPlayerCount(2);
		}
		else setPlayerCount(1);

	}
	public void onClick(ActionEvent event) {
		Button btn = (Button) event.getSource();
		Reponse rep = this.jeu.jouer( Integer.parseInt(btn.getId()));
		if ( rep == Reponse.PREMIERE) {
			if ( !btn.getStyleClass().contains("button-selected") ) {
				btn.getStyleClass().add("button-selected");
				btn.setDisable(true);
				this.premiereCarte = btn;
				if ( getFormattedElapsedTime().equals("00:00:00")) {
					startTimer();
				}
			}
		}
		if ( rep == Reponse.GAGNE) {
			if ( !btn.getStyleClass().contains("button-win") ) {
				btn.getStyleClass().add("button-win");
				btn.setDisable(true);
				if (this.mode =="extreme") {
					btn.setText(""+jeu.getCarteValeur(Integer.parseInt(btn.getId())));
				}
				lockedButtons.add(btn);
				this.premiereCarte.getStyleClass().remove("button-selected");
				this.premiereCarte.getStyleClass().add("button-win");
				this.premiereCarte.setDisable(true);
				if (this.mode =="extreme") {
					this.premiereCarte.setText(""+jeu.getCarteValeur(Integer.parseInt(this.premiereCarte.getId())));
				}
				lockedButtons.add(this.premiereCarte);
				if (this.type == "multi") {
					listNbPoints.get(this.joueurPlaying.get()-1).set(listNbPoints.get(this.joueurPlaying.get()-1).get()+1);
				}
				nbEssais.setValue(nbEssais.getValue()+1);
			}
		}
		if ( rep == Reponse.PERDU) {
				if (this.type == "multi") {
					if (this.joueurPlaying.get()>=this.nbJoueurs) {
						this.joueurPlaying.set(1);
					}
					else this.joueurPlaying.set(this.joueurPlaying.get()+1);
				}
				LockAllButton();				
				btn.getStyleClass().remove("button-selected");
				this.premiereCarte.getStyleClass().remove("button-selected");
				if (!this.premiereCarte.getStyleClass().contains("button-not")){
					this.premiereCarte.getStyleClass().add("button-not");
				}
				btn.setDisable(true);
				nbEssais.setValue(nbEssais.getValue()+1);
				if ( !btn.getStyleClass().contains("button-not") ) {
					btn.getStyleClass().add("button-not"); 
					new Thread(()-> {
						try {
							Thread.sleep(750);
						} catch (InterruptedException e) {}
						Platform.runLater(() -> {
							unlockAllButton();
							btn.getStyleClass().remove("button-not");
							btn.setDisable(false);
							this.premiereCarte.getStyleClass().remove("button-not");
							this.premiereCarte.setDisable(false);
							
						});
					}).start();
				}
		}
		if ( rep == Reponse.ERREUR) {
			
	
		}
		if ( jeu.isPartieTerminee() ) {
			if (this.type == "multi") {
				// get joueur gagnant
				int max = 0;
				int index = 0;
				for (int i = 0; i < listNbPoints.size(); i++) {
					if (listNbPoints.get(i).get() > max) {
						max = listNbPoints.get(i).get();
						index = i;
					}
				}
				gagner(index+1,true);
			}
			else gagner(1,false);
		}

	}
	private void LockAllButton() {
		for (Button btn : listButtons) {
			btn.setDisable(true);
		}
	} 
	private void unlockAllButton() {
		for (Button btn : listButtons) {
			if (btn != this.premiereCarte && !lockedButtons.contains(btn)) {
				btn.setDisable(false);
			}
		}
	} 
	private void displayAll() {
		for (Button btn : listButtons) {
			if ( !btn.getStyleClass().contains("button-selected") ) {
				btn.getStyleClass().add("button-selected"); 
			}
		}
	}
	private void HideAll() {
		for (Button btn : listButtons) {
			if ( (btn != this.premiereCarte && !lockedButtons.contains(btn)) && btn.getStyleClass().contains("button-selected") ) {
				btn.getStyleClass().remove("button-selected");
				if (this.mode == "extreme") {
					btn.setText(""); 
				}
			}
		}
	}
	public void gagner(int joueur, boolean multi) {
		stopTimer();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Partie terminée");
		alert.setHeaderText("Partie terminée");
		if (multi) {
			alert.setContentText("Le joueur "+joueur+" a gagné avec "+listNbPoints.get(joueur-1).get()+" paires trouvées !\n La partie a durée "+getFormattedElapsedTime());
		} else {
			alert.setContentText("Vous avez gagné en "+this.nbEssais.getValue()+" essais !\n La partie a durée "+getFormattedElapsedTime());
		}
		alert.initOwner(fenetrePrincipale);
		this.fenetrePrincipale.close();
		alert.getDialogPane().getStylesheets().add(getClass().getResource("accueil.css").toExternalForm());
		alert.showAndWait();
	}



	// BUTTON FUNCTION //
	@FXML
	private void actionQuitter() {
		if (jeu.isPartieTerminee()) {
			this.fenetrePrincipale.close();
		}
		else {

			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("Pause Jeux");
			confirm.setHeaderText("Voullez vous quitter votre partie ? ");
			confirm.initOwner(fenetrePrincipale);
			ButtonType oui = new ButtonType("Quitter la partie");
			ButtonType non = new ButtonType("Continuer de jouer");
			confirm.getButtonTypes().setAll(oui, non);
			confirm.getDialogPane().getStylesheets().add(getClass().getResource("accueil.css").toExternalForm());
			Optional<ButtonType> rep = confirm.showAndWait();
			
			if (rep.isPresent()) {
				if (rep.get() == oui ) {
					this.fenetrePrincipale.close();
				}
			}
		}
	}



	// CONSTRUCTOR FUNCTION //
	public GrilleController() {
		for (int i = 1; i <= 26; i++) {
			char c = (char) (asciiValue + i - 1);
			alphabet.add(c);
		}
		nbEssais.addListener((obs, oldVal, newVal) -> { labScore.setText("Nb Essais: "+newVal.toString());});
		joueurPlaying.addListener((obs, oldVal, newVal) -> { labJoueur.setText("Joueur: "+newVal.toString());});
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// System.out.println("Initialisation de PlusMoinsController");
	}

}
