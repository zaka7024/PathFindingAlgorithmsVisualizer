import edu.princeton.cs.algs4.StdDraw;

public class Grid {
    private final Node [][] nodes;
    final int length;

    public Grid(int n){
        nodes = new Node[n][n];
        length = n;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                nodes[j][i] = new Node(j, i);
            }
        }
        StdDraw.setXscale(-0.05 * n, 1.05 * n);
        StdDraw.setYscale(-0.05 * n, 1.05 * n);
    }

    public Node getNodeAt(int x, int y){
        return nodes[x][y];
    }

    public int calculateDistance(Node a, Node b){
        int x = Math.abs(a.x - b.x);
        int y= Math.abs(a.y - b.y);
        return 14 * Math.min(x, y) + Math.abs(x - y) * 10;
    }

    public void draw(){
        double n = nodes.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n ; j++){
                StdDraw.square(j + 0.5, i + 0.5 , 0.5);
            }
        }
    }
}
