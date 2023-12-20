package com.example.ungdungchplay.Adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.InterfaceManager.RoomListener;
import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.R;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> implements Filterable {
    private Context context;
    private List<Room> list;
    private List<Room> listOld;
    private RoomListener listener;

    public RoomAdapter(Context context,RoomListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<Room> list){
        this.list =  list;
        this.listOld = this.list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_item,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
            Room room = list.get(position);
            if (room == null){
                return;
            }
            holder.txt_name.setText(room.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.sendData(DbStruct.ITEM_CLICK,room);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.sendData(DbStruct.ITEM_LONG_CLICK,room);
                }
            });
    }
    @Override
    public int getItemCount() {
        if (list.size() != 0) return list.size();
        else return 0;
    }
    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_name;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.itemRoom_txtName);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString();
                if (query.isEmpty()){
                    list = listOld;
                }
                else {
                    List<Room> mList1 =  new ArrayList<>();
                    for (Room x: listOld){
                        if (x.getName().toLowerCase().contains(query.toLowerCase())){
                            mList1.add(x);
                        }
                    }
                    list = mList1;
                }
                FilterResults results = new FilterResults();
                results.values =  list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Room>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
