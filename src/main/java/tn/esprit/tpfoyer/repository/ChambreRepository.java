package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
//    List<Chambre> findAllByTypeChambre(TypeChambre tc);
//    Chambre findChambreByNumeroChambre(long num);


}
