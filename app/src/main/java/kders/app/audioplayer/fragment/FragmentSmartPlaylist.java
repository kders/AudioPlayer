package kders.app.audioplayer.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kders.app.audioplayer.Adapter.OptionAdapter;
import kders.app.audioplayer.Adapter.TrackAdapter;
import kders.app.audioplayer.R;
import kders.app.audioplayer.listener.RecyclerTouchListener;
import kders.app.audioplayer.model.Option;
import kders.app.audioplayer.model.Track;

/**
 * Created by User on 10/14/2017.
 */

public class FragmentSmartPlaylist extends Fragment {
    private List<Option> optionList = new ArrayList<>();
    private RecyclerView recyclerOtionView;
    private List<Track> trackList = new ArrayList<>();
    private RecyclerView recyclerTrackView;
    OptionAdapter myOptionAdapter;
    TrackAdapter myTrackAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public FragmentSmartPlaylist() {
    }
    public static FragmentSmartPlaylist newInstance(String param1, String param2) {
        FragmentSmartPlaylist fragment = new FragmentSmartPlaylist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView =  inflater.inflate(R.layout.fragment_smart_playlist, container, false);
        recyclerOtionView = (RecyclerView) rootView.findViewById(R.id.recycler_option_view);

        myOptionAdapter = new OptionAdapter(optionList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        recyclerOtionView.setLayoutManager(layoutManager);
        recyclerOtionView.setItemAnimator(new DefaultItemAnimator());
        recyclerOtionView.setAdapter(myOptionAdapter);
        prepareOption();
        recyclerOtionView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(),recyclerOtionView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Option movie = optionList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), movie.getType() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerTrackView = (RecyclerView) rootView.findViewById(R.id.recycler_track_view);
        myTrackAdapter = new TrackAdapter(trackList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerTrackView.setLayoutManager(layoutManager2);
        recyclerTrackView.setItemAnimator(new DefaultItemAnimator());
        recyclerTrackView.setAdapter(myTrackAdapter);
        prepareTrack();
        return rootView;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
