/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

public class MainActivity extends AppCompatActivity {

    private static final String[] state=
            {"Australian Capital Territory","New South Wales","Northern Territory",
                    "Queensland","South Australia","Tasmania","Victoria","Western Australia"};

    private static final String[] act={"Australian Capital Territory"};

    private static final String[] nsw={"Greater Sydney","Capital Region","Central Coast",
            "Central West","Coffs Harbour","Far West and Orana","Hunter Valley exc Newcastle",
            "Illawarra","Mid North Coast","Murray","Newcastle and Lake Macquarie",
            "New England and North West","Riverina","Southern Highlands and Shoalhaven",
            "Sydney - Baulkham Hills and Hawkesbury","Sydney - Blacktown","Sydney - City and Inner South",
            "Sydney - Eastern Suburbs","Sydney - Inner South West","Sydney - Inner West",
            "Sydney - Northern Beaches","Sydney - North Sydney and Hornsby",
            "Sydney - Outer South West","Sydney - Outer West and Blue Mountains","Sydney - Parramatta",
            "Sydney - Ryde","Sydney - South West","Sydney - Sutherland"};

    private static final String[] nt={"Greater Darwin","Northern Territory - Outback"};

    private static final String[] qld={"Greater Brisbane","Brisbane - East","Brisbane Inner City",
            "Brisbane - North","Brisbane - South","Brisbane - West","Cairns","Darling Downs - Maranoa",
            "Fitzroy","Gold Coast","Ipswich","Logan - Beaudesert","Mackay","Moreton Bay - North",
            "Moreton Bay - South","Queensland - Outback","Sunshine Coast","Toowoomba","Townsville",
            "Wide Bay"};

    private static final String[] sau={"Greater Adelaide","Adelaide - Central and Hills",
            "Adelaide - North","Adelaide - South","Adelaide - West","Barossa - Yorke - Mid North",
            "South Australia - Outback","South Australia - South East"};

    private static final String[] tas={"Greater Hobart","Launceston and North East","South East",
            "West and North West"};

    private static final String[] vic={"Greater Melbourne","Melbourne Inner","Melbourne Inner East",
            "Melbourne Inner South","Melbourne North East","Melbourne North West","Melbourne Outer East",
            "Melbourne South East","Melbourne West","Ballarat","Bendigo","Geelong","Hume","Shepparton",
            "North West", "Mornington Peninsula","Warrnambool and South West","Latrobe Gippsland"};

    private static final String[] wau={"Greater Perth","Bunbury","Mandurah","Perth - Inner",
            "Perth - North East","Perth - North West","Perth - South East","Perth - South West",
            "Western Australia - Outback","Western Australia - Wheat Belt"};

    private TextView view1,view2;
    private ImageButton next1;
    private Spinner spinner1,spinner2;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequestWithURLConnection();

        view1 = (TextView) findViewById(R.id.spinner1_textview);
        view2 = (TextView) findViewById(R.id.spinner2_textview);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,state);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new SpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

                String item = state[position];
                getArea(item);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setVisibility(View.VISIBLE);



        ImageButton web = (ImageButton) findViewById(R.id.webview);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.android.aurin_android_pengfeixu.WebViewActivity.class);
                String url = "http://aurin.org.au/";
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });



        next1 = (ImageButton) findViewById(R.id.next1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              sendRequestWithURLConnection();
                Intent intent = new Intent(MainActivity.this, com.example.android.aurin_android_pengfeixu.SecondActivity.class);
                intent.setAction("action");
                //Bundle bundle = new Bundle();
                //bundle.putParcelableArrayList("titles", titles);
                String bbox = spinner2.getSelectedItem().toString();
                BBOX filter_bbox = City_BBOX.city_bbox.get(bbox);
                Picked_City.picked_city = filter_bbox;
                // city_filter(filter_bbox);
                intent.putExtra("bbox", filter_bbox);
                System.out.println(filter_bbox.getHigherLa());
                startActivity(intent);
            }
        });



        ImageButton aboutus = (ImageButton) findViewById(R.id.about_us);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void city_filter(BBOX city){
        double city_lowla = city.getLowerLa();
        double city_lowlo = city.getLowerLon();
        double city_hila = city.getHigherLa();
        double city_hilo = city.getHigherLon();

        for (Capabilities cap : AllDatasets.lists){
            if (cap.bbox.getHigherLon()<city_lowlo && cap.bbox.getHigherLa()<city_lowla){
                AllDatasets.lists.remove(cap);
            }
            else if(cap.bbox.getLowerLa()>city_hila && cap.bbox.getLowerLon()>city_hilo){
                AllDatasets.lists.remove(cap);
            }

        }

    }

    // sending http request for all the datasets.
    private void sendRequestWithURLConnection() {
        //System.out.println("URL connection");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection;
                Authenticator.setDefault (new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication ("student", "dj78dfGF".toCharArray());
                    }
                });
                try{
                    //                    URL url = new URL("http://openapi.aurin.org.au/wfs?service=WFS&version=1.1.0&request=GetCapabilities")
                    URL url = new URL("http://openapi.aurin.org.au/wfs?service=WFS&version=1.1.0&request=GetCapabilities");
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
                    parseXMLWithPull(data);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // parsing the XML with pull method
    private void parseXMLWithPull (String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();

            String name ="";
            String title = "";
            String abstracts = "";
            String organization = "";
            String geoname = "";
            BBOX bbox = new BBOX();

            ArrayList<String> keywords = new ArrayList<>();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("Name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        }
                        else if ("Title".equals(nodeName)){
                            title = xmlPullParser.nextText();
                            String [] array1 = title.split(":");
                            organization = array1[1];
                        }
                        else if ("Abstract".equals(nodeName)){
                            String abstracts1 = xmlPullParser.nextText();
                            String [] array1 = abstracts1.split("\\.");
                            abstracts = array1[0];
                            if (abstracts1.contains("wkb_geometry")){
                                geoname = "wkb_geometry";
                            }
                            else if (abstracts1.contains("the_geom")){
                                geoname = "the_geom";
                            }
                            else if (abstracts1.contains("ogr_geometry")){
                                geoname = "ogr_geometry";
                            }
                            else if (abstracts1.contains("SHAPE")){
                                geoname = "SHAPE";
                            }
                            break;

                        }
                        else if ("ows:Keyword".equals(nodeName)){
                            String keyword = xmlPullParser.nextText();
                            keywords.add(keyword);
                        }
                        else if ("ows:LowerCorner".equals(nodeName)){
                            String[] lowerCorner = xmlPullParser.nextText().split(" ");
                            bbox.setLowerLon(Double.parseDouble(lowerCorner[0]));
                            bbox.setLowerLa(Double.parseDouble(lowerCorner[1]));
                        }
                        else if ("ows:UpperCorner".equals(nodeName)){
                            String[] upperCorner = xmlPullParser.nextText().split(" ");
                            bbox.setHigherLon(Double.parseDouble(upperCorner[0]));
                            bbox.setHigherLa(Double.parseDouble(upperCorner[1]));
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("FeatureType".equals(nodeName)) {
                            Capabilities cap = new Capabilities();
                            cap.name = name;
                            cap.title = title;
                            cap.organization = organization;
                            cap.abstracts = abstracts;
                            cap.keywords = keywords;
                            cap.geoname = geoname;
                            //cap.bbox = bbox;
                            cap.bbox.setHigherLa(bbox.getHigherLa());
                            cap.bbox.setHigherLon(bbox.getHigherLon());
                            cap.bbox.setLowerLa(bbox.getLowerLa());
                            cap.bbox.setLowerLon(bbox.getLowerLon());
                            switch (organization){
                                case " Government of New South Wales - Department of Planning and Environment":
                                    cap.image_id=R.drawable.government_of_new_south_wales_department_of_plaaning_and_envirnoment;
                                    break;
                                case " Internode Pty. Ltd.":
                                    cap.image_id=R.drawable.internode_pty_ltd;
                                    break;
                                case " Government of South Australia - RenewalSA":
                                    cap.image_id=R.drawable.government_of_south_australia_renewalsa;
                                    break;
                                case " Government of the Commonwealth of Australia - Geoscience Australia":
                                    cap.image_id=R.drawable.government_of_the_commonwealth_of_australia_geoscience_australia;
                                    break;
                                case " Local Government of Queensland - Brisbane City Council":
                                    cap.image_id=R.drawable.local_government_of_queensland_brisbane_city_council;
                                    break;
                                case " Local Government of South Australia - City of Salisbury":
                                    cap.image_id=R.drawable.local_government_of_south_australia_city_of_salisbury;
                                    break;
                                case " Government of South Australia - Department of Planning, Transport and Infrastructure":
                                    cap.image_id=R.drawable.government_of_south_australia_department_of_planning_transport_and_infrastructure;
                                    break;
                                case " Government of South Australia - Department for Communities and Social Inclusion":
                                    cap.image_id=R.drawable.government_of_south_australia_department_for_communities_and_social_inclusion;
                                    break;
                                case " Government of South Australia - Department of Environment, Water and Natural Resources":
                                    cap.image_id=R.drawable.government_of_south_australia_department_of_environment_water_and_aatural_resources;
                                    break;
                                case " Government of Queensland - Department of Transport and Main Roads - Road Statistics":
                                    cap.image_id=R.drawable.government_of_queensland_department_of_transport_and_main_roads_road_statistics;
                                    break;
                                case " Australian Government - Department of Social Services":
                                    cap.image_id=R.drawable.australian_government_department_of_social_services;
                                    break;
                                case " Government of South Australia - Local Government Association of South Australia":
                                    cap.image_id=R.drawable.government_of_south_australia_local_government_association_of_south_australia;
                                    break;
                                case " Government of the Commonwealth of Australia - Australian Bureau of Statistics":
                                    cap.image_id=R.drawable.government_of_the_commonwealth_of_australia_australian_bureau_of_statistics;
                                    break;
                                case " Government of New South Wales - Department of Education":
                                    cap.image_id=R.drawable.government_of_new_south_wales_department_of_education;
                                    break;
                                case " Government of Queensland - Department of Education and Training":
                                    cap.image_id=R.drawable.government_of_queensland_department_of_education_and_training;
                                    break;
                                case " Government of Queensland - Department of Natural Resources and Mines":
                                    cap.image_id=R.drawable.government_of_queensland_department_of_natural_resources_and_mines;
                                    break;
                                case " Government of South Australia - Attorney-General's Department":
                                    cap.image_id=R.drawable.government_of_south_australia_attorney_generals_department;
                                    break;
                                case " Government of South Australia - Department for Education and Child Development":
                                    cap.image_id=R.drawable.government_of_south_australia_department_for_education_and_child_development;
                                    break;
                                case " Government of South Australia - Department for State Development":
                                    cap.image_id=R.drawable.government_of_south_australia_department_for_state_development;
                                    break;
                                case "Government of South Australia - Department of Environment Water and Natural Resources":
                                    cap.image_id=R.drawable.government_of_south_australia_department_of_environment_water_and_natural_resources;
                                    break;
                                case " Government of South Australia - SA Health":
                                    cap.image_id=R.drawable.government_of_south_australia_sa_health;
                                    break;
                                case " Government of Tasmania - Department Of Primary Industries, Parks, Water And Environment":
                                    cap.image_id=R.drawable.government_of_tasmania_department_of_primary_industries_parks_water_and_environment;
                                    break;
                                case " Government of the Australian Capital Territory - Department of Education and Training":
                                    cap.image_id=R.drawable.government_of_the_australian_capital_territory_department_of_education_and_training;
                                    break;
                                case " Government of the Australian Capital Territory - Environment, Planning and Sustainable Development Directorate":
                                    cap.image_id=R.drawable.government_of_the_australian_capital_territory_environment_planning_long;
                                    break;
                                case " Government of the Commonwealth of Australia - Department of Human Services":
                                    cap.image_id=R.drawable.government_of_the_commonwealth_of_australia_department_of_human_services;
                                    break;
                                case " Government of Victoria - Crime Statistics Agency":
                                    cap.image_id=R.drawable.government_of_victoria_crime_statistics_agency;
                                    break;
                                case " Government of Victoria - Department of Education and Training":
                                    cap.image_id=R.drawable.government_of_victoria_department_of_education_and_training;
                                    break;
                                case " Government of Victoria - Department of Environment, Land, Water and Planning":
                                    cap.image_id=R.drawable.government_of_victoria_department_of_environment_land_water_and_planning;
                                    break;
                                case " Government of Victoria - Department of Health and Human Services":
                                    cap.image_id=R.drawable.government_of_victoria_department_of_health_and_human_services;
                                    break;
                                case " Government of Victoria - Victorian Commission for Gambling and Liquor Regulation":
                                    cap.image_id=R.drawable.government_of_victoria_victorian_commission_for_gambling_and_liquor_regulation;
                                    break;
                                case " NSW Bureau of Crime Statistics and Research":
                                    cap.image_id=R.drawable.nsw_bureau_of_crime_statistics_and_research;
                                    break;
                                case " Victoria State Government - Department of Treasury and Finance":
                                    cap.image_id=R.drawable.victoria_state_government_department_of_treasury_and_finance;
                                    break;
                                case " Melbourne Water Corporation":
                                    cap.image_id=R.drawable.melbourne_water_corporation;
                                    break;
                                default:
                                    cap.image_id=R.drawable.aurin;

                            }
                            if(! Big_Data.big_data.contains(cap.title))
                                AllDatasets.lists.add(cap);
                        }
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
    }

    // obtian areas related to the state.
    public void getArea(String str){
        String item=str;
        System.out.println(item);
        if(item.equals("Australian Capital Territory")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,act);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("New South Wales")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nsw);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("Northern Territory")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nt);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("Queensland")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,qld);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("South Australia")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sau);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("Tasmania")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tas);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else if(item.equals("Victoria")){
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vic);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
        else{
            adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,wau);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
            spinner2.setVisibility(View.VISIBLE);
        }
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }
}

