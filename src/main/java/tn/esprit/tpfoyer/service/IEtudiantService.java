package tn.esprit.tpfoyer.service;


import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.Date;
import java.util.List;

public interface IEtudiantService {
    public List<Etudiant> retrieveAllEtudiants();
    public Etudiant retrieveEtudiant(Long EtudiantId);
    public Etudiant addEtudiant(Etudiant e);
    public void removeEtudiant(Long etudiantId);
    public Etudiant modifyEtudiant(Etudiant etudiant);
    public List<Etudiant> searchEtudiantbyEcoleAndDateNaissance(String ecole, Date datenaissance);
    public Chambre trouverChselonEt(Long cin);

}
