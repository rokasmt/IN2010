import java.lang.reflect.Array;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

class Node {
    private int label;
    private boolean visited = false;
    public List<Node> neighbors = new LinkedList<Node>();

    public Node(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void addNeighbor(Node n) {
        // legger til en uretta kant fra this til n
        if (!neighbors.contains(n)) {
            neighbors.add(n);
            n.addNeighbor(this);
        }
    }

    public void addSuccessor(Node n) {
        // legger til en retta kant fra this til n
        if (!neighbors.contains(n)) {
            neighbors.add(n);
        }
    }

    public String toString() {
        return Integer.toString(label);
    }
}
