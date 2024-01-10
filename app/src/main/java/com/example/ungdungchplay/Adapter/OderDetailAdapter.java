package com.example.ungdungchplay.Adapter;

import static android.util.Log.d;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.SendData.OderListener;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.R;

import java.text.NumberFormat;
import java.util.List;

public class OderDetailAdapter extends RecyclerView.Adapter<OderDetailAdapter.OderDetailAdapterViewHolder>{
    private Context context;
    private List<Oder> list;
    private OderListener listener;
    private ServiceDAO serviceDAO;
    private Service service;
    private String quantity,totalMoney,price;

    public OderDetailAdapter(Context context , OderListener listener) {
        this.context = context;
        this.listener = listener;
        serviceDAO = new ServiceDAO(context);
    }
    public void setList (List<Oder> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OderDetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oder_detail_item_layout,parent,false);
        return new OderDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderDetailAdapterViewHolder holder, int position) {
        Oder oder = list.get(position);
        if (oder == null) return;
        service = serviceDAO.queryByID(oder.getServiceID());
        quantity = formatPrice(String.valueOf(oder.getQuantity()));
        totalMoney = formatPrice(String.valueOf(oder.getTotalMoney(service)));
        price = formatPrice(String.valueOf(service.getPrice()));
        holder.txt_name.setText(service.getName());
        holder.txt_quantity.setText(quantity);
        holder.txt_price.setText(price);
        holder.txt_totalMoney.setText(totalMoney);
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickEdit(oder);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) return list.size();
        return 0;
    }

    public class OderDetailAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name, txt_quantity, txt_price, txt_totalMoney;
        ImageView img_edit;
        public OderDetailAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.item_oder_detail_txtName);
            txt_quantity = itemView.findViewById(R.id.item_oder_detail_txtQuantity);
            txt_price = itemView.findViewById(R.id.item_oder_detail_txtPrice);
            txt_totalMoney = itemView.findViewById(R.id.item_oder_detail_txtTotal);
            img_edit = itemView.findViewById(R.id.item_oder_detail_ImgEdit);
        }
    }
    private String formatPrice(String price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        double fDouble = Double.parseDouble(price);
        String fPrice = numberFormat.format(fDouble);
        return fPrice;
    }
}
