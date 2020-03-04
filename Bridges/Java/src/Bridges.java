import java.util.*;

public class Bridges {
    int row;
    int col;
    int country[][];
    boolean visited[][];
    ArrayList singleIsland = new ArrayList<>();
    boolean foundIsland[][];
    int islandKey = 0;
    HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    Bridges(int row, int col){
        this.row = row;
        this.col = col;
        init();
    }

    public void init(){
        country= new int[row][col];
        visited = new boolean[row][col];
        foundIsland = new boolean[row][col];
    }

//    public void print(){
//        for(int i = 0; i < country.length; i++){
//            for(int j = 0; j < country[i].length; j++){
//                System.out.print(country[i][j]);
//            }
//            System.out.println();
//        }
//
//        for(int i = 0; i < visited.length; i++){
//            for(int j = 0; j < visited[i].length; j++){
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

    public int getShortestPath(){
        findIsland();
        return findShortestInIsland();
    }

    // Method to traverse country & find all islands
    public void findIsland(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(!foundIsland[i][j]){
                    singleIsland.clear();
                    dfs(i, j, country, visited);

                    if(!singleIsland.isEmpty()) {
                        ArrayList newSingleIsland = new ArrayList();
                        for(int h = 0; h < singleIsland.size(); h++){
                            newSingleIsland.add(singleIsland.get(h));
                        }
                        map.put(islandKey, newSingleIsland);
                        islandKey++;
                    }
                }
            }
        }
    }

    //dfs
    public void dfs(int x, int y, int[][] country, boolean[][] visited){
        if(x < 0 || y < 0 || x >= col || y >= row || country[x][y] == 0 || visited[x][y]){
            return;
        }

        visited[x][y] = true;
        foundIsland[x][y] = true;
        singleIsland.add(new int[]{x, y});

        dfs(x - 1, y, country, visited);
        dfs(x + 1, y, country, visited);
        dfs(x, y + 1, country, visited);
        dfs(x, y - 1, country, visited);
    }

    //bfs
    public int bfs(int[] src, int[][] country, boolean[][] newVisited){
        int count = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(src);

        while(!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0) {
                int[] c = queue.poll();
                for (int[] dir : dirs) {
                    int j = c[0] + dir[0];
                    int k = c[1] + dir[1];

                    if (j >= 0 && k >= 0 && j < row && k < col && !newVisited[j][k]) {
                        if (country[j][k] == 1) {
                            return count;
                        }
                        queue.add(new int[]{j, k});
                        newVisited[j][k] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    // Method to find the shortest path to another island from each position of each island & return the shortest one of all
    public int findShortestInIsland(){
        int count = Integer.MAX_VALUE;

        Set<Integer> keys = map.keySet();
        for(Integer key: keys) {

            boolean visited[][] = new boolean[row][col];

            for (int i = 0; i < map.get(key).size(); i++) {
                visited[map.get(key).get(i)[0]][map.get(key).get(i)[1]] = true;
            }

            for (int i = 0; i < map.get(key).size(); i++) {
                boolean[][] newVisited = new boolean[row][col];
                for(int j = 0; j < visited.length; j++){
                    for(int k = 0; k < visited[j].length; k++){
                        if(visited[j][k]) newVisited[j][k] = true;
                    }
                }

                int newCount = bfs(map.get(key).get(i), country, newVisited);
                if (newCount != -1 && newCount < count) {
                    count = newCount;
                }
            }
        }

        return count;
    }
}
