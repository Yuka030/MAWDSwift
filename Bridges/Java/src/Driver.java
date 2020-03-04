public class Driver {
    public static void main(String[] args) {
        Bridges b = new Bridges(10, 10);

        //temp input(sample01)
        b.country[0][0] = 1;
        b.country[0][1] = 1;
        b.country[0][2] = 1;
        b.country[0][7] = 1;
        b.country[0][8] = 1;
        b.country[0][9] = 1;

        b.country[1][0] = 1;
        b.country[1][1] = 1;
        b.country[1][2] = 1;
        b.country[1][3] = 1;
        b.country[1][8] = 1;
        b.country[1][9] = 1;

        b.country[2][0] = 1;
        b.country[2][2] = 1;
        b.country[2][3] = 1;
        b.country[2][8] = 1;
        b.country[2][9] = 1;

        b.country[3][2] = 1;
        b.country[3][3] = 1;
        b.country[3][4] = 1;
        b.country[3][9] = 1;

        b.country[4][3] = 1;
        b.country[4][9] = 1;

        b.country[5][9] = 1;

        b.country[7][4] = 1;
        b.country[7][5] = 1;

        b.country[8][4] = 1;
        b.country[8][5] = 1;
        b.country[8][6] = 1;

        System.out.println(b.getShortestPath()); // output - 3
        
    }
}
