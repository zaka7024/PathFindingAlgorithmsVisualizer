import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.List;
public class Main {

    private static void clearTheCanvas() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
    }

    public static void main(String[] args) {

        StdDraw.setCanvasSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);

        final int n = 50;
        Grid grid = new Grid(n);
        grid.draw();

        Node start = null, end = null;
        boolean blockedFinished = false;
        float factor = 0.5f;
        StdDraw.setPenColor(Color.RED);
        for(int i = 0; i < (n * n) * factor; i++) {
            int rand_x = StdRandom.uniform(n);
            int rand_y = StdRandom.uniform(n);
            StdDraw.filledSquare(rand_x + 0.5,rand_y + 0.5,0.5);
            grid.getNodeAt(rand_x, rand_y).isWalkAble = false;
        }

        while (true){
            while (!blockedFinished && !StdDraw.isKeyPressed(Constants.KEY_CODE_SPACE)){
                StdDraw.setPenColor(Color.RED);
                if(StdDraw.isMousePressed()){
                    int x = (int)Math.floor(StdDraw.mouseX());
                    int y = (int)Math.floor(StdDraw.mouseY());
                    if(x >= 0 && x < n && y >= 0 && y < n){
                        StdDraw.filledSquare(x + 0.5,y + 0.5,0.5);
                        grid.getNodeAt(x,y).isWalkAble = false;
                    }
                }
            }

            blockedFinished = true;

            if(StdDraw.isMousePressed()){
                int x = (int)Math.floor(StdDraw.mouseX());
                int y = (int)Math.floor(StdDraw.mouseY());

                if(x >= 0 && x < n && y >= 0 && y < n){
                    if(start == null) {
                        start = new Node(x, y);
                        StdDraw.setPenColor(Color.GREEN);
                        StdDraw.filledSquare(x + 0.5,y + 0.5,0.5);
                    }else if(end == null){
                        end = new Node(x, y);
                        StdDraw.setPenColor(Color.BLUE);
                        StdDraw.filledSquare(x + 0.5,y + 0.5,0.5);
                    }
                    StdDraw.pause(500);
                }
            }

            if(start != null && end != null) {
                List<Node> nodes;
                StdDraw.setPenColor(Color.GRAY);
                Finder finder = null;

                if (StdDraw.isKeyPressed(Constants.KEY_CODE_D)) {
                    finder = new DFS(grid);
                }else if(StdDraw.isKeyPressed(Constants.KEY_CODE_B)){
                    finder = new BFS(grid);
                }
                else if(StdDraw.isKeyPressed(Constants.KEY_CODE_A)){
                    finder = new A(grid);
                }
                else if(StdDraw.isKeyPressed(Constants.KEY_CODE_S)){
                    finder = new Dijkstra(grid);
                }
                if(finder != null) {
                    nodes = finder.findPath(grid.getNodeAt(start.x,start.y), grid.getNodeAt(end.x,end.y));
                    if(nodes == null) break;
                    StdDraw.setPenColor(Color.BLUE);
                    for (Node path: nodes) {
                        StdDraw.filledSquare(path.x + 0.5, path.y + 0.5, 0.5);
                    }

                    StdDraw.pause(Constants.RESTART_TIME);
                    clearTheCanvas();

                    grid = new Grid(n);
                    grid.draw();

                    start = null; end = null;
                    blockedFinished = false;
                }
            }
        }
    }
}
