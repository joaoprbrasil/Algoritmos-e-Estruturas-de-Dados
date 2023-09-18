package questao03;
import util.ListaDupla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class questao3{
    public static int getGap(int gap){
        gap /= 1.3;
        if(gap < 1)
            gap = 1; //testte
        return gap;
    }
    
    public static void combSort(ListaDupla<Integer> lista){
        int gap = lista.getTamanho();
        boolean trocou = true;
        while(gap != 1 || trocou){
            gap = getGap(gap);
            trocou = false;
            for(int i = 0; i < lista.getTamanho() - gap; i++){
                if((int) lista.get(i).getElem() > (int) lista.get(i + gap).getElem()){
                    int temp = (int) lista.get(i).getElem();
                    lista.get(i).setElem(lista.get(i + gap).getElem());
                    lista.get(i + gap).setElem(temp);
                    trocou = true;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        File arquivo = new File("src\\main\\java\\entradas\\dadosQ3.txt");
        Scanner sc = new Scanner(arquivo);
        String dados = sc.nextLine();
        String[] numeros = dados.split(",");
        
        ListaDupla<Integer> lista = new ListaDupla<>();   
        
        for (String numero : numeros) {
            lista.inserir(Integer.parseInt(numero));
        }

        combSort(lista);

        if(lista.getTamanho() % 2 == 0){
            int meio1 = (int) lista.get(lista.getTamanho() / 2).getElem();
            int meio2 = (int) lista.get((lista.getTamanho() / 2) - 1).getElem();
            System.out.println((double)(meio1 + meio2) / 2);
        }
        else
            System.out.println(lista.get(lista.getTamanho() / 2).getElem());
    }
}
