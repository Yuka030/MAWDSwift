import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ShortestPath {
    static class Graph {
        int V;
        int numOfRealRes;
        List<String> inputList = new ArrayList<>();
        LinkedList<Integer> adj[];
        ArrayList<Integer> realResList;
        ArrayList<Integer> realResTraversedList;

        Graph(String filePath){
            readInput(filePath);
            init();
//            System.out.println("inputList is " + inputList);
//            System.out.println("V is " + V);
//            System.out.println("numOfRealRes is " + numOfRealRes);
//            System.out.println("realResList is " + realResList);
//            System.out.println("adj list is ");
//            for(int i = 0; i < adj.length; i++) System.out.print(i + ":" + adj[i] + "  ");
//            System.out.println("\n");
        }

        public void init(){
            for(int i = 0; i < inputList.size(); i++){
                String[] edge = inputList.get(i).split(" ");
                switch(i) {
                    case 0:
                        this.V = Integer.parseInt(edge[0]);
                        this.numOfRealRes = Integer.parseInt(edge[1]);
                        this.realResList = new ArrayList<>();
                        this.realResTraversedList = new ArrayList<>();
                        this.adj = new LinkedList[V];
                        for(int n = 0; n < V; n++) adj[n] = new LinkedList<>();
                        break;
                    case 1:
                        for(int j = 0; j < edge.length; j++){
                            this.realResList.add(Integer.parseInt(edge[j]));
                        }
                        break;
                    default:
                        addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
                        break;
                }
            }
        }

        public void readInput(String filePath){
            BufferedReader reader;
            try{
                reader = new BufferedReader(new FileReader(filePath));
                String line = reader.readLine();

                while(line != null){
                    inputList.add(line);
                    line = reader.readLine();
                }
                System.out.println(inputList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void addEdge(int src, int dest){
            this.adj[src].add(dest);
            this.adj[dest].add(src);
        }

        public void printShortestDistance(){
            int pred[] = new int[V];
            int dist[] = new int[V];
            int sum = 0;

            BFS(0, -1, pred, dist);
            int deepest = realResTraversedList.get(numOfRealRes - 1);
            realResTraversedList.clear();
            BFS(deepest, -1, pred, dist);

            for(int i = 0; i < realResTraversedList.size() - 1; i++){
                sum += getShortestDistance(realResTraversedList.get(i), realResTraversedList.get(i + 1));
            }

            System.out.println(sum);
        }

        private boolean BFS(int src, int dest, int[] pred, int[] dist){
            LinkedList<Integer> queue = new LinkedList<>();
            boolean visited[] = new boolean[V];

            for(int i = 0; i < V; i++){
                visited[i] = false;
                pred[i] = - 1;
                dist[i] = Integer.MAX_VALUE;
            }

            visited[src] = true;
            dist[src] = 0;
            queue.add(src);

            // BFS
            while(!queue.isEmpty()){
                int u = queue.poll();
                if(dest < 0 && realResList.contains(u) && !realResTraversedList.contains(u)){
                    realResTraversedList.add(u);
                }
                for(int i = adj[u].size() - 1; i >= 0; i--){

                    if(visited[adj[u].get(i)] == false){
                        visited[adj[u].get(i)] = true;
                        dist[adj[u].get(i)] = dist[u] + 1;
                        queue.add(adj[u].get(i));

                        //stop BFS when it finds destination
                        if (dest >= 0 && adj[u].get(i) == dest) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int getShortestDistance(int src, int dest){
            int pred[] = new int[V];
            int dist[] = new int[V];
            int shortestDistance = 0;

            if(BFS(src, dest, pred, dist) == false){
                System.out.println("Given source and destination are not correct.");
                return -1;
            }

            // list of path that stores the shortest path
            ArrayList<Integer> path = new ArrayList();

            int crawl = dest;
            path.add(crawl);
            while(pred[crawl] != -1){
                int p = pred[crawl];
                path.add(p);
                crawl = p;
            }

            // distance from source is in distance array
            shortestDistance =  dist[dest];
            //System.out.println("shortestDistance is "  + shortestDistance + "\n");
            return shortestDistance;
        }

    }

    public static void main(String args[]) {
        Graph graph = new Graph("sushi1.txt");
        graph.printShortestDistance();
    }
}