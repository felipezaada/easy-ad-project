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
        listaOU.sort(Comparator.comparing(UnidadeOrganizacional::getNome));
        return listaOU;
    }

    public Optional<UnidadeOrganizacional> update(UnidadeOrganizacional unidadeOrganizacional) {
        return listaOU.stream()
                .filter(ou -> ou.getNome().equals(unidadeOrganizacional.getNome()))
                .findFirst()
                .map(ou -> {
                    ou.setDn(unidadeOrganizacional.getDn());
                    return ou;
                });
    }

    public boolean delete(String nome){
        return listaOU.removeIf(ou -> ou.getNome().equals(nome));
    }

    public Optional<UnidadeOrganizacional> findByName(String nome){
        return listaOU.stream()
                .filter(ou -> ou.getNome().equals(nome))
                .findFirst();
    }

}

