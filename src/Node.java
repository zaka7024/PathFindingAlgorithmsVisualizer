
public class Node implements Comparable<Node>{
    public int x, y;
    public int gCost, hCost, fCost;
    public boolean isWalkAble;
    public Node previousNode;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        gCost = Integer.MAX_VALUE;
        isWalkAble = true;
    }

    @Override
    public int compareTo(Node that) {
        return this.x == that.x && this.y == that.y ? 0 : -1;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
