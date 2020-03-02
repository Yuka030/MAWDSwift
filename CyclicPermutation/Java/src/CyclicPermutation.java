import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CyclicPermutation {
    int test;
    ArrayList<Integer>[] caseList;
    ArrayList<String> inputList = new ArrayList<>();

    public CyclicPermutation() {
        readInput("sample.txt");
        init();
    }

    public void init(){
        test = Integer.parseInt(inputList.get(0));
        caseList = new ArrayList[test];
        int t = 0;

        for(int i = 1; i <= test * 2; i++) {
            if(i % 2 == 0) {
                caseList[t] = new ArrayList<>();
                String[] edge = inputList.get(i).split(" ");
                for (int j = 0; j < edge.length; j++) {
                    caseList[t].add(Integer.parseInt(edge[j]) - 1);
                }
                t++;
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

    boolean[] visited;
    public void printCount(){
        for(int i = 0; i < caseList.length; i++){
            visited = new boolean[caseList[i].size()];
            System.out.println(search(caseList[i]));
        }
    }


    public int search(ArrayList array){
        int count = 0;
        for(int i = 0; i < 8; i++){
            if(visited[i]){
                continue;
            }
            count += 1;
            searchUtil(array, i);
        }
        return count;
    }

    public void searchUtil(ArrayList<Integer> array, int i){
        if(visited[i]) return;
        visited[i] = true;
        searchUtil(array, array.get(i));
    }

    public static void main(String[] args) {
        CyclicPermutation c = new CyclicPermutation();
        c.printCount();
    }
}