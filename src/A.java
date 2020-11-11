import edu.princeton.cs.algs4.StdDraw;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A implements Finder{// A*
    private final ArrayList<Node> closed;
    private final ArrayList<Node> open;
    private final Grid grid;

    public A(Grid grid){
        closed = new ArrayList<>();
        open = new ArrayList<>();
        this.grid = grid;
    }

    public Node getLowestCostNode(){
        if(open.isEmpty()) return null;
        Node x = open.get(0);
        for (Node a: open){
            if(a.fCost < x.fCost) x = a;
        }
        return x;
    }

    public List<Node> getNeighbours(Node a){
        ArrayList<Node> neighbours = new ArrayList<>();
        if(a.x + 1 < grid.length) neighbours.add(grid.getNodeAt(a.x + 1, a.y)); // right
        if(a.x - 1 >= 0) neighbours.add(grid.getNodeAt(a.x - 1, a.y)); // left
        if(a.y + 1 < grid.length) neighbours.add(grid.getNodeAt(a.x, a.y + 1)); // up
        if(a.y - 1 >= 0) neighbours.add(grid.getNodeAt(a.x, a.y - 1)); // down
        if(a.x + 1 < grid.length && a.y + 1 < grid.length) neighbours.add(grid.getNodeAt(a.x + 1, a.y + 1)); // right up
        if(a.x - 1 >= 0 && a.y + 1 < grid.length) neighbours.add(grid.getNodeAt(a.x - 1, a.y + 1)); // left up
        if(a.x + 1 < grid.length && a.y - 1 >= 0) neighbours.add(grid.getNodeAt(a.x + 1, a.y - 1)); // right down
        if(a.x - 1 > 0 && a.y - 1 > 0) neighbours.add(grid.getNodeAt(a.x - 1, a.y - 1)); // left down
        return neighbours;
    }

    @Override
    public List<Node> findPath(Node a, Node b){
        a.gCost = 0;
        a.hCost = grid.calculateDistance(a, b);
        a.fCost = a.gCost + a.hCost;
        open.add(a);

        while (open.size() > 0){
            StdDraw.setPenColor(Color.GRAY);
            Node current = getLowestCostNode();
            open.remove(current);
            closed.add(current);
            if(current.compareTo(b) == 0) return path(current);

            StdDraw.filledSquare(current.x + 0.5,current.y + 0.5,0.5);

            for (Node node: getNeighbours(current)){
                if(closed.contains(node) || !node.isWalkAble) continue;

                int cost = current.gCost + grid.calculateDistance(current, node);

                if(open.contains(node) && cost < node.fCost) open.remove(node);
                if(!open.contains(node)){
                    node.gCost = cost;
                    node.fCost = node.gCost + grid.calculateDistance(node, b);
                    node.previousNode = current;
                    open.add(node);
                }
            }
        }
        return null;
    }

    private List<Node> path(Node a){
        ArrayList<Node> list = new ArrayList<>();
        list.add(a);
        while (a.previousNode != null){
            a = a.previousNode;
            list.add(a);
        }
        Collections.reverse(list);
        return list;
    }
}
