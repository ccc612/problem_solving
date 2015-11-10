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

import java.lang.Integer;
import java.util.Scanner;
import java.util.Vector;


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

            Mordor mordor = new Mordor(tags);

            while (nProblem-- > 0) {
                String[] range = scan.nextLine().split(" ");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                //System.out.println(solveNormal(tags, start, end));
                System.out.println(mordor.solveSegment(start, end));
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


    private SegmentTree tree;

    public Mordor(int[] tags) {
        tree = new SegmentTree(tags);
    }

    public int solveSegment(int start, int end) {
        int min = tree.getMin(start, end);
        int max = tree.getMax(start, end);
        System.out.println("Min: " + min + ", Max: " + max);

        return max - min;
    }


    public class SegmentTree {
        private final Vector<Integer> mMinTree;
        private final Vector<Integer> mMaxTree;
        private final int size;

        public SegmentTree(int[] data) {
            int eSize = data.length;
            size = eSize * 2;
            mMinTree = new Vector<>(size);

            for (int i = 0; i < eSize; ++i) {
                mMinTree.add(0);
            }
            for(int d : data) {
                mMinTree.add(d);
            }

            mMaxTree = (Vector<Integer>) mMinTree.clone();

            makeMinMaxSegmentTree();
        }

        private int parent(int index) {
            if (index >= size)
                return -1;

            int parentIndex = index / 2;
            if (parentIndex == 0)
                return -1;
            return parentIndex;
        }

        private boolean isLeaf(int index) {
            return index >= (size / 2);
        }

        private int checkChildIndex(int index) {
            if (index >= size)
                return -1;
            if (isLeaf(index))
                return -1;

            return index * 2;
        }

        private int leftChild(int index) {
            return checkChildIndex(index);
        }

        private int rightChild(int index) {
            int childIndex = checkChildIndex(index);
            return childIndex == -1? childIndex : childIndex + 1;
        }

        private void makeMinMaxSegmentTree() {
            for (int i = (size / 2) - 1; i > 0; --i) {
                mMinTree.set(i, Math.min(mMinTree.get(leftChild(i)),
                                           mMinTree.get(rightChild(i))));

                mMaxTree.set(i, Math.max(mMaxTree.get(leftChild(i)),
                                         mMaxTree.get(rightChild(i))));
            }
        }

        public int getMin(int start, int end) {
            int left = (size / 2) + start;
            int right = (size / 2) + end;

            int leftMin = mMinTree.get(left);
            int rightMin = mMinTree.get(right);

            while (left < right) {
                if (left % 2 == 1)
                    leftMin = Math.min(leftMin, mMinTree.get(parent(left)));
                else
                    leftMin = mMinTree.get(parent(left));
                left = parent(left) + 1;

                if (right % 2 == 1)
                    rightMin = mMinTree.get(parent(right));
                else
                    rightMin = Math.min(rightMin, mMinTree.get(parent(right)));
                right = parent(right) - 1;
            }
            return Math.min(leftMin, rightMin);
        }

        public int getMax(int start, int end) {
            int left = (size / 2) + start;
            int right = (size / 2) + end;

            int leftMax = mMaxTree.get(left);
            int rightMax = mMaxTree.get(right);

            while (left < right) {
                if (left % 2 == 1)
                    leftMax = Math.max(leftMax, mMaxTree.get(parent(left)));
                else
                    leftMax = mMaxTree.get(parent(left));
                left = parent(left) + 1;

                if (right % 2 == 1)
                    rightMax = mMaxTree.get(parent(right));
                else
                    rightMax = Math.max(rightMax, mMaxTree.get(parent(right)));
                right = parent(right) - 1;
            }
            return Math.max(leftMax, rightMax);
        }
    }
}