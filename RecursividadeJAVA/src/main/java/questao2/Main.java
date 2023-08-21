/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author joaop
 */
public class Main {
      public static void main(String[] args) throws FileNotFoundException{
          File arquivo = new File("src\\main\\java\\questao2\\entrada.txt");
          Scanner sc = new Scanner(arquivo);
          ListaDupla<Integer> listaX = new ListaDupla<>();
          ListaDupla<Integer> listaY = new ListaDupla<>();
          
          while(sc.hasNextLine()){
              String linha = sc.nextLine();
              String[] partes = linha.split(";");
              if(partes.length == 2){
                try {
                    int num1 = Integer.parseInt(partes[0].trim());
                    int num2 = Integer.parseInt(partes[1].trim());
                    listaX.inserir(num1);
                    listaY.inserir(num2);

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter strings para inteiros: " + e.getMessage());
                }
              }else{
                  System.out.println("Dado inválido: " + linha);
              }
          }
          listaX.imprimir();
          
          
      }

}
