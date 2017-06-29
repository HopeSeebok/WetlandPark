package com.yunwei.wetlandpark.ui.mainFunctions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yunwei.wetlandpark.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hopeseebok on 27/06/2017.
 */

public class SignInDetailsAdapter extends RecyclerView.Adapter<SignInDetailsAdapter.ItemViewHolder> {

    private List<String> stringList;
    private List<Boolean> states;

    public SignInDetailsAdapter(List<String> stringList) {
        this.stringList = stringList;
        states = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            states.add(Boolean.FALSE);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sign_in_details, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.checkBox.setText(stringList.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                states.set(holder.getAdapterPosition(),b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_box)
        CheckBox checkBox;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<String> getCheckedDetails() {
        ArrayList<String> checkedDetails = new ArrayList<>();
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i)) {
                checkedDetails.add(stringList.get(i));
            }
        }
        return checkedDetails;
    }

}
