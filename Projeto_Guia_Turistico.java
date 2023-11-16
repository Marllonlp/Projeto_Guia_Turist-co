import java.io.*;
import java.util.Scanner;
public class Projeto_Guia_Turistico {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        menu();
        opcao = ler.nextInt();
        switch (opcao) {
            case 1:
                menuUsuario();
                break;
            case 2:
                validarAdmin();
                break;
            case 3:
                System.out.println("Encerrando...");
                break;
            default:
                // Caso padrão: Opção inválida
                System.out.println(" Opção Inválida! ");
        }
    }

    private static boolean cadastrarUsuario(int id, String path) {
        Scanner sc = new Scanner(System.in);
        Clientes cl = new Clientes();

        System.out.println("CADASTRAR: ");
        System.out.println("CPF: ");
        cl.email = sc.nextLine();
        System.out.println("SENHA: ");
        cl.senha = sc.nextLine();
        cl.id = id;
        try {
            gravarCliente(cl, path);
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível cadastrar: ");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Método para gravar as informações do cliente em um arquivo
    private static void gravarCliente(Clientes cl, String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + cl.id + ".txt");
        pw.println(cl.id);
        pw.println(cl.email);
        pw.println(cl.senha);
        pw.flush();
        pw.close();
    }

    // Método para gravar o id atual em um arquivo
    private static void gravarId(int id) {
        PrintWriter pw;
        try {
            pw = new PrintWriter("id.txt");
            pw.println(id);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para inicializar o programa e ler o id inicial
    private static int inicializar(String path) {
        int id = 0;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File arquivo = new File("id.txt");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(0);
        } else {
            id = lerId();
        }
        return id;
    }

    // Método para ler o id de um arquivo
    private static int lerId() {
        BufferedReader bf;
        int id = 0;
        try {
            bf = new BufferedReader(new FileReader("id.txt"));
            id = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Id não encontrado");
            e.printStackTrace();
        }
        return id;
    }

    // Método para verificar reservas
    private static void verificarReserva() {
        System.out.println("Sucesso");
    }

    // Método para cancelar reservas
    private static void cancelarReserva() {
        System.out.println("Sucesso");
    }

    // Método para exibir pacotes disponíveis
    private static void pacotesDisponivel() {
        System.out.println("Sucesso");
    }

    // Método para lidar com reservas de cavernas
    private static boolean reservaCaverna(int id) {
        Scanner sc = new Scanner(System.in);
        Clientes c = new Clientes();
        System.out.println("|-------------------|");
        System.out.println(" Reservando Passeio ");
        System.out.println("|-------------------|\n");
        // Inserir data e hora
        System.out.print("Adultos: ");
        c.adultos = sc.nextInt();
        System.out.print("Crianças: ");
        c.criancas = sc.nextInt();
        c.id = id;

        return false;
    }

    private static void menu() {
        System.out.println("\n|----------------------|");
        System.out.println("  Cavernas do Peruaçu  ");
        System.out.println("|----------------------|");
        System.out.println(" Entar como: ");
        System.out.println(" 1 - Usuario");
        System.out.println(" 2 - Administrador");
        System.out.println(" 3 - Encerrar");
        System.out.print("Opção:");
    }

    private static void menuUsuario() {
        String path = "clientes/";
        int id = 0;
        id = inicializar(path);
        Scanner ler = new Scanner(System.in);
        Clientes cl = new Clientes();
        int opcao = 0;

        while (opcao != 5) {
            // Exibindo o menu
            System.out.println("\n|----------------------|");
            System.out.println("  Cavernas do Peruaçu  ");
            System.out.println("|----------------------|\n");
            System.out.println(" 1 - Cadastrar Usuário ");
            System.out.println(" 2 - Fazer Reserva ");
            System.out.println(" 3 - Verificar Reserva ");
            System.out.println(" 4 - Cancelar Reserva ");
            System.out.println(" 5 - Sair ");
            System.out.print("\n Opção: ");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    // Opção 1: Cadastrar um novo usuário
                    if (cadastrarUsuario(id, path)) {
                        id++;
                        gravarId(id);
                    }
                    break;
                case 2:
                    // Opção 2: Reservar uma caverna
                    id = 0;
                    reservaCaverna(id);
                    break;
                case 3:
                    // Opção 3: Verificar reserva
                    verificarReserva();
                    break;
                case 4:
                    // Opção 4: Cancelar reserva
                    cancelarReserva();
                    break;
                case 5:
                    // Opção 6: Sair do programa
                    System.out.println("Saindo...");
                    break;
                default:
                    // Caso padrão: Opção inválida
                    System.out.println(" Opção Inválida! ");
            }
        }
    }

    private static void validarAdmin() {
        Scanner ler = new Scanner(System.in);
        Administrador senhaAdm = new Administrador();
        System.out.println("Infome a senha do Administrado");
        int senha = ler.nextInt();

        if (senha == senhaAdm.senhaAdmim) {
            menuAdmin();
        } else {
            System.out.println("Senha incorreta");
        }
    }

    private static void menuAdmin() {
        String path = "clientes/";
        int id = 0;
        id = inicializar(path);
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 4) {
            // Exibindo o menu
            System.out.println("\n|----------------------|");
            System.out.println("  Cavernas do Peruaçu  ");
            System.out.println("|----------------------|\n");
            System.out.println(" 1 - Fazer Reserva para Usuario ");
            System.out.println(" 2 - Verificar Reserva ");
            System.out.println(" 3 - Confirmar Reserva ");
            System.out.println(" 4 - Sair ");
            System.out.print("\n Opção: ");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    id = 0;
                    reservaCaverna(id);
                    break;
                case 2:
                    verificarReserva();
                    break;
                case 3:
                    confimarReserva();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    // Caso padrão: Opção inválida
                    System.out.println(" Opção Inválida! ");
                    break;
            }
        }
    }
    private static void confimarReserva() {

    }
}
