package com.sunco.rolewriter;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 12/8/2015.
 */
public class StoryList extends Fragment {
    // declare class variables
    public static final String ARG_PAGE = "Arg_Page";
    String[] storyname;
    ListView listView;
    DBHandler appDB;

    /*public static StoryListFragment newInstance (int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        StoryListFragment fragment = new StoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDB = appDB.getInstance(getActivity());
    }

    /** Called when the activity is first created. */
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View storylistview = inflater.inflate(R.layout.fragment_story_list,
                container, false);

        List<StoryClass> stories = appDB.getAllStories();
        int storyCount = appDB.getStoryCount();

        storyname = new String[storyCount];

        int i =0;
        //String log ="";
        for (StoryClass s : stories) {
            storyname[i] = s.getTitle();
            i++;
        }

        StoryAdapter adapter = new StoryAdapter(this.getActivity(), storyname);

        listView = (ListView) storylistview.findViewById(R.id.storyList);

        listView.setAdapter(adapter);

/*
        super.onCreate(savedInstanceState);

        appDB = DBHandler.getInstance(getContext());
        //Context context = getContext();

        // instantiate our ItemAdapter class
        m_adapter = new StoryAdapter(getContext(), R.layout.story_items, m_parts);
        setListAdapter(m_adapter);

        // here we are defining our runnable thread.
        viewParts = new Runnable(){
            public void run(){
                handler.sendEmptyMessage(0);
            }
        };

        // here we call the thread we just defined - it is sent to the handler below.
        Thread thread =  new Thread(null, viewParts, "MagentoBackground");
        thread.start();
*/
        return storylistview;
    }
/*
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            // create some objects
            // here is where you could also request data from a server
            // and then create objects from that data.
            List<StoryClass> storyList = appDB.getAllStories();

            for (StoryClass s : storyList){
                m_parts.add(new StoryItems(s.getTitle()));
            }

            m_adapter = new StoryAdapter(StoryListFragment.this.getContext(), R.layout.story_items, m_parts);

            // display the list.
            setListAdapter(m_adapter);


            }
    };

*/
}
