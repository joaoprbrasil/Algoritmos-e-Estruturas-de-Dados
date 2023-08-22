#include <stdio.h>
#include <stdlib.h>

typedef int elemento;

#include "TadPilha.h"

void imprime_pilha(tipo_pilha p) {
  ptrNodo aux = p.topo;
  elemento e;
  
  printf("Elementos da pilha:\n");
  while (aux != NULL) {
    e = aux->elem;
    printf("%d\n", e);
    aux = aux->prox;
  }
}

// Sem usar a TAD
void inverte_pilha(tipo_pilha *p) {
  ptrNodo atual = p->topo;
  ptrNodo proximo = NULL;
  ptrNodo anterior = NULL;

  while (atual != NULL) {
    proximo = atual->prox;
    atual->prox = anterior;
    anterior = atual;
    atual = proximo;
  }
  
  p->topo = anterior;
}

void excluir_metade2(tipo_pilha *p){
  ptrNodo aux = p->topo, nodoExc;
  int metade = p->tamanho(&p)/2;

  for(int i=1; i<metade; i++){
    aux = aux->prox;
  }
  nodoExc = aux->prox;
  aux->prox = NULL;
  aux = nodoExc->prox;
  
  while(aux!=NULL){
    free(nodoExc);
    nodoExc = aux;
    aux = aux->prox;
    p->tamanho--;
  }

}

void excluir_metade1(tipo_pilha *p){
  ptrNodo aux;
  int metade = p->tamanho/2;

  for(int i=1; i<=metade; i++){
    aux = p->topo;
    p->topo = p->topo->prox;
    free(aux);
    p->tamanho--;
  }
  
}

int main(void) {
	tipo_pilha p;
  elemento e;

  printf("PILHA\n\n");
  criarPilha(&p);
  
	empilharElemento(&p,1);
  empilharElemento(&p,2);
  empilharElemento(&p,3);
  empilharElemento(&p,4);
  empilharElemento(&p,5);
  empilharElemento(&p,6);

  excluir_metade2(&p);
  imprime_pilha(p);
  excluir_metade2(&p);
  imprime_pilha(p);


  /*
        printf("\n\nTestes:\n");
      //Sai o topo da pilha
        desempilharElemento(&p, &e);
        printf("%d\n", e);
      //Novo topo da pilha
        obterTopo(p, &e);
        printf("%d\n", e);
  */
  
  return 0;
}