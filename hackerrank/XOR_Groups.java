/** 
 * Problem Name: XOR Groups
 * Problem Link: https://www.hackerrank.com/contests/hourrank-1/challenges/xor-groups
 * Solution Description:
 * 1. 모든 수를 XOR 한다.
 * 2-1. 결과가 0 이면 => 어떻게 배치하던 서로 같다.
 * 2-2. 결과가 0이 아니면 서로 다른 그룹에 배치했을 때 절대 같아지지 않는다. 따라서 return 0.
 * 3. 배치 가능한 모든 경우의 수를 계산해서 return 한다.
 *          (pow(2, 수의 갯수) - 2) / 2 
 *          (각 수가 그룹 1이나 2에 배치될 모든 경우의 수 - 한쪽에만 모든 수가 배치되는 경우의 수) / 2
 */
 
 
 
import java.io.*;
import java.util.*;

public class Solution {
    public static final long MOD_VALUE = ((long)Math.pow(10, 9)) + 7;
        
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long count = scan.nextLong();
        
        long value = scan.nextLong();        
        for(int i = 0; i < count - 1; ++i) {
            value = (value ^ scan.nextLong());
        }
        
        if (value != 0) {
            System.out.println("0");
            return;
        }
        
        long result = 1;
        for(int i=0; i < count - 1; ++i) {
            result = (result * 2) % MOD_VALUE;
        }        
        result -= 1;
        
        System.out.println(result);        
    }
}
