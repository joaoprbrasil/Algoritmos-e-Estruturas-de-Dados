package questao1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author joaop
 */
/*
    1. Dada uma lista binária ordenada, encontre a quantidade de 1s presentes.
    Entrada: 0>0>0>0>0>1>1>1
    Saída: Quantidade de 1s = 3
*/

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        
        File arquivo = new File("src\\main\\java\\questao1\\entrada.txt");
        Scanner sc = new Scanner(arquivo);

         ListaDupla<Integer> lista = new ListaDupla<>();
            
            while (sc.hasNext()) {
                String linha = sc.nextLine();
                String[] valores = linha.split(">");
                
                for (String valor : valores) {
                    try {
                        int numero = Integer.parseInt(valor);
                        if (numero == 0 || numero == 1) {
                            lista.inserir(numero);
                        } else {
                            System.out.println("Valor inválido: " + valor);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido: " + valor);
                    }
                }

            }
            
            int con = conta1s(lista.getPrimeiro());
            System.out.println(con);
        
    }

    private static int conta1s(No<Integer> aux) {
        
        if(aux != null){
            if(aux.getElem() == 1){
                return 1 + conta1s(aux.getProximo());
            }else{
                return conta1s(aux.getProximo());
            }
        }else{
            return 0;
        } 
    }

}
