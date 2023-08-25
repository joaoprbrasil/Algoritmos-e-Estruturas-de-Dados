#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <string.h>

int tempo=0, ativos=0;
// Tempo do relógio em minutos
// Ativos =  quantos onibus estão no terminal

typedef struct {
  char data[8];
  char hora[4];
  char placa[7];
  int tempo_espera;
  int tempo_limite;
} onibus;

typedef onibus elemento;

const elemento VL_NULO = {};

#include "tadfila.h"
#include "ler_dados.h"

elemento plataforma[30];
tipo_fila fila_rodoviaria;

int hora_para_minutos(elemento e){
  int num = atoi(e.hora);
  int hr = num / 100;         
  int min = num - hr*100;
  return hr*60 + min;
}

void verificacao_de_chegada(){
  elemento elem = VL_NULO;

//---------------------------------------------------
  primeiroFila(fila_rodoviaria, &elem);
  int min_onibus = hora_para_minutos(elem);
  
  if(min_onibus ==  tempo){
    for(int i=0; i<30; i++){
      if(plataforma[i].tempo_limite == 0){
        elem.tempo_limite = rand() % 30 + 1;
        plataforma[i] = elem;
        i+=30;
      }
    }
    sairElemento(&fila_rodoviaria, &elem);
    ativos++;
//---------------------------------------------------
    primeiroFila(fila_rodoviaria, &elem); 
    min_onibus = hora_para_minutos(elem);
    if(min_onibus ==  tempo){
    tempo--;
    }
  }

}

void inicializa_plataforma(){
  for (int i = 0; i <30; i++){
      plataforma[i] = VL_NULO;
  }
}

void limpa_tela(){
  system("clear");
}

void imprimir_relogio(){
    printf("                        Relógio: ");
    
    int hr=0, temporario;
    temporario =  tempo;
    while(temporario>59){
      hr++;
      temporario-=60;
    }

//---------------------------------------------------
    if(hr<10){
      printf("0%d:", hr);
    }else{
      printf("%d:", hr);
    }                              
    if(temporario<10){
      printf("0%d\n\n", temporario);
    }else{
      printf("%d\n\n", temporario);
    }
  
}

void imprimir_terminal(){
  printf("ÔNIBUS NO TERMINAL:  %d\n\n", ativos);

  elemento elem;
  printf("Plataforma |    Placa    | Horario de Chegada | Tempo de espera (min) \n");
  
  for(int i=0; i<30; i++){
    if(plataforma[i].tempo_limite != 0){
      if(i<9){
  printf("    0%d     |   %s   |       %c%c:%c%c        |          %d           \n", i+1, plataforma[i].placa, plataforma[i].hora[0], plataforma[i].hora[1], plataforma[i].hora[2], plataforma[i].hora[3], plataforma[i].tempo_espera++  );
      }else{
  printf("    %d     |   %s   |       %c%c:%c%c        |          %d           \n", i+1, plataforma[i].placa, plataforma[i].hora[0], plataforma[i].hora[1], plataforma[i].hora[2], plataforma[i].hora[3], plataforma[i].tempo_espera++  );
      }

      // Se o onibus bater o tempo limite então ambos são zerados e ele vai embora (ativo--)
      if(plataforma[i].tempo_espera == plataforma[i].tempo_limite){
        plataforma[i].tempo_limite = 0;
        plataforma[i].tempo_espera = 0;
        ativos--;
      }
    }else{
      if(i<9){
        printf("    0%d     |   -------   |       --:--        |          --           \n", i+1);
      }else{
        printf("    %d     |   -------   |       --:--        |          --           \n", i+1);
      }
    }
    
  }
  
  
}



int main() {
  criarFila(&fila_rodoviaria); //INICIALIZAÇÃO DA FILA DO PROGRAMA
  carregar_fila(&fila_rodoviaria);//IMPORT DOS DADOS DO ARQUIVO TXT

  while(tempo < 1440){
    
    limpa_tela();
    imprimir_relogio();
    imprimir_terminal();
    
    verificacao_de_chegada();
    
    sleep(1);
    tempo++;
  }

}