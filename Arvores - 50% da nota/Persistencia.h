#define MAX_LINE_LENGTH 1000

void salvar_dados_arvore(tree arv, FILE *fptr) {
  if (arv != NULL) {
    fprintf(fptr, "%s; %d; %d; %d; %.2f; %d;\n", arv->info.nome, arv->info.codigo, arv->info.idade, arv->info.codPai, arv->info.bonus, arv->info.indicacoes);
    salvar_dados_arvore(arv->esq, fptr);
    salvar_dados_arvore(arv->dir, fptr);
  }
}

void salvarDados(tree arv, const char* nome_arquivo) {
  FILE *fptr = fopen(nome_arquivo, "w");
  if (fptr == NULL) {
    printf("Erro ao abrir o arquivo %s\n", nome_arquivo);
    return;
  }
  salvar_dados_arvore(arv, fptr);
  fclose(fptr);
}

//------------------------------------------------------------------


tree carregarDados(tree *arv, const char* nome_arquivo){
  FILE *fptr = fopen(nome_arquivo, "r");
  elemento elem;
  char linha[MAX_LINE_LENGTH];
  char *token;

  int con = 0;
  while (fgets(linha, sizeof(linha), fptr) != NULL){ 
    con++;
  }
  
  fptr = fopen(nome_arquivo, "r");
  
  if (fptr == NULL) {
    printf("Erro ao abrir o arquivo %s\n", nome_arquivo);
    return;
  }else if(con>0){
    while (fgets(linha, sizeof(linha), fptr) != NULL) {
      token = strtok(linha, ";");
      int contador = 0;
      while (token != NULL) {
        if (contador == 0) {
          strcpy(elem.nome, token);
        }
        if (contador == 1) {
          elem.codigo = atoi(token);
        }
        if (contador == 2) {
          elem.idade = atoi(token);
        }
        if (contador == 3) {
          elem.codPai = atoi(token);
        }
        if (contador == 4) {
          elem.bonus = atof(token);
        }
        if (contador == 5) {
          elem.indicacoes = atoi(token);
        }
        token = strtok(NULL, ";");
        contador++;
      }
      //Inserção de elementos aq
      elemento pai=VL_NULO;
      ptrNodo father = NULL;
      
        if(*arv==NULL){
          criaRaiz(&arv, elem);
          quantidade++;
        }else if(*arv != NULL){
          pai.codigo = elem.codPai;
          if(pai.codigo==0){
            criarNodoDir( percorrerDir(&arv), elem);
            quantidade++;
          }else{
            father = localiza(arv,pai);
            elem.codPai = pai.codigo;
            if(father->esq == NULL){
              adicionarEsqPai(arv,pai,elem);
              quantidade++;
            }else{
              criarNodoDir(percorrerDir(&father->esq),elem);
              quantidade++;
            }
          } 
        }
    }
  }
  if(*arv==NULL)
    define(&arv);
  return arv;
  fclose(fptr);
}
