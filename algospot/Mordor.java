/**
 * Problem Name: MORDOR
 * Problem Link: https://algospot.com/judge/problem/read/MORDOR
 * Solution Description:
 *              ������ �ּҰ��� �ִ밪�� ���ؼ� �� ���̸� ����ϸ� �Ǵ� ������ �����̴�.
 *              �׷��� �ð������� 5���̱� ������ �Ϲ������� Ǯ�� �ð� �ʰ��� ����
 *              �׷��� ���� aaa���� ��� �ź��� �ڷᱸ�� segment tree�� �����ؼ� Ǯ��� �ߴ�.
 *              �ּҰ�, �ִ밪 segment tree�� ���� ���� log N ���� ���� ���� �� �� ���� Ǯ������ �� �����̴�.
 *              �� �� �׳� ������ �Ͱ� �ӵ��� ���غ��� (note: �׳� ������ 5�� �Ѿ)
 *
 * Reference:
 *              Segment tree - https://en.wikipedia.org/wiki/Segment_tree
 */

import java.util.ArrayList;
import java.util.Scanner;


// algospot�� �����ϱ� ���� class �̸��� Main ���� ���� �ؾ���
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