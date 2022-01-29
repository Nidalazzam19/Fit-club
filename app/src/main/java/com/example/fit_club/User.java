package com.example.fit_club;

public class User {
    String nom,prenom,tele,email,profile_image;


    public User(){

    }

    public User(String nom, String prenom, String tele, String email,String profile_image) {
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
        this.email = email;
        this.profile_image = profile_image;
    }

    public String getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
