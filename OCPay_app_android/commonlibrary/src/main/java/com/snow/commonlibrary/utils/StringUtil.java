package com.snow.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;

    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String checkEmpty(String str) {
        if (str == null)
            return "";

        return str;
    }

    public static String bracket(Object o) {
        return "(" + o + ")";
    }

    public static String bracketIntGt1Lt99(int o) {
        if (o > 99)
            o = 99;
        return o > 1 ? "(" + o + ")" : "";
    }

    public static String toString(Object o) {
        if (o == null)
            return "";

        if (o instanceof String)
            return (String) o;

        return o.toString();
    }

    @SuppressLint("SimpleDateFormat")
    static final SimpleDateFormat sDateFormat = new SimpleDateFormat("hh:mm");

    public static String formatTimeAgo(long time) {
        long current = System.currentTimeMillis();
        long diffInSecond = (current - time) / 1000L;
        if (diffInSecond < 0)
            diffInSecond = 0;

        if (diffInSecond < 60) {
            return "Now";
        }

        if (diffInSecond < 3600) {
            return "" + (diffInSecond / 60) + " min";
        }

        return sDateFormat.format(new Date(time));
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    private static final String[] padding = {"", " ", "  ", "   ", "    ",
            "     ", "      ", "       ", "        ", "         ", "          "};

    public static String join(Collection<?> strings, String sep) {
        return join(strings.iterator(), sep);
    }

    public static String join(Iterator<?> strings, String sep) {
        if (!strings.hasNext()) {
            return "";
        }
        String start = strings.next().toString();
        if (!strings.hasNext()) {
            return start;
        }
        StringBuilder sb = new StringBuilder(64).append(start);
        while (strings.hasNext()) {
            sb.append(sep);
            sb.append(strings.next());
        }
        return sb.toString();
    }

    public static String padding(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("width must be > 0");
        }
        if (width < padding.length) {
            return padding[width];
        }
        char[] out = new char[width];
        for (int i = 0; i < width; i++)
            out[i] = ' ';
        return String.valueOf(out);
    }

    public static boolean isBlank(String string) {
        if ((string == null) || (string.length() == 0)) {
            return true;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!isWhitespace(string.codePointAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isNumeric(String string) {
        if ((string == null) || (string.length() == 0)) {
            return false;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(string.codePointAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isWhitespace(int c) {
        return (c == 32) || (c == 9) || (c == 10) || (c == 12) || (c == 13);
    }

    public static String normaliseWhitespace(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        appendNormalisedWhitespace(sb, string, false);
        return sb.toString();
    }

    public static void appendNormalisedWhitespace(StringBuilder accum,
                                                  String string, boolean stripLeading) {
        boolean lastWasWhite = false;
        boolean reachedNonWhite = false;

        int len = string.length();
        int c;
        for (int i = 0; i < len; i += Character.charCount(c)) {
            c = string.codePointAt(i);
            if (isWhitespace(c)) {
                if (((!stripLeading) || (reachedNonWhite)) && (!lastWasWhite)) {
                    accum.append(' ');
                    lastWasWhite = true;
                }
            } else {
                accum.appendCodePoint(c);
                lastWasWhite = false;
                reachedNonWhite = true;
            }
        }
    }

    public static boolean in(String needle, String... haystack) {
        for (String hay : haystack) {
            if (hay.equals(needle))
                return true;
        }
        return false;
    }

    // static final Pattern sBlankPattern = Pattern.compile("\\s*|\t|\r|\n");
    static final Pattern sBlankPattern = Pattern.compile("\t|\r|\n");

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = sBlankPattern.matcher(str);
            dest = m.replaceAll(" ");
        }
        return dest;
    }

    public static String limitLength(String str, int maxLength) {
        if (str == null)
            return null;

        if (str.length() < maxLength)
            return str;

        return str.substring(0, Math.max(0, maxLength));
    }

    public static String limitLength200ReplaceBlank(String str) {
        return replaceBlank(limitLength(str, 200));
    }

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    private final static Pattern sStrictEmailPattern = Pattern
            .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public static boolean isStrictEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;

        return sStrictEmailPattern.matcher(email.trim()).matches();
    }

    public static String getString(Context ctx, int resId) {
        try {
            return ctx.getString(resId);
        } catch (Exception e) {
            return "";
        }
    }


    public static SpannableStringBuilder setPartTextColor(Context context, String str, int start, int end, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan buildColor = new ForegroundColorSpan(color);
        builder.setSpan(buildColor, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public static SpannableStringBuilder setPartTextStyleAndColor(Context context, String str, int start, int end, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan buildColor = new ForegroundColorSpan(color);
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(buildColor, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
