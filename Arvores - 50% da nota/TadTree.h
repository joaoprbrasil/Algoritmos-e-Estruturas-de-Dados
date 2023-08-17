/*implementções de uma Árvore*/
typedef struct nodo *ptrNodo;
struct nodo{
              elemento info;
              ptrNodo 	esq;
              ptrNodo   dir;
         };
typedef ptrNodo tree;


void define(tree *t) {
      *t = NULL;
}

void criaRaiz(tree *t, elemento item) {
   ptrNodo no;
   no = (ptrNodo)malloc(sizeof(struct nodo));
   no->esq = NULL;
   no->dir = NULL;
   no->info = item;
   *t = no;
}

int vazia (tree t) {
   return (t==NULL);
}

void criarNodoDir(tree t, elemento item) {
	    ptrNodo  no;
	    no = (ptrNodo)malloc(sizeof(struct nodo));
      no->esq = NULL;
      no->dir = NULL;
      no->info = item;
      t->dir = no;
}

void criarNodoEsq(tree t, elemento item) {
	    ptrNodo  no;
	    no = (ptrNodo)malloc(sizeof(struct nodo));
      no->esq = NULL;
      no->dir = NULL;
      no->info = item;
      t->esq = no;
}

ptrNodo localiza(tree t, elemento item) {
   ptrNodo locesq, locdir;
   locesq = NULL;
   locdir = NULL;
   if (comparaelementos(t->info,item) != true) {
      if (t->esq != NULL)
         locesq = localiza(t->esq,item);
      if (locesq == NULL) {
         if (t->dir != NULL)
            locdir = localiza(t->dir,item);
         if (locdir == NULL)
            return NULL;
         else
            return locdir;
      }
      else
         return locesq;
   } else
      return t;
}

int adicionarDirPai(tree t, elemento item_pai, elemento item) {
   tree pai, no;
   pai = localiza(t,item_pai);
   if (pai!=NULL) {
      if (pai->dir!=NULL)
         return 0;
      else {
         no = (ptrNodo)malloc(sizeof(struct nodo));
         no->esq = NULL;
         no->dir = NULL;
         no->info = item;
         pai->dir = no;
         return 1;
      }
   }
}

int adicionarEsqPai(tree t, elemento item_pai, elemento item) {
   tree pai, no;
   pai = localiza(t,item_pai);
   if (pai!=NULL)
      if (pai->esq!=NULL)
         return 0;
      else {
         no = (ptrNodo)malloc(sizeof(struct nodo));
         no->esq = NULL;
         no->dir = NULL;
         no->info = item;
         pai->esq =no;
         return 1;
      }
}

int alturatree(tree *t){
  if(t==NULL){
    return -1;
  }else{
    
  }
} 

ptrNodo percorrerDir(tree *t){
  ptrNodo aux = *t;
  while(1){
    if(aux->dir!= NULL){
      aux = aux->dir;
    }else{
      return aux;
    }
  }
}

ptrNodo percorrerEsq(tree *t){
  ptrNodo aux = *t;
  while(1){
    if(aux->esq!= NULL){
      aux = aux->esq;
    }else{
      return aux;
    }
  }
}