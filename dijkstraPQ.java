import java.util.*;

public class dijkstraPQ {
    
    private static int INF = Integer.MAX_VALUE;
    
    public static void dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> dist[a]-dist[b]);
        pq.offer(start);
        
        while(!pq.isEmpty()) {
            int u = pq.poll();
            for(int v=0; v<n; v++) {
                if(graph[u][v]!=0 && dist[u]+graph[u][v]<dist[v]) {
                    dist[v] = dist[u]+graph[u][v];
                    pq.offer(v);
                }
            }
        }
        
        for(int i=0; i<n; i++) {
			if(dist[i] > 1000){
				System.out.println("Shortest distance from node " + start + " to node " + i + " is NA");
			} else {
				System.out.println("Shortest distance from node " + start + " to node " + i + " is " + dist[i]);
			}
            
        }
    }
    
    public static void main(String[] args) {
		int startNode = 0;
        int[][] graph = new int[][]{
            {0, 0, 5, 5, 7, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0},
            {5, 0, 0, 0, 2, 2, 6, 0},
            {5, 0, 0, 0, 0, 3, 0, 0},
            {7, 0, 2, 0, 0, 0, 2, 0},
            {0, 7, 2, 3, 0, 0, 0, 0},
			{0, 0, 6, 0, 2, 0, 0, 0},        		
			{0, 0, 0, 0, 0, 0, 0, 0}
		};
        
        dijkstra(graph, 0);
    }
}
