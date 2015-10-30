/**
 * Problem Name: The Binary Search Efficiency Doubter
 * Problem Link: https://www.hackerrank.com/contests/2014-icpc-north-central-regional-north-america-practice/challenges/the-binary-search-efficiency-doubter
 * Solution Description:
 *    Binary Search의 재귀를 변형해 
 *    solve(left) + depth + solve(right) 의 형식으로 
 *    각 단계의 middle value의 depth를 구해서 합산하도록 구현 함
 *    edge value로 size가 짝수일 경우 left는 짝수로 계속 쪼개지는데 
 *    결국 size가 2까지 가면 제대로 값을 못 구해서(solve(0) + depth + solve(0)) 재귀 종료 조건으로 2를 추가함
 */

import java.io.*;
import java.util.*;

public class Solution {

    public static long solve(long size, int depth) {
        if (size < 2) return depth;
        if (size == 2) return depth * 2 + 1;
        return solve(size / 2, depth + 1) + depth + solve(size - ((size / 2) + 1), depth + 1);
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int caseN = 1;
        while( scan.hasNextLong() ) {
            long size = scan.nextLong();
            System.out.println("Case " + (caseN++) + ": " + solve(size, 1));
        }
    }
}
