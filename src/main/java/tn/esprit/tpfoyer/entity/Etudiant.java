package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    private String Enom;
    private String Eprenom;
    private Long cin;

    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getEnom() {
        return Enom;
    }

    public void setEnom(String enom) {
        Enom = enom;
    }

    public String getEprenom() {
        return Eprenom;
    }

    public void setEprenom(String eprenom) {
        Eprenom = eprenom;
    }

    private String ecole;
    private Date dateNaissance;



    @ManyToMany(mappedBy = "etudiants")
    private Set<Reservation> reservatios ;


}
