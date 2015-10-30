/**
 * Problem Name: The Binary Search Efficiency Doubter
 * Problem Link: https://www.hackerrank.com/contests/2014-icpc-north-central-regional-north-america-practice/challenges/the-binary-search-efficiency-doubter
 * Solution Description:
 *     Binary Search의 재귀를 변형해 
 *     solve(left) + depth + solve(right) 의 형식으로 
 *     각 단계의 middle value의 depth를 구해서 합산하도록 구현 함
 *     edge value로 size가 짝수일 경우 left는 짝수로 계속 쪼개지는데 
 *     결국 size가 2까지 가면 제대로 값을 못 구해서(solve(0) + depth + solve(0)) 재귀 종료 조건으로 2를 추가함
 * 
 *     size가 최대 10,000,000 까지 들어와서 stack overflow가 걱정되어 계산을 해보았다.
 *     최대 가능한 stack depth 는 log2 10,000,000 = 약 24 로 비교적 양호한 수준이고,
 *     JVM의 default stack size는 http://docs.oracle.com/cd/E13150_01/jrockit_jvm/jrockit/jrdocs/refman/optionX.html#wp1024112 참고
 *     최악의 경우인 Windows 32bit 에서도 64KB는 되서 비교적 안전하다고 판단해 사용했다.
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
