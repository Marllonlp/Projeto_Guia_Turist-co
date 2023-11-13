package projeto2;
import java.io.*;
import java.util.*;

public class Agenda {

	public static void main(String[] args) {
		String path = "contatos/";
		int op =6;
		Scanner leia = new Scanner(System.in);
		int id = 0;
		
		id = inicializar(path);
		Contato c = new Contato();
		do {
			menu();
			op = leia.nextInt();
			switch(op) {
			case 1:
				if(CadastrarContato(id, path)) {
					id++;
					gravarId(id);
				};
				break;
			case 2:
				c = buscar(path);
				if(c!= null) {
					System.out.println(c.id+ " - "+c.nome+" - "+c.email);
				}else {
					System.out.println("Contato inexistente!");
				}
				break;
			case 3:
				listar(path);
				break;
			case 4:
				remover(path);
				break;
			case 5:
				resetar(path);
				id = 0;
				gravarId(0);
				break;
			case 6:
				gravarId(id);
				System.out.println("Saindo...");
				break;				
			}
		}while(op!=6);
	}

	private static void resetar(String path) {
		Scanner leia = new Scanner(System.in);
		System.out.println("Tem certeza que deseja apagar a agenda (s/n)? ");
		char op = leia.next().charAt(0);
		if(op == 's') {
			File dir = new File(path);			
			if(dir.exists()) {
				String [] arquivos = dir.list();
				for(int i=0; i<arquivos.length; i++) {
					File arq = new File(path+arquivos[i]);
					if(arq.delete())
						System.out.println("Apagando contato "+arquivos[i].substring(0,arquivos[i].length()-4));

				}
				dir.delete();
			}
			gravarId(0);
		}
			
	}

	private static void listar(String path) {
		File dir = new File(path);
		String [] arquivos = dir.list();
		for(int i=0; i<arquivos.length; i++) {
			int id = Integer.parseInt(arquivos[i].substring(0,arquivos[i].length()-4));
			try {
				Contato c = lerContato(id,path);
				System.out.println(c.id + " - "+ c.nome+ " - "+ c.email);
			} catch (IOException e) {
				System.out.println("Contato não encontrado");
				e.printStackTrace();
			}
		}
		if(arquivos.length == 0) {
			System.out.println("Não tem contato cadastrado");
		}
			
		
	}

	private static Contato buscar(String path) {
		Scanner leia = new Scanner(System.in);
		int id;
		Contato c = new Contato();
		System.out.println("Qual o id do contato?");
		id = leia.nextInt();
		try {
			c = lerContato(id,path);
			
		} catch (IOException e) {
			return null;
		}
	
		return c;
	}

	private static void remover(String path) {
		
		Scanner leia = new Scanner(System.in);
		int id;
		Contato c = new Contato();
		System.out.println("Qual o id do contato a ser removido?");
		id = leia.nextInt();
		File arquivo = new File(path+id+".txt");
		if(!arquivo.exists()) {
			System.out.println("Contato inexistente!");
		}else {
			if(arquivo.delete())
				System.out.println("Contato removido");
			else System.out.println("Contato não removido - erro");
			}
		
	}

	private static boolean CadastrarContato(int id, String path) {
		Scanner leia = new Scanner(System.in);
		Contato c = new Contato();
		System.out.println("Cadastrando contato - "+id);
		System.out.println("nome: ");
		c.nome = leia.nextLine();
		System.out.println("email: ");
		c.email = leia.nextLine();
		c.id=id;
		try {
			gravarContato(c,path);
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possivel gravar o contato");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void menu() {
		System.out.println("-------------------------"
				+ "\n1 - Cadastrar"
				+ "\n2 - Buscar"
				+ "\n3 - Listar"
				+ "\n4 - Remover"
				+ "\n5 - Resetar"
				+ "\n6 - Sair"
				+ "\n-------------------------");
		
	}

	private static Contato lerContato(int id, String path) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(path+id+".txt"));
		Contato c = new Contato();
		c.id = Integer.parseInt(bf.readLine());
		c.nome = bf.readLine();
		c.email = bf.readLine();
		bf.close();
		return c;
	}

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
			 id=lerId(); 			//é feita a leitura
		}
		return id;	
	}

	private static void gravarContato(Contato c, String path) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(path+c.id+".txt");
		pw.println(c.id);
		pw.println(c.nome);
		pw.println(c.email);
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
	private static int lerId() {
		BufferedReader bf;
		int id=0;
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
}
