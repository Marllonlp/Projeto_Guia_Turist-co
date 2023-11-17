import java.io.*;
import java.util.Scanner;
public class Projeto_Guia_Turistico {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        String path = "clientes/";
        String caminhoReserva = "Reservas/";
        int id = 0;
        menu();

        opcao = ler.nextInt();
        switch (opcao) {
            case 1:
                menuCadastro(id, path);
                break;
            case 2:
                validarAdmin(id, caminhoReserva);
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

        System.out.println("|-----------|");
        System.out.println("  Cadastrar  ");
        System.out.println("|-----------|\n");
        System.out.print("E-mail: ");
        cl.email = sc.nextLine();
        System.out.print("Senha: ");
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
        pw.println("Id do Usuario: "+cl.id);
        pw.println("Email do Usuario: "+cl.email);
        pw.println("Senha do Usuario: "+cl.senha);
        pw.flush();
        pw.close();
    }
    private static void gravarReserva(Clientes c, String caminhoReserva) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(caminhoReserva + c.id + ".txt");
        pw.println("Id do Usuario: "+c.id);
        pw.println(" \nNome: "+c.nome);
        pw.println(" \nContato: "+c.contato);
        pw.println(" \nQuantidade de Adultos: "+c.adultos);
        pw.println(" \nQuantidade de Crianças: "+c.criancas);
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
        File dir=new File(path);
        if(!dir.exists()) { 			//Verifica se o diretório contendo contatos ja existe
            dir.mkdir(); 				//cria se não existir
        }
        File arquivo = new File("id.txt");
        if(!arquivo.exists()) { 		//verifica se o arquivo de id já existe
            try {
                arquivo.createNewFile(); //cria se não existir
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(0); 				//grava o id=0 para inicializar a agenda
        }else { 						//caso o arquivo de id já exista,
            id=lerId(); 			    //é feita a leitura
        }
        return id;
    }
    private static int inicializarReserva(String caminhoReserva) {
        int id = 0;
        File dir1 = new File(caminhoReserva);
        if(!dir1.exists()) { 			//Verifica se o diretório contendo contatos ja existe
            dir1.mkdir(); 				//cria se não existir
        }
        File arquivo = new File("id.txt");
        if(!arquivo.exists()) { 		//verifica se o arquivo de id já existe
            try {
                arquivo.createNewFile(); //cria se não existir
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(0); 				//grava o id=0 para inicializar a agenda
        }else { 						//caso o arquivo de id já exista,
            id=lerId(); 			    //é feita a leitura
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
    private static void verificarPasseio() {
        System.out.println("Sucesso");
    }

    // Método para cancelar reservas
    private static void cancelarPasseio() {
        System.out.println("Sucesso");
    }

    // Método para exibir pacotes disponíveis
    private static void pacotesDisponivel() {
        System.out.println("Sucesso");
    }

    // Método para lidar com reservas de cavernas
    private static boolean reservaPasseio(int id, String caminhoReserva) {
        Scanner sc = new Scanner(System.in);
        Clientes c = new Clientes();
        System.out.println("|-------------------|");
        System.out.println(" Reservando Passeio ");
        System.out.println("|-------------------|\n");
        // Inserir data e hora
        System.out.print("Nome: ");
        c.nome = sc.nextLine();
        System.out.print("Contato: ");
        c.contato = sc.nextLine();
        System.out.print("Adultos: ");
        c.adultos = sc.nextInt();
        System.out.print("Crianças: ");
        c.criancas = sc.nextInt();
        c.id = id;

        try {
            gravarReserva(c, caminhoReserva);
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível fazer Reserva: ");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void menu() {
        System.out.println("\n|----------------------|");
        System.out.println("  Cavernas do Peruaçu  ");
        System.out.println("|----------------------|\n");
        System.out.println(" Entrar como: ");
        System.out.println(" 1 - Usuario ");
        System.out.println(" 2 - Administrador ");
        System.out.println(" 3 - Encerrar\n");
        System.out.print("Opção:");
    }

    private static void menuCadastro(int id, String path) {
        id = inicializar(path);
        Scanner ler = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            // Exibindo o menu
            System.out.println("\n|-------------------------|");
            System.out.println("  Cadastramento ou Login ");
            System.out.println("|---------------------------|\n");
            System.out.println(" 1 - Cadastrar Usuário ");
            System.out.println(" 2 - Fazer Login ");
            System.out.println(" 3 - Sair ");
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
                    loginUsuario();
                    break;
                case 3:
                    // Opção 6: Sair do programa
                    System.out.println("Saindo...");
                    break;
                default:
                    // Caso padrão: Opção inválida
                    System.out.println(" Opção Inválida! ");
            }
        }

    }

    private static void loginUsuario() {
        //Fazer login
    }

    private static void validarAdmin(int id, String caminhoReserva) {
        Scanner ler = new Scanner(System.in);
        Administrador senhaAdm = new Administrador();
        System.out.println("Infome a senha do Administrado");
        int senha = ler.nextInt();

        if (senha == senhaAdm.senhaAdmin) {
            menuAdmin(id, caminhoReserva);
        } else {
            System.out.println("Senha incorreta");
        }
    }

    private static void menuAdmin(int id, String caminhoReserva) {
        id = inicializarReserva(caminhoReserva);
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
                    if (reservaPasseio(id, caminhoReserva)) {
                        id++;
                        gravarId(id);
                    }
                    break;
                case 2:
                    verificarPasseio();
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
