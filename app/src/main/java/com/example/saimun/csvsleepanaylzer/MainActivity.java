package com.example.saimun.csvsleepanaylzer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.*;


public class MainActivity extends AppCompatActivity {

    public int count1_10 = 0;
    public int count11_20 = 0;
    public int count21_30 = 0;
    public int count31_40 = 0;
    public int count41_50 = 0;
    public int count51_60 = 0;
    public int count61_70 = 0;
    public int count71_80 = 0;
    public int count81_90 = 0;
    public int count91_100 = 0;
    public int count101_110 = 0;
    public int count111_120 = 0;
    public int count121_130 = 0;
    public int count131_140 = 0;
    public int count141_150 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processSleepData();
    }


    public void processSleepData() {

        try {
            TextView textElement = (TextView) findViewById(R.id.text);
            textElement.setMovementMethod(new ScrollingMovementMethod());

            InputStreamReader csvStreamReader = new InputStreamReader(
                    MainActivity.this.getAssets().open(
                            "ParsedData.csv"));

            CSVReader reader = new CSVReader(csvStreamReader);
            String [] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                String [] sleepData = nextLine[1].split(";");

                for (int i=0; i< sleepData.length;
                    int uniqueId = Integer.parseInt(nextLine[0]);
                    i++ ) {

                    int[] data = hexStringToInt(sleepData[i].substring(14));
                    //System.out.println("Local Id: " + nextLine[0] + " sleepData: " +  Arrays.toString(data));
                    textElement.append("LocalId: " + nextLine[0] + " sleepData: " + Arrays.toString(data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)   //"<<" is the binary left shift operator
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static int[] hexStringToInt(String s) {
        int len = s.length();
        int stringCounter = 0;

        int[] data = new int[len/2];
        for (int i=0; i < len; i +=2) {
            String HexStr = s.substring(stringCounter, stringCounter + 2);
            stringCounter += 2;
            data[i / 2] = Integer.parseInt(HexStr, 16);
        }
        return data;
    }


//    public ArrayList<ArrayList<ArrayList<Integer>>> processSleepData() {
//        ArrayList<ArrayList<ArrayList<Integer>>> sleepHrsData = new ArrayList<>();
//        //SleepHrsData = 1 sleep data
//        //SleepMinData = store one 15 min sleep data
//        if (sleepPatternItem != null) {
//            ArrayList<ArrayList<Integer>> sleepMinData = new ArrayList<>();
//
//            String[] data = sleepPatternItem.getSleepData().split(";");
//            deepStart = false;
//            countStop = false;
//            boolean isFrontZeros = true;
//            int frontZerosMin = 0;
//            for (int i = 0; i < data.length; i++) {
//                //each time block (15min), calc sleep quality value (average from 2min)
//                //4 time block = 1hr block
//                byte[] databytes = StringUtils.hexStringToByteArray(data[i]);
//                if (databytes.length > 0) {
//                    String hextime = StringUtils.byteToHexString(databytes[5]);
//                    int timeblock = Integer.parseInt(hextime, 16) % 4;
//                    if (timeblock == 0 && sleepMinData.size() > 0) {
//                        //next hr ---> store previous hour
//                        if (sleepMinData.size() > 0)
//                            sleepHrsData.add(sleepMinData);
//                        sleepMinData = new ArrayList<>();
//                    }
//
//                    //calc sleep quality average: sum of 2min quality value / sum of 2min blocks
//                    int quality = 0;
//                    int num = 0;
//
//                    ArrayList<Integer> sleep2MinData = new ArrayList<>();
//                    for (int j = 7; j < databytes.length; j++) {
//                        String hexMin = StringUtils.byteToHexString(databytes[j]);
//                        int min = Integer.parseInt(hexMin, 16);
//
//                    }
//                }
//            }
//        }
//    }
}