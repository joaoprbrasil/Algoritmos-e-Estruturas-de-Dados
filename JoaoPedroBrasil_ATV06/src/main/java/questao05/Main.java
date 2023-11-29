/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package questao05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import principal.Grafo;

/**
 *
 * @author joaop
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Grafo<String> grafo = new Grafo<>();
        File arquivo = new File("src\\main\\java\\entradas\\dadosQ5.txt");
        Scanner sc = new Scanner(arquivo);
        String divisor;

        if(caractereSplit(sc))
            divisor = "; ";
        else
            divisor = ";";
        
        sc = new Scanner(arquivo);
        String origem = sc.nextLine();
        String destino = sc.nextLine();

        while(sc.hasNextLine()){
                String linha = sc.nextLine();
                String[] dados = linha.split(divisor);
                if (dados.length == 3) { // Certifica-se de que a linha está no formato correto
                    grafo.adicionarVertice(dados[0]);
                    grafo.adicionarVertice(dados[1]);
                    double distancia = Double.parseDouble(dados[2]); 
                    grafo.adicionarAresta(distancia, dados[0], dados[1], true);
                }
        }
        
        // Encontrando menor caminho com o método de Floyd Warshall
        grafo.floydWarshall(origem, destino);

    }
    
    private static boolean caractereSplit(Scanner scanner) {
        // Move para a terceira linha
        for (int i = 1; i <= 2 && scanner.hasNextLine(); i++) {
            scanner.nextLine();
        }

        // Verifica se os dados são divididos por "; " ou ";"
        return scanner.hasNextLine() && scanner.nextLine().contains("; ");
    }
}
