/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao03;


/**
 *
 * @author 20221SI0013
 */
public class questao33 {
    public static int getGap(int gap){
        gap /= 1.3;
        if(gap<1)
            gap = 1;
        return gap;
    }
    
    public static void combSort(int [] list){
        int gap = list.length;
        boolean trocou = true;
        while(gap != 1 || trocou  == true ){
            gap = getGap(gap);
            trocou = false;
            for(int i = 0; i < list.length - gap; i++){
                if(list[i] > list[i + gap]){
                    int temp = list[i];
                    list[i] = list[i + gap];
                    list[i + gap] = temp;
                    trocou = true;
                }
            }
        }
    }

    public static void main(String[] args){
        int list[] = {1, 2, 3, 4, 7, 22, 425, 17, 54, 32, 93, 105, 55, 56, 57, 58, 59};

        combSort(list); // Este método organiza a lista em ordem crescente usando a lógica COMB SORT
        
        if(list.length%2==0)
            System.out.println((double)(list[(list.length/2)]+list[(list.length/2)-1])/2);
        else
            System.out.println(list[list.length/2]);
        
    }
}
