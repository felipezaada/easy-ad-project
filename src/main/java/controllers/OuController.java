package controllers;

import models.UnidadeOrganizacional;
import services.OuService;

import java.util.List;
import java.util.Optional;

public class OuController {
    private final OuService ouService;

    public OuController(OuService ouService) {
        this.ouService = ouService;
    }

    public List<UnidadeOrganizacional> create(List<UnidadeOrganizacional> unidadeOrganizacional){
       return ouService.create(unidadeOrganizacional);
    }

    public List<UnidadeOrganizacional> listAll() {
        return ouService.listAll();
    }

    public UnidadeOrganizacional update(UnidadeOrganizacional unidadeOrganizacional){
       return ouService.update(unidadeOrganizacional);
    }

    public boolean delete(String name){
        return ouService.delete(name);
    }

    public UnidadeOrganizacional findByName(String name){
       return ouService.findByName(name);
    }
}
