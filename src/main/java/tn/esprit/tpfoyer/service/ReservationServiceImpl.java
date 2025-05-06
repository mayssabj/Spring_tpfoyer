package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService{
    @Autowired
    ReservationRepository reservationRepository;
    static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);
    //@Scheduled(fixedRate = 50000) // toutes les 50 secondes
    @Override
    public void mettreAJourEtAfficherReservations() {
        // Date de référence
        LocalDate dateLimite = LocalDate.of(2024, 1, 1);

        // Mettre à jour les réservations faites avant le 01/01/2024
        List<Reservation> anciennesReservations = reservationRepository.findByAnneeuniversitaire(dateLimite);
        for (Reservation reservation : anciennesReservations) {
            reservation.setEstValide(false);
        }
        reservationRepository.saveAll(anciennesReservations);

        // Afficher toutes les réservations
        List<Reservation> toutesLesReservations = reservationRepository.findAll();
        toutesLesReservations.forEach(System.out::println);
    }


    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(String reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void removeReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
