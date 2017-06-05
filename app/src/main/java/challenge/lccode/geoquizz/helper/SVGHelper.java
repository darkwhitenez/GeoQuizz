package challenge.lccode.geoquizz.helper;

/**
 * Created by sebo on 6/4/17.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SVGHelper {
    private static String svg = "";

    public static String getContent(Context context, String fileName) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        String line = "";
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);

            while ((line = bufReader.readLine()) != null)
                svg += line;

            colorCountry("RS", "");
            return svg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void colorCountry(String id, String opacity) {
        int idIndex = svg.indexOf(id);
        int styleIndex = svg.indexOf("style", idIndex);

        String replaceWith = "style=\"fill:#ff0000;fill-rule:evenodd\"";
        String forReplacement = svg.substring(styleIndex, styleIndex+38);

        String svgStart = svg.substring(0, styleIndex-1);
        String svgMiddle = svg.replace(forReplacement, replaceWith).substring(styleIndex, styleIndex+38);
        String svgEnd = svg.substring(styleIndex+39);

        svg = svgStart + svgMiddle + svgEnd;
    }
}
