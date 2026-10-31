package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chenmo
 * @date 2025/4/7
 */
public class RandomTreeHot100 {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(96);
        list.add(98);
        list.add(101);
        list.add(104);
        list.add(105);
        list.add(114);
        list.add(124);
        list.add(208);
        list.add(226);
        list.add(236);
        list.add(297);
        list.add(538);
        list.add(543);
        list.add(617);
        System.out.println(list.get(new Random().nextInt(list.size())));
    }
}
