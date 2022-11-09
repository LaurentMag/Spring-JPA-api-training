package com.example.springformstagiaireapi.controller;

import com.example.springformstagiaireapi.entity.Stagiaire;
import com.example.springformstagiaireapi.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stagiaire")
public class StagiaireController {

    @Autowired
    private StagiaireService stagiaireService;

    // ===============================================================================================
    // GET

    /**
     * récupère tout les stagiaires dans la DB
     * @return Liste de stagiaires
     */
    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    private List<Stagiaire> findAll() {
        return stagiaireService.findAll();
    }

    /**
     *
     * @param id Id de la requete HTTP pour trouver un stagiaire particulier
     * @return le stagiaire ayant l'id recherché
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Stagiaire findById(@PathVariable Long id) {
        return stagiaireService.findById(id);
    }

    // ===============================================================================================
    // POST - CREATE

    /**
     *  crée un stagiaire
     * @param stagiaire envoyé dans le body de la request HTTP
     * @return le stagiare crée
     */
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    // request body dit que dans la requète il doit trouver un object Guitare le prendre et le met dans la function
    public Stagiaire create(@RequestBody Stagiaire stagiaire) {
        return stagiaireService.create(stagiaire);
    }

    /**
     * Update un stagiaire
     * @param stagiaire stagiaire issue du body de la requete HTTP
     * @param id ID du stagiaire a mettre à jour
     * @return retourne le stagiaire updaté
     */
    @PostMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Stagiaire update(@RequestBody Stagiaire stagiaire, @PathVariable Long id) {
        if (!id.equals(stagiaire.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "stagiaie existe pas");
        }
        return stagiaireService.update(stagiaire);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un stagiaire de la DB
     * @param id l'id du stagiare a updaté
     * @return retourne le stagiaire supprimé
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.GONE)
    public Stagiaire delete(@PathVariable Long id) {

        return stagiaireService.delete(id);
    }


}
