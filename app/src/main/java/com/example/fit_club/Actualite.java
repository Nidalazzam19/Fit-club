package com.example.fit_club;

public class Actualite {


    private String titre;
    private String date;
    private String description;

    public Actualite(String titre, String date,String description) {
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





//
//    public String getA_intitule() {
//        return a_intitule;
//    }
//
//    public void setA_intitule(String a_intitule) {
//        this.a_intitule = a_intitule;
//    }
//
//    public String getA_date() {
//        return a_date;
//    }
//
//    public void setA_date(String a_date) {
//        this.a_date = a_date;
//    }

//    public int getIdImage() {
//        return idImage;
//    }
//
//    public void setIdImage(int idImage) {
//        this.idImage = idImage;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }




//    @Override
//    public String toString() {
//        return "Actualite{" +
//                "a_intitule='" + a_intitule + '\'' +
//                ", a_date='" + a_date + '\'' +
//                ", idImage='" + idImage + '\'' +
//                ", description=" + description +
//                '}';
//    }
}
