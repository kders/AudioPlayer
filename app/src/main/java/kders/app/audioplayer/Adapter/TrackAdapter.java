package kders.app.audioplayer.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kders.app.audioplayer.R;
import kders.app.audioplayer.model.Track;

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
