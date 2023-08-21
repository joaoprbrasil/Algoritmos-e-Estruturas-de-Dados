/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author joaop
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        
                
        File arquivo = new File("src\\main\\java\\questao3\\entrada.txt");
        Scanner sc = new Scanner(arquivo);

         Tree<Integer> arvore = new Tree<>();
            int con = 0;
            while (sc.hasNext()) {
                String linha = sc.nextLine();
                String[] valores = linha.split(" ");
                
                for (String valor : valores) {
                    try {
                        int numero = Integer.parseInt(valor);
                            arvore.inserir(numero);
                            con++;
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido: " + valor);
                    }
                }

            }
            int altura = menorAltura(arvore.getRaiz());
            System.out.println(altura);
    }

    private static int menorAltura(NoTree<Integer> aux) {
        int alturaDir, alturaEsq;
        if(aux == null)
            return 0;
        else{
            alturaDir = menorAltura(aux.getDir());
            alturaEsq = menorAltura(aux.getEsq());
            if(alturaDir < alturaEsq)
                return alturaDir + 1;
            else
                return alturaEsq + 1;
        }

    }
    
}
