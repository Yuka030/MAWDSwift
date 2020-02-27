import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LCASolution {
    List<String> inputList = new ArrayList<>();
    ArrayList<Integer>[] nodeList;
    ArrayList<Integer>[] pairsList;

    int numOfNodes;
    int numOfPairs;

    List<Integer> path1 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();

    public void readInput(String filePath){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            inputList.add(line);

            while(line != null){
                line = reader.readLine();
                inputList.add(line);
            }

            init(inputList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(List<String> list){
        numOfNodes = Integer.parseInt(list.get(0));
        nodeList = new ArrayList[numOfNodes + 1];

        // initializing
        for (int i = 0; i < numOfNodes + 1; i++) {
            nodeList[i] = new ArrayList<Integer>();
        }

        for(int i = 1; i < nodeList.length - 1; i++){
            String[] edge = list.get(i).split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            nodeList[u].add(v);
            nodeList[v].add(u);
        }

        numOfPairs = Integer.parseInt(inputList.get(numOfNodes));
        pairsList = new ArrayList[numOfPairs];

        // initializing
        for (int i = 0; i <numOfPairs; i++) {
            pairsList[i] = new ArrayList<Integer>();
        }

        for(int i = 0 ; i < numOfPairs; i++){
            String[] edge = list.get(numOfNodes + i + 1).split(" ");
            int v1 = Integer.parseInt(edge[0]);
            int v2 = Integer.parseInt(edge[1]);
            pairsList[i].add(v1);
            pairsList[i].add(v2);
        }
    }

    public void printNodeList(){
        for(int i = 1; i < nodeList.length; i++){
            System.out.println("node " + i + ": " + nodeList[i]);
        }
    }

    public void printLCAAll(){
        for(int i = 0; i < pairsList.length; i++){
            printLCA(1, pairsList[i].get(0), pairsList[i].get(1));
        }
    }

    private void printLCA(int root, int v1, int v2){
        int lca = -1;
        path1.clear();
        path2.clear();

        if(findPath(root, v1, path1) && findPath(root, v2, path2)){
            if(path1.isEmpty() || path2.isEmpty()){
                System.out.println("No LCA found.");
                return;
            }

            for(int i = 0; i < path1.size() && i < path2.size(); i++){
                if(path1.get(i).equals(path2.get(i))) lca = path1.get(i);
            }
        }

        if(lca != -1){
            System.out.println("LCA: " + lca);
            return;
        }
        System.out.println("No LCA found.");
    }


    private boolean findPath(int root, int target, List<Integer> path){
        //base case
        if(root == target){
            path.add(root);
            return true;
        }
        if(nodeList[root].size() < 2) return false;

        path.add(root);

        //recursive case
        if(nodeList[root] != null) {
            for (int i = 1; i < nodeList[root].size(); i++) {
                if(root == 1){
                    if (nodeList[root].get(i - 1) != null && findPath(nodeList[root].get(i - 1), target, path)) return true;
                }
                if (nodeList[root].get(i) != null && findPath(nodeList[root].get(i), target, path)) return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
