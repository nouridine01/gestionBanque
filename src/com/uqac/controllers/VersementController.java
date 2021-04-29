package com.uqac.controllers;

import java.awt.TextField;

import com.uqac.App;
import com.uqac.dao.CompteDao;
import com.uqac.metier.BanqueService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class VersementController {
	String view;
	String titre;
	@FXML
	private TextField NumeroCompte;
	@FXML
	private TextField Montant;
	@FXML
	private Button EffectuerVersement;
	@FXML
	private MenuItem Virement;
	@FXML
	private MenuItem retrait;
	@FXML
	private MenuItem GestionClients;
	@FXML
	private MenuItem GestionCompte;
	@FXML
	private MenuItem logout;

	
	@FXML
	public void GestionClients(ActionEvent event) {
		// TODO Autogenerated
		App app = new App();
		view="GestionClient";
		titre="GESTION CLIENT";
		app.changeView(view, titre);
	}
		@FXML
		public void Virement(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Virement";
			titre="VIREMENT";
			app.changeView(view, titre);
		}
		
		@FXML
		public void Retrait(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Retrait";
			titre="RETRAIT";
			app.changeView(view, titre);
		}
		
		@FXML
		public void GestionCompte(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Compte";
			titre="GESTION COMPTE";
			app.changeView(view, titre);
		}
		
		@FXML
		public void logout(ActionEvent event) {
			App app = new App();
			view="Login";
			titre="LOGIN";
			app.changeView(view, titre);
		}
		
		@FXML
		public void EffectuerVersement(ActionEvent event) {
			
			long NumC = Long.parseLong(NumeroCompte.getText());
			long Mont = Long.parseLong(Montant.getText());
			CompteDao dao = new CompteDao();
			BanqueService Bank = new BanqueService();
			if (dao.find(NumC) != null)
			{
				Bank.versement(NumC,Mont);	 
			}
			else
			{
				Alert info = new Alert(AlertType.ERROR);
		           info.setTitle("Saisie des informations");
		           info.setHeaderText("veuillez saisir des Informations valide");
		           info.showAndWait();
			}
			App app = new App();
			view="GestionClients";
			titre="GESTION CLIENTS";
			app.changeView(view, titre);
		}
		
	
}