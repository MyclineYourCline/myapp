package com.example.ungdungchplay.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.InterfaceManager.SendData.TableListener;
import com.example.ungdungchplay.ModelManager.Table;
import com.example.ungdungchplay.R;

import java.util.ArrayList;
import java.util.List;
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> implements Filterable {
    private Context context;
    private List<Table> list;
    private List<Table> listOld;
    private TableListener listener;

    public TableAdapter(Context context,TableListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<Table> list){
        this.list =  list;
        this.listOld = this.list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TableAdapter.TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_item,parent,false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.TableViewHolder holder, int position) {
        Table table = list.get(position);
        if (table == null){
            return;
        }
        holder.txt_name.setText(table.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sendData(DbStruct.ITEM_CLICK,table);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.sendData(2,table);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        if (list.size() != 0) return list.size();
        else return 0;
    }
    public class TableViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_name;
        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.itemTable_txtName);
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
                    List<Table> mList1 =  new ArrayList<>();
                    for (Table x: listOld){
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

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Table>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
