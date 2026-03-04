import controllers.OuController;
import models.UnidadeOrganizacional;
import repositories.OuRepository;
import services.OuService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        OuRepository ouRepository = new OuRepository();
        OuService ouService = new OuService(ouRepository);
        OuController ouController = new OuController(ouService);

        Scanner scanner = new Scanner(System.in);
        int entrada = 0;

        while (entrada != 9) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Criar OU");
            System.out.println("2 - Listar todas as OUs");
            System.out.println("3 - Deletar OU");
            System.out.println("4 - Buscar OU por Alias");
            System.out.println("5 - Atualizar Distinguished Name (DN) da OU");
            System.out.println("9 - Sair");
            System.out.print("Opção: ");

            entrada = scanner.nextInt();
            scanner.nextLine();

            switch (entrada) {
                case 1:
                    List<UnidadeOrganizacional> tempList = List.of(
                            new UnidadeOrganizacional("Vendas", "OU=Vendas,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("TI", "OU=TI,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Marketing", "OU=Marketing,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Recursos Humanos", "OU=RH,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Financeiro", "OU=Financeiro,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Logística", "OU=Logistica,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Compras", "OU=Compras,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Jurídico", "OU=Juridico,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Atendimento", "OU=Atendimento,DC=empresa,DC=com"),
                            new UnidadeOrganizacional("Pesquisa e Desenvolvimento", "OU=P&D,DC=empresa,DC=com")
                    );
                    ouController.create(tempList).forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Listando todas as Unidades Organizacionais: ");
                    ouController.listAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Nome da Unidade Organizacional para deletar: ");
                    String aliasDelete = scanner.nextLine();
                    boolean deleted = ouController.delete(aliasDelete);
                    System.out.println("Deletada? " + deleted);
                    break;

                case 4:
                    System.out.print("Nome da Unidade Organizacional para buscar: ");
                    String aliasProcurado = scanner.nextLine();
                    Optional<UnidadeOrganizacional> encontrado = ouController.findByName(aliasProcurado);
                    System.out.println(encontrado);
                    break;
                case 5:
                    System.out.print("Alias da Unidade Organizacional para atualizar: ");
                    String aliasUpdate = scanner.nextLine();
                    System.out.print("Novo Distinguished Name (DN): ");
                    String novoEndereco = scanner.nextLine();
                    UnidadeOrganizacional updatedUo = new UnidadeOrganizacional(aliasUpdate, novoEndereco);
                    Optional<UnidadeOrganizacional> updated = ouController.update(updatedUo);
                    System.out.println(updated);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}