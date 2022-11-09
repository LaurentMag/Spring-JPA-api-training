package com.example.springformstagiaireapi.controller;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Matiere;
import com.example.springformstagiaireapi.service.AdressService;
import com.example.springformstagiaireapi.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/matiere")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    // ===============================================================================================
    // GET

    /**
     * récupère tout les matiere dans la DB
     *
     * @return Liste de matiere
     */
    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    private List<Matiere> findAll() {
        return matiereService.findAll();
    }

    /**
     * @param id Id de la requete HTTP pour trouver un matiere particulier
     * @return le stagiaire ayant l'id recherché
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Matiere findById(@PathVariable Long id) {
        return matiereService.findById(id);
    }

    // ===============================================================================================
    // POST - CREATE

    /**
     * Creation d'une matière unique
     * @param matiere envoyé dans le body de la request HTTP
     * @return le matiere crée
     */
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Matiere create(@RequestBody Matiere matiere) {
        return matiereService.create(matiere);
    }

    /**
     * crée une matiere, en ajoutant une liste d'ID qui vont correspondre aux ID de formateur à ajouter dans la liste
     * {id_formateurs} sera une succession d'ID dans l'url
     * ex : /addFormateurList/5,6,7,8,34
     * @param matiere envoyé dans le body de la request HTTP
     * @return le matiere crée
     */
    @PostMapping("/addFormateurList/{id_formateurs}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Matiere createWithList(@RequestBody Matiere matiere, @PathVariable String id_formateurs) {
        List<String> listIdFormateur = new ArrayList<>(Arrays.asList(id_formateurs.split(",")));
        return matiereService.createWithList(matiere, listIdFormateur);
    }

    /**
     * Update un matiere
     *
     * @param matiere matiere issue du body de la requete HTTP
     * @param id      ID du matiere a mettre à jour
     * @return retourne le matiere updaté
     */
    @PostMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Matiere update(@RequestBody Matiere matiere, @PathVariable Long id) {
        if (!id.equals(matiere.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "stagiaie existe pas");
        }
        return matiereService.update(matiere);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un matiere de la DB
     *
     * @param id l'id du matiere a updaté
     * @return retourne le matiere supprimé
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.GONE)
    public Matiere delete(@PathVariable Long id) {

        return matiereService.delete(id);
    }


}
