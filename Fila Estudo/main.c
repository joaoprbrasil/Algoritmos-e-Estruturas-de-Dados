#include <stdio.h>
#include <stdlib.h>

typedef int elemento;

#include "TadFila.h"

void imprime_fila(tipo_fila f) {
  ptrNodo aux = f.inicio;
  elemento e;
  
  printf("Elementos da fila:\n");
  while(aux!=NULL){
    e = aux->elem;
    printf("%d\n", e);
    aux = aux->prox;
  }
}
/*
ptrNodo aux;
elemento e;
 sairElemento( f, e); O elemento saí da fila mas ainda possuo o elemento
*/
void exclui_metade_1(tipo_fila *f){
  ptrNodo aux;
  int metade =  f->tamanho/2;

  for(int i=1; i<=metade; i++){
    aux = f->inicio;
    f->inicio = f->inicio->prox;
    free(aux);
    f->tamanho--;
  }
  
}


void exclui_metade_2(tipo_fila *f){
  ptrNodo nodo_excluido, aux = f->inicio;
  int metade =  f->tamanho/2;

  for(int i=1; i<=metade; i++){
    aux = aux->prox;
  }
  nodo_excluido = aux->prox;
  aux->prox = NULL;
  aux = nodo_excluido;
  
  while(aux!=NULL){
    nodo_excluido = aux;
    aux = aux->prox;
    free(nodo_excluido);
    f->tamanho--;
  }
  
}


int main(void) {
	tipo_fila f;
  elemento e;

  printf("FILA\n\n");
  criarFila(&f);

  entrarElemento(&f, 1);
  entrarElemento(&f, 2);
  entrarElemento(&f, 3);
  entrarElemento(&f, 4);
  entrarElemento(&f, 5);
  entrarElemento(&f, 6);

  exclui_metade_2(&f);
  imprime_fila(f);

  /*
      //Sai o primeiro da fila
        primeiroFila(f, &e);
        printf("%d\n", e);
        sairElemento(&f, &e);
      //Novo primeiro da fila
        primeiroFila(f, &e);
        printf("%d\n", e);
  */
  

  
  return 0;
}