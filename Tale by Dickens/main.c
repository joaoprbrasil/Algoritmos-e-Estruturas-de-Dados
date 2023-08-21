#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct no {
    char palavra[50];
    struct no *direita;
    struct no *esquerda;
} No;

  void imprimeArvore(No *raiz, int nivel) {
    if (raiz == NULL) {
        return;
    }
    for (int i = 0; i < nivel; i++) {
        printf("|  ");
    }
    if(raiz->palavra > 0 )
      printf("|-->%s\n", raiz->palavra);
    imprimeArvore(raiz->esquerda, nivel + 1);
    imprimeArvore(raiz->direita, nivel + 1);
    sleep(1);
}

No *criaNo() {
    No *novoNo = (No*)malloc(sizeof(No));
    novoNo->esquerda = NULL;
    novoNo->direita = NULL;
    return novoNo;
}



int main(void) {
    FILE* arquivo;
    char linha[100];
    char* palavra;
    int palavras_lidas = 0, palavras_na_linha = 0;
    int linhas =0;
  
    No *raiz = criaNo();
    No *atual = raiz;
   
    arquivo = fopen("tale.txt", "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    while (fgets(linha, sizeof(linha), arquivo)) {
        palavra = strtok(linha, " \n\"''.,;:?!-+[]()/*#%$_");
        if(palavra != NULL) {
           // Primeira palavra da linha.
          if(palavras_na_linha == 0) {
            strcpy(atual->palavra, palavra);
            palavras_na_linha ++;
          }
        }

          palavra = strtok(NULL, " \n\"''.,;:?!-+[]()/*#%$_");
          if (palavra != NULL) { //printf("%s ", palavra);   //segunda palavra da linha
            
            No *aux = criaNo();
            strcpy(aux->palavra, palavra);
            palavras_na_linha++;
            atual->esquerda = aux;
            atual = atual->esquerda;

            palavra = strtok(NULL, " \n\"''.,;:?!-+[]()/*#%$_");
            
            while (palavra != NULL) {
              palavra = strtok(NULL, " \n\"''.,;:?!-+[]()/*#%$_");
              if (palavra != NULL) {//printf("%s ", palavra); //resto da frase
                    aux = criaNo();
                    strcpy(aux->palavra, palavra);
                    palavras_na_linha ++;
                    atual->esquerda = aux;
                    atual = atual->esquerda;
                  }          
            }
          }
        

      No* aux = raiz;
      while(aux->direita != NULL){
          aux = aux->direita;
      }
      aux->direita = criaNo();
      atual = aux->direita;
      
      linhas++;
      palavras_lidas += palavras_na_linha;
      palavras_na_linha = 0;
    }
  
  printf("Linhas:  %d\nPalavras lidas:  %d\n", linhas, palavras_lidas);
  

    imprimeArvore(raiz, 0);


  
    return 0;
}