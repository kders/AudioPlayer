package kders.app.audioplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<Option> optionList = new ArrayList<>();
    private RecyclerView recyclerOtionView;
    private List<Track> trackList = new ArrayList<>();
    private RecyclerView recyclerTrackView;
    OptionAdapter myOptionAdapter;
    TrackAdapter myTrackAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerOtionView = (RecyclerView) findViewById(R.id.recycler_option_view);

        myOptionAdapter = new OptionAdapter(optionList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerOtionView.setLayoutManager(layoutManager);
        recyclerOtionView.setItemAnimator(new DefaultItemAnimator());
        recyclerOtionView.setAdapter(myOptionAdapter);
        prepareOption();
        recyclerOtionView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerOtionView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Option movie = optionList.get(position);
                Toast.makeText(getApplicationContext(), movie.getType() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerTrackView = (RecyclerView) findViewById(R.id.recycler_track_view);
        myTrackAdapter = new TrackAdapter(trackList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerTrackView.setLayoutManager(layoutManager2);
        recyclerTrackView.setItemAnimator(new DefaultItemAnimator());
        recyclerTrackView.setAdapter(myTrackAdapter);
        prepareTrack();

    }
    private void prepareOption() {
        Option option = new Option("TRACKS");
        optionList.add(option);
        option = new Option("ALBUMS");
        optionList.add(option);
        option = new Option("ARTISTS");
        optionList.add(option);
        option = new Option("GENRES");
        optionList.add(option);
        option = new Option("PLAYLISTS");
        optionList.add(option);
        myOptionAdapter.notifyDataSetChanged();
    }
    private void prepareTrack() {
        Track track = new Track("Noi nay co anh","Son Tung MTP", "2:41");
        trackList.add(track);
        track = new Track("Noi nay co anh","Son Tung MTP", "2:41");
        trackList.add(track);
        track = new Track("Noi nay co anh","Son Tung MTP", "2:41");
        trackList.add(track);
        track = new Track("Noi nay co anh","Son Tung MTP", "2:41");
        trackList.add(track);
        track = new Track("Noi nay co anh","Son Tung MTP", "2:41");
        trackList.add(track);
        myTrackAdapter.notifyDataSetChanged();
    }
    public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {

        private List<Option> optionList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView type;

            public MyViewHolder(View view) {
                super(view);
                type = (TextView) view.findViewById(R.id.optionTextView);

            }
        }


        public OptionAdapter(List<Option> optionList) {
            this.optionList = optionList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_option_items_layout, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Option option = optionList.get(position);
            holder.type.setText(option.getType());

        }

        @Override
        public int getItemCount() {
            return optionList.size();
        }
    }
    public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {

        private List<Track> trackList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView singer;
            public TextView duration;
            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.txt_name);
                singer = (TextView) view.findViewById(R.id.txt_singer);
                duration = (TextView) view.findViewById(R.id.txt_duration);
            }
        }


        public TrackAdapter(List<Track> trackList) {
            this.trackList = trackList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_track_item_layout, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Track track = trackList.get(position);
            holder.name.setText(track.getName());
            holder.singer.setText(track.getSinger());
            holder.duration.setText(track.getDuration());

        }

        @Override
        public int getItemCount() {
            return trackList.size();
        }
    }
}
