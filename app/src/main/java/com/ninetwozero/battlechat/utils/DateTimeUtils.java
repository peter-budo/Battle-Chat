package com.ninetwozero.battlechat.utils;

import android.text.format.DateUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class DateTimeUtils {
    public final static int SECOND = 0;
    public final static int MINUTE = 60;
    public final static int HOUR = MINUTE*60;
    public final static int DAY = HOUR*24;

    private final static int OUTPUT_LIMIT = 2;
    private final static Map<Integer, String> literalMapping = new LinkedHashMap<Integer, String>() {{
        put(DAY, "d");
        put(HOUR, "h");
        put(MINUTE, "m");
        put(SECOND, "s");
    }};

    public static String toLiteral(final int seconds) {
        final StringBuilder stringBuilder = new StringBuilder();
        int secondsKeeper = seconds;
        int count;
        int numOutputs = 0;

        for (int key : literalMapping.keySet()) {
            count = key == 0? secondsKeeper : secondsKeeper / key;
            if (count > 0 || numOutputs > 0) {
                stringBuilder.append(count).append(literalMapping.get(key)).append(" ");
                secondsKeeper = key == 0? secondsKeeper : secondsKeeper % key;
                if (++numOutputs == OUTPUT_LIMIT) {
                    break;
                }
            }
        }
        return stringBuilder.toString().trim();
    }

    public static String toRelative(final long timeInSeconds) {
        return DateUtils.getRelativeTimeSpanString(timeInSeconds*1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            //timeInSeconds * 1000, SystemClock.currentThreadTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }
}
