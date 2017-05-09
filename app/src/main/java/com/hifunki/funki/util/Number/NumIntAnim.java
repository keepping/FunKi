package com.hifunki.funki.util.Number;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.www.teststatus.NumFloatAnim.java
 * @link
 * @since 2017-05-09 13:48:48
 */
public class NumIntAnim {

    //每秒刷新多少次
    private static final int COUNTPERS = 100;

    public static void startAnim(TextView textV, int num) {
        startAnim(textV, num, 500);
    }

    public static void startAnim(TextView textV, int num, long time) {
        if (num == 0) {
            textV.setText(NumberInt(num));
            return;
        }
        Integer[] nums = splitnum(num, (int) ((time / 1000f) * COUNTPERS));
        Counter counter = new Counter(textV, nums, time);
        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    private static Integer[] splitnum(int num, int count) {
        Random random = new Random();
        int numtemp = num;
        int sum = 0;
        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(0);
        while (true) {

            int countNew=num/count;

            int randomInt = random.nextInt(countNew);
            int nextInt = randomInt;
            System.out.println(nextInt);
            if (numtemp - nextInt >= 0) {
                sum = sum + nextInt;
                nums.add(sum);
                numtemp -= nextInt;
            } else {
                nums.add(num);
                return nums.toArray(new Integer[0]);
            }
        }
    }

    static class Counter implements Runnable {

        private final TextView view;
        private Integer[] nums;
        private long pertime;

        private int i = 0;

        Counter(TextView view, Integer[] nums, long time) {
            this.view = view;
            this.nums = nums;
            this.pertime = time / nums.length;
        }

        @Override
        public void run() {
            if (i > nums.length - 1) {
                view.removeCallbacks(Counter.this);
                return;
            }
            view.setText(NumberInt(nums[i++]));
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }

    public static String NumberInt(int f) {
        return String.valueOf(f);
    }

}
