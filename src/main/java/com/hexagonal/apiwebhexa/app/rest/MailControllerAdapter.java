package com.hexagonal.apiwebhexa.app.rest;

import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.exception.EnvoiMailException;
import com.hexagonal.apiwebhexa.domain.exception.ReglesMetierException;
import com.hexagonal.apiwebhexa.domain.exception.ValidatorMetierException;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionHabitants;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mails")
public class MailControllerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailControllerAdapter.class);
    private static final String MAUVAISE_DONNEES_DANS_LE_CONTROLLER = "Mauvaise données dans le controller : '{}'";
    private static final String ERREUR_DANS_LES_TRAITEMENTS = "Erreur dans les traitements : '{}'";

    private GestionHabitants getHabitant;
    private GestionMail preparerEnvoiMail;

    public MailControllerAdapter(GestionHabitants getHabitant, GestionMail preparerEnvoiMail) {
        this.getHabitant = getHabitant;
        this.preparerEnvoiMail = preparerEnvoiMail;
    }

    @GetMapping("/categorieSocioProfessionnelle/{codeCategorieSocioProfessionnelle}/{objetMail}")
    public ResponseEntity<Void> envoiMailParCategorieSocioProfessionnelle(@PathVariable("codeCategorieSocioProfessionnelle") int codeCategorieSocioProfessionnelle,
                                                                          @PathVariable("objetMail") String objetMail) {

        try {
            List<Personne> habitants = getHabitant.getAllHabitantsParCategorieSocioProfessionnelle(codeCategorieSocioProfessionnelle);
            preparerEnvoiMail.envoyerMail(habitants, objetMail);
        } catch (ValidatorMetierException e) {
            LOGGER.warn(MAUVAISE_DONNEES_DANS_LE_CONTROLLER, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ReglesMetierException | EnvoiMailException e) {
            LOGGER.warn(ERREUR_DANS_LES_TRAITEMENTS, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/trancheDage/{ageMinimum}/{ageMaximum}/{objetMail}")
    public ResponseEntity<Void> envoiMailParTrancheDage(@PathVariable("ageMinimum") int ageMinimum, @PathVariable("ageMaximum") int ageMaximum,
                                        @PathVariable("objetMail") String objetMail) {

        try {
            List<Personne> habitants = getHabitant.getAllHabitantsParTrancheAge(ageMinimum, ageMaximum);
            preparerEnvoiMail.envoyerMail(habitants, objetMail);
        } catch (ValidatorMetierException e) {
            LOGGER.warn(MAUVAISE_DONNEES_DANS_LE_CONTROLLER, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ReglesMetierException | EnvoiMailException e) {
            LOGGER.warn(ERREUR_DANS_LES_TRAITEMENTS, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/habitants/{objetMail}")
    public ResponseEntity<Void> envoiMailPourTousLesHabitants(@PathVariable("objetMail") String objetMail) {
        try {
            List<Personne> habitants = getHabitant.getAllHabitants();
            preparerEnvoiMail.envoyerMail(habitants, objetMail);
        } catch (ValidatorMetierException e) {
            LOGGER.warn(MAUVAISE_DONNEES_DANS_LE_CONTROLLER, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ReglesMetierException | EnvoiMailException e) {
            LOGGER.warn(ERREUR_DANS_LES_TRAITEMENTS, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
