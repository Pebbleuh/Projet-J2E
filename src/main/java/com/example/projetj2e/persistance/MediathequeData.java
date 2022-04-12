package com.example.projetj2e.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mediatek2022.*;

// classe mono-instance dont l'unique instance est connue de la mediatheque
// via une auto-declaration dans son bloc static

public class MediathequeData implements PersistentMediatheque{
	static {
		try {
			Mediatheque.getInstance().setData(new MediathequeData());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private List<mediatek2022.Document> documents;

	private MediathequeData() throws SQLException, ClassNotFoundException {
	}

	// renvoie la liste de tous les documents disponibles de la mediatheque
	@Override
	public List<mediatek2022.Document> tousLesDocumentsDisponibles() {
		synchronized (this){
			try {
				Connection c = ConnexionBD.getConnexion();
				documents = new ArrayList<>();
				assert c != null;
				Statement queryAvailableDoc = c.createStatement();
				ResultSet availableDocs = queryAvailableDoc.executeQuery("SELECT IdDoc, NomDoc, AuteurDoc, DateSortieDoc, TypeDoc, DisponibiliteDoc FROM DOCUMENT WHERE DisponibiliteDoc = 1");

				if (!availableDocs.next()) {
					System.out.println("Aucun document disponible !");
					return null;
				} else {
					do {
						documents.add(new PersistantDocument(
								availableDocs.getInt("IdDoc"),
								availableDocs.getString("NomDoc"),
								availableDocs.getString("AuteurDoc"),
								availableDocs.getString("DateSortieDoc"),
								availableDocs.getInt("TypeDoc"),
								availableDocs.getInt("DisponibiliteDoc")));
					} while (availableDocs.next());
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return documents;
		}

	}

	// va recuperer le User dans la BD et le renvoie
	// si pas trouve, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized (this){
			try {
				Connection c = ConnexionBD.getConnexion();
				assert c != null;
				PreparedStatement queryUser = c.prepareStatement("SELECT * FROM UTILISATEUR U WHERE IdU =? AND MdpU =?");
				queryUser.setString(1,login);
				queryUser.setString(2,password);
				ResultSet user = queryUser.executeQuery();
				if (!user.next()){
					return null;
				} else {
					return new PersistantUtilisateur(
							user.getInt("NumU"),
							user.getString("NomU"),
							user.getString("PrenomU"),
							user.getString("IdU"),
							user.getString("MdpU"),
							user.getInt("Statut")
					);
				}

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	// va recuperer le document de numero numDocument dans la BD
	// et le renvoie
	// si pas trouve, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		synchronized (this){
			try {
				Connection c = ConnexionBD.getConnexion();
				assert c != null;
				PreparedStatement queryDoc = c.prepareStatement("SELECT * FROM DOCUMENT WHERE IdDoc = ?");
				queryDoc.setInt(1, numDocument);
				ResultSet doc = queryDoc.executeQuery();
				if (!doc.next()){
					return null;
				} else {
					return new PersistantDocument(doc.getInt("IdDoc"), doc.getString("NomDoc"), doc.getString("AuteurDoc"), doc.getString("DateSortieDoc"), doc.getInt("TypeDoc"), doc.getInt("DisponibiliteDoc"));
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		synchronized (this){
			try {
				Connection c = ConnexionBD.getConnexion();
				assert c != null;
				PreparedStatement queryAddDoc = c.prepareStatement("INSERT INTO DOCUMENT (IdDoc, NomDoc, AuteurDoc, DateSortieDoc, TypeDoc, DisponibiliteDoc, Emprunteur, Ajouteur) VALUES (SEQDOCUMENT.nextval,?,?,?,?,?,?,?)");
					//queryAddDoc.setInt(1, nextID_from_seq);
					queryAddDoc.setObject(1, args[0]);
					queryAddDoc.setObject(2, args[1]);
					queryAddDoc.setObject(3, args[2]);
					queryAddDoc.setInt(4, type);
					queryAddDoc.setInt(5, 1);
					queryAddDoc.setObject(6, null);
					queryAddDoc.setObject(7, args[3]);
					queryAddDoc.executeQuery();
				} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
	}


	public static List<mediatek2022.Document> documentsEmpruntes(int idU) throws SQLException, ClassNotFoundException {
		Connection c = ConnexionBD.getConnexion();
		synchronized (Objects.requireNonNull(c)){
			List <mediatek2022.Document> documents = new ArrayList<>();
			PreparedStatement queryDocs = c.prepareStatement("SELECT * FROM DOCUMENT WHERE Emprunteur = ?");
			queryDocs.setInt(1, idU);
			ResultSet doc = queryDocs.executeQuery();
			if (!doc.next()){
				return null;
			} else {
				do {
					documents.add(new PersistantDocument(
							doc.getInt("IdDoc"),
							doc.getString("NomDoc"),
							doc.getString("AuteurDoc"),
							doc.getString("DateSortieDoc"),
							doc.getInt("TypeDoc"),
							doc.getInt("DisponibiliteDoc")));
				} while (doc.next());
			}
			return documents;
		}
	}

	public static void emprunterDoc(int numDoc, int emprunteur) throws SQLException, ClassNotFoundException {
		Connection c = ConnexionBD.getConnexion();
		synchronized (Objects.requireNonNull(c)){;
			PreparedStatement queryModif = c.prepareStatement("UPDATE DOCUMENT SET DisponibiliteDoc = 0, Emprunteur = ? WHERE IdDoc = ?");
			queryModif.setInt(1,emprunteur);
			queryModif.setInt(2, numDoc);
			queryModif.executeQuery();
		}
	}

	public static void retournerDoc(int numDoc) throws SQLException, ClassNotFoundException {
		Connection c = ConnexionBD.getConnexion();
		synchronized (Objects.requireNonNull(c)){
			PreparedStatement queryModif = c.prepareStatement("UPDATE DOCUMENT SET DisponibiliteDoc = 1, Emprunteur = null WHERE IdDoc = ?");
			queryModif.setInt(1, numDoc);
			queryModif.executeQuery();
		}

	}
}
