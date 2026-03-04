package services;

import exceptions.DuplicateResourceException;
import exceptions.EmptyRepositoryException;
import exceptions.ResourceNotFoundException;
import models.UnidadeOrganizacional;
import repositories.OuRepository;

import java.util.List;
import java.util.Optional;

public class OuService {
    private final OuRepository ouRepository;

    public OuService(OuRepository ouRepository) {
        this.ouRepository = ouRepository;
    }

    public List<UnidadeOrganizacional> listAll(){
        List<UnidadeOrganizacional> tempList = ouRepository.listAll();
        if(tempList.isEmpty()) throw new EmptyRepositoryException("Repositório vazio.");
        return tempList;
    }

    public List<UnidadeOrganizacional> create(List<UnidadeOrganizacional> unidadeOrganizacional){
        List<String> DuplicatedOU = unidadeOrganizacional.stream()
                .map(UnidadeOrganizacional::getNome)
                .filter(nome -> ouRepository.findByName(nome).isPresent())
                .toList();

        if (!DuplicatedOU.isEmpty()) throw new DuplicateResourceException("OUs duplicadas: " + DuplicatedOU);
        return ouRepository.create(unidadeOrganizacional);
    }

    public Optional<UnidadeOrganizacional> update(UnidadeOrganizacional unidadeOrganizacional){
        Optional<UnidadeOrganizacional> optionalOU = ouRepository.update(unidadeOrganizacional);
        if(optionalOU.isEmpty()) throw new ResourceNotFoundException("OU com nome: " + unidadeOrganizacional.getNome() + " não encontrada!");
        return optionalOU;
    }

    public boolean delete(String name){
        boolean deleted = ouRepository.delete(name);
        if(!deleted) throw new ResourceNotFoundException();
        return true;
    }

    public Optional<UnidadeOrganizacional> findByName(String name){
        Optional<UnidadeOrganizacional> optionalOU = ouRepository.findByName(name);
        if(optionalOU.isEmpty()) throw new ResourceNotFoundException("OU com nome: " + name + " não encontrada!");
        return optionalOU;
    }
}
