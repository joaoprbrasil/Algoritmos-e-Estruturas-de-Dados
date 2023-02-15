/*
 Exercício 3: Faça alterações no Exercício 2,
de forma a acrescentar as seguintes
funcionalidades no MENU:
5 - Pesquisa nome de cliente
6 - Calcular o total da bilheteria
*/

#include <stdio.h>
#include <string.h>
#include <windows.h>

typedef struct{

    char nome[100];
    float valor;
    int status;

}Poltronas;

Poltronas poltronas[7][7];

int main(void){
int menu(void);
void mostrarPoltronas();
void sistemaMenu(int x);
void salvarDados();
void carregarDados();

    carregarDados();

int x=0;
    while(x!=9){
        x = menu();
        if(x == 1){
            mostrarPoltronas();
        }
        if(x>1 && x<7){
            sistemaMenu(x);
        }

    }

    salvarDados();

return 0;
}

int menu(void){

int x=0;

    printf("1 ) Exibir poltronas.\n");
    printf("2 ) Reservar poltrona.\n");
    printf("3 ) Comprar poltrona.\n");
    printf("4 ) Bloquear poltrona.\n");
    printf("5 ) Pesquisar nome de cliente.\n");
    printf("6 ) Calcular o total da bilheteria.\n");
    printf("9 ) Sair e salvar\n> ");
    while((x<1 || x>6) && x!=9){
        scanf("%d", &x);
    }

return x;
}

void mostrarPoltronas(){

    system("cls");
    printf("1 -> Poltrona livre.\n");
    printf("2 -> Poltrona reservada\n");
    printf("3 -> Poltrona comprada\n");
    printf("4 -> Poltrona bloqueada\n\n");

    for(int i=0; i<7; i++){
        for(int j=0; j<7; j++){
            printf("%d ", poltronas[i][j].status);
        }
        printf("\n");
    }
    printf("\n\n");
}

void sistemaMenu(int x){
int linha, coluna;
float soma=0;
char nome[50];

        if(x == 6){
                system("cls");
                for(int i=0; i<7; i++){
                    for(int j=0; j<7; j++){
                        soma += poltronas[i][j].valor;
                    }
                }
                printf("O valor total da bilheteria é: R$ %.2f\n", soma);

        }else if(x == 5){
            printf("Digite o nome do cliente, considere letras maiúsculas e minúsculas.\n> ");
            scanf("%s", nome);
            for(int i=0; i<7; i++){
                for(int j=0; j<7; j++){
                    if( strcmp(nome, poltronas[i][j].nome) == 0 ){
                        printf("Existe um cliente com o nome %s.\n", nome);
                        printf("A poltrona do cliente está na linha %d e coluna %d.\n",i+1,j+1);
                    }
                }
            }

        }else{
            printf("Escolha a linha da poltrona:\n> ");
            scanf("%d", &linha);
            linha-=1;
            printf("Escolha a coluna da poltrona:\n> ");
            scanf("%d", &coluna);
            coluna-=1;

            system("cls");
            if(poltronas[linha][coluna].status != 1){
                printf("Poltrona inválida para uso.\n");
            }else{
                printf("Digite o seu nome:\n> ");
                scanf(" %49[^\n]", nome);

                if(x == 2){
                    strcpy(poltronas[linha][coluna].nome, nome);
                    poltronas[linha][coluna].status = 3;
                    printf("Poltrona reservada com sucesso!\n");
                }
                else if(x == 3){
                    strcpy(poltronas[linha][coluna].nome, nome);
                    poltronas[linha][coluna].status = 2;
                    poltronas[linha][coluna].valor = 21.99;
                    printf("Poltrona comprada com sucesso!\n");
                }
                else if(x == 4){
                    strcpy(poltronas[linha][coluna].nome, nome);
                    poltronas[linha][coluna].status = 4;
                    printf("Poltrona bloqueada com sucesso!\n");
                }

            }
        }



}

void salvarDados(){

        FILE *fptr;
        fptr = fopen("poltronas.bin", "wb");
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                fprintf(fptr, "%s %.2f %d ", poltronas[i][j].nome, poltronas[i][j].valor, poltronas[i][j].status);
            }
                fprintf(fptr, "\n");
        }
        fclose(fptr);


}

void carregarDados(){

    FILE *fptr = fopen("poltronas.bin", "rb");
    if(fptr == NULL){
        fclose(fptr);
        fptr = fopen("poltronas.bin", "wb");
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                poltronas[i][j].status= 1;
                poltronas[i][j].valor= 0.0;
                strcpy(poltronas[i][j].nome, "Livre");
                fprintf(fptr, "%s %f %d ", poltronas[i][j].nome, poltronas[i][j].valor, poltronas[i][j].status);
            }
                fprintf(fptr, "\n");
        }
        fclose(fptr);

    }else{
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                fscanf(fptr, "%s %f %d", &poltronas[i][j].nome, &poltronas[i][j].valor, &poltronas[i][j].status);
            }
                fprintf(fptr, "\n");
        }
    }

}
