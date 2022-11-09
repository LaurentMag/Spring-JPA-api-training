package com.example.springformstagiaireapi.service;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Formateur;
import com.example.springformstagiaireapi.repository.AdressRepository;
import com.example.springformstagiaireapi.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    // ===============================================================================================
    // GET

    /**
     *  requete tout les adress existant dans la base de donnée
     * @return List de adress
     */
    public List<Adress> findAll() {
        return adressRepository.findAll();
    }

    /**
     * Retourne un adress recherché par son ID
     * @param id ID du adress issue de la requete HTTP
     * @return Adress de la DB dont l'id correspond
     */
    public Adress findById(Long id) {
        Optional<Adress> optionalAdress = adressRepository.findById(id);

        if (optionalAdress.isPresent()) {
            return optionalAdress.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // ===============================================================================================
    // POST

    /**
     * Crée un adress utilisant
     * @param adress issue du body de la request POST
     * @return adress crée
     */
    public Adress create(Adress adress) {
        return adressRepository.save(adress);
    }

    /**
     * update adress en function de l'object adress envoyé dans la requete HTTP
     * Utilisation de POST pour remplacement
     * @param adress
     * @return updated adress
     */
    public Adress update(Adress adress) {
        if (!adressRepository.existsById(adress.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Adress non existant");
        }
        return adressRepository.save(adress);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un adress de la DB en le selectionnant par l'id
     * @param id ID du adress a supprimer issue de la requete HTTP
     * @return retourne le adress supprimé
     */
    public Adress delete(Long id) {

        if(!adressRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun adress d'id " + id);
        }

        Adress adressDeleted = this.findById(id);
        adressRepository.deleteById(id);

        if (adressRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soucis de suppression adress d'id " + id);
        }
        return adressDeleted;
    }

}
