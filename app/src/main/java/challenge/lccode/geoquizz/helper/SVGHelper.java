package challenge.lccode.geoquizz.helper;

/**
 * Created by sebo on 6/4/17.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SVGHelper
{
    public static String getContent(Context context, String fileName)
    {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        String line = "";
        String Result = "";
        try
        {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);

            while ((line = bufReader.readLine()) != null)
                Result += line;

            System.out.print(Result);
            return Result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}