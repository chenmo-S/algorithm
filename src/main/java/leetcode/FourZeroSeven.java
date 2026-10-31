package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 接雨水Ⅱ
 *
 * @author chenmo
 * @date 2025/3/28
 */
public class FourZeroSeven {

    int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // 先把周围一圈加入 因为周围一圈肯定不能存水
        for (int i = 0; i < m; i++) {
            queue.add(new Pair(i, 0, heightMap[i][0]));
            queue.add(new Pair(i, n - 1, heightMap[i][n - 1]));
            visit[i][0] = true;
            visit[i][n - 1] = true;
        }
        for (int i = 1; i < n - 1; i++) {
            queue.add(new Pair(0, i, heightMap[0][i]));
            queue.add(new Pair(m - 1, i, heightMap[m - 1][i]));
            visit[0][i] = true;
            visit[m - 1][i] = true;
        }
        // 每次找出最小的
        int res = 0;
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            for (int[] arr : direct) {
                int x = arr[0] + poll.i;
                int y = arr[1] + poll.j;
                if (x < 0 || x >= m || y < 0 || y >= n || visit[x][y]) {
                    continue;
                }
                int water = Math.max(0, poll.val - heightMap[x][y]);
                res += water;
                visit[x][y] = true;
                queue.add(new Pair(x, y, Math.max(heightMap[x][y], poll.val)));
            }
        }
        return res;
    }

    static class Pair {
        int i;
        int j;
        int val;

        public Pair(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FourZeroSeven().trapRainWater(new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        }));
    }


}
