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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity{

    ArrayList<Capabilities> cap2 = new ArrayList<>();
    ArrayList<String> spinner_Items = new ArrayList<>();
    ArrayAdapter<String> adapter1;
    public Spinner spinner;
    public EditText eSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("SSSSSSSSSSSATR 2222222!!!");

        Intent intent = getIntent();
        if ("action".equals(intent.getAction())){
            BBOX filter_bbox;
            filter_bbox = (BBOX)intent.getSerializableExtra("bbox");
            Picked_City.picked_city = filter_bbox;
            for (int i = 0; i< AllDatasets.lists.size();i++){
                cap2.add(AllDatasets.lists.get(i));
            }
            System.out.println("doooooooooooooo filter!!!");
            System.out.println("finishhhhhhhhhhhhh filter!!!");

            for(int i =0; i<cap2.size(); i++){
                String org = cap2.get(i).organization;
                if(! spinner_Items.contains(org))
                    spinner_Items.add(org);
            }
            spinner_Items.add("All Organizations");

            spinner = (Spinner) findViewById(R.id.organization);

            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,spinner_Items);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setSelection(spinner_Items.size()-1);
            spinner.setOnItemSelectedListener(new SpinnerSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setVisibility(View.VISIBLE);

            CapAdapter adapter = new CapAdapter(SecondActivity.this, R.layout.list_view_sub, cap2);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            System.out.println(filter_bbox.getHigherLa());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Capabilities cap = cap2.get(position);
                    Intent intent = new Intent(SecondActivity.this, DetailActivity.class);
                    intent.setAction("action");
                    intent.putExtra("capobj", cap);
                    startActivity(intent);
                }
            });

            ImageButton go;

            eSearch = (EditText) findViewById(R.id.etSearch);


            go = (ImageButton) findViewById(R.id.keyword);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String orgselec = spinner.getSelectedItem().toString();
                    final String search = eSearch.getText().toString();
                    ArrayList<String> organdselec = new ArrayList<>();
                    organdselec.add(orgselec);
                    organdselec.add(search);

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.setAction("action");
                    intent.putExtra("organdselec",organdselec);
                    startActivity(intent);
                }
            });
        }


    }

    private void filter(BBOX filter_bbox) {
        System.out.println("ininininini!");

        double citylowlo = filter_bbox.getLowerLon();
        double citylowla = filter_bbox.getHigherLa();
        double cityhighlo = filter_bbox.getHigherLon();
        double cityhighla = filter_bbox.getHigherLa();
        double temp;

        for(int i=0, len =cap2.size();i<len;i++ ){
            double datalowlo = cap2.get(i).bbox.getLowerLon();
            double datalowla = cap2.get(i).bbox.getHigherLa();
            double datahighlo = cap2.get(i).bbox.getHigherLon();
            double datahgihla = cap2.get(i).bbox.getHigherLa();

            if(cityhighlo < citylowlo){
                temp = cityhighlo;
                cityhighlo = citylowlo;
                citylowlo = temp;
            }
            if(cityhighla < citylowla){
                temp = cityhighla;
                cityhighla = citylowla;
                citylowla = temp;
            }
            if(datahighlo < datalowlo){
                temp = datahighlo;
                datahighlo = datalowlo;
                datalowlo = temp;
            }
            if(datahgihla < datalowla){
                temp = datahgihla;
                datahgihla = datalowla;
                datalowla = temp;
            }
            if (max(citylowlo,datalowlo) > min(cityhighlo,datahighlo) ||
                    max(citylowla,datalowla) > min(cityhighla,datahgihla)){
                cap2.remove(i);
                len--;
                i--;
            }
        }

        for(int i = 0; i< cap2.size(); i++){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+cap2.get(i).name);
        }
        System.out.println("outouotuotuotuo!");
    }

    private double max(double a, double b) {
        if(a>=b)
            return a;
        else
            return b;
    }

    private double min(double a, double b) {
        if (a<=b)
            return a;
        else
            return b;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view0, int position, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }

}