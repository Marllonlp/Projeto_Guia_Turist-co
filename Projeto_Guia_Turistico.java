import java.io.*;
import java.util.Scanner;

public class Projeto_Guia_Turistico {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        String path = "clientes/";
        String caminhoCadastro = path + "cadastro/";
        String caminhoReserva = path + "reservas/";

        inicializarGeral(path);
        inicializarReservas(path);
        int id = 0;
        int idR = 0;
        menu();
        opcao = ler.nextInt();
        switch (opcao) {
            case 1:
                menuCadastroUsuario(id, path, caminhoCadastro, caminhoReserva, idR);
                break;
            case 2:
                validarAdmin(id, caminhoReserva, idR);
                break;
            case 3:
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Opção Inválida!");
        }
    }

    // Métodos de inicialização
    private static int inicializarGeral(String caminhoReserva) {
        int id = 0;
        File dir = new File(caminhoReserva);
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
            gravarId(id, "id.txt");
        } else {
            id = lerId("id.txt");
        }
        return id;
    }

    private static int inicializarReservas(String caminhoReserva) {
        int idR = 0;
        File dir = new File(caminhoReserva);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File arquivo = new File("idR.txt");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Não foi possível criar o ID");
                e.printStackTrace();
            }
            gravarId(idR, "idR.txt");
        } else {
            idR = lerId("idR.txt");
        }
        return idR;
    }

    private static void gravarId(int id, String arquivo) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(arquivo);
            pw.println(id);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int lerId(String arquivo) {
        BufferedReader bf;
        int id = 0;
        try {
            bf = new BufferedReader(new FileReader(arquivo));
            id = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Id não encontrado");
            e.printStackTrace();
        }
        return id;
    }

    // Métodos relacionados ao cadastro de usuários
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
            gravarCadastroCliente(cl, path);
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível cadastrar");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void gravarCadastroCliente(Clientes cl, String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path + cl.id + ".txt");
        pw.println("Id do Usuario: " + cl.id);
        pw.println("Email do Usuario: " + cl.email);
        pw.println("Senha do Usuario: " + cl.senha);
        pw.flush();
        pw.close();
    }

    // Métodos relacionados à reserva de passeios
    private static void gravarReserva(Reserva c, String caminhoReserva, int idR) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(caminhoReserva + c.id + "_" + idR + ".txt");
        pw.println("\nDados da Reserva");
        pw.println("Id do Usuario: " + c.id);
        pw.println("Id da Reserva: " + c.idR);
        pw.println("\nNome: " + c.nome);
        pw.println("\nContato: " + c.contato);
        pw.println("\nQuantidade de Adultos: " + c.adultos);
        pw.println("\nQuantidade de Crianças: " + c.criancas);
        pw.println("\nPacote escolhido: " + c.pacote);
        pw.flush();
        pw.close();
    }

    private static boolean reservaPasseio(int id, String caminhoReserva, int idR) {
        Scanner sc = new Scanner(System.in);
        Reserva c = new Reserva();
        System.out.println("|-------------------|");
        System.out.println(" Reservando Passeio ");
        System.out.println("|-------------------|\n");
        System.out.print("Nome: ");
        c.nome = sc.nextLine();
        System.out.print("Contato: ");
        c.contato = sc.nextLine();
        System.out.print("Adultos: ");
        c.adultos = sc.nextInt();
        System.out.print("Crianças: ");
        c.criancas = sc.nextInt();
        System.out.println("|-------------------|");
        System.out.println(" Opções de Passeio ");
        System.out.println("|-------------------|\n");
        System.out.println("1 - Todo o Percurso");
        System.out.println("2 - Meio Percurso");
        System.out.print("opção: ");
        c.pacote = sc.nextInt();
        c.id = id;
        c.idR = idR;

        try {
            gravarReserva(c, caminhoReserva, idR);
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível fazer Reserva: ");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Métodos relacionados à validação do administrador
    private static void validarAdmin(int id, String caminhoReserva, int idR) {
        Scanner ler = new Scanner(System.in);
        Administrador senhaAdm = new Administrador();
        System.out.println("Informe a senha do Administrador");
        int senha = ler.nextInt();

        if (senha == senhaAdm.senhaAdmin) {
            menuAdmin(id, caminhoReserva, idR);
        } else {
            System.out.println("Senha incorreta");
        }
    }

    private static boolean loginUsuario(String caminhoCadastro, int id, String caminhoReserva, int idR) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o seu e-mail: ");
        String email = sc.nextLine();
        System.out.println("Informe a sua senha: ");
        String senha = sc.nextLine();

        File diretorio = new File(caminhoCadastro);
        File[] arquivos = diretorio.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(arquivo));
                    String linha;
                    String emailArquivo = "";
                    String senhaArquivo = "";
                    while ((linha = bf.readLine()) != null) {
                        if (linha.contains("Email do Usuario: ")) {
                            emailArquivo = linha.replace("Email do Usuario: ", "");

                        }
                        if (linha.contains("Senha do Usuario: ")) {
                            senhaArquivo = linha.replace("Senha do Usuario: ", "");
                        }

                    }
                    if (email.equals(emailArquivo) && senha.equals(senhaArquivo)) {
                        System.out.println("Login realizado com sucesso!");
                        menuUsuario(id, caminhoReserva, idR);
                        return true;
                    }
                    bf.close();
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo");
                    e.printStackTrace();
                }
            }

        }
        System.out.println("E-mail ou senha incorretos!");
        return false;
    }

    private static void confimarReserva() {
        // Implementar método de confirmação de reserva
    }

    private static void verificarPasseio() {
        // Implementar método de verificação de reserva
    }

    private static void cancelarReserva() {
        // Implementar método de cancelamento de reserva
    }

    private static void informacaoSobreParque() {
        System.out.println("\n|-------------------------|");
        System.out.println("  CAVERNAS DO PERUAÇU");
        System.out.println("|---------------------------|\n");
        System.out.println("O Parque Nacional Cavernas do Peruaçu é um local onde belas paisagens são emolduradas pela arte rupestre pré-histórica, em sítios arqueológicos milenares de importância internacional e suas cavernas de grandeza colossal.\n" + "\n" + "A Unidade de Conservação foi criada em 1999, e possui uma área de 56.448 hectares, que compreende os municípios de Januária, Itacarambi e São João das Missões, na região norte de Minas Gerais.");
    }

    // Métodos relacionados aos menus
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

    private static void menuCadastroUsuario(int id, String path, String caminhoCadastro, String caminhoReserva, int idR) {
        //id = inicializarGeral(caminhoCadastro);

        Scanner ler = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
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
                    if (    cadastrarUsuario(id, caminhoCadastro)) {
                        id++;
                        gravarId(id, "id.txt");
                    }
                    break;
                case 2:
                        //ateção aqui
                    loginUsuario(caminhoCadastro, id, caminhoReserva, idR);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private static void menuAdmin(int id, String caminhoCadastro, int idR) {
        id = inicializarGeral(caminhoCadastro);
        idR = inicializarReservas("clientes/reservas/");
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n|----------------------|");
            System.out.println("  Cavernas do Peruaçu  ");
            System.out.println("|----------------------|\n");
            System.out.println(" 1 - Fazer Reserva para Usuario ");
            System.out.println(" 2 - Verificar Reserva para Usuario");
            System.out.println(" 3 - Confirmar Reserva");
            System.out.println(" 4 - Sair ");
            System.out.print("\n Opção: ");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Qual o ID do Usuario: ");
                    id = ler.nextInt();
                    if (reservaPasseio(id, caminhoCadastro, idR)) {
                        idR++;
                        gravarId(idR, "idR.txt");
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
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

    private static void menuUsuario(int id, String caminhoReserva, int idR) {
        Scanner ler = new Scanner(System.in);
        idR = inicializarReservas("clientes/reservas/");
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n|----------------------|");
            System.out.println("  Cavernas do Peruaçu  ");
            System.out.println("|----------------------|\n");
            System.out.println(" 1 - Ver Informações sobre o Parque");
            System.out.println(" 2 - Fazer Reserva");
            System.out.println(" 3 - Cancelar Reserva");
            System.out.println(" 4 - Sair ");
            System.out.print("\n Opção: ");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    informacaoSobreParque();
                    break;
                case 2:
                    if (reservaPasseio(id, caminhoReserva, idR)) {
                        idR++;
                        gravarId(idR, "idR.txt");
                    }
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }
}
