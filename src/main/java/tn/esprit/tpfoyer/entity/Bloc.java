package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private String capaciteBloc;

    public Long getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(Long idBloc) {
        this.idBloc = idBloc;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public String getCapaciteBloc() {
        return capaciteBloc;
    }

    public void setCapaciteBloc(String capaciteBloc) {
        this.capaciteBloc = capaciteBloc;
    }

    public Foyer getFoyers() {
        return foyers;
    }

    public void setFoyers(Foyer foyers) {
        this.foyers = foyers;
    }

    @ToString.Exclude

    @ManyToOne(cascade = CascadeType.ALL)
    Foyer foyers;

    @OneToMany(mappedBy = "blocs")
    private Set<Chambre>  chambre;


}
