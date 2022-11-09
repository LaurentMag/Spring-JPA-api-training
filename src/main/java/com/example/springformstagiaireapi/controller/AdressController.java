package com.example.springformstagiaireapi.controller;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Formateur;
import com.example.springformstagiaireapi.service.AdressService;
import com.example.springformstagiaireapi.service.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/adresse")
public class AdressController {

    @Autowired
    private AdressService adressService;

    // ===============================================================================================
    // GET

    /**
     * récupère tout les adress dans la DB
     * @return Liste de adress
     */
    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    private List<Adress> findAll() {
        return adressService.findAll();
    }

    /**
     *
     * @param id Id de la requete HTTP pour trouver un adress particulier
     * @return le stagiaire ayant l'id recherché
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Adress findById(@PathVariable Long id) {
        return adressService.findById(id);
    }

    // ===============================================================================================
    // POST - CREATE

    /**
     *  crée un adress
     * @param adress envoyé dans le body de la request HTTP
     * @return le adress crée
     */
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    // request body dit que dans la requète il doit trouver un object Guitare le prendre et le met dans la function
    public Adress create(@RequestBody Adress adress) {
        return adressService.create(adress);
    }

    /**
     * Update un adress
     * @param adress adress issue du body de la requete HTTP
     * @param id ID du adress a mettre à jour
     * @return retourne le adress updaté
     */
    @PostMapping("{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Adress update(@RequestBody Adress adress, @PathVariable Long id) {
        if (!id.equals(adress.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "stagiaie existe pas");
        }
        return adressService.update(adress);
    }

    // ===============================================================================================
    // DELETE

    /**
     * supprime un adress de la DB
     * @param id l'id du adress a updaté
     * @return retourne le adress supprimé
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.GONE)
    public Adress delete(@PathVariable Long id) {

        return adressService.delete(id);
    }


}
