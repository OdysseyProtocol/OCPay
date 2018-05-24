package com.stormfives.ocpay.common.util;



import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Random;

/**
 * Created by zxb
 */
public class PictureCodeUtil {

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();
    static {
        cs.setColorFactory(x -> {
            int[] c = new int[3];
            int i = random.nextInt(c.length);
            for (int fi = 0; fi < c.length; fi++) {
                if (fi == i) {
                    c[fi] = random.nextInt(71);
                } else {
                    c[fi] = random.nextInt(256);
                }
            }
            return new Color(c[0], c[1], c[2]);
        });
        cs.setBackgroundFactory(image -> {
            Graphics graphics = image.getGraphics();
            graphics.setColor(new Color(235, 235, 235));
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }
    public static void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
    public static ConfigurableCaptchaService getCS(){
        return cs;
    }
    public static Random getRandom(){
        return random;
    }
}
