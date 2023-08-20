#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_LENGTH 1024
#define MAX_FIELD_LENGTH 256

int beneficios, beneficios2;

typedef struct{
  char mes[9];
  char uf[5];
  char codigo_municipio[7];
  char nome_municipio[50];
  char cpf[17];
  char nis[14];
  char rgp[13];
  char nome_favorecido[50];
  float valor;

}dados_Seguro_Defeso;

typedef dados_Seguro_Defeso elemento;
const elemento VL_NULO = {"", "", "", "", "", "", "", "", 0};

#include "tadfila.h"

tipo_fila fila_seguro_defeso;

int carregar_dados(){
    
    FILE *file = fopen("202201_SeguroDefeso.csv", "r");
    if (file == NULL) {
      printf("Erro: nao foi possivel abrir o arquivo\n");
      return 0;
    }

    char line[MAX_LINE_LENGTH];
    int line_count = 0;
    elemento e = VL_NULO;

    // Lê cada linha do arquivo
    while (fgets(line, MAX_LINE_LENGTH, file)) {
      e = VL_NULO;
        // Remove o caractere '\n' da linha lida
        line[strcspn(line, "\n")] = 0;

        // Separa os campos da linha usando o caractere ';'
        char *field;
        char *token = strtok(line, ";");
        char valor[100];
        int field_count = 0;

        while (token != NULL) {
            // Processa cada campo da linha
            if (field_count == 0) {
                // Mês de referência
                field = token;
                strcpy(e.mes, field);
            } else if (field_count == 1) {
                // UF
                field = token;
                strcpy(e.uf, field);
            } else if (field_count == 2) {
                // Código do município SIAFI
                field = token;
                strcpy(e.codigo_municipio, field);
            } else if (field_count == 3) {
                // Nome do município
                field = token;
                strcpy(e.nome_municipio, field);
            } else if (field_count == 4) {
                // CPF do favorecido
                field = token;
                strcpy(e.cpf, field);
            } else if (field_count == 5) {
                // NIS do favorecido
                field = token;
                strcpy(e.nis, field);
            } else if (field_count == 6) {
                // RGP do favorecido
                field = token;
                strcpy(e.rgp, field);
            } else if (field_count == 7) {
                // Nome do favorecido
                field = token;
                strcpy(e.nome_favorecido, field);
            } else if (field_count == 8) {
                field = token;
                field[strcspn(field, "\"")] = ' '; //remove aspas do valor
                e.valor = atof(field);
            }

            entrarElemento(&fila_seguro_defeso, e);

            // Lê o próximo campo
            token = strtok(NULL, ";");
            field_count++;
        }

        line_count++;
    }

    // Fecha o arquivo
    fclose(file);
}

void calculo_seguro_defesa(float valor){

elemento e;
  int flag=0;
float valor_com_dividas = valor;

  primeiroFila(fila_seguro_defeso, &e);
  
  while(valor>e.valor || valor_com_dividas>e.valor){
    if(!strcmp(e.nome_favorecido, "")){
      primeiroFila(fila_seguro_defeso, &e);
    }
    //-------------------
    if(e.valor>valor){
      flag++;
    }
    
    if(e.valor > 0){
      if(flag==0){
        valor -= e.valor;
        beneficios++;
      }
        valor_com_dividas -= e.valor;
        beneficios2++;
      
    }else if(e.valor < 0){
        valor_com_dividas -= e.valor;
    }
    //-------------------
    
    e = VL_NULO;
    sairElemento(&fila_seguro_defeso, &e);
    
  }

  printf("\nBenificiados:  %d\n", beneficios);
  printf("Beneficiados se as dívidas fossem pagas:  %d\n", beneficios2);
  
}

int main() {
    criarFila(&fila_seguro_defeso);
    carregar_dados();

    float valor;

    printf("Digite o valor disponível pelo governo para pagamento do Seguro :\n>  ");
    scanf("%f", &valor);
    calculo_seguro_defesa(valor);

    return 0;
}

