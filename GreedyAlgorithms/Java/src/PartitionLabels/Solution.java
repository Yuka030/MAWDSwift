package PartitionLabels;

/*
* A string S of lowercase letters is given. We want to partition this string into as many parts as possible
* so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
* Input: S = "ababcbacadefegdehijhklij"
  Output: [9,7,8]
* */

class Solution {
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }

        int[] chars = new int[26];
        for(int i = 0; i < S.length(); i ++){
            chars[S.charAt(i) - 'a'] = i;
        }

        List list = new ArrayList();
        int last = 0;
        int start = 0;

        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, chars[S.charAt(i) - 'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }

        return list;
    }
}