package com.zsl.util;

import java.util.Random;

/**
 * @Author zsl
 * @Date 2021/12/31 10:03
 */
public class CodeUtils {

    private static final int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static final Random random = new Random(31);

    public static String getCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(nums[random.nextInt(10)]);
        }
        return code.toString();
    }
}
