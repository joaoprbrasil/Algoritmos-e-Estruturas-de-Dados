/*
 Exercício 1: Faça um programa em Linguagem C que controla
o status de uma matriz de poltronas de um teatro.

Matriz de números inteiros onde para cada poltrona[7][7]:
1 - livre
2 - ocupado/vendida
3 - reservada
4 - bloqueada

No Menu
1 - Exibir status das poltronas (matriz)
2 - Reservar
3 - Comprar
4 - Bloquear
9 - Sair e salvar


 Ao sair do programa, os dados deverão ser salvos em arquivo
texto. Ao retornar ao programa, esses dados deverão ser
carregados para a matriz.
*/


#include <stdio.h>
#include <string.h>
#include <windows.h>

int main(void){
int menu(void);
void mostrarPoltronas(int poltronas[7][7]);
void editarPoltrona(int x, int poltronas[7][7]);
void salvarDados(int poltronas[7][7]);

int poltronas[7][7];

    FILE *fptr = fopen("poltronas.txt", "r+");
    if(fptr == NULL){
        fclose(fptr);
        fptr = fopen("poltronas.txt", "w");
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                poltronas[i][j]=1;
                fprintf(fptr, "%d ", poltronas[i][j]);
            }
                fprintf(fptr, "\n");
        }
        fclose(fptr);
        main();

    }else{
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                fscanf(fptr, "%d", &poltronas[i][j]);
            }
                fprintf(fptr, "\n");
        }
    }

int x=0;
    while(x!=9){
        x = menu();
        if(x == 1){
            mostrarPoltronas(poltronas);
        }
        if(x>1 && x<5){
            editarPoltrona(x, poltronas);
        }

    }

    salvarDados(poltronas);
    fclose(fptr);

return 0;
}

int menu(void){

int x=0;

    printf("1 ) Exibir poltronas.\n");
    printf("2 ) Reservar poltrona.\n");
    printf("3 ) Comprar poltrona.\n");
    printf("4 ) Bloquear poltrona.\n");
    printf("9 ) Sair e salvar\n> ");
    while((x<1 || x>4) && x!=9){
        scanf("%d", &x);
    }

return x;
}

void mostrarPoltronas(int poltronas[7][7]){

    system("cls");
    for(int i=0; i<7; i++){
        for(int j=0; j<7; j++){
            printf("%d ", poltronas[i][j]);
        }
        printf("\n");
    }

}

void editarPoltrona(int x, int poltronas[7][7]){
int linha, coluna;

        printf("Escolha a linha da poltrona:\n> ");
        scanf("%d", &linha);
        linha-=1;
        printf("Escolha a coluna da poltrona:\n> ");
        scanf("%d", &coluna);
        coluna-=1;

        system("cls");
        if(poltronas[linha][coluna] != 1){
            printf("Poltrona inválida para uso.\n");
        }
        else if(x == 2){
            poltronas[linha][coluna] = 3;
            printf("Poltrona reservada com sucesso!\n");
        }
        else if(x == 3){
            poltronas[linha][coluna] = 2;
            printf("Poltrona comprada com sucesso!\n");
        }
        else if(x == 4){
            poltronas[linha][coluna] = 4;
            printf("Poltrona bloqueada com sucesso!\n");
        }


}

void salvarDados(int poltronas[7][7]){

        FILE *fptr;
        fptr = fopen("poltronas.txt", "w");
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                fprintf(fptr, "%d ", poltronas[i][j]);
            }
                fprintf(fptr, "\n");
        }
        fclose(fptr);


}
