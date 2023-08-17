/*FUNÇÕES AUXILIARES*/
void screenclear(){
  system("clear");
}

void screenpause(){
  getchar(); getchar();
}

void codigo(int *code){
  printf("Informe o Código do indicador\n>>  ");
  fflush(stdin);
  scanf("%d", code);


}

/*FUNÇÕES DE UTILIZAÇÃO*/
void Listar(tree arv){
  if(arv!=NULL){
    printf("_________________________________\n");
    printf("Nome:            %s\n", arv->info.nome);
    printf("Idade:           %d\n", arv->info.idade);
    printf("Código:          %d\n", arv->info.codigo);
    if(arv->info.codPai!=0)
      printf("Indicação:       %d\n", arv->info.codPai);
    printf("Bonus:           R$ %.2f\n", arv->info.bonus);
    printf("Indicados:       %d\n", arv->info.indicacoes);
    printf("Valor à pagar:   R$ %.2f\n", 320 - arv->info.bonus);
    Listar(arv->esq);
    Listar(arv->dir);
  }
}
void listarIndicador(tree arv){
  int cod;
  printf("Digite o código do indicador:  ");
  scanf("%d", &cod);
  elemento pai = VL_NULO;
  ptrNodo father;
  pai.codigo = cod;
    if(localiza(arv,pai)){
      father = localiza(arv,pai);
      Listar(father->esq);
    }else{
      printf("Código inválido.\n");
    }
  
}


void menu() {
  printf("|                               |\n");
  printf("| 1) Cadastrar cliente.         |\n");
  printf("| 2) Listar clientes.           |\n");
  printf("| 3) Listar por indicação.      |\n");
  printf("| 4) Mostrar bônus de cliente.  |\n");
  printf("| 5) Salvar dados.              |\n");
  printf("|_______________________________|\n");
}

void cadastrar(tree *arv){
  ptrNodo father = NULL, help = NULL;
  elemento e = VL_NULO;
  elemento pai=VL_NULO;
  
  printf("Informe o Nome do Cliente\n>>  ");
  fflush(stdin);
  scanf(" %99[^\n]", e.nome);
  printf("Informe a idade do Cliente\n>>  ");
  fflush(stdin);
  scanf("%d", &e.idade);
  while(e.idade<14){
    screenclear();
    printf("Você é muito novo para o CURSO TOP.\nEspere %d anos.\n", 14 - e.idade);
    screenpause();
    return;
  }
  e.codigo = ++quantidade;
  
  if(vazia(*arv)){
    criaRaiz(arv,e);
    printf("Cliente adicionado!");
    return;
  }else if(*arv != NULL){
    codigo(&pai.codigo);
    
    if(pai.codigo==0){
      criarNodoDir(percorrerDir(&arvore),e);
      printf("\nCliente adicionado!\n");
      return;
    }
    
    while(!localiza(*arv,pai)){
      screenclear();
      printf("Código de indicador incorreto.\n");
      Listar(*arv);
      printf("\nDigite o código correto.\n");
      codigo(&pai.codigo);
    }
    
    father = localiza(*arv,pai);
    e.codPai = pai.codigo;
    
    if(father->esq == NULL){
      adicionarEsqPai(*arv,pai,e);
      ++father->info.indicacoes;
      adicionabonus(father);
      printf("Cliente indicado por %s adicionado!\n", father->info.nome);
      return;
    }else{
      criarNodoDir(percorrerDir(&father->esq),e);
      ++father->info.indicacoes;
      adicionabonus(father);
      printf("Cliente indicado por %s adicionado!\n", father->info.nome);
      return;
    }
    
  }
}

