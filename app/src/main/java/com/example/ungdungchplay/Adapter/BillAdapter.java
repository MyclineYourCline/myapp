package com.example.ungdungchplay.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungchplay.Database.OderDAO;
import com.example.ungdungchplay.Database.RoomDAO;
import com.example.ungdungchplay.Database.TableDAO;
import com.example.ungdungchplay.InterfaceManager.SendData.OderListener;
import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.ModelManager.Table;
import com.example.ungdungchplay.R;
import com.ramotion.foldingcell.FoldingCell;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> implements OderListener {
    private Context context;
    private List<Bill> list;
    private TableDAO tableDAO;
    private OderDAO oderDAO;
    private RoomDAO roomDAO;
    private OderDetailAdapter oderDetailAdapter;

    public BillAdapter(Context context) {
        this.context = context;
        tableDAO = new TableDAO(context);
        oderDAO = new OderDAO(context);
        roomDAO = new RoomDAO(context);
        oderDetailAdapter = new OderDetailAdapter(context,this);
    }
    public void setList (List<Bill> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_item,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = list.get(position);
        if (bill == null){ return;}
        Oder oder = oderDAO.getByID(String.valueOf(bill.getOderID()));
        Table table = tableDAO.getByID(oder.getTableID());
        Room room = roomDAO.queryById(String.valueOf(table.getRoomID()));

        holder.title_txtDate.setText(bill.getDate());
        holder.title_txt_total.setText(formatPrice(bill.getTotalMoney()));
        holder.title_txtTableName.setText(table.getName() +"-"+ room.getName());


        holder.fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.content_txtDate.setText(bill.getDate());
                holder.content_txt_total.setText(formatPrice(bill.getTotalMoney()));
                holder.content_txtTableName.setText(table.getName() +"-"+ room.getName());
                //
                List<Oder> listOder = oderDAO.getListByID(String.valueOf(bill.getOderID()));
                oderDetailAdapter.setList(listOder);
                holder.oderRecycleView.setAdapter(oderDetailAdapter);
                //
                holder.fc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.fc.toggle(false);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) return list.size();
        return 0;
    }

    @Override
    public void changeOder(int totalQuantity, int totalMoney) {

    }

    @Override
    public void clickEdit(Oder oder) {

    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView content_txtDate, content_txtTableName,content_txt_total,
                title_txtDate, title_txtTableName,title_txt_total;
        private RecyclerView oderRecycleView;
        private FoldingCell fc;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            content_txtDate = itemView.findViewById(R.id.item_cell_content_txt_date);
            content_txtTableName = itemView.findViewById(R.id.item_cell_content_txt_tableName);
            content_txt_total = itemView.findViewById(R.id.item_cell_content_txt_totalMoney);
            title_txtDate = itemView.findViewById(R.id.item_cell_title_txt_date);
            title_txtTableName = itemView.findViewById(R.id.item_cell_title_txt_tableName);
            title_txt_total = itemView.findViewById(R.id.item_cell_title_txt_total);
            oderRecycleView = itemView.findViewById(R.id.item_cell_content_rcv_oder);
            fc = itemView.findViewById(R.id.item_bill_foldingCell);

        }
    }
    private String formatPrice(int price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        String fPrice = numberFormat.format(price);
        return fPrice;
    }
}
