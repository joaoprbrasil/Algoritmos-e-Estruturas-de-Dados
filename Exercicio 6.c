/*
 Escreva um programa que leia um arquivo e imprime no final desse mesmo
arquivo, sem sobrescrevê-lo, qual é o caractere que mais se repete.
 O nome do arquivo a ser lido deve ser dado pelo usuário.
*/

#include <stdio.h>
#include <string.h>

int main(void){

char nomeArq[50], textoArq[100], caractere, modal;
int con, maiorCon=0, j=0;

    FILE *fptr;
    scanf(" %49[^\n]", nomeArq);
    fptr = fopen(nomeArq, "r+");

    if(fptr == NULL){
        printf("O arquivo não existe.");
    }else{
            while(fgets(textoArq, 100, fptr)){
                for(int i=0; i<strlen(textoArq); i++){
                    caractere = textoArq[i];
                    con=0;
                    for(int j=0; j<strlen(textoArq); j++){
                        if(textoArq[j] == caractere && caractere != ' '){
                            con++;
                        }
                    }
                    if(con>maiorCon){
                        maiorCon = con;
                        modal = textoArq[i];
                    }
                }
                j++;
            }

        printf("O caractere que mais se repete é: %c", modal);
        fprintf(fptr, "\nO caractere que mais se repete é: %c", modal);
        fclose(fptr);
    }

return 0;
}
