package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;



    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
         chambreRepository.deleteById(chambreId);
    }

    @Override
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
    @Override
    public List<Chambre> searchChambrepartype(TypeChambre tch) {
        return chambreRepository.findAllByTypeChambre(tch);
    }
    @Override
    public Chambre searchChambreparnumerochambre(Long numch) {
        return chambreRepository.findChambreByNumeroChambre(numch);
    }
    @Override
    public Chambre addProjetAndProjetDetailAndAssign(Chambre ch) {
        return chambreRepository.save(ch);

    }

}
