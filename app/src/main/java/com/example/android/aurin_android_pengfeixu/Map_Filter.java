/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;

public class Map_Filter extends AppCompatActivity {

    ArrayList<String> attributes = new ArrayList<>();
    ArrayList<String> classifiers = new ArrayList<>();
    private static String[] colors = {"Red","Blue","Green","Gray","Purple"};
    private static String[] level = {"1","2","3","4","5","6"};
    public static final int SHOW_RESPONSE = 0;

    private Spinner sele_atrri;
    private Spinner sele_class;
    private Spinner sele_level;
    private Spinner sele_color;
    private SeekBar sele_opacity;
    private TextView seek_value;
    private ImageButton view;
    private ImageButton fig;

    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    parseXMLWithPull(response);
                    sele_atrri.setAdapter(adapter1);
                    sele_class.setAdapter(adapter2);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_filter);
        sendRequestWithURLConnection();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        sele_atrri = (Spinner) findViewById(R.id.select_attribute);
        sele_class = (Spinner) findViewById(R.id.select_classifier);
        sele_level = (Spinner) findViewById(R.id.select_level);
        sele_color = (Spinner) findViewById(R.id.select_color);
        seek_value = (TextView) findViewById(R.id.seek_value);
        sele_opacity = (SeekBar) findViewById(R.id.select_opacity);
        view = (ImageButton) findViewById(R.id.submit);
        fig = (ImageButton) findViewById(R.id.figure_view);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,attributes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sele_atrri.setAdapter(adapter1);
        sele_atrri.setSelection(attributes.size() - 1);
        sele_atrri.setOnItemSelectedListener(new SpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sele_atrri.setVisibility(View.VISIBLE);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,classifiers);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sele_class.setAdapter(adapter2);
        sele_class.setSelection(classifiers.size() - 1);
        sele_class.setOnItemSelectedListener(new SpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sele_class.setVisibility(View.VISIBLE);


        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,colors);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sele_color.setAdapter(adapter4);
        sele_color.setOnItemSelectedListener(new SpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sele_color.setVisibility(View.VISIBLE);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,level);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sele_level.setAdapter(adapter3);
        sele_level.setOnItemSelectedListener(new SpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sele_level.setVisibility(View.VISIBLE);

        sele_opacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //Toast.makeText(Map_Filter.this, "滑动结束", Toast.LENGTH_LONG).show();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //Toast.makeText(Map_Filter.this, "滑动开始", Toast.LENGTH_LONG).show();
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                seek_value.setText("Select a opacity:  " + progress + "%");
                // TODO Auto-generated method stub

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!sele_atrri.getSelectedItem().equals(null)) {
                    String attselec = sele_atrri.getSelectedItem().toString();
                    Map_Setting.attribute = attselec;
                } else {
                    Map_Setting.attribute = "NO attributes";
                }

                if (!sele_class.getSelectedItem().equals(null)) {
                    String classselec = sele_class.getSelectedItem().toString();
                    Map_Setting.classifier = classselec;
                } else {
                    Map_Setting.classifier = "1";
                }

                String levelselec = sele_level.getSelectedItem().toString();
                Map_Setting.level = levelselec;

                String colorselec = sele_color.getSelectedItem().toString();
                Map_Setting.color_select = colorselec;

                int opacityselec = sele_opacity.getProgress();
                Map_Setting.opacity = opacityselec;

                Intent intent = new Intent(Map_Filter.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        fig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(! sele_atrri.getSelectedItem().equals(null)){
                    String attselec = sele_atrri.getSelectedItem().toString();
                    Map_Setting.attribute = attselec;
                }
                else{
                    Map_Setting.attribute = "NO attributes";
                }

                if(! sele_class.getSelectedItem().equals(null)){
                    String classselec = sele_class.getSelectedItem().toString();
                    Map_Setting.classifier = classselec;
                }
                else {
                    Map_Setting.classifier = "1";
                }

                String levelselec = sele_level.getSelectedItem().toString();
                Map_Setting.level = levelselec;

                String colorselec = sele_color.getSelectedItem().toString();
                Map_Setting.color_select = colorselec;

                int opacityselec = sele_opacity.getProgress();
                Map_Setting.opacity = opacityselec;

                Intent intent = new Intent(Map_Filter.this, ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    // sending the http request for type descriptions of certain dataset
    private void sendRequestWithURLConnection() {
        final String typename = Picked_City.cap_picked.name;
        //System.out.println("URL connection");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                Authenticator.setDefault (new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication ("student", "dj78dfGF".toCharArray());
                    }
                });
                try{
//                    URL url = new URL("http://openapi.aurin.org.au/wfs?request=DescribeFeatureType&service=WFS&version=1.1.0&typeName=aurin:datasource-SA_LGovt_CSC-UA_WISeR_csc_contours_2009");
//                    URL url = new URL("http://openapi.aurin.org.au/wfs?request=DescribeFeatureType&service=WFS&version=1.1.0&typeName=aurin:datasource-NSW_Govt_DPE-UoM_AURIN_DB_nsw_srlup_additional_rural_2014");
                    URL url = new URL("http://openapi.aurin.org.au/wfs?request=" +
                            "DescribeFeatureType&service=WFS&version=1.1.0&TypeName="+typename);
                    System.out.println(url);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //System.out.println(" connection complete ");
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    //System.out.println("line complete");
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    String data = response.toString();
                    //System.out.println(data);
                    //parseXMLWithPull(data);
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = data;
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    // paring the xml with pull methods.
    private void parseXMLWithPull (String xmlData) {

        System.out.println("Start parsing");

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();

            String attribute;
            String classifier;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                System.out.println(nodeName);
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("xsd:element".equals(nodeName)) {
                            String type = xmlPullParser.getAttributeValue(null,"type");
                            if (type.equals("xsd:string")){
                                attribute = xmlPullParser.getAttributeValue(null,"name");
                                System.out.println(attribute);
                                attributes.add(attribute);
                            }
                            else if (type.equals("xsd:double")){
                                classifier = xmlPullParser.getAttributeValue(null, "name");
                                System.out.println(classifier);
                                classifiers.add(classifier);
                            }
                            else if (type.equals("xsd:float")){
                                classifier = xmlPullParser.getAttributeValue(null, "name");
                                System.out.println(classifier);
                                classifiers.add(classifier);
                            }
                            else if (type.equals("xsd:int")){
                                classifier = xmlPullParser.getAttributeValue(null, "name");
                                System.out.println(classifier);
                                classifiers.add(classifier);
                            }
                            else {
                                classifier = xmlPullParser.getAttributeValue(null, "name");
                                System.out.println(classifier);
                                classifiers.add(classifier);
                            }
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("xsd:element".equals(nodeName))
                            break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Finish");
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }
}
