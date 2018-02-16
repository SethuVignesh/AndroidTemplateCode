package com.sethu.androidtemplatecode.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.sethu.androidtemplatecode.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoordinatorLayoutExamples extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_examples);

        final ListView listview = (ListView) findViewById(R.id.list_view);
        final String[] values = new String[]{"Snackbar and FAB", "FAB follows Widget", "CollapsingToolbar and Appbar",
                "Implementing custom behaviors"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
                switch (item){
                    case "Snackbar and FAB":
                        startActivity(new Intent(CoordinatorLayoutExamples.this, FabAndSnackbarActivity.class));

                        break;

                    case "FAB follows Widget":
                        startActivity(new Intent(CoordinatorLayoutExamples.this, FabFollowsWidgetActivity.class));

                        break;
                    case "CollapsingToolbar and Appbar":
                        startActivity(new Intent(CoordinatorLayoutExamples.this, CollapsingToolbarActivity.class));

                        break;
                    case "Implementing custom behaviors":
                        startActivity(new Intent(CoordinatorLayoutExamples.this, MainActivity.class));

                        break;
                }

            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
