/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author 20221SI0013
 */
public class NoLista<Elemento> { 
    private Elemento elem;
    private NoLista<Elemento> proximo;
    private NoLista<Elemento> anterior;
    
    public NoLista(){
        this.elem = null;
    }
    
    public NoLista(Elemento novoElem){
        this.elem = novoElem;
    }

    public Elemento getElem() {
        return elem;
    }

    public void setElem(Elemento elem) {
        this.elem = elem;
    }

    public NoLista<Elemento> getProximo() {
        return proximo;
    }

    public void setProximo(NoLista<Elemento> proximo) {
        this.proximo = proximo;
    }

    public NoLista<Elemento> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoLista<Elemento> anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return "NoLista{" + "Elemento = " + elem + ", Próximo = " + proximo + ", Anterior = " + anterior + '}';
    }
}

