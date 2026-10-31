package leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 数据流的中位数
 *
 * @author chenmo
 * @date 2025/5/14
 */
public class TwoNineFive {

    /**
     * 使用双堆维护 偶数则大根堆和小根堆数量一致 奇数小根堆多一个 <br>
     * leetCode 104ms
     */
    class MedianFinder {

        PriorityQueue<Integer> minPriorityQueue;
        PriorityQueue<Integer> maxPriorityQueue;

        public MedianFinder() {
            // 一个大根堆 一个小根堆
            minPriorityQueue = new PriorityQueue<>();
            maxPriorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        /**
         * 时间复杂度O(logN)
         *
         * @param num
         */
        public void addNum(int num) {
            int total = minPriorityQueue.size() + maxPriorityQueue.size() + 1;
            if ((total & 1) == 1) {
                // min数量需要多一个
                if (minPriorityQueue.isEmpty() || num < minPriorityQueue.peek()) {
                    if (maxPriorityQueue.isEmpty() || num >= maxPriorityQueue.peek()) {
                        minPriorityQueue.add(num);
                    } else {
                        Integer maxPoll = maxPriorityQueue.poll();
                        minPriorityQueue.add(maxPoll);
                        maxPriorityQueue.add(num);
                    }
                } else {
                    minPriorityQueue.add(num);
                }
            } else {
                // 二者数量需要保持相等 total只会>=2
                if (maxPriorityQueue.isEmpty() || num > maxPriorityQueue.peek()) {
                    if (num <= minPriorityQueue.peek()) {
                        maxPriorityQueue.add(num);
                    } else {
                        Integer minPoll = minPriorityQueue.poll();
                        maxPriorityQueue.add(minPoll);
                        minPriorityQueue.add(num);
                    }
                } else {
                    maxPriorityQueue.add(num);
                }
            }
        }

        public double findMedian() {
            if ((minPriorityQueue.size() + maxPriorityQueue.size()) % 2 == 1) {
                return minPriorityQueue.peek();
            } else {
                return (minPriorityQueue.peek() + maxPriorityQueue.peek()) / 2.0;
            }
        }
    }

    /**
     * 简单的有序数组 居然过了 leetCode 1100ms <br>
     * 优化为二分查找后 用时368ms
     */
    class MedianFinder1 {

        List<Integer> list;

        public MedianFinder1() {
            list = new ArrayList<>();
        }

        /**
         * add的时间复杂度为O(N) 优化为二分查找 可以加快查询的速度
         *
         * @param num
         */
        public void addNum(int num) {
            if (list.size() == 0 || num >= list.get(list.size() - 1)) {
                list.add(num);
                return;
            }
            // 二分查找 加快下速度
            int left = 0, right = list.size() - 1;
            int index = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) == num) {
                    index = mid;
                    break;
                } else if (list.get(mid) > num) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            list.add(index, num);
        }

        public double findMedian() {
            int i = list.size() / 2;
            if ((list.size() & 1) == 1) {
                // 奇数
                return (double) list.get(i);
            } else {
                return (list.get(i) + list.get(i - 1)) / 2.0;
            }
        }
    }

    public static void main(String[] args) {
        TwoNineFive twoNineFive = new TwoNineFive();
        MedianFinder medianFinder = twoNineFive.new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
