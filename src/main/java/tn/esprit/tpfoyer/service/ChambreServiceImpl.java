package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor

public class ChambreServiceImpl implements IChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    static final Logger log = LoggerFactory.getLogger(ChambreServiceImpl.class);
    @Autowired
    BlocRepository blocRepository ;


    //    @Scheduled(fixedRate = 60000)
    @Override

//    public List<Chambre> retrieveAllChambres() {
//        return chambreRepository.findAll();
//    }
   // @Scheduled(fixedDelay = 10000) // 10000 millisecondes
    public List<Chambre> retrieveAllChambres() {
        List<Chambre> listC = chambreRepository.findAll();
        for (Chambre c: listC) {
            log.info("Chambre :" + c);
        }
        return listC;
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
    @Scheduled(cron = "0 * * * * *") // toutes les minutes
    public void listeChambresParBloc() {
        log.info("***********************");

        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {
            log.info("Bloc => {} ayant une capacité {}", bloc.getNomBloc(), bloc.getCapaciteBloc());

            List<Chambre> chambres = chambreRepository.findByBlocs(bloc);

            if (chambres.isEmpty()) {
                log.info("Pas de chambre disponible dans ce bloc");
            } else {
                log.info("La liste des chambres pour ce bloc:");
                for (Chambre chambre : chambres) {
                    log.info("NumChambre: {} type: {}", chambre.getNumeroChambre(), chambre.getTypeChambre());
                }
            }

            log.info("***********************");
        }
    }

    @Scheduled(cron = "0 */54 * * * *") // toutes les 5 minutes
    public void pourcentageChambreParTypeChambre() {
        List<Chambre> chambres = chambreRepository.findAll();
        int total = chambres.size();

        log.info("Nombre total des chambre: {}", total);

        if (total == 0) {
            log.info("Le pourcentage des chambres pour le type SIMPLE est égal à 0.0");
            log.info("Le pourcentage des chambres pour le type DOUBLE est égal à 0.0");
            log.info("Le pourcentage des chambres pour le type TRIPLE est égal à 0.0");
            return;
        }

        long simple = chambres.stream().filter(c -> c.getTypeChambre().equals(TypeChambre.SIMPLE)).count();
        long doubleC = chambres.stream().filter(c -> c.getTypeChambre().equals(TypeChambre.DOUBLE)).count();
        long triple = chambres.stream().filter(c -> c.getTypeChambre().equals(TypeChambre.TRIPLE)).count();

        log.info("Le pourcentage des chambres pour le type SIMPLE est égal à {}", (simple * 100.0 / total));
        log.info("Le pourcentage des chambres pour le type DOUBLE est égal à {}", (doubleC * 100.0 / total));
        log.info("Le pourcentage des chambres pour le type TRIPLE est égal à {}", (triple * 100.0 / total));
    }
    @Scheduled(cron = "0 */17 * * * *")
    public void nbPlaceDisponibleParChambreAnneeEnCours() {
        int anneeEnCours = LocalDate.now().getYear();
        List<Chambre> chambres = chambreRepository.findAllWithReservations();

        for (Chambre chambre : chambres) {
            long nbOccupants = chambre.getReservations().stream()
                    .filter(res -> {
                        if (res.getAnneeuniversitaire() == null) return false;
                        LocalDate anneeReservation = res.getAnneeuniversitaire().toLocalDate();
                        return anneeReservation.getYear() == anneeEnCours && Boolean.TRUE.equals(res.getEstValide());
                    })
                    .count();

            int capacite = switch (chambre.getTypeChambre()) {
                case SIMPLE -> 1;
                case DOUBLE -> 2;
                case TRIPLE -> 3;
            };

            long nbDispo = capacite - nbOccupants;

            if (nbDispo <= 0) {
                System.out.printf("Chambre %s N°%d est complète%n", chambre.getTypeChambre(), chambre.getNumeroChambre());
            } else {
                System.out.printf("Chambre %s N°%d a %d place(s) disponible(s)%n", chambre.getTypeChambre(), chambre.getNumeroChambre(), nbDispo);
            }
        }
    }



}





