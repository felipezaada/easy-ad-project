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
        List<String> ouNames = unidadeOrganizacional.stream().map(UnidadeOrganizacional::getName)
                .toList();

        List<String> duplicateOUs = ouRepository.findByNameIn(ouNames)
                .stream()
                .map(UnidadeOrganizacional::getName)
                .toList();

        List<UnidadeOrganizacional> originalOUs = unidadeOrganizacional.stream()
                .filter(ou -> !duplicateOUs.contains(ou.getName()))
                .toList();

        if (!duplicateOUs.isEmpty() && !originalOUs.isEmpty()) {
            ouRepository.create(originalOUs);
            throw new DuplicateResourceException("OUs inseridas: " + originalOUs + "\nOUs duplicadas: " + duplicateOUs);
        } else if (!duplicateOUs.isEmpty()) throw new DuplicateResourceException("OUs duplicadas: " + duplicateOUs);

        return ouRepository.create(unidadeOrganizacional);
    }

    public UnidadeOrganizacional update(UnidadeOrganizacional unidadeOrganizacional){
        Optional<UnidadeOrganizacional> optionalOU = ouRepository.update(unidadeOrganizacional);
        if(optionalOU.isEmpty()) throw new ResourceNotFoundException("OU com nome \"" + unidadeOrganizacional.getName() + "\" não encontrada!");
        return optionalOU.get();
    }

    public boolean delete(String name){
        boolean deleted = ouRepository.delete(name);
        if(!deleted) throw new ResourceNotFoundException("OU não encontrada!");
        return true;
    }

    public UnidadeOrganizacional findByName(String name){
        Optional<UnidadeOrganizacional> optionalOU = ouRepository.findByName(name);
        if(optionalOU.isEmpty()) throw new ResourceNotFoundException("OU com nome \"" + name + "\" não encontrada!");
        return optionalOU.get();
    }
}
