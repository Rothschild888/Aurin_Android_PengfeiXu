/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    ArrayList<String> org_selec = new ArrayList<>();
    ArrayList<Capabilities> cap3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        if ("action".equals(intent.getAction())){
            //cap3 = AllDatastes.lists;
            for (int i =0; i<AllDatasets.lists.size();i++){
                cap3.add(AllDatasets.lists.get(i));
            }
            System.out.println("sssssss"+cap3.size());
            boolean showflag = false;
            org_selec = (ArrayList<String>)intent.getSerializableExtra("organdselec");
            System.out.println(org_selec.get(0).toString());
            System.out.println(org_selec.get(1).toString());

            if(! org_selec.get(0).toString().equals("All Organizations")){
                for(int i=0,len= cap3.size();i<len; i++){
                    if(! cap3.get(i).organization.toString().equals(org_selec.get(0).toString())){
                        cap3.remove(i);
                        len--;
                        i--;
                    }
                }
            }

            boolean flag = false;
            String query;
            if (org_selec.size()==2)
                query = org_selec.get(1);
            else
                query = "";

            if(! (query.equals("")||query.equals("All Organizations"))){

                for(int i=0,len= cap3.size();i<len; i++){
                    ArrayList<String> key = cap3.get(i).keywords;
                    for (int j = 0; j < key.size(); j++) {
                        if (key.get(j).toLowerCase().matches(query.toLowerCase())) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag == false)
                        cap3.remove(i);
                    len--;
                    i--;
                }
            }

            showflag=true;

            if(showflag==true){
                CapAdapter adapter = new CapAdapter(ThirdActivity.this, R.layout.list_view_sub, cap3);
                ListView listView = (ListView) findViewById(R.id.list_view11);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Capabilities cap = cap3.get(position);
                        Intent intent = new Intent(ThirdActivity.this, DetailActivity.class);
                        intent.setAction("action");
                        intent.putExtra("capobj", cap);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
