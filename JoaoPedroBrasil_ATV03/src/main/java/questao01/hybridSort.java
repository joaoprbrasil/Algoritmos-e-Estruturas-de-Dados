/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package questao01;

/**
 *
 * @author 20221SI0013
 */
public class hybridSort {

    public static void bubbleSort(int list[]){
        int aux = 0;
        for(int i=0; i< list.length-1; i++){
            if(list[i]>list[i+1]){
                aux = list[i];
                list[i] = list[i+1];
                list[i+1] = aux;
            }
        }
    }
    
    public static void selectionSort(int list[]){
        int aux = 0;
        for(int i=0; i<list.length; i++){
            int smallestNum = i;
            for(int j=i; j<list.length; j++){
                if(list[smallestNum] > list[j])
                    smallestNum = j;
            }
            aux = list[i];
            list[i] = list[smallestNum];
            list[smallestNum] = aux;
        }
    }
    
    public static void hybrid(int list[]){
        int aux = 0;
        for(int i=0; i<list.length-1; i++){
            int smallestNum = i;
            if(list[i]>list[i+1]){
                aux = list[i];
                list[i] = list[i+1];    // BUBBLE SORT
                list[i+1] = aux;
            }
            for(int j=i; j<list.length; j++){
                if(list[smallestNum] > list[j])
                    smallestNum = j;
            }
            aux = list[i];
            list[i] = list[smallestNum];    // SLECTION SORT
            list[smallestNum] = aux;
        }
    }
    
    public static boolean sorted(int list[]){  // VERIFICA SE A LISTA ESTÁ ORDENADA
        for(int i=0; i<list.length-1; i++){
            if(list[i]>list[i+1])
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        int list[] = {871, 236, 479, 682, 57, 305, 998, 691, 815, 142, 479, 906, 10, 542, 801, 265, 693, 144, 827, 72, 619, 301, 919, 407, 573, 33, 968, 218, 754, 915, 689, 751, 553, 623, 122, 475, 843, 251, 652, 367, 25, 880, 431, 145, 378, 529, 692, 617, 910, 505, 257, 96, 789, 528, 603, 774, 819, 253, 630, 145, 433, 362, 238, 432, 331, 790, 699, 156, 577, 800, 285, 175, 430, 760, 84, 388, 352, 916, 335, 977, 323, 399, 729, 808, 238, 642, 842, 870, 249, 405, 767, 317, 761, 918, 462, 507, 20, 437, 294, 956, 291};
        
        hybrid(list);
        
        int x = 0;
        while( x < list.length ){
            System.out.println(list[x]);
            x++;
        }
        System.out.println(sorted(list));
    }
}
