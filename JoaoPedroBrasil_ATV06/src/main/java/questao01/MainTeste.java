/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao01;

import principal.Grafo;

/**
 *
 * @author joaop
 */
public class MainTeste {

    public static void main(String[] args) {
        // Criar um grafo de strings
        Grafo<String> grafo = new Grafo<>();

        // Adicionar vértices
        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        // Adicionar arestas
        grafo.adicionarAresta(1.0, "A", "B", true);
        grafo.adicionarAresta(1.0, "A", "C", true);
        grafo.adicionarAresta(1.0, "B", "D", true);

        // Imprimir vértices e seus adjacentes
        System.out.println("Vértices e seus adjacentes:");
        grafo.imprimirAdjacentes();

        // Pesquisar vértice
        grafo.pesquisarVertice("A"); // Vértice encontrado: A
        grafo.pesquisarVertice("E"); // Vértice não encontrado: E

        // Pesquisar aresta
        grafo.pesquisarAresta("A", "B"); // Aresta encontrada entre A e B
        grafo.pesquisarAresta("B", "A"); // Aresta não encontrada entre B e A
        grafo.pesquisarAresta("A", "D"); // Vértices não encontrados para a pesquisa de aresta entre A e D

        // Imprimir todos os vértices
        grafo.imprimirVertices();
        
        // Remover vértice
        grafo.removerVertice("D");
        
        // Imprimir todos os vértices
        grafo.imprimirVertices();

        // Imprimir vértices e seus adjacentes após a remoção
        System.out.println("\nVértices e seus adjacentes após remover C:");
        grafo.imprimirAdjacentes();
        
        // Verificação de ciclos com e sem Ordenação Topologica
        grafo.temCiclo();                       // Nenhum ciclo detectado.
        grafo.temCicloOrdenacaoTopologica();    // Nenhum ciclo detectado.
        grafo.adicionarAresta(1.0, "B", "C", true);
        grafo.adicionarAresta(1.0, "C", "A", true);
        grafo.temCiclo();                       // Ciclo detectado!
        grafo.temCicloOrdenacaoTopologica();    // Ciclo detectado!
    }
    

}
