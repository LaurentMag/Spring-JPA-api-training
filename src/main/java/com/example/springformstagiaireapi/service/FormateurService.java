package com.example.springformstagiaireapi.service;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Formateur;
import com.example.springformstagiaireapi.entity.Stagiaire;
import com.example.springformstagiaireapi.repository.AdressRepository;
import com.example.springformstagiaireapi.repository.FormateurRepository;
import com.example.springformstagiaireapi.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private AdressRepository adressRepository;

    // ===============================================================================================
    // GET

    /**
     *  requete tout les formateur existant dans la base de donnée
     * @return List de formateur
     */
    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }

    /**
     * Retourne un formateur recherché par son ID
     * @param id ID du formateur issue de la requete HTTP
     * @return Formateur de la DB dont l'id correspond
     */
    public Formateur findById(Long id) {
        Optional<Formateur> optionalFormateur = formateurRepository.findById(id);

        if (optionalFormateur.isPresent()) {
            return optionalFormateur.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // ===============================================================================================
    // POST

    /**
     * Crée un formateur issue du body de la requete HTTP.
     * Ajout d'une vérification si ajout d'une adress avec le formateur pour la créer si non existante
     * @param formateur issue du body de la request POST
     * @return formateur crée
     */
    public Formateur create(Formateur formateur) {
        Adress adressIncoming = formateur.getAdress();
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
        return formateurRepository.save(formateur);
    }

    /**
     * update formateur en function de l'object formateur envoyé dans la requete HTTP
     * Utilisation de POST pour remplacement
     * @param formateur
     * @return updated stagiaire
     */
    public Formateur update(Formateur formateur) {
        if (!formateurRepository.existsById(formateur.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Formateur non existant");
        }
        return formateurRepository.save(formateur);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un formateur de la DB en le selectionnant par l'id
     * @param id ID du formateur a supprimer issue de la requete HTTP
     * @return retourne le formateur supprimé
     */
    public Formateur delete(Long id) {

        if(!formateurRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun formateur d'id " + id);
        }

        Formateur formateurDeleted = this.findById(id);
        formateurRepository.deleteById(id);

        if (formateurRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soucis de suppression formateur d'id " + id);
        }
        return formateurDeleted;
    }

}
