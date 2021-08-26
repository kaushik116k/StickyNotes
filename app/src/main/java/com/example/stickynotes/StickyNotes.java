package com.example.stickynotes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StickyNotes {
    
    void setStick(String textToBeSaved, Context context)
    {
        String text = textToBeSaved;
        FileOutputStream fos = null;
        try
        {
            fos = context.getApplicationContext().openFileOutput("gfg.txt",Context.MODE_APPEND);
            fos.write(text.getBytes());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
