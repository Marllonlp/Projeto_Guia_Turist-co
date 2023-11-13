import java.util.Scanner;

public class Projeto_Guia_Turistico {
    public static void main(String[] args) {
        menu();

    }
    private static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n|----------------------|");
            System.out.println("  Cavernas do Peruaçu  ");
            System.out.println("|----------------------|\n");
            System.out.println(" 1 - Cadastrar Usuario ");
            System.out.println(" 2 - Reservar Caverna ");
            System.out.println(" 3 - Verificar Reserva ");
            System.out.println(" 4 - Cancelar Reserva ");
            System.out.println(" 5 - Pacotes Disponiveis ");

            System.out.println(" 6 - Sair ");
            System.out.print("\n Opção: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    reservaCaverna();
                    break;
                case 3:
                    verificarReserva();
                    break;
                case 4:
                    cancelarReserva();
                    break;
                case 5:
                    pacotesDisponivel();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println(" Opção Invalida! ");
            }
        }
    }

//Metodo de Cadastramento do Usuario
    private static void cadastrarUsuario() {
        System.out.println("Sucesso");
    }
    //Metodo de Cadastramento do Usuario
    private static void reservaCaverna() {
        System.out.println("Sucesso");
    }

    private static void verificarReserva() {
        System.out.println("Sucesso");
    }

    private static void cancelarReserva() {
        System.out.println("Sucesso");
    }
    private static void pacotesDisponivel() {
        System.out.println("Sucesso");
    }
}

