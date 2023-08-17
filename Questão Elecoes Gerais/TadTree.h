typedef struct nodo *ptrNodo;
struct nodo {
              elemento info;
              ptrNodo 	esq;
              ptrNodo   dir;
         };

typedef ptrNodo tree;

int comparaelementos(elemento item1, elemento item2){
   if(item1.NR_ZONA == item2.NR_ZONA)
     return 1;
   else 
     return 0;
}


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
   if (comparaelementos(t->info,item) != 0) {
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