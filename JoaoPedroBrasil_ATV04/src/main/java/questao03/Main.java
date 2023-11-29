/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package questao03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import util.TabelaHash;


/**
 *
 * @author joaop
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        TabelaHash<String,Cliente<String>> tabela = new TabelaHash();
        File arquivo = new File("src\\main\\java\\questao03\\entrada.txt");
        Scanner sc = new Scanner(arquivo);
        while(sc.hasNextLine()){
                String linha = sc.nextLine();
                String[] dados = linha.split(";");
                if (dados.length == 4) { // Certifica-se de que a linha está no formato correto
                    String id = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String cidade = dados[3];
                    Cliente<String> cliente = new Cliente(id, nome, email, cidade);
                    tabela.inserir(id, cliente);
                }
        }
         
        arquivo = new File("src\\main\\java\\questao03\\exec.txt");
        sc = new Scanner(arquivo);
         while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] partes = linha.split(";");
                
            switch (partes.length) {
                case 5 ->
                {
                        String comando = partes[0].trim();
                        String id = partes[1].trim();
                        String nome = partes[1].trim();
                        String email = partes[1].trim();
                        String cidade = partes[1].trim();
                        if("INSERIR".equals(comando)){
                            Cliente<String> C = new Cliente(id, nome, email, cidade);
                            tabela.inserir(id, C);
                            System.out.println("Elemento "+ id +" inserido.");
                        }                      
                }
                case 2 ->                     
                {
                        String comando = partes[0].trim();
                        String id = partes[1].trim();
                        System.out.println(comando + "; " + id);
                        if("REMOVER".equals(comando)){
                            tabela.remover(id);
                            System.out.println("Elemento " + id + " removido.");
                        }   if("BUSCAR".equals(comando)){
                            Cliente<String> cliente = tabela.buscar(partes[1]);
                            if (cliente != null) {
                                System.out.println(cliente.toString());
                            } else {
                                System.out.println("Não foi possível encontrar o cliente de id: " + partes[1]);
                            }
                        }                      
                }
                case 1 ->                     
                {
                        String comando = partes[0];
                        System.out.println(comando+";");
                        if("IMPRIMIR".equals(comando) || "IMPRIMIR;".equals(comando)){
                            tabela.imprimir();
                        }                      
                }
                default -> 
                {
                }
            }
            }
    }
}
