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
 * Reference: Segment tree - https://en.wikipedia.org/wiki/Segment_tree
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;

// algospot에 commit 하기위해서는 Class이름을 Main으로 바꾸어야 함
public class Mordor {
    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int testCase = Integer.parseInt(reader.readLine());

            while(testCase-- > 0) {
                String[] caseStr = reader.readLine().split(" ");
                int nTag = Integer.parseInt(caseStr[0]);
                int nProblem = Integer.parseInt(caseStr[1]);

                int[] tags = new int[nTag];
                int i = 0;
                for (String tag : reader.readLine().split(" ")) {
                    tags[i++] = Integer.parseInt(tag);
                }

                SolveMordor mordor = new SolveMordor(tags);

                while (nProblem-- > 0) {
                    String[] range = reader.readLine().split(" ");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);
                    System.out.println(mordor.solveSegment(start, end));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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


    static class SolveMordor {
        private final SegmentTree tree;

        public SolveMordor(int[] tags) {
            tree = new SegmentTree(tags);
        }

        public int solveSegment(int start, int end) {
            return tree.getValue(start, end);
        }
    }

    public static class SegmentTree {
        private static final int[] mMinTree = new int[100000 * 2];
        private static final int[] mMaxTree = new int[100000 * 2];
        private final int size;

        public SegmentTree(int[] data) {
            int eSize = data.length;
            size = eSize * 2;

            int i = eSize;
            for(int d: data) {
                mMinTree[eSize] = d;
                mMaxTree[eSize] = d;
                eSize++;
            }

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
                mMinTree[i] = Math.min(mMinTree[leftChild(i)],
                                           mMinTree[rightChild(i)]);

                mMaxTree[i] = Math.max(mMaxTree[leftChild(i)],
                                         mMaxTree[rightChild(i)]);
            }
        }

        public int getValue(int start, int end) {
            int left = (size / 2) + start;
            int right = (size / 2) + end;

            int leftMin = mMinTree[left];
            int rightMin = mMinTree[right];

            int leftMax = mMaxTree[left];
            int rightMax = mMaxTree[right];

            while (left < right) {
                if (left % 2 == 1) {
                    leftMin = Math.min(leftMin, mMinTree[parent(left) + 1]);
                    leftMax = Math.max(leftMax, mMaxTree[parent(left) + 1]);

                    left = parent(left) + 1;
                }
                else {
                    leftMin = mMinTree[parent(left)];
                    leftMax = mMaxTree[parent(left)];

                    left = parent(left);
                }

                if (right % 2 == 1) {
                    rightMin = mMinTree[parent(right)];
                    rightMax = mMaxTree[parent(right)];
                    right = parent(right);
                } else {
                    rightMin = Math.min(rightMin, mMinTree[parent(right) - 1]);
                    rightMax = Math.max(rightMax, mMaxTree[parent(right) - 1]);
                    right = parent(right) - 1;
                }
            }
            return Math.max(leftMax, rightMax) - Math.min(leftMin, rightMin);
        }
    }
}