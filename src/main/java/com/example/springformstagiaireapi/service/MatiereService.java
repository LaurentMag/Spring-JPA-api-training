package com.example.springformstagiaireapi.service;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Formateur;
import com.example.springformstagiaireapi.entity.Matiere;
import com.example.springformstagiaireapi.repository.AdressRepository;
import com.example.springformstagiaireapi.repository.FormateurRepository;
import com.example.springformstagiaireapi.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    // ===============================================================================================
    // GET

    /**
     *  requete tout les adress existant dans la base de donnée
     * @return List de adress
     */
    public List<Matiere> findAll() {
        return matiereRepository.findAll();
    }

    /**
     * Retourne un adress recherché par son ID
     * @param id ID du adress issue de la requete HTTP
     * @return Matiere de la DB dont l'id correspond
     */
    public Matiere findById(Long id) {
        Optional<Matiere> optionalMatiere = matiereRepository.findById(id);

        if (optionalMatiere.isPresent()) {
            return optionalMatiere.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // ===============================================================================================
    // POST

    /**
     * creation d'une matière
     * @param matiere issue du body de la request POST
     * @return matiere crée
     */
    public Matiere create(Matiere matiere) {
        return matiereRepository.save(matiere);
    }


    /**
     * Crée une matiere issue du body de la request HTTP.
     * Récupère une liste d'id qui vont être utilisé comme ID de formateur pour générer une liste de formateur
     * qui vont enseigner la matiere.
     * @param matiere issue du body de la request POST
     * @return matiere crée
     */
    public Matiere createWithList(Matiere matiere, List<String> formateurID) {
        List<Long> IDtoLong = formateurID.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());


        List<Formateur> formateursToMatiere = new ArrayList<>();
        for (Long idFormateur: IDtoLong) {
            if (this.formateurRepository.existsById(idFormateur)) {
                formateursToMatiere.add(this.formateurRepository.findById(idFormateur).get());
            }
        }
        matiere.setListFormateurs(formateursToMatiere);
        return matiereRepository.save(matiere);
    }

    /**
     * update matiere en function de l'object matiere envoyé dans la requete HTTP
     * Utilisation de POST pour remplacement
     * @param matiere
     * @return updated matiere
     */
    public Matiere update(Matiere matiere) {
        if (!matiereRepository.existsById(matiere.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Matiere non existant");
        }
        return matiereRepository.save(matiere);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un matiere de la DB en le selectionnant par l'id
     * @param id ID du matiere a supprimer issue de la requete HTTP
     * @return retourne le matiere supprimé
     */
    public Matiere delete(Long id) {

        if(!matiereRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun matiere d'id " + id);
        }

        Matiere matiereDeleted = this.findById(id);
        matiereRepository.deleteById(id);

        if (matiereRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soucis de suppression matiere d'id " + id);
        }
        return matiereDeleted;
    }

}
