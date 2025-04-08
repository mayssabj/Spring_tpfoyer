package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.Date;
import java.util.List;
@Tag(name="Gestion Etudiants")
@RestController
@RequestMapping("/etudiant")
public class EtudiantRestController {
    @Autowired
    IEtudiantService etudiantService;

    @Operation(description = "récupérer toutes les etudiants de la base de données")
    // http://localhost:8089/tpfoyer/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listChambres = etudiantService.retrieveAllEtudiants();
        return listChambres;
    }

    // http://localhost:8089/tpfoyer/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long eId) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(eId);
        return etudiant;

    }
    // http://localhost:8089/tpfoyer/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }
    // http://localhost:8089/tpfoyer/etudiant/remove-etudiant/{etudiant-id}
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long eId) {
        etudiantService.removeEtudiant(eId);
    }

    // http://localhost:8089/tpfoyer/etudiant/modify-etudiant
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.modifyEtudiant(e);
        return etudiant;
    }
    @GetMapping("trouveretudiantbyecole/{ecole}/{date}")
    public List<Etudiant> trouveretudiantbyecoleAndDateNaissance(@PathVariable("ecole") String ecole,@PathVariable("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Etudiant> etudiants = etudiantService.searchEtudiantbyEcoleAndDateNaissance(ecole,date);
        return etudiants;
    }
    @GetMapping("trouverChselonEt/{cin}")
    public Chambre trouverChselonEt(@PathVariable("cin") Long cin) {
        Chambre ch = etudiantService.trouverChselonEt(cin);
        return ch;
    }

}
