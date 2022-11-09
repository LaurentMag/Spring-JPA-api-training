package com.example.springformstagiaireapi.controller;

import com.example.springformstagiaireapi.entity.Formateur;
import com.example.springformstagiaireapi.entity.Stagiaire;
import com.example.springformstagiaireapi.service.FormateurService;
import com.example.springformstagiaireapi.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/formateur")
public class FormateurController {

    @Autowired
    private FormateurService formateurService;

    // ===============================================================================================
    // GET

    /**
     * récupère tout les formateur dans la DB
     * @return Liste de formateur
     */
    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    private List<Formateur> findAll() {
        return formateurService.findAll();
    }

    /**
     *
     * @param id Id de la requete HTTP pour trouver un formateur particulier
     * @return le stagiaire ayant l'id recherché
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Formateur findById(@PathVariable Long id) {
        return formateurService.findById(id);
    }

    // ===============================================================================================
    // POST - CREATE

    /**
     *  crée un formateur
     * @param formateur envoyé dans le body de la request HTTP
     * @return le formateur crée
     */
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    // request body dit que dans la requète il doit trouver un object Guitare le prendre et le met dans la function
    public Formateur create(@RequestBody Formateur formateur) {
        return formateurService.create(formateur);
    }

    /**
     * Update un formateur
     * @param formateur formateur issue du body de la requete HTTP
     * @param id ID du formateur a mettre à jour
     * @return retourne le formateur updaté
     */
    @PostMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Formateur update(@RequestBody Formateur formateur, @PathVariable Long id) {
        if (!id.equals(formateur.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "stagiaie existe pas");
        }
        return formateurService.update(formateur);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un formateur de la DB
     * @param id l'id du formateur a updaté
     * @return retourne le formateur supprimé
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.GONE)
    public Formateur delete(@PathVariable Long id) {

        return formateurService.delete(id);
    }


}
