package contes.atosspringbootlocadora;

import java.util.Scanner;

public final class Message {

    public static String clear = "\033[H\033[2J";

    public static String menuPrincipal(Scanner scanner) {
        System.out.println("SISTEMA DE ALGUEL DE CARROS\n");
        System.out.println("S - SAIR");
        System.out.println("00 - BUSCAR UM CONTRATO DE LOCACAO");
        System.out.println("01 - INICIAR NOVO CONTRATO DE LOCACAO");
        System.out.println("02 - BUSCAR UM AUTOMOVEL");
        System.out.println("03 - CADASTRAR AUTOMOVEL");
        System.out.println("04 - DESCADASTRAR AUTOMOVEL");
        System.out.println("05 - BUSCAR UM CLIENTE");
        System.out.println("06 - CADASTRAR CLIENTE");
        System.out.println("07 - DESCADASTRAR CLIENTE");
        System.out.println("08 - LISTAR TODOS OS CONTRATOS ATIVOS");
        System.out.println("09 - LISTAR TODOS OS AUTOMOVEIS");
        System.out.println("10 - LISTAR TODOS OS CLIENTES ATIVOS");
        System.out.print("\nEscolha uma opcao: ");
        String opcao = scanner.nextLine();
        printEnd();

        return opcao;
    }

    public static void printEnd() {
        System.out.print(clear);
    }

    public static void printEnd(String msg, Scanner scanner) {
        System.out.print(msg);
        scanner.nextLine();
        System.out.print(clear);
    }

}
