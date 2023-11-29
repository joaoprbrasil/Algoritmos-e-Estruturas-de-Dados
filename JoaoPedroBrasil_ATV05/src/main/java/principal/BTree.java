package principal;

import java.util.ArrayList;
import java.util.List;

public class BTree<T extends Comparable<T>> {

    
    private int T;
    private No raiz;
    
    public BTree(int t) {
        this.T = t;
        this.raiz = new No();
        this.raiz.folha = true;
    }



//----------------------------
    public class No {
        int qntChaves;
        List<T> chave;
        List<No> filho;
        boolean folha = true;

        public No() {
            this.qntChaves = 0;
            this.chave = new ArrayList<>(2 * T - 1);
            this.filho = new ArrayList<>(2 * T);
        }

        public int indiceChave(T key) {
            for (int i = 0; i < this.qntChaves; i++) {
                if (chave.get(i).equals(key)) {
                    return i;
                }
            }
            return -1;
        }
    }
//----------------------------
    public No buscar(T chave){
        if(contemChave(chave)){
            System.out.println("Elemento encontrado");
            return buscar(raiz, chave);
        }else{
            System.out.println("Não foi possível encontrar o elemento.");
            return null;
        }
    }
    
    private No buscar(No aux, T chave) {
        int i = 0;
        while (i < aux.qntChaves && chave.compareTo(aux.chave.get(i)) > 0) {
            i++;
        }
        if (i < aux.qntChaves && chave.compareTo(aux.chave.get(i)) == 0) {
            return aux;
        } else if (aux.folha) {
            return null;
        } else {
            return buscar(aux.filho.get(i), chave);
        }
    }
//----------------------------
    private void dividir(No noPai, int pos, No aux) {
        No novoNo = new No();
        novoNo.folha = aux.folha;
        novoNo.qntChaves = T - 1;

        for (int j = 0; j < T - 1; j++) {
            novoNo.chave.add(aux.chave.get(j + T));
        }

        if (!aux.folha) {
            for (int j = 0; j < T; j++) {
                novoNo.filho.add(aux.filho.get(j + T));
            }
        }

        aux.qntChaves = T - 1;
        noPai.filho.add(pos + 1, novoNo);
        noPai.chave.add(pos, aux.chave.get(T - 1));
        noPai.qntChaves++;
    }
//----------------------------
    public void inserir(final T chave) {
        No root = raiz;
        if (root.qntChaves == 2 * T - 1) {
            No novaRaiz = new No();
            raiz = novaRaiz;
            novaRaiz.folha = false;
            novaRaiz.qntChaves = 0;
            novaRaiz.filho.add(0, root);
            dividir(novaRaiz, 0, root);
            inserirSemOverflow(novaRaiz, chave);
        } else {
            inserirSemOverflow(root, chave);
        }
    }
//----------------------------
    private void inserirSemOverflow(No aux, T chave) {
        int i = aux.qntChaves - 1;

        if (aux.folha) {
            while (i >= 0 && chave.compareTo(aux.chave.get(i)) < 0) {
                i--;
            }
            aux.chave.add(i + 1, chave);
            aux.qntChaves++;
        } else {
            while (i >= 0 && chave.compareTo(aux.chave.get(i)) < 0) {
                i--;
            }
            i++;
            No filho = aux.filho.get(i);
            if (filho.qntChaves == 2 * T - 1) {
                dividir(aux, i, filho);
                if (chave.compareTo(aux.chave.get(i)) > 0) {
                    i++;
                }
            }
            inserirSemOverflow(aux.filho.get(i), chave);
        }
    }
//----------------------------
    public boolean contemChave(T chave) {
        return buscar(raiz, chave) != null;
    }
//----------------------------
    public void imprimir() {
        if (raiz == null) {
            System.out.println("Árvore vazia.");
        } else {
            imprimirRecursivamente(raiz, 0);
        }
    }
//----------------------------
    private void imprimirRecursivamente(No aux, int level) {
        System.out.print("Nível " + level + ": ");
        for (int i = 0; i < aux.qntChaves; i++) {
            System.out.print(aux.chave.get(i) + " ");
        }
        System.out.println();

        if (!aux.folha) {
            for (int i = 0; i <= aux.qntChaves; i++) {
                imprimirRecursivamente(aux.filho.get(i), level + 1);
            }
        }
    }
//----------------------------   
    public void remover(T chave) {
        if (raiz == null) {
            System.out.println("Árvore vazia.");
            return;
        }
        
        if(contemChave(chave)){
            removerRecursivamente(raiz, chave);

            if (raiz.qntChaves == 0 && !raiz.folha) {
                raiz = raiz.filho.get(0);
            }
            System.out.println("Elemento removido.");
        }else{
            System.out.println("Elemento não encontrado.");
        }
    }
//----------------------------
    private void removerRecursivamente(No aux, T chave) {
        int indiceAux = aux.indiceChave(chave);

        if (indiceAux != -1) {
            if (aux.folha) {
                removerNaFolha(aux, indiceAux);
            } else {
                removerSemFolha(aux, indiceAux);
            }
        } else {
            int i = 0;
            while (i < aux.qntChaves && chave.compareTo(aux.chave.get(i)) > 0) {
                i++;
            }

            if (aux.filho.get(i).qntChaves < T) {
                rebalancearEstrutura(aux, i);
            }

            if (i == aux.qntChaves || chave.compareTo(aux.chave.get(i)) < 0) {
                removerRecursivamente(aux.filho.get(i), chave);
            } else {
                removerSemFolha(aux, i);
            }
        }
    }
//----------------------------
    private void removerNaFolha(No node, int index) {
        node.chave.remove(index);
        node.qntChaves--;
    }
//----------------------------
    private void removerSemFolha(No aux, int index) {
        T key = aux.chave.get(index);

        if (aux.filho.get(index).qntChaves >= T) {
            T anterior = getAnterior(aux.filho.get(index));
            aux.chave.set(index, anterior);
            removerRecursivamente(aux.filho.get(index), anterior);
        } else if (aux.filho.get(index + 1).qntChaves >= T) {
            T successor = getSucessor(aux.filho.get(index + 1));
            aux.chave.set(index, successor);
            removerRecursivamente(aux.filho.get(index + 1), successor);
        } else {
            fusao(aux, index);
            removerRecursivamente(aux.filho.get(index), key);
        }
    }
//----------------------------
    private T getAnterior(No aux) {
        while (!aux.folha) {
            aux = aux.filho.get(aux.qntChaves);
        }
        return aux.chave.get(aux.qntChaves - 1);
    }
//----------------------------
    private T getSucessor(No aux) {
        while (!aux.folha) {
            aux = aux.filho.get(0);
        }
        return aux.chave.get(0);
    }
//----------------------------
    private void rebalancearEstrutura(No aux, int indice) {
        if (indice != 0 && aux.filho.get(indice - 1).qntChaves >= T) {
            emprestarChaveDoAnterior(aux, indice);
        } else if (indice != aux.qntChaves && aux.filho.get(indice + 1).qntChaves >= T) {
            emprestarChaveDoProximo(aux, indice);
        } else {
            if (indice != aux.qntChaves) {
                fusao(aux, indice);
            } else {
                fusao(aux, indice - 1);
            }
        }
    }
//----------------------------
    private void emprestarChaveDoAnterior(No aux, int indice) {
        No filho = aux.filho.get(indice);
        No filhoAnterior = aux.filho.get(indice - 1);

        filho.chave.add(0, aux.chave.get(indice - 1));
        if (!filho.folha) {
            filho.filho.add(0, filhoAnterior.filho.remove(filhoAnterior.qntChaves));
        }

        aux.chave.set(indice - 1, filhoAnterior.chave.remove(filhoAnterior.qntChaves - 1));
        filho.qntChaves++;
        filhoAnterior.qntChaves--;
    }
//----------------------------
    private void emprestarChaveDoProximo(No aux, int indice) {
        No filho = aux.filho.get(indice);
        No filhoSucessor = aux.filho.get(indice + 1);

        filho.chave.add(aux.chave.get(indice));
        if (!filho.folha) {
            filho.filho.add(filhoSucessor.filho.remove(0));
        }

        aux.chave.set(indice, filhoSucessor.chave.remove(0));
        filho.qntChaves++;
        filhoSucessor.qntChaves--;
    }
//----------------------------
    private void fusao(No aux, int indice) {
        No filho = aux.filho.get(indice);
        No filhoSucessor = aux.filho.get(indice + 1);

        filho.chave.add(aux.chave.remove(indice));
        filho.qntChaves++;

        for (int i = 0; i < filhoSucessor.qntChaves; i++) {
            filho.chave.add(filhoSucessor.chave.get(i));
            filho.qntChaves++;
        }

        if (!filho.folha) {
            for (int i = 0; i <= filhoSucessor.qntChaves; i++) {
                filho.filho.add(filhoSucessor.filho.get(i));
            }
        }

        aux.filho.remove(indice + 1);
        aux.qntChaves--;
    }
    
}
