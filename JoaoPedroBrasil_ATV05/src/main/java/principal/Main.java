/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

/**
 *
 * @author joaop
 */
public class Main {
        public static void main(String[] args) {
            BTree<String> b = new BTree<>(2);
            /*
            
                    Max Elementos = T * 2 - 1
                    Max Elementos = 2 * 2 - 1
                    Max Elementos = 3
                Com o T sendo igual a 2, cada chave terá no máximo 3 elementos.
               
            */
            
            
            b.inserir("h");
            b.inserir("g");
            b.inserir("e");
            b.inserir("q");
            b.inserir("u");
            b.inserir("v");
            b.inserir("a");
            b.inserir("i");
            b.inserir("p");


            /*b.buscar(1);
            b.buscar(2);
            b.buscar(3);
            b.buscar(4);
            b.buscar(5);
            b.buscar(6);
            b.buscar(7);
            
            b.inserir(7);
            
            b.buscar(7);
            
            b.remover(7);
            
            b.imprimir();*/
            
            b.imprimirPreOrdemPosOrdem();

        }
}
