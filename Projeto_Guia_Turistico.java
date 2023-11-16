import java.io.*;
import java.util.Scanner;


public class Projeto_Guia_Turistico {
    public static void main(String[] args) {

        String path = "clientes/";
        int id = 0;
        id = inicializar(path);
        Scanner sc = new Scanner(System.in);
        Clientes cl = new Clientes();

        int opcao = 0;
        while (opcao != 6) {
            menu();
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if (cadastrarUsuario(id, path)) {
                        id++;
                        gravarId(id);
                    }
                    ;
                    break;
                case 2:
                    id = 0;
                    reservaPasseio(id);
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

    private static void menu() {

        System.out.println("\n|----------------------|");
        System.out.println("  Cavernas do Peruaçu  ");
        System.out.println("|----------------------|\n");
        System.out.println(" 1 - Cadastrar Usuario ");
        System.out.println(" 2 - Fazer Reserva ");
        System.out.println(" 3 - Verificar Reserva ");
        System.out.println(" 4 - Cancelar Reserva ");
        System.out.println(" 5 - Pacotes Disponiveis ");

        System.out.println(" 6 - Sair ");
        System.out.print("\n Opção: ");

    }


    private static boolean cadastrarUsuario(int id, String path) {
        Scanner sc = new Scanner(System.in);
        Clientes cl = new Clientes();

        System.out.println("|-----------|");
        System.out.println("  Cadastrar ");
        System.out.println("|-----------|\n");


        System.out.println("Email: ");
        cl.email = sc.nextLine();
        System.out.println("Senha: ");
        cl.senha = sc.nextLine();
        cl.id = id;
        try {
            gravarCliente(cl, path);
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possivel cadastrar: ");
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private static void gravarCliente(Clientes cl, String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + cl.id + ".txt");
        pw.println(cl.id);
        pw.println(cl.email);
        pw.println(cl.senha);
        pw.flush();
        pw.close();
    }


    private static void gravarId(int id) {
        PrintWriter pw;
        try {
            pw = new PrintWriter("id.txt");
            pw.println(id);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static int inicializar(String path) {
        int id = 0;
        File dir = new File(path);
        if (!dir.exists()) {            //Verifica se o diretório contendo contatos ja existe
            dir.mkdir();                //cria se não existir
        }
        File arquivo = new File("id.txt");
        if (!arquivo.exists()) {        //verifica se o arquivo de id já existe
            try {
                arquivo.createNewFile(); //cria se não existir
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(0);                //grava o id=0 para inicializar a agenda
        } else {                        //caso o arquivo de id já exista,
            id = lerId();                //é feita a leitura
        }
        return id;
    }

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

    private static void verificarReserva() {
        System.out.println("Sucesso");
    }

    private static void cancelarReserva() {
        System.out.println("Sucesso");
    }

    private static void pacotesDisponivel() {
        System.out.println("Sucesso");
    }

    private static boolean reservaPasseio(int id) {
        Scanner sc = new Scanner(System.in);
        Clientes c = new Clientes();
        System.out.println("|-------------------|");
        System.out.println(" Reservando Passeio ");
        System.out.println("|-------------------|\n");
        // Colocar data e hora
        System.out.print("Adultos: ");
        c.adultos = sc.nextInt();
        System.out.print("Crianças: ");
        c.criancas = sc.nextInt();
        c.id = id;

        return false;
    }
}
