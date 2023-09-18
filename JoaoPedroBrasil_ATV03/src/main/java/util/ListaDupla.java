/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


/**
 *
 * @author joaop
 * @param <Elemento>
 */

public class ListaDupla<Elemento> {
    private NoLista<Elemento> primeiro;
    private NoLista<Elemento> ultimo;
    private int tamanho;
    
    public ListaDupla() {
        this.tamanho = 0;
    }

    public NoLista<Elemento> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoLista<Elemento> primeiro) {
        this.primeiro = primeiro;
    }

    public NoLista<Elemento> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NoLista<Elemento> ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public boolean inserir(Elemento novoElem){
        NoLista<Elemento> aux = new NoLista<Elemento>(novoElem);
        if(this.primeiro == null){
            this.primeiro = aux;
            this.ultimo = aux;
            this.tamanho++;
            return true;
        }else{
            aux.setAnterior(this.ultimo);
            this.ultimo.setProximo(aux);
            this.ultimo = aux;
            this.tamanho++;
            return true;
        }
    }
    
    public boolean remover(Elemento elem){
        NoLista<Elemento> aux = this.primeiro;
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
                this.tamanho--;
                return true;
            }
        }
        return false;
    }
    
    public void imprimir() {
        NoLista<Elemento> atual = primeiro;
        while (atual != null) {
            System.out.println(atual.getElem());
            atual = atual.getProximo();
        }
    }
    
    public NoLista buscar(Elemento elem){
        NoLista<Elemento> aux;
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
    
    public NoLista get(int posicao){
        NoLista aux = this.primeiro;
        for(int i=0; i < posicao; i++){
            if(aux.getProximo() != null){
                aux = aux.getProximo();
            }
        }
        return aux;
    }
    
    public int getElemento(int posicao){
        NoLista aux = this.primeiro;
        for(int i=0; i < posicao; i++){
            if(aux.getProximo() != null){
                aux = aux.getProximo();
            }
        }
        return (int) aux.getElem();
    }
}
