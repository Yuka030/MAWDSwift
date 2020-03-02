import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TomatoFarm {
    int box[][];
    int row;
    int col;
    int total;
    int numOfRipe = 0;
    int count = 0;
    List<String> inputList = new ArrayList<>();
    ArrayList<int[]> ripe = new ArrayList<>();

    public TomatoFarm(String filePath) {
        readInput(filePath);
        init();
        print();
    }

    public void init(){
        String[] edge = inputList.get(0).split(" ");
        this.col = Integer.parseInt(edge[0]);
        this.row = Integer.parseInt(edge[1]);
        box = new int[row][col];

        for(int i = 1; i < inputList.size(); i++) {
            edge = inputList.get(i).split(" ");
            for(int j = 0; j < edge.length; j++){
                box[i-1][j] = Integer.parseInt(edge[j]);
                if(box[i-1][j] != -1) total++;
                if(box[i-1][j] == 1){
                    numOfRipe++;
                    ripe.add(new int[]{i-1, j});
                }
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

    public boolean floodFillUtil(int x, int y, int[][] box) {
        if (x < 0 || x >= row|| y < 0 || y >= col) {
            return false;
        }
        if (box[x][y] == 1){
            return false;
        }
        if(box[x][y] == -1){
            return false;
        }
        box[x][y] = 1;
        return true;
    }

    public boolean isAllRipe(){
        int num = 0;
        for(int i = 0; i < box.length; i++){
            for(int j = 0; j < box[i].length; j++){
                if(box[i][j] == 1){
                    num++;
                };
            }
        }
        return num == total;
    }

    public int floodFill(ArrayList<int[]> coordinates, int[][] box){
        if(numOfRipe == 0) return -1;
        if(isAllRipe()) return count;

        ArrayList<int[]> nextCoordinates= new ArrayList<>();

        for(int[] c: coordinates){
            if(floodFillUtil(c[0] + 1, c[1], box)){
                nextCoordinates.add(new int[]{c[0] + 1, c[1]});
            }
            if(floodFillUtil(c[0] - 1, c[1], box)){
                nextCoordinates.add(new int[]{c[0] - 1, c[1]});
            }
            if(floodFillUtil(c[0], c[1] + 1, box)){
                nextCoordinates.add(new int[]{c[0], c[1] + 1});
            }
            if(floodFillUtil(c[0], c[1] - 1, box)){
                nextCoordinates.add(new int[]{c[0], c[1] - 1});
            }
        }
        if(nextCoordinates.size() < 1) return -1;
        count++;
        return floodFill(nextCoordinates, box);
    }

    public void print(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(box[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TomatoFarm t = new TomatoFarm("tomatofarm1.txt");
        System.out.println("output: " + t.floodFill(t.ripe, t.box) + "\n");
    }
}