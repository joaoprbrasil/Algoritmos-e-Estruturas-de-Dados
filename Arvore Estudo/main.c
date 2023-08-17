#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct {
                   int num;
		} arvore_de_numeros;
typedef arvore_de_numeros elemento;

#include "TadTree.h"

void imprimeArvore(tree a, int nivel) {
    if (a != NULL){
      for (int i=1; i<=nivel;i++,printf(".."));
      printf(" %d \n",a->info.num);
      imprimeArvore(a->esq,nivel+1);
      imprimeArvore(a->dir,nivel+1);
    }
}

// WHAT DA HEEEEEEEEEEEEEEEEEEEEEEEEEEEL OH MAH GAWD NO WAYAYAYAYAYYYYYAH

int somaParNos(tree t) {
    if (t != NULL){
      if (t->info.num % 2 == 0){
        return (t->info.num + somaParNos(t->esq) + somaParNos(t->dir));
      }
      return (0 + somaParNos(t->esq) + somaParNos(t->dir));
    }else{
      return 0;
    }
}

int soma_nos(tree arv){
  int total = 0;

  if(arv != NULL){
    total++;
    if(arv->esq != NULL)
      total += soma_nos(arv->esq);
    if(arv->dir != NULL)
      total += soma_nos(arv->dir);
  }
  return total;
  
}

int soma_nos_internos(tree arv){
  int total = 0;

  if(arv != NULL){
    if(arv->esq != NULL || arv->dir != NULL)
      total++;
    total += soma_nos_internos(arv->esq);
    total += soma_nos_internos(arv->dir);
  }
  return total;
}

int completa(tree t, int p) {
   /* ou pode fazer pela equação dos slides tn = 2n - 1 */
   /* inicialmente na primeira chamada é 1 */
   if (t == NULL)
      return p;
   else {
      p++;
      if (completa(t->esq, p) == completa(t->dir, p)) 
         return p;
      else
         return 0;
   }
}

int espelhadas(tree t1, tree t2) {
   if ((t1 == NULL) && (t2 == NULL))
      return 1;
   else
      if (((t1 == NULL) && (t2 != NULL)) || ((t1 != NULL) && (t2 == NULL)))
         return 0;
      else {
         if (t1->info.num == t2->info.num) 
            /* sub-árvores com conteúdo */
            if (espelhadas(t1->esq, t2->dir) && espelhadas(t1->dir, t2->esq))
               return 1;
            else
               return 0;
         else
            return 0;
      }
}

int nivel(tree t, tree nodo, int p) {
   /* inicialmente na primeira chamada p é -1 */
  int r=0;
   if (t == NULL)
      return -1;
   else {
      p++;
      if (t->info.num == nodo->info.num) /* ou pesquisar pelo endereço (t == nodo) */
         return p;
      else{
         if (nivel(t->esq, nodo, p) > 0){
           r = nivel(t->esq, nodo, p);
            return r;
         }else 
            return (nivel(t->dir, nodo, p)); /* retorna -1 caso não ache */
      }
   }
}

void posOrd(tree t) {
   if (t != NULL) {
      posOrd(t->esq);
      /* percorre subarvore esq */
      posOrd(t->dir); /* percorre subarvore dir */
      printf("%d\n", t->info.num);
   }
}

int contaNos(tree t) {
   if (t != NULL)
      return (1 + contaNos(t->esq) + contaNos(t->dir));
   else 
      return 0;
}

int soma(tree arv){
  if(arv != NULL){
    return arv->info.num + soma(arv->esq) + soma(arv->dir);
  }
  return 0;
}


int profund(tree t) {
  int a=0,  b=0;
   if (t == NULL)
      return 0;
   else {
      a = profund(t->esq);
      b = profund(t->dir);
      if (a > b) 
         return a+1;
      else
         return b+1;
   }
}

int arvBalanceada(tree t) {
   if ((t == NULL) || ((t->esq == NULL) && (t->dir == NULL)))
       return 1;
   else   
      if (((t->esq == NULL) && (t->dir != NULL)) || ((t->esq != NULL) && (t->dir == NULL)))
         return 0;
      else
         return arvBalanceada(t->esq) && arvBalanceada(t->esq);
}

void menu(void){
  printf("1 ) Imprimir a árvore.\n");
  printf("2 ) Soma dos números.\n");
  printf("3 ) Número de nós.\n");
  printf("4 ) Número de nós internos.\n");
  printf("5 ) Soma dos números pares da arvore.\n>  ");
}

void limpa_tela(void){
  system("clear");
}

void pausa(void){
  printf("Pressione qualquer botão para continuar.\n");
  getchar(); getchar();
}

int main() {
  tree arv;
  elemento e;

  srand(time(0));
  
  define(&arv);
  int nivel = 0;
  int tamanho;
  
  printf("Digite quantos números aleátorios serão inseridos na árvore:\n>  ");
  scanf("%d", &tamanho);


  for(int i = 1; i <= tamanho; i++){
    e.num = (rand() % tamanho) + 1;
    if(vazia(arv)){
        criaRaiz(&arv, e);
    } else {
        tree aux = arv;
        while(aux != NULL){
            if(e.num <= aux->info.num){
                if(aux->dir == NULL){
                    criarNodoDir(aux, e);
                    break;
                } else {
                    aux = aux->dir;
                    nivel++;
                }
            } else {
                if(aux->esq == NULL){
                    criarNodoEsq(aux, e);
                    break;
                } else {
                    aux = aux->esq;
                    nivel++;
                }
            }
        }
        nivel = 0;
    }
}

  printf("Uma árvore aleátoria foi gerada.\n");

  while(1){
    limpa_tela();
    menu();
    int escolha;
    scanf("%d", &escolha);

    switch(escolha){
      case 1:
        printf("Elementos da árvore:\n");
        imprimeArvore(arv, 0);
        pausa();
        break;
      case 2:
        printf("\nSoma dos números: ");
        printf("%d\n", soma(arv));
        pausa();
        break;
      case 3:
        printf("\nNúmero de nós:  ");
        printf("%d\n", soma_nos(arv));
        pausa();
        break;
      case 4:
        printf("\nNúmero de nós internos:  ");
        printf("%d\n", soma_nos_internos(arv));
        pausa();
        break;
      case 5:
        printf("\nSoma Par:  ");
        printf("%d\n", somaParNos(arv));
        pausa();
        break;
      default:
        printf("Opção inválida.\n");
        pausa();
        break;
    }
  }
  
  return 0;
}

int comparaelementos(elemento item1, elemento item2) {
   return 0;
}