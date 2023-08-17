#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct{
  char nome[100];
  int codigo;
  int idade;
  int codPai;
  float bonus;
  int indicacoes;
}cliente;
const cliente VL_NULO = {"", 0, 0, 0,0};

typedef cliente elemento;
/*VARIÁVEL AUXILIAR*/
int quantidade = 0;

bool comparaelementos(elemento code1 , elemento code2 ){
  return (code1.codigo == code2.codigo);
}

/*ARQUIVOS CABEÇALHOS*/
#include "TadTree.h"
tree arvore;

#include "FuncoesAux.h"
//#include "FuncoesAux-LINHAGEM.h"
#include "Persistencia.h"


int calculofilhos(tree pai){
  if(pai!=NULL){
      int esquerda = calculofilhos(pai->esq);
      int direita = calculofilhos(pai->dir);
    return 1 + esquerda;
      
  }else{
    return 0;
  }
}

int adicionabonus(tree dad){
  float bonus=0, antbonus=0; int filhos;
  ptrNodo ascendencia = NULL;
  filhos = calculofilhos(dad)-1;
    for(int i=5;i>5-filhos;i--){
      bonus = bonus + i;
    }
  dad->info.bonus = bonus;
  for(int j=1;j<quantidade;j++){
    elemento elem = VL_NULO;
    elem.codigo = j;
    ascendencia = localiza(arvore,elem);
     filhos = calculofilhos(ascendencia)-1;
    for(int i=5;i>5-filhos;i--){
      antbonus = antbonus + i;
    }
    ascendencia->info.bonus = antbonus;
    antbonus=0;
  }
  return 1;
}

void Mostrabonus(tree *arv){
  ptrNodo aux = *arv;
  ptrNodo pai = NULL;
  elemento dad = VL_NULO;
  codigo(&dad.codigo);
  while(!localiza(*arv,dad)){
    screenclear();
    printf("Codigo incorreto.\n");
    Listar(*arv);
    printf("\nDigite o código correto.  \n");
    codigo(&dad.codigo);
  }
  pai = (localiza(aux,dad));  
  strcpy(dad.nome, pai->info.nome);
  dad.idade = pai->info.idade;
  dad.bonus = pai->info.bonus;
  printf("Nome:        %s\nCódigo:      %d\nIndicações:   %d\nBonus:       R$ %.2f\n>>",dad.nome, dad.codigo,dad.indicacoes,dad.bonus);
}


int main(void) {
  define(&arvore);
  arvore = carregarDados(&arvore, "dados.txt");
  char op;
  int cod;
  do{
    screenclear();
    printf("\n_________________________________\n");
    printf("|                               |\n");
    printf("|         CURSO TOP             |\n");
    printf("|     %d Alunos cadastrados      |\n",quantidade);
    printf("|_______________________________|\n");
    menu();
    printf(">>  ");
    op = getchar();
  if(vazia(arvore) && op!='1'){
    screenclear();
    printf("Cadastre algum cliente!");
    screenpause();
  }else{
     switch(op){
       case '1':
          cadastrar(&arvore);
          screenpause();
          break;
       case '2':
          Listar(arvore);
          screenpause();
          break;
       case '3':
          listarIndicador(arvore);
          screenpause();
          break;
       case '4':
          Mostrabonus(&arvore);
          screenpause();
          break;
       case '5':
          salvarDados(arvore, "dados.txt");
      }
  }
  }while(true);
  
}