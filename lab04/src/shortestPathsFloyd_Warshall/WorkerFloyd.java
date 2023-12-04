package shortestPathsFloyd_Warshall;

public class WorkerFloyd implements Runnable {
    private final int k;
    private final int[][] graph;

    public WorkerFloyd(int k, int[][] graph) {
        this.k = k;
        this.graph = graph;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                synchronized (graph) {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }
        }
    }
}
