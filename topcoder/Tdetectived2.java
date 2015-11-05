/**
 * Problem Name: Tdetectived2
 * Problem Link: https://community.topcoder.com/stat?c=problem_statement&pm=14076&rd=16552
 * Solution Description:
 *                  BFS�� Ǯ���ٰ� �򰥷��� DFS�� �����ع��ȴ�.
 *                  ��� �����ڸ� �湮�ϴ� ��츦 �������� �� �湮�ϴ� ������ depth�� ���� ���� ���� ���ϵ��� �� ��
 *                  ���߿� �Ѳ����� ���� ����ϴ� ������� Ǯ����.
 *                  System test 1�� �ð� �ʰ��� fail �ϴµ� ���߿� �� ������ �� ����� ã�ƺ��߰ڴ�.
 *                  �Ƹ� BFS�� �ϰ� �� �湮�� ������ �ߴ��ϸ� �ξ� �� ������ �����ϴ�.
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