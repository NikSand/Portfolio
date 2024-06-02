package com.example.task_1;
import java.util.LinkedList;

public class Solution {

    static boolean areBalanced(String s) {
        final var stack = new LinkedList<Character>();
        final var n = s.length();
        for (var i = 0; i < n; i++) {
            final var ch = s.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    final char top = stack.pop();
                    if (top != ch) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    public void result (String stroka) {
        boolean testResult = areBalanced(stroka);
        if (testResult) {
            System.out.println(stroka + " - правильная скобочная последовательность");
        } else {
            System.out.println(stroka + " - не правильная скобочная последовательность");
        }
    }
}