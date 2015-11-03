import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Integer;
import java.util.*;

/**
 * Problem Name: Travel
 * Problem Link: https://code.google.com/codejam/contest/10214486/dashboard#s=p0
 * Solution Description
 *      모든 경우의 수를 다 탐색하되 현재까지 구해진 최소값 보다 커지는 시점에서 그 루트를 더이상 탐색하지 않는다.
 *
 * Problem of solution
 *      can`t solve the large set
 *      (초당 94개 case를 풀어야 4분내로 large set을 풀 수 있는데
 *      현재는 초당 1~5개 정도밖에 풀지 못함,
 *      새로운 알고리즘을 사용해야 할 듯)
 *
 * Lessen Learned
 *      * java의 set 사용법
 *      * java의 pair 사용법
 *      * java의 Integer.getInteger("XXX") 는 "XXX"에 해당하는 환경변수를 가져오는 함수라는 것
 *        (String => int 변환 함수인 줄 알고 한참 삽질)
 *
 */

public class Travel {
    public static final String SMALL_FILE = "A-small-practice.in";
    public static final String LARGE_FILE = "A-large-practice.in";
    public static final String OUT_FILE = "Travel.out";

    public static void main(String args[]) {
        Scanner scan = null;
        PrintWriter writer = null;
        try {
            //File file = new File(SMALL_FILE);
            File file = new File(LARGE_FILE);

            scan = new Scanner(file);
            //writer = new PrintWriter(OUT_FILE);
            writer = new PrintWriter(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int cases = Integer.parseInt(scan.nextLine().trim());
        for(int i = 1; i <= cases; ++i) {
            writer.print("Case #" + i + ":");
            String[] initValue = scan.nextLine().split(" ");
            assert initValue.length == 3;
            int quiz = Integer.parseInt(initValue[2]);
            Travel travel = new Travel(Integer.parseInt(initValue[0]),
                    Integer.parseInt(initValue[1]),
                    writer);
            travel.parseRoads(scan);

            for(int j = 0; j < quiz; ++j) {
                String[] quizValue = scan.nextLine().split(" ");
                assert quizValue.length == 2;
                writer.print(" " + travel.solve(Integer.parseInt(quizValue[0]),
                        Integer.parseInt(quizValue[1])));
                writer.flush();
            }
            writer.println();
        }
        writer.close();
    }

    PrintWriter mWriter = null;
    int mCity = 0;
    int mRoad = 0;
    HashMap<HashSet<Integer>, List<Integer>> mRoadCost = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> mRoadCity = new HashMap<>();

    public Travel(int city, int road, PrintWriter writer) {
        mCity = city;
        mRoad = road;
        mWriter = writer;
        for(int i = 1; i <= city; ++i)
            mRoadCity.put(i, new HashSet<>());
    }

    int mMinCost = Integer.MAX_VALUE;
    private int solve(int dest, int time) {
        if (mMinCost != Integer.MAX_VALUE)
            mMinCost = mMinCost * 3;

        Set<Integer> notVisited = new HashSet<>(mRoadCity.keySet());
        if (!notVisited.contains(dest))
            return -1;
        for(int i = 2; i <= mCity; ++i)
            notVisited.add(i);

        solveRecursion(1, dest, time, 0, notVisited);
        if (mMinCost == Integer.MAX_VALUE) {
            mRoadCity.remove(dest);
            return -1;
        } else
            return mMinCost;
    }

    private void solveRecursion(int current, int dest, int time, int cost, Set<Integer> notVisited) {
        if (notVisited.isEmpty()) return;
        if (cost > mMinCost) return;
        if (current == dest) {
            mMinCost = Math.min(cost, mMinCost);
            return;
        }

        HashSet<Integer> tmpSet = new HashSet<>(mRoadCity.get(current));
        tmpSet.retainAll(notVisited);
        if (tmpSet.isEmpty()) return;

        for(int next: tmpSet) {
            int curCost = mRoadCost.get(createPair(current, next)).get(time);
            notVisited.remove(next);
            solveRecursion(next, dest, (time + curCost) % 24, cost + curCost, notVisited);
            notVisited.add(next);
        }
    }

    private void parseRoads(Scanner scan) {
        for(int i=0; i < mRoad; ++i) {
            String[] keys = scan.nextLine().split(" ");
            int cityA = Integer.parseInt(keys[0]);
            int cityB = Integer.parseInt(keys[1]);

            List<Integer> roadTime = new ArrayList<>();
            for(String time: scan.nextLine().split(" "))
                roadTime.add(Integer.parseInt(time));
            mRoadCost.put(createPair(cityA, cityB), roadTime);

            ((Set<Integer>)mRoadCity.get(cityA)).add(cityB);
            ((Set<Integer>)mRoadCity.get(cityB)).add(cityA);
        }
    }

    static private HashSet<Integer> createPair(int a, int b) {
        HashSet<Integer> pair = new HashSet<>();
        pair.add(a);
        pair.add(b);
        return pair;
    }
}