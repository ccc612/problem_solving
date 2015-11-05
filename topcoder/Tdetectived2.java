/**
 * Problem Name: Tdetectived2
 * Problem Link: https://community.topcoder.com/stat?c=problem_statement&pm=14076&rd=16552
 * Solution Description:
 *                  BFS로 풀려다가 헷갈려서 DFS로 구현해버렸다.
 *                  모든 용의자를 방문하는 경우를 따져보고 각 방문하는 시점의 depth가 가장 작을 것을 취하도록 한 후
 *                  나중에 한꺼번에 값을 계산하는 방식으로 풀었다.
 *                  System test 1개 시간 초과로 fail 하는데 나중에 더 빠르게 할 방법을 찾아봐야겠다.
 *                  아마 BFS로 하고 다 방문한 시점에 중단하면 훨씬 더 빠를거 같긴하다.
 */

public class Tdetectived2
{
    public int reveal(String[] s) {
        int len = s[0].length();

        boolean[] visited = new boolean[len];
        int[] visitedDepth = new int[len];
        int[] score = new int[len];

        visited[0] = true;
        visitedDepth[0] = 1;

        visit(s, visited, visitedDepth, score, 0, 1);

        int result = 0;
        for (int i = 1; i < len; ++i) {
            result += (i * visitedDepth[i]);
        }

        return result;
    }

    private void visit(String[] s, boolean[] visited, int[] visitedDepth, int[] score, int cur, int depth) {
        int len = s[0].length();

        int curMax = 0;
        for (int i = 0; i < len; ++i) {
            if (visited[i] == false) {
                int hate = Integer.parseInt(s[cur].substring(i, i + 1));
                score[i] = Math.max(score[i], hate);
                curMax = Math.max(score[i], curMax);
            }
        }

        ;
        for (int i = 0; i < len; ++i) {
            if (visited[i] == false && curMax == score[i]) {
                if (visitedDepth[i] == 0)
                    visitedDepth[i] = depth;
                else
                    visitedDepth[i] = Math.min(depth, visitedDepth[i]);

                visited[i] = true;
                visit(s, visited, visitedDepth, score.clone(), i, depth + 1);
                visited[i] = false;
            }
        }
    }
}