package controllers;

import exceptions.DuplicateResourceException;
import exceptions.EmptyRepositoryException;
import exceptions.ResourceNotFoundException;
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
        try {
            return ouService.create(unidadeOrganizacional);
        } catch(DuplicateResourceException e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    public List<UnidadeOrganizacional> listAll() {
        try {
            return ouService.listAll();
        } catch (EmptyRepositoryException e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    public Optional<UnidadeOrganizacional> update(UnidadeOrganizacional unidadeOrganizacional){
        try{
            return ouService.update(unidadeOrganizacional);
        }catch(ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public boolean delete(String nome){
        try{
            return ouService.delete(nome);
        }catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<UnidadeOrganizacional> findByName(String nome){
        try{
            return ouService.findByName(nome);
        }catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
