package com.example.springformstagiaireapi.service;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Stagiaire;
import com.example.springformstagiaireapi.repository.AdressRepository;
import com.example.springformstagiaireapi.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private AdressRepository adressRepository;

    // ===============================================================================================
    // GET

    /**
     *  requete tout les stagiaire existant dans la base de donnée
     * @return List de stagiaire
     */
    public List<Stagiaire> findAll() {
        return stagiaireRepository.findAll();
    }

    /**
     * Retourne un stagiaire recherché par son ID
     * @param id ID du stagiaire issue de la requete HTTP
     * @return Stagiaire de la DB dont l'id correspond
     */
    public Stagiaire findById(Long id) {
        Optional<Stagiaire> optionalStagiaire = stagiaireRepository.findById(id);

        if (optionalStagiaire.isPresent()) {
            return optionalStagiaire.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // ===============================================================================================
    // POST

    /**
     * Crée un stagiaire issue du body de la requete HTTP.
     * Ajout d'une stagiaire si ajout d'une adress avec le stagiaire pour la créer si non existante
     * @param stagiaire issue du body de la request POST
     * @return stagiaire crée
     */
    public Stagiaire create(Stagiaire stagiaire) {
        Adress adressIncoming = stagiaire.getAdress();
        if (adressIncoming != null) {
            // is id est null pas d'id dans postman donc surement pas un object existant
            if (adressIncoming.getId() == null) {
                this.adressRepository.save(adressIncoming);
            } else {
                if (!this.adressRepository.existsById(adressIncoming.getId())) {
                    this.adressRepository.save(adressIncoming);
                }
            }
        }
        return stagiaireRepository.save(stagiaire);
    }

    /**
     * update stagiare en function de l'object stagiaire envoyé dans la requete HTTP
     * Utilisation de POST pour remplacement
     * @param stagiaire
     * @return updated stagiaire
     */
    public Stagiaire update(Stagiaire stagiaire) {
        if (!stagiaireRepository.existsById(stagiaire.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Stagiaire non existant");
        }
        return stagiaireRepository.save(stagiaire);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un stagiaire de la DB en le selectionnant par l'id
     * @param id ID du stagiaire a supprimer issue de la requete HTTP
     * @return retourne le stagiaire supprimé
     */
    public Stagiaire delete(Long id) {

        if(!stagiaireRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun stagiare d'id " + id);
        }

        Stagiaire stagiaireDeleted = this.findById(id);
        stagiaireRepository.deleteById(id);

        if (stagiaireRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soucis de suppression stagiair d'id " + id);
        }
        return stagiaireDeleted;
    }

}
