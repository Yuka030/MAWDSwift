import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LCASolution lca = new LCASolution();
        lca.root = new Node(1);
        List<Node> list01 = new ArrayList<>();
        list01.add(new Node(2));
        list01.add(new Node(3));
        lca.root.children = list01;

        List<Node> list02 = new ArrayList<>();
        list02.add(new Node(4));
        list02.add(new Node(5));
        list02.add(new Node(6));
        lca.root.children.get(0).children = list02;

        List<Node> list03 = new ArrayList<>();
        list03.add(new Node(7));
        list03.add(new Node(8));
        lca.root.children.get(1).children = list03;

        lca.printLCA(lca.root, 4, 6);

        lca.readInput("input.txt");
        lca.printNodeList();
    }
}
