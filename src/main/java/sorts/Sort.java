package sorts;

import util.ArrayUtil;

/**
 * 排序算法通用接口实现 这里给出各类排序算法的时间复杂度以及空间复杂度比较 <br>
 * <a href="https://www.yuque.com/chenmo-7husl/etlgaf/iwzs3cy6qnuz7gtl">排序算法对比</a>
 *
 * @author chenmo
 * @date 2024/3/27
 */
public interface Sort {

    void sort(int[] nums);

    default void swap(int[] nums, int i, int j) {
        ArrayUtil.swap(nums, i, j);
    }
}
