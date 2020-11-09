import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

public class BFS implements Finder{
    private final Grid grid;

    public BFS(Grid grid){
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
        Queue<Node> nodesQueue = new Queue<>();
        HashMap<Node, Boolean> visited = new HashMap<>();
        nodesQueue.enqueue(a);
        visited.put(a, true);
        while (!nodesQueue.isEmpty()){
            Node current = nodesQueue.dequeue();
            for(Node node: getNeighbours(current)){
                if(!node.isWalkAble) continue;
                if(!visited.containsKey(node)){
                    nodesQueue.enqueue(node);
                    node.previousNode = current;
                    visited.put(node, true);
                }
                if(node.compareTo(b) == 0) return path(node, a);
                StdDraw.filledSquare(current.x + 0.5,current.y + 0.5,0.5);
                //StdDraw.pause(Constants.MEDIUM_SPEED);
            }
        }

        return new ArrayList<>();
    }
}
