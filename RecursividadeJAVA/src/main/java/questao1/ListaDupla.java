/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author joaop
 * @param <Elemento>
 */




public class ListaDupla<Elemento> {
    private No<Elemento> primeiro;
    private No<Elemento> ultimo;
    private int tamanho;
    
    public ListaDupla() {
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
        if(this.primeiro == null){
            this.primeiro = aux;
            this.ultimo = aux;
            this.tamanho++;
            return 1;
        }else{
            aux.setAnterior(this.ultimo);
            this.ultimo.setProximo(aux);
            this.ultimo = aux;
            this.tamanho++;
            return 1;
        }
    }
    
    public boolean remover(Elemento elem){
        No<Elemento> aux = this.primeiro;
        for(int i=0; i<this.getTamanho(); i++){
            if(aux.getElem().equals(elem)){
                if(this.tamanho == 1){
                    this.primeiro = null;
                    this.ultimo = null;
                }
                if(aux == primeiro){
                    this.primeiro = aux.getProximo();
                    this.primeiro.setAnterior(null);
                    aux.setProximo(null);
                }else if(aux == ultimo){
                    this.ultimo = aux.getAnterior();
                    this.ultimo.setProximo(null);
                }else{
                    aux.setProximo(aux.getProximo());
                }
                aux = null;
                this.tamanho--;
                return true;
            }
        }
        return false;
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
        No aux = this.primeiro;
        for(int i=0; i < posicao; i++){
            if(aux.getProximo() != null){
                aux = aux.getProximo();
            }
        }
        return aux;
    }

}

class No<Elemento> { 
    private Elemento elem;
    private No<Elemento> proximo;   // dur
    private No<Elemento> anterior;  // esq 
    
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

    public No<Elemento> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<Elemento> anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return "No{" + "Elememto = " + elem + ", Próximo = " + proximo + ", Anterior = " + anterior + '}';
    }
}
