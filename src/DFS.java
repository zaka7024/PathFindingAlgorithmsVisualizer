import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DFS implements Finder{
    private final Grid grid;

    public DFS(Grid grid){
        this.grid = grid;
    }

    public ArrayList<Node> getNeighbours(Node a){
        ArrayList<Node> nodes = new ArrayList<>();
        if(a.x + 1 < grid.length) nodes.add(grid.getNodeAt(a.x + 1, a.y));
        if(a.x - 1 >= 0) nodes.add(grid.getNodeAt(a.x - 1, a.y));
        if(a.y + 1 < grid.length) nodes.add(grid.getNodeAt(a.x, a.y + 1));
        if(a.y - 1 >= 0) nodes.add(grid.getNodeAt(a.x, a.y - 1));
        return nodes;
    }

    private ArrayList<Node> path(Node end, Node start){
        ArrayList<Node> path = new ArrayList<>();
        if (end == null) return path;
        path.add(end);
        Node a = end;
        while (a.previousNode!= null && a.previousNode.compareTo(start) != 0){
            a = a.previousNode;
            path.add(a);
        }

        Collections.reverse(path);
        return path;
    }

    @Override
    public List<Node> findPath(Node a, Node b){
        Stack<Node> nodesStack = new Stack<>();
        HashMap<Node, Boolean> visited = new HashMap<>();
        nodesStack.push(a);
        visited.put(a, true);
        while (!nodesStack.isEmpty()){
            Node current = nodesStack.pop();
            for(Node node: getNeighbours(current)){
                if(!node.isWalkAble) continue;
                if(!visited.containsKey(node)){
                    nodesStack.push(node);
                    node.previousNode = current;
                    visited.put(node, true);
                }
                if(node.compareTo(b) == 0) return path(node, a);
                StdDraw.filledSquare(current.x + 0.5,current.y + 0.5,0.5);
            }
        }

        return new ArrayList<>();
    }
}
