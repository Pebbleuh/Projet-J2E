package com.example.projetj2e.persistance;

import mediatek2022.Utilisateur;

import java.sql.SQLException;

public class PersistantDocument implements mediatek2022.Document {
    private int idDoc;
    private String nomDoc;
    private String auteurDoc;
    private String dateSortieDoc;
    private String typeDoc;
    private boolean disponibiliteDoc;
    private String emprunteur;
    private String ajouteur;

    public PersistantDocument(int id, String n, String a, String date, int t, int d) {
        this.idDoc = id;
        this.nomDoc = n;
        this.auteurDoc = a;
        this.dateSortieDoc = date;
        setTypeDoc(t);
        setDisponibiliteDoc(d);
    }

    public void setTypeDoc(int t) {
        switch (t) {
            case 1:
                this.typeDoc = "Livre";
                break;
            case 2:
                this.typeDoc = "CD";
                break;
            case 3:
                this.typeDoc = "DVD";
                break;
        }
    }

    public void setDisponibiliteDoc(int d) {
        if (d == 0) {
            this.disponibiliteDoc = false;
        } else if (d == 1){
            this.disponibiliteDoc = true;
        }
    }

    @Override
    public boolean disponible() {
        return this.disponibiliteDoc;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        this.emprunteur = u.name();
        this.disponibiliteDoc = false;
        MediathequeData.emprunterDoc(this.idDoc, (Integer) u.data()[0]);
    }

    @Override
    public void retour() {
        this.emprunteur = null;
        this.disponibiliteDoc = true;
        try {
            MediathequeData.retournerDoc(this.idDoc);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return idDoc + "/" + nomDoc + "/" + auteurDoc + "/" + dateSortieDoc + "/" + typeDoc + "/" + disponibiliteDoc;
    }
}
