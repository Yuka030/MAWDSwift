import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LCASolution {
    Node root = null;
    List<String> inputList = new ArrayList<>();
    ArrayList<Integer>[] nodeList;

    int numOfNodes;
    int numOfPairs;
    int[] pairsArray;

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
    }

    public void printNodeList(){
        for(int i = 1; i < nodeList.length; i++){
            System.out.println("node " + i + ": " + nodeList[i]);
        }
    }

    public void printLCA(Node root, int v1, int v2){
        int lca = -1;
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


    private boolean findPath(Node root, int target, List<Integer> path){
        //base case 1
        if(root == null) return false;
        path.add(root.val);

        //base case 2
        if(root.val == target) return true;

        //recursive case
        if(root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                if (root.children.get(i) != null && findPath(root.children.get(i), target, path)) return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
