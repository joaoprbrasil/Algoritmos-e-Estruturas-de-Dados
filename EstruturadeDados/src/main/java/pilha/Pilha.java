/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilha;

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


public class Pilha<Elemento> {
    private No<Elemento> ultimo;
    private int tamanho;
    
    public Pilha() {
        this.tamanho = 0;
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

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public int inserir(Elemento novoElem){
        No<Elemento> aux = new No<Elemento>(novoElem);
        if(this.tamanho == 0){
            this.ultimo = aux;
            this.tamanho++;
            return 1;
        }else{
            aux.setProximo(ultimo);
            this.ultimo = aux;
            this.tamanho++;
            return 1;
        }
    }
    
    public boolean remover(){
        if(this.tamanho > 0){
            No<Elemento> aux = this.ultimo;
            this.ultimo = aux.getProximo();
            this.tamanho--;
            return true;
        }else{
            return false;
        }
    }
    
    public void imprimir() {
        No<Elemento> atual = ultimo;
        while (atual != null) {
            System.out.println(atual.getElem());
            atual = atual.getProximo();
        }
    }
    
    public No buscar(Elemento elem){
        No<Elemento> aux;
        if(this.tamanho>0){
            aux = this.ultimo;
            while(aux != null){
                if(elem.equals(aux.getElem()))
                    return aux;
                aux = aux.getProximo();
            }
        }
        return null;
    }
    
    
    public No get(int posicao){
        No aux = this.ultimo;
        for(int i=0; i<posicao; i++){
            if(aux.getProximo() != null){
                aux = aux.getProximo();
            }
        }
        return aux;
    }
 
}
