package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joaop
 * @param <K>
 * @param <V>
 */

public class TabelaHash<K,V> {
    private int tamanho;
    private int elementosInseridos;
    private ListaDupla<Entrada<K,V>>[] tabela;

    public TabelaHash() {
        this(10); // Tamanho Padrão
    }

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.elementosInseridos = 0;
        this.tabela = new ListaDupla[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaDupla<>();
        }
    }
    
    public void inserir(K chave, V valor) {
        int indice = funcaoHash(chave, tamanho);
        Entrada<K,V> novaEntrada = new Entrada<>(chave, valor);
        tabela[indice].inserir(novaEntrada);
        elementosInseridos++;
        if ((double) elementosInseridos / tamanho >= 0.7) {
            redimensionarTabela();
        }
    }
    
    public int funcaoHash(K chave, int tamanho) {
        double A = 0.6180339887;

        if (chave instanceof Integer) {
            int chaveInt = (int) chave;
            return (int) (tamanho * ((chaveInt * A) % 1));
            
            
        } else if (chave instanceof String) {
            String chaveString = (String) chave;
            int somaCaracteres = 0;

            for (int i = 0; i < chaveString.length(); i++) {
                somaCaracteres += (int) chaveString.charAt(i);
            }

            return (int) (tamanho * ((somaCaracteres * A) % 1));
        } else {
            throw new IllegalArgumentException("Chave inválida");
        }
    }

    
    private void redimensionarTabela() {
        int novoTamanho = tamanho * 2;
        ListaDupla<Entrada<K,V>>[] novaTabela = new ListaDupla[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new ListaDupla<>();
        }
        for (int i = 0; i < tamanho; i++) {
            ListaDupla<Entrada<K,V>> lista = tabela[i];
            for (Entrada<K,V> entrada : lista) {
                int novoIndice = funcaoHash(entrada.getChave(), novoTamanho);
                novaTabela[novoIndice].inserir(entrada); 
            }
        }
        tamanho = novoTamanho;
        tabela = novaTabela;
    }

    public V remover(K chave) {
        int indice = funcaoHash(chave, tamanho);
        ListaDupla<Entrada<K,V>> lista = tabela[indice];
        for (Entrada<K,V> entrada : lista) {
            if (entrada.getChave().equals(chave)) {
                V valorRemovido = entrada.getValor();
                lista.remover(entrada);
                return valorRemovido;
            }
        }
        return null;
    }
    
    public V buscar(K chave) {
        int indice = funcaoHash(chave, tamanho);
        ListaDupla<Entrada<K,V>> lista = tabela[indice];
        for (Entrada<K,V> entrada : lista) {
            if (entrada.getChave().equals(chave)) {
                return entrada.getValor();
            }
        }
        return null;
    }

    public void imprimir() {
        System.out.println("Tabela Hash");
        for (int i = 0; i < tamanho; i++) {
            ListaDupla<Entrada<K,V>> lista = tabela[i];
            System.out.print("Slot " + i + ": ");
            if (lista.getTamanho() == 0) {
                System.out.println(" ");
            } else {
                for (Entrada<K,V> entrada : lista) {
                    System.out.print("(" + entrada.getChave() + ", " + entrada.getValor() + ") ");
                }
                System.out.println();
            }
        }
    }
    
    public boolean contemChave(K chave) {
        int indice = funcaoHash(chave, tamanho);
        ListaDupla<Entrada<K, V>> lista = tabela[indice];
        for (Entrada<K, V> entrada : lista) {
            if (entrada.getChave().equals(chave)) {
                return true;
            }
        }
        return false;
    }


    private static class Entrada<K,V> {
        private final K chave;
        private final V valor;
        public Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
        public K getChave() {
            return chave;
        }
        public V getValor() {
            return valor;
        }
    }
}