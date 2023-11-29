/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package questao04;

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

        
        File arquivo = new File("src\\main\\java\\entradas\\dadosQ4.txt");
        Grafo<Integer> grafo = new Grafo<>();
        
        // Contagem de linhas e letras
        Scanner sc = new Scanner(arquivo);
        int quantidadeLetras=0, quantidadeLinhas=0;
        while(sc.hasNextLine()){
                String primeiraLinha = sc.nextLine();
                quantidadeLetras = contarLetras(primeiraLinha);
                quantidadeLinhas++;    
        }
        
        // Convertendo o .txt em uma matriz de caracteres e salvando os números dos vertices no grafo
        char[][] labirinto = new char[quantidadeLinhas][quantidadeLetras];
        sc = new Scanner(arquivo);
        int con=0, entrada = 0, saida = 0;
        System.out.println("Labirinto:");
        while(sc.hasNextLine()){
                String linha = sc.nextLine();
                for (int i = 0; i < linha.length(); i++) {
                    
                    labirinto[con][i] = linha.charAt(i);
                    
                    // Impressão do labirinto
                    if(labirinto[con][i] != 'X')
                        if((i+con*quantidadeLetras) > 9)
                            System.out.printf(" %d ", i+con*quantidadeLetras);
                        else
                            System.out.printf(" %d  ", i+con*quantidadeLetras);
                    else
                        System.out.printf(" X  ");
                    
                    // Adicionando os números no grafo
                    grafo.adicionarVertice(i+con*quantidadeLetras);
                    // Armazenando a Entrada e a Saida do labirinto
                    if(labirinto[con][i] == 'E'){
                        entrada = i+con*quantidadeLetras;
                    }
                    if(labirinto[con][i] == 'S'){
                        saida = i+con*quantidadeLetras;
                    }
                }
                con++;
                System.out.println();
        }
        System.out.println("Entrada: " + entrada);
        System.out.println("Saida: " + saida);

        
        for(int i=0; i<quantidadeLinhas; i++){
            for(int j=0; j<quantidadeLetras; j++){
                if(labirinto[i][j] == 'A' || labirinto[i][j] == 'E' || labirinto[i][j] == 'S'){
                    
                    // Relacionando os grafos horizontalmente
                    if((j+1<quantidadeLetras) && (labirinto[i][j+1] == 'A' || labirinto[i][j+1] == 'E' || labirinto[i][j+1] == 'S')){
                        grafo.adicionarAresta(1.0, j+i*quantidadeLetras, (j+i*quantidadeLetras)+1, false);
                    }    

                    // Relacionando os grafos verticalmente
                    if (i+1<quantidadeLinhas && (labirinto[i + 1][j] == 'A' || labirinto[i + 1][j] == 'E' || labirinto[i + 1][j] == 'S')) {
                        grafo.adicionarAresta(1.0, j+i*quantidadeLetras, (((i + 1) * quantidadeLetras) + j), false);
                    }
                }
            }
        }
        grafo.encontrarCaminho(entrada, saida);
    }
    
    // Método para contar letras em uma linha
    private static int contarLetras(String linha) {
        int contador = 0;
        for (char caractere : linha.toCharArray()) {
            if (Character.isLetter(caractere)) {
                contador++;
            }
        }
        return contador;
    }
}
