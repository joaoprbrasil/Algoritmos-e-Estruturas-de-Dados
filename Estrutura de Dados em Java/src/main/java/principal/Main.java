/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package principal;

import arvore.Tree;
import fila.Fila;
import pilha.Pilha;
import listaEncadeadaDupla.ListaDupla;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author joaop
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

    
         File arquivo = new File("src\\main\\java\\dados\\exec.txt");
         Scanner sc = new Scanner(arquivo);
         String estruturaTipo = sc.nextLine();

         Object estrutura = null;
        
         switch (estruturaTipo) {
            case "lista":
                estrutura = new ListaDupla<String>();
                break;
            case "fila":
                estrutura = new Fila<String>();
                break;
            case "pilha":
                estrutura = new Pilha<String>();
                break;
            case "arvore":
                estrutura = new Tree<String>();
                break;
            default:
                break;
        }
         if(estrutura == null)
             System.out.println("Estrutura de dados inválida.");
         System.out.println("Tipo de Estrutura: " + estruturaTipo);
         
         
         while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] partes = linha.split(";");
                
                if (partes.length == 2) {
                    String comando = partes[0].trim();
                    String argumento = partes[1].trim();
                    
                    System.out.println(comando + "; " + argumento);
                    
                    
                    if("INSERIR".equals(comando)){
                        if (estrutura instanceof ListaDupla) {
                            ((ListaDupla<String>) estrutura).inserir(argumento);
                        }else if(estrutura instanceof Fila){
                            ((Fila<String>) estrutura).inserir(argumento);
                        }else if(estrutura instanceof Pilha){
                            ((Pilha<String>) estrutura).inserir(argumento);
                        }else if(estrutura instanceof Tree){
                            ((Tree<String>) estrutura).inserir(argumento);
                        } 
                    }
                    if("REMOVER".equals(comando)){
                       if (estrutura instanceof ListaDupla) {
                            if(!((ListaDupla<String>) estrutura).remover(argumento))
                                System.out.println("Elemento não encontrado");
                            else
                                System.out.println("Elemento removido.");
                        }else if(estrutura instanceof Fila){
                            if(((Fila<String>) estrutura).remover())
                                System.out.println("Primeiro elemento da fila removido.");
                            else
                                System.out.println("Erro na remoção.");
                        }else if(estrutura instanceof Pilha){
                            if(((Pilha<String>) estrutura).remover())
                                System.out.println("Elemento do topo da pilha removido.");
                            else
                                System.out.println("Erro na remoção.");
                        }else if(estrutura instanceof Tree){
                            if(!((Tree<String>) estrutura).remover(argumento))
                                System.out.println("Elemento não encontrado");
                            else
                                System.out.println("Elemento removido.");
                        }
                    }
                    if("BUSCAR".equals(comando)){
                        if (estrutura instanceof ListaDupla) {
                            if(((ListaDupla) estrutura).buscar(argumento) != null)
                                System.out.println("Elemento encontrado");
                            else
                                System.out.println("Elemento não encontrado");
                        }else if (estrutura instanceof Fila) {
                            if(((Fila) estrutura).buscar(argumento) != null)
                                System.out.println("Elemento encontrado");
                            else
                                System.out.println("Elemento não encontrado");
                        }else if (estrutura instanceof Pilha) {
                            if(((Pilha) estrutura).buscar(estrutura) !=null)
                                System.out.println("Elemento encontrado");
                            else
                                System.out.println("Elemento não encontrado");
                        }else if (estrutura instanceof Tree) {
                            if(((Tree) estrutura).buscar(((Tree) estrutura).getRaiz(), argumento) != null)
                                System.out.println("Elemento encontrado");
                            else
                                System.out.println("Elemento não encontrado");
                        }
                                
                    }
                }else if(partes.length == 1){
                    String comando = partes[0];
                    System.out.println(comando+";");
                    if("IMPRIMIR".equals(comando) || "IMPRIMIR;".equals(comando)){
                        if (estrutura instanceof ListaDupla && ((ListaDupla) estrutura).getTamanho()>0) {
                                ((ListaDupla<String>) estrutura).imprimir();
                        }else if(estrutura instanceof Fila && ((Fila) estrutura).getTamanho()>0){
                                ((Fila<String>) estrutura).imprimir();
                        }else if(estrutura instanceof Pilha && ((Pilha) estrutura).getTamanho()>0){
                            ((Pilha<String>) estrutura).imprimir();
                        }else if(estrutura instanceof Tree && ((Tree) estrutura).getTamanho()>0){
                            ((Tree<String>) estrutura).imprimir();
                        }else{
                            System.out.println("A " + estruturaTipo + " está vazia.");
                        }
                    }
                }
            }
    }
}
