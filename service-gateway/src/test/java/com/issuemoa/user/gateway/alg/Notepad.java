package com.issuemoa.user.gateway.alg;

import java.util.*;

public class Notepad {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            List<Node> child = stack.pop().children;
            for (int i = child.size()-1; i >= 0; i--) {
                stack.push(child.get(i));
                result.add(child.get(i).val);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Map<Integer,Integer> hMap = new HashMap<>();

        List<Integer> list = new ArrayList<>();

        int[] result = list.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(result);

        char c = 't';
        String str = "str";
    }
}
