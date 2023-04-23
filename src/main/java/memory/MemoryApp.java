package memory;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import memory.*;

public class MemoryApp extends Application{

	// GLOBAL VAR //
	private BorderPane accueilPane;
	private VBox   grillePane;
	private Stage      primaryStage;
	



	// GENERAL FUNCTION //
	public static void main2(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {	
			this.primaryStage = primaryStage;
			
			FXMLLoader loaderAccueil      = new FXMLLoader(getClass().getResource("accueil.fxml"));
			this.accueilPane      = loaderAccueil.load();
			AccueilController accueilCtrl = loaderAccueil.getController();
			accueilCtrl.setFenetrePrincipale(primaryStage);
			Scene accueilScene = new Scene(accueilPane);
			accueilPane.getStylesheets().add(getClass().getResource("accueil.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(accueilScene);
			primaryStage.setTitle("Memory Maestro");
			primaryStage.show();
			
			accueilCtrl.butJouer.setOnAction(event -> {
				if ( accueilCtrl.mode != null && accueilCtrl.tailleGrille != null && accueilCtrl.type != null) {
					try {
						FXMLLoader loaderGrille   = new FXMLLoader(getClass().getResource("grille.fxml"));
						this.grillePane           = loaderGrille.load();
						grillePane.getStylesheets().add(getClass().getResource("grille.css").toExternalForm());
						GrilleController grilleController = loaderGrille.getController();
			
						grilleController.setFenetrePrincipale(this.primaryStage);
						Stage grilleStage =new Stage();
						Scene grilleScene = new Scene(this.grillePane);
						
						grilleStage.setScene(grilleScene);
						grilleStage.show();
						grilleController.setGrilleSize(accueilCtrl.tailleGrille);
						grilleController.setFenetrePrincipale(grilleStage);
						grilleController.lancerJeu(accueilCtrl.type ,accueilCtrl.mode, accueilCtrl.tailleGrille);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					Alert creditAlert = new Alert(AlertType.ERROR);
					creditAlert.setTitle("Erreur");
					creditAlert.setHeaderText("Veuillez selectionner \nun mode de jeu\nun type de grille\net un type de jeux.");
					creditAlert.initOwner(accueilCtrl.fenetrePrincipale);
					creditAlert.showAndWait();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}