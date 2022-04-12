package com.example.projetj2e.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistantUtilisateur implements mediatek2022.Utilisateur {
    private int idUser;
    private String name;
    private String surname;
    private String loginU;
    private String passwordU;
    private boolean isBiblio;

    public PersistantUtilisateur(int numU, String n, String s, String id, String p, int b) {
        idUser = numU;
        name = n;
        loginU = id;
        surname = s;
        passwordU = p;
        setIsBiblio(b);
    }

    private void setIsBiblio(int b){
        if (b == 0) {
            isBiblio = false;
        } else if (b == 1){
            isBiblio = true;
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isBibliothecaire() {
        return isBiblio;
    }

    @Override
    public Object[] data() {
        try {
            return new Object[]{idUser, surname, loginU, passwordU, isBiblio, MediathequeData.documentsEmpruntes(idUser)};
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
