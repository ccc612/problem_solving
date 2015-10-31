import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.util.*;

/**
 * Problem Name: Travel
 * Problem Link: https://code.google.com/codejam/contest/10214486/dashboard#s=p0
 * Solution Description
 *      모든 경우의 수를 다 탐색하되 현재까지 구해진 최소값 보다 커지는 시점에서 그 루트를 더이상 탐색하지 않는다.
 *
 * Lessen Learned
 *      * java의 set 사용법
 *      * java의 pair 사용법
 *      * java의 Integer.getInteger("XXX") 는 "XXX"에 해당하는 환경변수를 가져오는 함수라는 것
 *        (String => int해주는 함수인 줄 알고 한참 삽질)
 *     
 */

public class Travel {
    public static final String DIR = "/Users/mcjung/IntelliJProjects/problem_solving/google_code_jam/";
    public static final String SMALL_FILE = "A-small-practice.in";
    public static final String LARGE_FILE = "A-large-practice.in";

    int mCity = 0;
    int mRoad = 0;
    Map mRoadCost = new HashMap<Pair<Integer,Integer>, List<Integer>>();
    Map mRoadCity = new HashMap<Integer, Set<Integer>>();
    public Travel(int city, int road) {
        mCity = city;
        mRoad = road;
    }

    public static void main(String args[]) {
        Scanner scan = null;
        try {
            File file = new File(DIR + SMALL_FILE);
            //file = new File(DIR + LARGE_FILE);

            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int cases = Integer.parseInt(scan.nextLine().trim());
        for(int i = 1; i <= cases; ++i) {
            System.out.print("Case #" + i + ":");
            String[] initValue = scan.nextLine().split(" ");
            assert initValue.length == 3;
            int quiz = Integer.parseInt(initValue[2]);
            Travel travel = new Travel(Integer.parseInt(initValue[0]),
                                        Integer.parseInt(initValue[1]));
            travel.parseRoads(scan);

            for(int j = 0; j < quiz; ++j) {
                String[] quizValue = scan.nextLine().split(" ");
                assert quizValue.length == 2;
                System.out.print(" " + travel.solve(Integer.parseInt(quizValue[0]),
                                                    Integer.parseInt(quizValue[1])));
            }
            System.out.println();
        }
    }

    private int solve(int dest, int time) {
        int minCost = Integer.MAX_VALUE;
        //TODO solve problem
    }

    private void parseRoads(Scanner scan) {
        for(int i=0; i < mRoad; ++i) {

            String[] keys = scan.nextLine().split(" ");
            int cityA = Integer.parseInt(keys[0]);
            int cityB = Integer.parseInt(keys[1]);
            List<Integer> roadTime = new ArrayList<Integer>();
            for(String time: scan.nextLine().split(" "))
                roadTime.add(Integer.parseInt(time));
            mRoadCost.put(new Pair(cityA, cityB), roadTime);
            mRoadCost.put(new Pair(cityB, cityA), roadTime);

            //TODO insert city into mRoadCity as a set
        }
    }
}