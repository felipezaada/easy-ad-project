package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnidadeOrganizacional {
    private String name;
    private String dn;

    @Override
    public String toString() {
        return "Nome: " + name + " | DN: " + dn;
    }
}