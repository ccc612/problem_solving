import java.io.*;
import java.util.*;

public class Solution {
    
    public static long bsCount(long size) {
        long count = 0;
        long nextNodeCount = 1;
        for (int i = 1; size > 0; ++i) {
            count += (Math.min(nextNodeCount,size) * i);
            size -= nextNodeCount;
            nextNodeCount *= 2;
        }
        
        return count;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for(int i = 1; scan.hasNextLong(); ++i) {
            System.out.println("Case " + i + ": " + bsCount(scan.nextLong()));
        }
    }
}
