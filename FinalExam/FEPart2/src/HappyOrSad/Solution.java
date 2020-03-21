package HappyOrSad;

public class Solution {
    static String getFeeling(String s){
        char[] arr = s.toCharArray();
        int happy = 0;
        int sad = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == ':') {
                for (int j = 1; j < 3; j++) {
                    if(j == 1 && arr[i + j] != '-') break;
                    if(j == 2 && arr[i + j] == ')') happy ++;
                    if(j == 2 && arr[i + j] == '(') sad ++;
                }
            }
        }

        if(happy == 0 && sad == 0){
            return "none";
        }else if(happy == sad ){
            return "unsure";
        }else if(happy > sad){
            return "happy";
        }else {
            return "sad";
        }
    }

    public static void main(String[] args) {
        System.out.println(getFeeling("How are you :-) doing :-( today :-)?"));
        System.out.println(getFeeling("This:-(is str:-(:-(ange te:-)xt."));
        System.out.println(getFeeling(":)"));
    }
}
