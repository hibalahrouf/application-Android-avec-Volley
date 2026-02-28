package com.example.lab8.beans;


import com.google.gson.annotations.SerializedName;

public class Etudiant {

    private int id;

    @SerializedName("nom")
    private String lastNameh1;

    @SerializedName("prenom")
    private String firstNameh1;

    @SerializedName("ville")
    private String cityh1;

    @SerializedName("sexe")
    private String genderh1;

    public Etudiant() {}

    public Etudiant(int id, String lastNameh1, String firstNameh1, String cityh1, String genderh1) {
        this.id = id;
        this.lastNameh1 = lastNameh1;
        this.firstNameh1 = firstNameh1;
        this.cityh1 = cityh1;
        this.genderh1 = genderh1;
    }
    public int getId() {
        return id;
    }

    public String getLastNameh1() {
        return lastNameh1;
    }

    public String getFirstNameh1() {
        return firstNameh1;
    }

    public String getCityh1() {
        return cityh1;
    }

    public String getGenderh1() {
        return genderh1;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", lastNameh1='" + lastNameh1 + '\'' +
                ", firstNameh1='" + firstNameh1 + '\'' +
                ", cityh1='" + cityh1 + '\'' +
                ", genderh1='" + genderh1 + '\'' +
                '}';
    }
}