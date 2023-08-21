/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao3;

/**
 *
 * @author joaop
 * @param <Elemento>
 */

class NoTree<Elemento> {
    private Elemento elem;
    private NoTree<Elemento> dir;
    private NoTree<Elemento> esq; 
    
    public NoTree(){
        this.elem = null;
        this.dir = null;
        this.esq = null;
    }
    
    public NoTree(Elemento novoElem){
        this.elem = novoElem;
        this.dir = null;
        this.esq = null;
    }

    public Elemento getElem() {
        return elem;
    }

    public void setElem(Elemento elem) {
        this.elem = elem;
    }

    public NoTree<Elemento> getDir() {
        return dir;
    }

    public void setDir(NoTree<Elemento> dir) {
        this.dir = dir;
    }

    public NoTree<Elemento> getEsq() {
        return esq;
    }

    public void setEsq(NoTree<Elemento> esq) {
        this.esq = esq;
    }
}

public class Tree<Elemento extends Comparable> {
    private NoTree<Elemento> raiz;
    private int tamanho;
    
    public Tree(){
        this.raiz = null;
        this.tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    
    public void inserir(Elemento elem){
        NoTree<Elemento> aux = new NoTree<>(elem);
        if(this.raiz == null){
            this.raiz = aux;
            this.tamanho++;
        }else{
            NoTree<Elemento> atual = this.raiz;
            while(true){
                if( aux.getElem().compareTo(atual.getElem()) == -1 ){
                    if(atual.getEsq() != null ){
                        atual = atual.getEsq();
                    }else{
                        atual.setEsq(aux);
                        this.tamanho++;
                        break;
                    }
                }else{
                    if(atual.getDir() != null){
                        atual = atual.getDir();
                        
                    }else{
                        atual.setDir(aux);
                        this.tamanho++;
                        break;
                    }
                }
            }
        }
    }
    
    public NoTree<Elemento> getRaiz(){
        return raiz;
    }
    
    public void imprimir(){
        preOrdem(this.raiz);
    }
    
    public NoTree<Elemento> buscar(NoTree<Elemento> aux, Elemento elem) {
        if (aux == null) {
            return null;  // Não encontrou o elemento na árvore
        }

        if (aux.getElem().equals(elem)) {
            return aux;  // Encontrou o elemento na árvore
        } else {
            // Recursivamente buscar nas subárvores esquerda e direita
            NoTree<Elemento> encontradoEsquerda = buscar(aux.getEsq(), elem);
            NoTree<Elemento> encontradoDireita = buscar(aux.getDir(), elem);

            if (encontradoEsquerda != null) {
                return encontradoEsquerda;
            } else {
                return encontradoDireita;
            }
        }
    }

    
    public void emOrdem(NoTree<Elemento> aux){
        if(aux != null){
            emOrdem(aux.getEsq());
            System.out.println(aux.getElem());
            emOrdem(aux.getDir());
        }
    }
    
    public void preOrdem(NoTree<Elemento> aux){
        if(aux != null){
            System.out.println(aux.getElem());
            preOrdem(aux.getEsq());
            preOrdem(aux.getDir());
        }
    }
    
    public void posOrdem(NoTree<Elemento> aux){
        if(aux != null){
            posOrdem(aux.getEsq());
            posOrdem(aux.getDir());
            System.out.println(aux.getElem());
        }
    }
    
    public boolean remover(Elemento elem) {
        int tamanhoAtual = this.tamanho;
        raiz = removerRecursivo(raiz, elem);
        return tamanhoAtual != this.tamanho;
    }

    private NoTree<Elemento> removerRecursivo(NoTree<Elemento> atual, Elemento elem) {
        if (atual == null) {
            return null;
        }

        int comparacao = elem.compareTo(atual.getElem());

        if (comparacao < 0) {
            atual.setEsq(removerRecursivo(atual.getEsq(), elem));
        } else if (comparacao > 0) {
            atual.setDir(removerRecursivo(atual.getDir(), elem));
        } else {
            // Nó a ser removido encontrado

            if (atual.getEsq() == null) {
                this.tamanho--;
                return atual.getDir();
            } else if (atual.getDir() == null) {
                this.tamanho--;
                return atual.getEsq();
            }

            NoTree<Elemento> sucessor = encontrarSucessor(atual.getDir());
            atual.setElem(sucessor.getElem());
            atual.setDir(removerRecursivo(atual.getDir(), sucessor.getElem()));
            
        }

        return atual;
    }

    private NoTree<Elemento> encontrarSucessor(NoTree<Elemento> atual) {
        while (atual.getEsq() != null) {
            atual = atual.getEsq();
        }
        return atual;
    }
}
