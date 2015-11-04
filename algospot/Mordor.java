/**
 * Problem Name: MORDOR
 * Problem Link: https://algospot.com/judge/problem/read/MORDOR
 * Solution Description:
 *              구간의 최소값과 최대값을 구해서 그 차이를 출력하면 되는 간단한 문제이다.
 *              그러나 시간제한이 5초이기 때문에 일반적으로 풀면 시간 초과가 난다
 *              그래서 어제 aaa에서 배운 신비한 자료구조 segment tree를 구현해서 풀기로 했다.
 *              최소값, 최대값 segment tree를 만들어서 각각 log N 만에 값을 구해 좀 더 빨리 풀리도록 할 예정이다.
 *              이 후 그냥 구현한 것과 속도를 비교해보자 (note: 그냥 구현은 5초 넘어감)
 *
 * Reference:
 *              Segment tree - https://en.wikipedia.org/wiki/Segment_tree
 */

import java.util.ArrayList;
import java.util.Scanner;


// algospot은 제출하기 전에 class 이름을 Main 으로 변경 해야함
public class Mordor {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        int testCase = Integer.parseInt(scan.nextLine());

        while(testCase-- > 0) {
            String[] caseStr = scan.nextLine().split(" ");
            int nTag = Integer.parseInt(caseStr[0]);
            int nProblem = Integer.parseInt(caseStr[1]);

            int[] tags = new int[nTag];
            int i = 0;
            for(String tag: scan.nextLine().split(" ")) {
                tags[i++] = Integer.parseInt(tag);
            }

            while (nProblem-- > 0) {
                String[] range = scan.nextLine().split(" ");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                System.out.println(solveNormal(tags, start, end));
            }
        }
    }

    static public int solveNormal(int[] tags, int start, int end) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = start; i <= end; ++i) {
            min = Math.min(min, tags[i]);
            max = Math.max(max, tags[i]);
        }

        return max - min;
    }


    //TODO implements MIN, MAX senment tree
    class SegmentTree {
        private ArrayList<Integer> mTree = null;
        public SegmentTree(int size, int[] data) {
            mTree = new ArrayList<>(size * 2);
            int index = size + 1;
            for(int d : data)
                mTree.set(index++, d);
            makeSegmentTree();
        }

        private void makeSegmentTree() {

        }

        public int getRangeValue(int start, int end) {
            return 0;
        }

    }
}