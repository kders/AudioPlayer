package kders.app.audioplayer.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kders.app.audioplayer.model.Option;
import kders.app.audioplayer.R;

/**
 * Created by User on 10/14/2017.
 */

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

