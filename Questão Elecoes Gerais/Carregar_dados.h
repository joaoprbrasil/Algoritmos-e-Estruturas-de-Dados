#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_LENGTH 1000

int insereNodo(ptrNodo *t, elemento e) {
  ptrNodo aux = *t;
  while (1) {
    if (comparaelementos(aux->info, e) == 1) {
      if (aux->dir == NULL) {
        ptrNodo no;
        no = (ptrNodo)malloc(sizeof(struct nodo));
        no->esq = NULL;
        no->dir = NULL;
        no->info = e;
        aux->dir = no;
        return 1;
      }
      aux = aux->dir;
    } else {
      if (aux->esq == NULL) {
        ptrNodo no;
        no = (ptrNodo)malloc(sizeof(struct nodo));
        no->esq = NULL;
        no->dir = NULL;
        no->info = e;
        aux->esq = no;
        return 1;
      }
      aux = aux->esq;
    }
  }
}

void removerAspas(char *string) {
  int i, j = 0;
  for (i = 0; string[i] != '\0'; i++) {
    if (string[i] != '"') {
      string[j] = string[i];
      j++;
    }
  }
  string[j] = '\0';
}

int carregar_dados(tree *arv) {
  FILE *fptr;
  char linha[MAX_LINE_LENGTH];
  char *token;
  elemento elem = VL_NULO;

  // Abre o arquivo
  fptr = fopen("Boletim de Urna.csv", "r");
  if (fptr == NULL) {
    printf("Erro ao abrir o arquivo.\n");
    return 1;
  }

  fgets(linha, sizeof(linha), fptr); // Pula a primeira linha

  // Lê cada linha do arquivo
  while (fgets(linha, sizeof(linha), fptr) != NULL) {
    // Obtém o primeiro token da linha (separado por ";")
    token = strtok(linha, ";");

    // Salva os campos desejados
    int contador = 0;

    while (token != NULL) {
      removerAspas(token);
      if (contador == 13) { // NR_ZONA
        elem.NR_ZONA = atoi(token);
      }
      if (contador == 14) { // NR_SECAO
        elem.NR_SECAO = atoi(token);
      }
      if (contador == 22) { // QT_APTOS
        elem.QT_APTOS = atoi(token);
      }
      if (contador == 23) { // QT_COMPARECIMENTOS
        elem.QT_COMPARECIMENTOS = atoi(token);
      }
      if (contador == 24) { // QT_ABSTENCOES
        elem.QT_ABSTENCOES = atoi(token);
      }
      if (contador == 29) { // NR_VOTAVEL
        elem.NR_VOTAVEL = atoi(token);
      }
      if (contador == 30) { // NM_VOTAVEL
        strcpy(elem.NM_VOTAVEL, token);
      }
      if (contador == 31) { // QT_VOTOS
        elem.QT_VOTOS = atoi(token);
      }

      token = strtok(NULL, ";");
      contador++;
    }

    if (*arv == NULL) {
      criaRaiz(arv, elem);
    } else {
      insereNodo(arv, elem);
    }
  }
  printf("Dados carregados com sucesso!\n\n");
  fclose(fptr);
  return 0;
}
