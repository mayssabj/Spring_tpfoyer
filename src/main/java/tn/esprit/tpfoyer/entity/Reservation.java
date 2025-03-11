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

public class Reservation {
    @Id
    private String idReservation ;
    private Date anneeuniversitaire;
    private Boolean estValide ;
    @ToString.Exclude
    @ManyToMany
    private Set<Etudiant> etudiants ;

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    public Date getAnneeuniversitaire() {
        return anneeuniversitaire;
    }

    public void setAnneeuniversitaire(Date anneeuniversitaire) {
        this.anneeuniversitaire = anneeuniversitaire;
    }
}
