import java.util.*;

class Graph {
    private Node[] nodes;
    private ArrayList<ArrayList<Node>> component = new ArrayList<ArrayList<Node>>();
    private ArrayList<Node> comp = new ArrayList<Node>();
    private int counter = 0;

    public Graph(Node[] nodes) {
        this.nodes = nodes;
    }

    public void printNeighbors() {
        for (Node n1 : nodes) {
            String s = n1.toString() + ": ";
            for (Node n2 : n1.getNeighbors()) {
                s += n2.toString() + " ";
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }

    public Node[] getNodes() { return nodes; }

    private static Graph buildExampleGraph() {
        // ukeoppgave
        Node[] nodes = new Node[7];
        for (int i = 0; i < 7; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0].addNeighbor(nodes[1]);
        nodes[0].addNeighbor(nodes[2]);
        nodes[1].addNeighbor(nodes[2]);
        nodes[2].addNeighbor(nodes[3]);
        nodes[2].addNeighbor(nodes[5]);
        nodes[3].addNeighbor(nodes[4]);
        nodes[4].addNeighbor(nodes[5]);
        nodes[5].addNeighbor(nodes[6]);
        return new Graph(nodes);
    }

    private static Graph buildRandomSparseGraph(int numberofV, long seed) {
        // seed brukes av java.util.Random for å generere samme sekvens for samme frø
        // (seed) og numberofV
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    private static Graph buildRandomDenseGraph(int numberofV, long seed) {
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < numberofV * numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addNeighbor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    private static Graph buildRandomDirGraph(int numberofV, long seed) {
        java.util.Random tilf = new java.util.Random(seed);
        int tilfeldig1 = 0, tilfeldig2 = 0;
        Node[] nodes = new Node[numberofV];

        for (int i = 0; i < numberofV; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < 2 * numberofV; i++) {
            tilfeldig1 = tilf.nextInt(numberofV);
            tilfeldig2 = tilf.nextInt(numberofV);
            if (tilfeldig1 != tilfeldig2)
                nodes[tilfeldig1].addSuccessor(nodes[tilfeldig2]);
        }
        return new Graph(nodes);
    }

    public void DFS(Node s) {
        comp.add(s);
        //System.out.println(s);
        s.visit();
        for (Node n : s.getNeighbors()) {
            if (n.isVisited() == false) {
                this.DFS(n);
            }
        }
    }

    public void DFSFull() {
        for (Node n : this.nodes) {
            if (n.isVisited() == false) {
                  counter++;
                  DFS(n);
                  component.add(comp);
                  comp = new ArrayList<Node>();
            }
        }
    }

    public int numberOfComponents() {
        DFSFull();
        int antall = counter;
        counter = 0;
        return antall;



        /*for (Node n : nodes)
            if (!n.isVisited()) {
                DFS(n);
                counter++;
            }

        return counter;*/
    }

    public Graph transformDirToUndir() {
        /*
        1. Opprett tom array med plass til like mange noder som original
        2. Opprett nye noder med samme labels som originalen og legg dem i den nye lista
         */


        // Oppgave 1B
        // TODO

        // Oppretter tom liste
        Node[] liste = new Node[nodes.length];

        // Legger til noder med labels
        for (Node n : nodes) {
            Node nyNode = new Node(n.getLabel());
            liste[nyNode.getLabel()] = nyNode;
        }

        // Finner rettede naboer
        for (Node n : nodes) {
            for (Node nabo : n.getNeighbors()) {
                liste[n.getLabel()].addNeighbor(liste[nabo.getLabel()]);
            }
        }

        // Gjør rettet til urettet
        for (Node n : liste) {
            n.visit();
            for (Node nabo : n.getNeighbors()) {
                if (!nabo.isVisited()) {
                    nabo.addNeighbor(n);
                }
            }
        }

        return new Graph(liste);
    }

    public boolean isConnected() {
        /*
        for (Node n : nodes) {
            for (Node nabo : n.getNeighbors()) {
                nabo.addNeighbor(n);
                if (n.getNeighbors() < getNeighbors().size);
            }

        }
        // Oppgave 1C
        return false; // for at prekoden skal kompilere
         */

        return numberOfComponents() == 1;

    }

    public Graph biggestComponent () {
        //1D

        DFSFull();

        ArrayList<Node> s = component.get(0);
        for(ArrayList<Node> i : component){
            if (i.size() > s.size()) {
                s = i;
            }
        }
        Node[] noder = new Node[s.size()];
        for (int i = 0; i < s.size(); i++){
            noder[i] = new Node(s.get(i).getLabel());
        }

        for (int i = 0; i < s.size(); i++) {
            for (Node n : s.get(i).getNeighbors())
            noder[i].addNeighbor(noder[s.indexOf(n)]);
        }
        return new Graph(nodes);
    }

    public int[][] buildAdjacencyMatrix () {
        int[][] nabomatrise = new int[nodes.length][nodes.length];

        for (Node n: nodes){
            for (Node nabo : n.getNeighbors()) {
                nabomatrise[n.getLabel()][nabo.getLabel()] = 1;
            }
        }

        return nabomatrise;
    }

    public static void main (String[]args){
        //Graph graph = buildExampleGraph();
        //graph = buildRandomSparseGraph(11, 201909202359L);
        //graph.printNeighbors();
        //System.out.println("");
        //graph = buildRandomDenseGraph(15, 201909202359L);
        //graph.printNeighbors();
        //System.out.println();
        //Graph urettet = graph.transformDirToUndir();
        //urettet.printNeighbors();
        Node[] nodes = new Node[7];
        for (int i = 0; i < 7; i++) {
            nodes[i] = new Node(i);
        }
        nodes[0].addSuccessor(nodes[1]);
        nodes[0].addSuccessor(nodes[2]);
        nodes[1].addSuccessor(nodes[2]);
        nodes[2].addSuccessor(nodes[3]);
        nodes[2].addSuccessor(nodes[5]);
        nodes[3].addSuccessor(nodes[4]);
        nodes[4].addSuccessor(nodes[5]);
        //nodes[5].addSuccessor(nodes[6]);
        Graph rettet = new Graph(nodes);
        rettet.printNeighbors();

        System.out.println();
        Graph urettet = rettet.transformDirToUndir();
        urettet.printNeighbors();
        System.out.println();

        System.out.println(rettet.numberOfComponents());

        System.out.println();
        rettet.biggestComponent().printNeighbors();

        System.out.println("");
        System.out.println("Nabo matrise:");

        int[][] matrix = urettet.buildAdjacencyMatrix();

        for (int[] a : matrix) {
             for (int b : a) {
                   System.out.print(b + " ");
             }
             System.out.println();
       }

    }
}
