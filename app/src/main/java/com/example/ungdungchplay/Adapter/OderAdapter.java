package com.example.ungdungchplay.Adapter;

import static android.util.Log.d;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.SendData.OderListener;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.R;

import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OderAdapter extends RecyclerView.Adapter<OderAdapter.OderViewHolder>{
    private Context context;
    private List<Oder> list;
    private OderListener listener;
    private int countQuantity;
    private double totalMoney;
    private ServiceDAO serviceDAO;

    public OderAdapter(Context context, OderListener listener) {
        this.context = context;
        this.listener = listener;
        serviceDAO = new ServiceDAO(context);
    }
    public void  setList (List<Oder> list){
        this.list = list;
        resultOder();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_oder_item,parent,false);
        return new OderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Oder oder = list.get(position);
        Service service = serviceDAO.queryByID(oder.getServiceID());
        if (service == null){
            return;
        }
        if (service.getImageURi() == null){
            holder.image.setImageResource(R.drawable.add_2);
        }
        else{
            Glide.with(context.getApplicationContext())
                    .load(Uri.parse(service.getImageURi()))
                    .into(holder.image);
            //
        }
        holder.name.setText(service.getName());
        holder.price.setText(formatPrice(String.valueOf(service.getPrice())));
        holder.quantity.setText(oder.getQuantity()+"");
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d("ca" + "chung", "onClick: ssss");
                int value = Integer.parseInt(holder.quantity.getText().toString().trim()) +1;
                holder.quantity.setText(value+"");
                oder.setQuantity(value);
                resultOder();

            }
        });
        holder.btn_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(holder.quantity.getText().toString().trim()) -1;
                if (value < 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                   builder.setTitle("delete item ?");
                   builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           list.remove(position);
                           dialog.cancel();
                           notifyDataSetChanged();
                           resultOder();
                       }
                   });
                   builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           holder.quantity.setText("1");
                           dialog.cancel();
                       }
                   });
                   builder.show();
                }
                else{
                    holder.quantity.setText(value+"");
                    oder.setQuantity(value);
                    resultOder();

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public class OderViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name, price, quantity;
        Button btn_more, btn_less;
        public OderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.oderItem_img);
            name = itemView.findViewById(R.id.oderItem_txt_name);
            price = itemView.findViewById(R.id.oderItem_txt_price);
            quantity = itemView.findViewById(R.id.oderItem_txt_quantity);
            btn_more = itemView.findViewById(R.id.oderItem_cart_more);
            btn_less = itemView.findViewById(R.id.oderItem_btn_less);
        }
    }
    private String formatPrice(String price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        double fDouble = Double.parseDouble(price);
        String fPrice = numberFormat.format(fDouble);
        return fPrice;
    }
    private void resultOder (){
        int size = list.size();
        int totalMoney = 0;
        for (Oder oder: list){
            Service service = serviceDAO.queryByID(oder.getServiceID());
            totalMoney += oder.getQuantity() * service.getPrice();
        }
        listener.changeOder(size,totalMoney);
    }

}
