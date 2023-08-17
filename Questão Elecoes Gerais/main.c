#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
      int  NR_ZONA;
      int  NR_SECAO;
      int  QT_APTOS;
      int  QT_COMPARECIMENTOS;
      int  QT_ABSTENCOES;
      int  NR_VOTAVEL;
      char NM_VOTAVEL[50];
      int  QT_VOTOS;
}BU;

typedef BU elemento;
const elemento VL_NULO = {0,0,0,0,0,0,"",0};

#include "TadTree.h"
tree arv;
#include "Carregar_dados.h"


//----------------------------------------------------------------------
void menu(void){
  printf("\nDados das Eleições Gerais 2022 - 2º turno para o estado do Maranhão\n");
  printf("1 ) Carregar dados do arquivo na árvore.\n");
  printf("2 ) Listar sessões por zona eleitorial.\n");
  printf("3 ) Quantidade de votos por candidato em determinada zona eleitoral.\n");
  printf("4 ) Quantidade de votos por candidado em determinada seção eleitoral.\n");
  printf("5 ) Quantidade de seções eleitorais em cada zona eleitoral.\n");
  printf("6 ) Sair do programa.\n");
  printf(">  ");
}
void limpa_tela(void){
  system("clear");
}
char escolhe_opcao() {
	return getchar();
}
void imprimeArvore(tree a, int nivel) {
    if (a != NULL){
      for (int i=1; i<=nivel;i++,printf(".."));
      printf(" %d \n",a->info.NR_ZONA);
      imprimeArvore(a->esq,nivel+1);
      imprimeArvore(a->dir,nivel+1);
    }
}
//----------------------------------------------------------------------



void imprimeZonas(tree t){
  if (t != NULL){
    printf("N° %d \n",t->info.NR_ZONA);
    imprimeZonas(t->esq);
  }
}
int listar_zona_eleitoral(tree arv, int zona){
  elemento elem;
  elem.NR_ZONA = zona;
  ptrNodo aux = arv;
  if(arv == NULL){
    return 0;
  }
  
  if (comparaelementos(arv->info, elem) == 1){
    do{
      printf("Zona eleitoral:          %d\n", aux->info.NR_ZONA);
      printf("Seção eleitoral:         %d\n", aux->info.NR_SECAO);
      printf("Quantidade de aptos:     %d\n", aux->info.QT_APTOS);
      printf("Comparecimentos:         %d\n", aux->info.QT_COMPARECIMENTOS);
      printf("Abstenções:              %d\n", aux->info.QT_ABSTENCOES);
      printf("Número votável:          %d\n", aux->info.NR_VOTAVEL);
      printf("Nome votável:            %s\n", aux->info.NM_VOTAVEL);
      printf("Quantidade de votos:     %d\n", aux->info.QT_VOTOS);
      printf("_______________________________________________\n");
      aux = aux->dir;
    }while(aux != NULL);
  }else{
    listar_zona_eleitoral(arv->esq, zona);
  }
  return 0;
}
int votos_por_zona(tree arv, int zona){
  elemento elem;
  elem.NR_ZONA = zona;
  ptrNodo aux = arv;
  int bolsonaro = 0, lula = 0;
  if(arv == NULL){
    return 0;
  }
  if (comparaelementos(arv->info, elem) == 1){
    do{
      if(aux->info.NR_VOTAVEL == 22){
        bolsonaro+=aux->info.QT_VOTOS;
      }else if(aux->info.NR_VOTAVEL == 13){
        lula+=aux->info.QT_VOTOS;;
      }
      aux = aux->dir;
    }while(aux != NULL);
    printf("Votos para o Bolsonaro:  %d\n", bolsonaro);
    printf("Votos para o Lula:  %d\n", lula);
  }else{
    votos_por_zona(arv->esq, zona);
  }
  return 0;
}

int conta_secoes(tree arv){
  int total = 0;
  ptrNodo aux = arv;

  if(arv == NULL){
    return 0;
  }
  
  if(arv != NULL){
    printf("Zona N° %d:  ", arv->info.NR_ZONA);
    do{
      total++;
      aux = aux->dir;
    }while(aux != NULL);
    printf("%d\n", total);
  }
  
  conta_secoes(arv->esq);
  
}
int imprimeSecoes(tree arv, int zona){
elemento elem;
elem.NR_ZONA = zona;
ptrNodo aux = arv;
int secao_atual = -1;
  if(aux == NULL){
    return 0;
  }
  if (comparaelementos(aux->info, elem) == 1){
    do{
      if(secao_atual != aux->info.NR_SECAO){
        printf("N° %d \n",aux->info.NR_SECAO);
        secao_atual = aux->info.NR_SECAO;
      }
      aux = aux->dir;
    }while(aux != NULL);

  }else{
    imprimeSecoes(aux->esq, zona);
  }
  return 0;
}

int votos_por_secao(tree arv, int zona, int secao){
  elemento elem;
  elem.NR_ZONA = zona;
  ptrNodo aux = arv;
  int bolsonaro = 0, lula = 0;
  if(arv == NULL){
    return 0;
  }
  if (comparaelementos(arv->info, elem) == 1){
    do{
      if( aux->info.NR_VOTAVEL == 22 && aux->info.NR_SECAO == secao ){
        bolsonaro+=aux->info.QT_VOTOS;
      }else if(aux->info.NR_VOTAVEL == 13 && aux->info.NR_SECAO == secao ){
        lula+=aux->info.QT_VOTOS;
      }
      aux = aux->dir;
    }while(aux != NULL);
    printf("Votos para o Bolsonaro:  %d\n", bolsonaro);
    printf("Votos para o Lula:  %d\n", lula);
  }else{
    votos_por_secao(arv->esq, zona, secao);
  }
}




int main(void) {
  define(&arv);
  char opcao;

  while(1){
    menu();
    opcao = escolhe_opcao();
    getchar();
    if(opcao != '1' && arv == NULL){
      limpa_tela();
      printf("Carregue os dados na árvore primeiro.\n\n");
    }else{
    switch (opcao) {
      
      case '1':                      // Carregar dados
        limpa_tela();
        carregar_dados(&arv);
        break;

      
      case '2':                      // Listar seções de uma zona
        limpa_tela();
        printf("Zonas eleitorais:\n");
        imprimeZonas(arv);
        printf("Escolha a zona eleitoral pelo número dela:\n>  ");
        int zona; scanf("%d", &zona);
        limpa_tela();
        listar_zona_eleitoral(arv, zona);
        getchar();
        break;

      
      case '3':                      // Total de votos de uma zona
        limpa_tela();
        printf("Zonas eleitorais:\n");
        imprimeZonas(arv);
        printf("Escolha a zona eleitoral pelo número dela:\n>  ");
        scanf("%d", &zona);
        limpa_tela();
        votos_por_zona(arv, zona);
        getchar();
        break;

      
      case '4':                      // Total de votos por secao
        limpa_tela();
        printf("Zonas eleitorais:\n");
        imprimeZonas(arv);
        printf("Escolha a zona eleitoral pelo número dela:\n>  ");
        scanf("%d", &zona);
        limpa_tela();
        printf("Seções eleitorais na zona N° %d:\n", zona);
        imprimeSecoes(arv, zona);
        printf("Escolha a seção eleitoral pelo número dela:\n>  ");
        int secao;
        scanf("%d", &secao);
        votos_por_secao(arv, zona, secao);
        getchar();
        break;

      
      case '5':                      // Quantidades de seções por zona
        limpa_tela();
        printf("Quantidades de seções:\n");
        conta_secoes(arv);
        break;
      case '6':
        return 0;
        break;
    }
    }
  }
}
