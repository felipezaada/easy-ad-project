package repositories;

import models.UnidadeOrganizacional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OuRepository {
    List<UnidadeOrganizacional> listaOU = new ArrayList<>();

    public List<UnidadeOrganizacional> create(List<UnidadeOrganizacional> unidadeOrganizacional){
        listaOU.addAll(unidadeOrganizacional);
        return listAll();
    }

    public List<UnidadeOrganizacional> listAll(){
        listaOU.sort(Comparator.comparing(UnidadeOrganizacional::getName));
        return listaOU;
    }

    public Optional<UnidadeOrganizacional> update(UnidadeOrganizacional unidadeOrganizacional) {
        return listaOU.stream()
                .filter(ou -> ou.getName().equals(unidadeOrganizacional.getName()))
                .findFirst()
                .map(ou -> {
                    ou.setDn(unidadeOrganizacional.getDn());
                    return ou;
                });
    }

    public boolean delete(String name){
        return listaOU.removeIf(ou -> ou.getName().equals(name));
    }

    public Optional<UnidadeOrganizacional> findByName(String name){
        return listaOU.stream()
                .filter(ou -> ou.getName().equals(name))
                .findFirst();
    }

    public List<UnidadeOrganizacional> findByNameIn(List<String> names) {
        return listaOU.stream()
                .filter(ou -> names.contains(ou.getName()))
                .toList();
    }
}

