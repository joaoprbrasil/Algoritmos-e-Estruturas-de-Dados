package fila;

/**
 *
 * @author joaop
 */
class No<Elemento> { 
    private Elemento elem;
    private No<Elemento> proximo; 
    
    public No(){
        this.elem = null;
    }
    
    public No(Elemento novoElem){
        this.elem = novoElem;
    }

    public Elemento getElem() {
        return elem;
    }

    public void setElem(Elemento elem) {
        this.elem = elem;
    }

    public No<Elemento> getProximo() {
        return proximo;
    }

    public void setProximo(No<Elemento> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "No{" + "Elememto = " + elem + ", Próximo = " + proximo + ", Anterior = " + '}';
    }
}


public class Fila<Elemento> {
    private No<Elemento> primeiro;
    private No<Elemento> ultimo;
    private int tamanho;
    
    public Fila() {
        this.tamanho = 0;
    }

    public No<Elemento> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(No<Elemento> primeiro) {
        this.primeiro = primeiro;
    }

    public No<Elemento> getUltimo() {
        return ultimo;
    }

    public void setUltimo(No<Elemento> ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public int inserir(Elemento novoElem){
        No<Elemento> aux = new No<Elemento>(novoElem);
        if(this.tamanho == 0){
            this.primeiro = aux;
            this.ultimo = aux;
            this.tamanho++;
        }else{
            this.ultimo.setProximo(aux);
            this.ultimo = aux;
            this.tamanho++;
        }
        return 1;
    }
    
    public boolean remover(){
        switch (this.tamanho) {
            case 1:
                this.ultimo = null;
                this.primeiro = null;
                this.tamanho--;
                return true;
            case 0:
                return false;
            default:
                this.primeiro = this.primeiro.getProximo();
                this.tamanho--;
                return true;
        }
    }
    
    public void imprimir() {
        No<Elemento> atual = primeiro;
        while (atual != null) {
            System.out.println(atual.getElem());
            atual = atual.getProximo();
        }
    }
    
    public No buscar(Elemento elem){
        No<Elemento> aux;
        if(this.tamanho>0){
            aux = this.primeiro;
            while(aux != null){
                if(elem.equals(aux.getElem()))
                    return aux;
                aux = aux.getProximo();
            }
        }
        return null;
    }
  
    public No get(int posicao){
        if(posicao <= tamanho){
            No aux = this.primeiro;
            for(int i=0; i<posicao; i++){
                if(aux.getProximo() != null){
                    aux = aux.getProximo();
                }
            }
            return aux;
        }else{
            return null;
        }
    }
    
}
