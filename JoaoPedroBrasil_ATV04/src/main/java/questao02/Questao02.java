/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package questao02;

import util.TabelaHash;

/**
 *
 * @author joaop
 */
public class Questao02 {

    public static void main(String[] args) {
        int[] entrada = {5, 3, 2, 7, 1, 3, 9};
        TabelaHash<Integer, Boolean> numeros = new TabelaHash<>();
        TabelaHash<Integer, Integer> tabela = new TabelaHash<>();

        
        System.out.print("Números repetidos: ");
        for (int i=0; i<entrada.length; i++) {
            if (tabela.contemChave(entrada[i])) {
                if (!numeros.contemChave(entrada[i])) {
                    System.out.print(entrada[i] + " ");
                    numeros.inserir(entrada[i], true);
                }
            }
            tabela.inserir(entrada[i], entrada[i]);
        }
    }

}
