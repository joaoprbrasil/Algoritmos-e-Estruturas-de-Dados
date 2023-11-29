/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao02;

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
        File arquivo = new File("src\\main\\java\\entradas\\dadosQ2.txt");
        Scanner sc = new Scanner(arquivo);
        
        while(sc.hasNextLine()){
                String linha = sc.nextLine();
                String[] dados = linha.split(";");
                if (dados.length == 2) { // Certifica-se de que a linha está no formato correto
                    grafo.adicionarVertice(dados[0]);
                    grafo.adicionarVertice(dados[1]);
                    grafo.adicionarAresta(1.0 , dados[0], dados[1], false);
                }
        }
        
        grafo.temCiclo();

    }
    
}
