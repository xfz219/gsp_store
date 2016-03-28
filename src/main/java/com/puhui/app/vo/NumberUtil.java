package com.puhui.app.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author quan
 */
public class NumberUtil {
    private static NumberFormat nf = new DecimalFormat("0000");

    /**
     * 给定总数，返回页数
     * 
     * @param total
     * @param perPage
     * @return
     */
    public static int getPageCount(int total, int perPage) {
        return (int) Math.ceil((double) total / perPage);
    }

    /**
     * double 相加
     * 
     * @param dd
     * @return
     */
    public static double doubleAdd(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return result.doubleValue();
    }

    /**
     * double 相加 - 四舍五入
     * 
     * @param dd
     * @return
     */
    public static double doubleAddScaled(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return scale(result);
    }

    /**
     * double 相减
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtract(double d1, double d2) {
        return new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)).doubleValue();
    }

    /**
     * double 相减 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtractScaled(double d1, double d2) {
        return scale(new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)));
    }

    /**
     * 四舍五入 - RoundingMode.HALF_UP
     * 
     * @param decimal
     * @return
     */
    private static double scale(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 货币形式格式字符串
     * 
     * @param ####,###0.00
     * @param value
     */
    public static String formatNumberString(String format, String value) {
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat(format);
        return df.format(bd);
    }


    /**
     * 将整数转化为四位数字
     * 
     * @param number
     * @return
     */
    public static String formatFourNumber(Integer number) {
        return nf.format(number);
    }

    /**
     * 获取带千分符并保留两位小数的金额
     * 
     * @author zhoue
     * @param amount
     * @return
     */
    public static String formatNumber(double amount) {
        String result = NumberFormat.getCurrencyInstance().format(amount).substring(1);
        return result;
    }
}
