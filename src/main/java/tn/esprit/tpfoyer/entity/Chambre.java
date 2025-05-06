package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre ;
    private Long numeroChambre ;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeChambre;

    @ToString.Exclude

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    public Bloc getBlocs() {
        return blocs;
    }

    public void setBlocs(Bloc blocs) {
        this.blocs = blocs;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @ManyToOne
    private Bloc blocs ;

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public void setNumeroChambre(Long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public TypeChambre getTypeC() {
        return typeChambre;
    }

    public void setTypeC(TypeChambre typeC) {
        this.typeChambre = typeC;
    }

    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }


}
