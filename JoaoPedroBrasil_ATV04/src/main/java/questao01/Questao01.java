package questao01;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import util.TabelaHash;
/**
 *
 * @author joaop
 */
public class Questao01 {
    
    
    public static void main(String[] args) {
        TabelaHash<Integer, Integer> tabela = new TabelaHash<>();
        tabela.inserir(1, 5);
        System.out.println(tabela.buscar(1));       
        tabela.imprimir();
        tabela.remover(1);
        tabela.imprimir();
    }
}