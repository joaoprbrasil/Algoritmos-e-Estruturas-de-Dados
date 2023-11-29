/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author joaop
 * @param <TIPO>
 */
public class Grafo<TIPO> {
    private final ArrayList<Vertice<TIPO>> vertices;
    private final ArrayList<Aresta<TIPO>> arestas;
    
    public Grafo(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }
    
    public void adicionarVertice(TIPO dado){
        Vertice<TIPO> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim, boolean direcionado) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);

        if (!direcionado) {
            // Se o grafo não é direcionado, adicionamos uma aresta na direção oposta
            Aresta<TIPO> arestaNaoDirecionada = new Aresta<>(peso, fim, inicio);
            inicio.adicionarArestaEntrada(arestaNaoDirecionada);
            fim.adicionarArestaSaida(arestaNaoDirecionada);
            this.arestas.add(arestaNaoDirecionada);
        }
    }
    
    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    
    public void buscaEmLargura(){
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(!fila.isEmpty()){
            Vertice<TIPO> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestasSaida().size(); i++){
                Vertice<TIPO> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ //se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
    
    public void removerVertice(TIPO dado) {
        // Encontrar o vértice com o dado especificado
        Vertice<TIPO> verticeRemover = getVertice(dado);

        // Verificar se o vértice foi encontrado
        if (verticeRemover != null) {
            // Remover todas as arestas que envolvem o vértice a ser removido
            removerArestasDoVertice(verticeRemover);

            // Remover o vértice da lista de vértices
            this.vertices.remove(verticeRemover);
            System.out.println("Vértice " + dado + " removido.");
        } else {
            System.out.println("Vértice não encontrado: " + dado);
        }
    }

    private void removerArestasDoVertice(Vertice<TIPO> vertice) {
        // Remover todas as arestas de entrada
        for (Aresta<TIPO> arestaEntrada : vertice.getArestasEntrada()) {
            arestaEntrada.getInicio().getArestasSaida().remove(arestaEntrada);
            this.arestas.remove(arestaEntrada);
        }

        // Remover todas as arestas de saída
        for (Aresta<TIPO> arestaSaida : vertice.getArestasSaida()) {
            arestaSaida.getFim().getArestasEntrada().remove(arestaSaida);
            this.arestas.remove(arestaSaida);
        }
    }

    public void removerAresta(TIPO dadoInicio, TIPO dadoFim) {
        // Encontrar os vértices de início e fim da aresta a ser removida
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);

        // Verificar se os vértices foram encontrados
        if (inicio != null && fim != null) {
            // Encontrar a aresta correspondente
            Aresta<TIPO> arestaRemover = null;
            for (Aresta<TIPO> aresta : arestas) {
                if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                    arestaRemover = aresta;
                    break;
                }
            }

            // Verificar se a aresta foi encontrada
            if (arestaRemover != null) {
                // Remover a aresta das listas de arestas dos vértices
                inicio.getArestasSaida().remove(arestaRemover);
                fim.getArestasEntrada().remove(arestaRemover);

                // Remover a aresta da lista de arestas do grafo
                this.arestas.remove(arestaRemover);
            } else {
                System.out.println("Aresta não encontrada entre " + dadoInicio + " e " + dadoFim);
            }
        } else {
            System.out.println("Vértices não encontrados para a aresta entre " + dadoInicio + " e " + dadoFim);
        }
    }
    
    public boolean pesquisarVertice(TIPO dado) {
        for (Vertice<TIPO> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                System.out.println("Vértice encontrado: " + dado);
                return true;
            }
        }

        System.out.println("Vértice não encontrado: " + dado);
        return false;
    }
    
    public boolean pesquisarAresta(TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);

        if (inicio != null && fim != null) {
            for (Aresta<TIPO> aresta : arestas) {
                if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                    System.out.println("Aresta encontrada entre " + dadoInicio + " e " + dadoFim);
                    return true;
                }
            }
            System.out.println("Aresta não encontrada entre " + dadoInicio + " e " + dadoFim);
            return false;
        } else {
            System.out.println("Vértices não encontrados para a pesquisa de aresta entre " + dadoInicio + " e " + dadoFim);
            return false;
        }
    }
    
    public ArrayList<Vertice<TIPO>> obterAdjacentes(TIPO dado) {
        Vertice<TIPO> vertice = getVertice(dado);

        if (vertice != null) {
            ArrayList<Vertice<TIPO>> adjacentes = new ArrayList<>();
            for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
                adjacentes.add(aresta.getFim());
            }
            return adjacentes;
        } else {
            System.out.println("Vértice não encontrado: " + dado);
            return null;
        }
    }


    public void imprimirAdjacentes() {
        for (Vertice<TIPO> vertice : vertices) {
            System.out.print("Vértice " + vertice.getDado() + " -> Adjacentes: ");
            ArrayList<Vertice<TIPO>> adjacentes = obterAdjacentes(vertice.getDado());

            if (adjacentes != null && !adjacentes.isEmpty()) {
                for (Vertice<TIPO> adjacente : adjacentes) {
                    System.out.print(adjacente.getDado() + " ");
                }
            } else {
                System.out.print("Nenhum adjacente");
            }

            System.out.println();
        }
    }
    
    public void imprimirVertices() {
        System.out.println("Lista de vértices:");
        for (Vertice<TIPO> vertice : vertices) {
            System.out.println(vertice.getDado());
        }
    }
    
    // Verificação de ciclo sem usar a lógica de Ordenação Topologica
    public boolean temCiclo() {
        Set<Vertice<TIPO>> visitados = new HashSet<>();
        Set<Vertice<TIPO>> pilha = new HashSet<>();

        for (Vertice<TIPO> vertice : vertices) {
            if (temCicloAux(vertice, visitados, pilha)) {
                System.out.println("Ciclo detectado!");
                return true;
            }
        }

        System.out.println("Nenhum ciclo detectado.");
        return false;
    }

    private boolean temCicloAux(Vertice<TIPO> vertice, Set<Vertice<TIPO>> visitados, Set<Vertice<TIPO>> pilha) {
        if (pilha.contains(vertice)) {
            return true; // Ciclo detectado
        }

        if (visitados.contains(vertice)) {
            return false; // Já visitado, não há ciclo
        }

        visitados.add(vertice);
        pilha.add(vertice);

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            if (temCicloAux(aresta.getFim(), visitados, pilha)) {
                return true; // Ciclo detectado na recursão
            }
        }

        pilha.remove(vertice);
        return false;
    }
    
    
    // Verificação de ciclo usando a lógica de Ordenação Topologica
    public boolean temCicloOrdenacaoTopologica() {
        Set<Vertice<TIPO>> visitados = new HashSet<>();
        Stack<Vertice<TIPO>> pilha = new Stack<>();

        for (Vertice<TIPO> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                if (temCicloOrdenacaoTopologicaAux(vertice, visitados, pilha)) {
                    System.out.println("Ciclo detectado!");
                    return true;
                }
            }
        }

        System.out.println("Nenhum ciclo detectado.");
        return false;
    }

    private boolean temCicloOrdenacaoTopologicaAux(Vertice<TIPO> vertice, Set<Vertice<TIPO>> visitados, Stack<Vertice<TIPO>> pilha) {
        visitados.add(vertice);
        pilha.push(vertice);

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            Vertice<TIPO> vizinho = aresta.getFim();

            if (!visitados.contains(vizinho)) {
                if (temCicloOrdenacaoTopologicaAux(vizinho, visitados, pilha)) {
                    return true;
                }
            } else if (pilha.contains(vizinho)) {
                System.out.println("Ciclo detectado!");
                return true;
            }
        }

        pilha.pop();
        return false;
    }

    public ArrayList<Vertice<TIPO>> ordenacaoTopologica() {
        Stack<Vertice<TIPO>> pilha = new Stack<>();
        Set<Vertice<TIPO>> visitados = new HashSet<>();

        for (Vertice<TIPO> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                ordenacaoTopologicaAux(vertice, visitados, pilha);
            }
        }

        ArrayList<Vertice<TIPO>> resultado = new ArrayList<>();
        while (!pilha.isEmpty()) {
            resultado.add(pilha.pop());
        }

        return resultado;
    }

    private void ordenacaoTopologicaAux(Vertice<TIPO> vertice, Set<Vertice<TIPO>> visitados, Stack<Vertice<TIPO>> pilha) {
        visitados.add(vertice);

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            Vertice<TIPO> vizinho = aresta.getFim();

            if (!visitados.contains(vizinho)) {
                ordenacaoTopologicaAux(vizinho, visitados, pilha);
            }
        }

        pilha.push(vertice);
    }

    public void encontrarCaminho(TIPO inicio, TIPO destino) {
        Vertice<TIPO> verticeInicio = this.getVertice(inicio);
        Vertice<TIPO> verticeDestino = this.getVertice(destino);

        if (verticeInicio != null && verticeDestino != null) {
            Stack<Vertice<TIPO>> caminho = new Stack<>();
            HashSet<Vertice<TIPO>> visitados = new HashSet<>();

            if (dfs(verticeInicio, verticeDestino, caminho, visitados)) {
                System.out.println("Caminho encontrado:");

                
                int con=0;
                while (!caminho.isEmpty() && con < caminho.size()-1) {
                    System.out.print(caminho.pop().getDado() + " ");
                }
                System.out.println(destino);
            } else {
                System.out.println("Caminho não encontrado.");
            }
        } else {
            System.out.println("Vértices de início ou destino não encontrados.");
        }
    }

    private boolean dfs(Vertice<TIPO> atual, Vertice<TIPO> destino, Stack<Vertice<TIPO>> caminho, HashSet<Vertice<TIPO>> visitados) {
        visitados.add(atual);

        if (atual.equals(destino)) {
            caminho.push(atual); // Adiciona o vértice de destino ao caminho
            return true;  // Chegou ao destino
        }

        for (Aresta<TIPO> aresta : atual.getArestasSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!visitados.contains(proximo)) {
                if (dfs(proximo, destino, caminho, visitados)) {
                    caminho.push(atual); // Adiciona o vértice atual ao caminho
                    return true;
                }
            }
        }

        return false;
    }
    
    public void floydWarshall(TIPO origem, TIPO destino) {
        int numVertices = vertices.size();
        double[][] distancias = new double[numVertices][numVertices];
        int[][] proximo = new int[numVertices][numVertices];

        // Inicialização da matriz de distâncias e da matriz de próximos vértices
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                    proximo[i][j] = i;
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                    proximo[i][j] = -1;
                }
            }
        }

        // Preenchimento da matriz com as distâncias conhecidas
        for (Aresta<TIPO> aresta : arestas) {
            int indiceInicio = vertices.indexOf(aresta.getInicio());
            int indiceFim = vertices.indexOf(aresta.getFim());
            distancias[indiceInicio][indiceFim] = aresta.getPeso();
            proximo[indiceInicio][indiceFim] = indiceFim;
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        proximo[i][j] = proximo[i][k];
                    }
                }
            }
        }

        // Imprimir caminho entre origem e destino
        imprimirCaminho(origem, destino, distancias, proximo);
    }

    private void imprimirCaminho(TIPO origem, TIPO destino, double[][] distancias, int[][] proximo) {
        int indiceOrigem = vertices.indexOf(getVertice(origem));
        int indiceDestino = vertices.indexOf(getVertice(destino));

        if (distancias[indiceOrigem][indiceDestino] == Double.POSITIVE_INFINITY) {
            System.out.println("Não há caminho entre " + origem + " e " + destino);
        } else {
            System.out.println("Menor caminho entre " + origem + " e " + destino + ":");
            List<TIPO> caminho = new ArrayList<>();
            int atual = indiceOrigem;
            while (atual != indiceDestino) {
                caminho.add(vertices.get(atual).getDado());
                atual = proximo[atual][indiceDestino];
            }
            caminho.add(destino);

            // Imprimir a lista do caminho
            for (int i = 0; i < caminho.size() - 1; i++) {
                TIPO verticeAtual = caminho.get(i);
                TIPO proximoVertice = caminho.get(i + 1);
                double peso = 0;

                // Iterar sobre a lista de arestas para encontrar a aresta entre verticeAtual e proximoVertice
                for (Aresta<TIPO> aresta : arestas) {
                    if (aresta.getInicio().getDado().equals(verticeAtual) && aresta.getFim().getDado().equals(proximoVertice)) {
                        peso = aresta.getPeso();
                        break;
                    }
                }

                System.out.printf("%s - %.1f km -> ", verticeAtual, peso);
            }
            System.out.println(caminho.get(caminho.size() - 1));


            System.out.println("Distancia total: " + distancias[vertices.indexOf(getVertice(origem))][vertices.indexOf(getVertice(destino))] + " km.");
        }
    }
    
}