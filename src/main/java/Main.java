import controllers.OuController;
import exceptions.GlobalExceptionHandle;
import models.UnidadeOrganizacional;
import repositories.OuRepository;
import services.OuService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static void aguardarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void menu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Criar OU");
        System.out.println("2 - Listar todas as OUs");
        System.out.println("3 - Deletar OU");
        System.out.println("4 - Buscar OU por Alias");
        System.out.println("5 - Atualizar Distinguished Name (DN) da OU");
        System.out.println("9 - Sair");
        System.out.print("\nOpção: ");
    }

    public static void main(String[] args) {
        OuRepository ouRepository = new OuRepository();
        OuService ouService = new OuService(ouRepository);
        OuController ouController = new OuController(ouService);

        int option = 0;

        while (option != 9) {
            try {
                menu();
                option = Integer.parseInt(scanner.nextLine());
                System.out.print("\n");

                switch (option) {
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
                        String aliasToDelete = scanner.nextLine();
                        if (ouController.delete(aliasToDelete)) System.out.println("OU deletada com sucesso!");
                        break;

                    case 4:
                        System.out.print("Nome da Unidade Organizacional para buscar: ");
                        String aliasToSearch = scanner.nextLine();
                        System.out.println(ouController.findByName(aliasToSearch));
                        break;
                    case 5:
                        System.out.print("Alias da Unidade Organizacional para atualizar: ");
                        String aliasUpdate = scanner.nextLine();
                        System.out.print("Novo Distinguished Name (DN): ");
                        String newDistinguishedName = scanner.nextLine();
                        System.out.println(ouController.update(new UnidadeOrganizacional(aliasUpdate, newDistinguishedName)));
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                GlobalExceptionHandle.handle(e);
            }
            aguardarEnter();
        }
        scanner.close();
    }
}