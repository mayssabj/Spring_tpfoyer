package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;


import java.util.List;
@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements IBlocService{
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    FoyerRepository foyerRepository;
    static final Logger log = LoggerFactory.getLogger(BlocServiceImpl.class);

    @Override
//    public List<Bloc> retrieveAllblocs() {
//        return blocRepository.findAll();
//    }
    //@Scheduled(cron = "* 1 * * * *")
    public List<Bloc> retrieveAllblocs() {
        List<Bloc> listC = blocRepository.findAll();
        for (Bloc c: listC) {
            log.info("Chambre :" + c);
        }
        return listC;
    }

    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId).get();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }
    @Override
    public void assignBlocToFoyer(Long blocId, Long foyerId) {
        Bloc bloc = blocRepository.findById(blocId).get();
        Foyer foyer = foyerRepository.findById(foyerId).get();
// on set le fils dans le parent :
        foyer.getBlocs().add(bloc);
        foyerRepository.save(foyer);
    }

}
