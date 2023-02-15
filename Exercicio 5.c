/*
  Faça um programa que lê de um arquivo, o sexo, o primeiro nome, e a idade
de uma lista de pessoas. Imprima em outro arquivo, o número de homens,
o número de mulheres e a idade da pessoa mais velha. A primeira linha
do arquivo de entrada deve conter o número de pessoas.
*/

#include <stdio.h>

int main(void){

int con, indice, idade, maiorIdade=0, homens=0, mulheres=0;
char texto[100], strIdade[3];

    FILE *fptr, *fptr2;
    fptr = fopen("pessoas.txt", "r");
    fptr2 = fopen("arquivo.txt", "w");

    while(fgets(texto, 100, fptr)){

        if(texto[0] == 'M'){
            homens++;
        }
        if(texto[0] == 'F'){
            mulheres++;
        }

        con=0;
        indice=0;
        for(int i=0; i<strlen(texto); i++){
            if(texto[i] == ' '){
                con++;
            }
            if(con == 2 && texto[i]!=' '){
                strIdade[indice] = texto[i];
                indice++;
                idade = atoi(strIdade);
                if(idade>maiorIdade){
                    maiorIdade = idade;
                }
            }
        }

    }

    fprintf(fptr2, "O número de homens: %d\n", homens);
    fprintf(fptr2, "O número de mulheres: %d\n", mulheres);
    fprintf(fptr2, "A idade da pessoa mais velha: %d\n", maiorIdade);

return;
}
