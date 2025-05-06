package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.Date;
import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findAllByTypeChambre(TypeChambre tc);
    Chambre findChambreByNumeroChambre(Long num);

    List<Chambre> findByBlocs(Bloc bloc);
    @Query("SELECT c FROM Chambre c LEFT JOIN FETCH c.reservations")
    List<Chambre> findAllWithReservations();


}
