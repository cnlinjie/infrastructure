package org.linjie.framework.util;


public class MappingUtils {

    private static final double EARTH_RADIUS = 6378137;

    /**
     * 计算两坐标 之间距离
     *
     * @param startLat 开始纬度
     * @param startLng 开始经度
     * @param endLat 结束纬度
     * @param endLng 结束纬度
     * @return
     */
    public static double calculateDistance(double startLat, double startLng, double endLat, double endLng) {
        double radStartLat = rad(startLat);
        double radEndLat = rad(endLat);
        double latDifference = radStartLat - radEndLat;
        double lngDifference = rad(startLng) - rad(endLng);
        double sineValue = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDifference / 2), 2) +
                Math.cos(radStartLat) * Math.cos(radEndLat) * Math.pow(Math.sin(lngDifference / 2), 2)));

        sineValue = sineValue * EARTH_RADIUS;
        sineValue = Math.round(sineValue * 10000) / 10000;

        return sineValue;
    }

    /**
     * 画点角度。
     * @param d
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}
