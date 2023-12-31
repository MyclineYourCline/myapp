package com.example.ungdungchplay.Adapter;

import static android.util.Log.d;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceAdapterViewHolder> implements Filterable {
    private Context context;
    private List<Service> list;
    private List<Service> listOld;
    private ServiceListener listener;
    private final int CLICK = 1;
    private final int LONG_CLICK = 2;

    public ServiceAdapter(Context context, ServiceListener listener) {
        this.context = context;
        this.listener = listener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setList (List<Service> list){
        this.list = list;
        this.listOld =  this.list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ServiceAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item_layout_1,parent,false);
        return new ServiceAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapterViewHolder holder, int position) {
        Service service = list.get(position);
        if (service == null) return;
        //
        if (service.getImageURi() == null){
            holder.image.setImageResource(R.drawable.add_2);
        }
        else{
            Glide.with(context.getApplicationContext())
                    .load(Uri.parse(service.getImageURi()))
                    .into(holder.image);
            //
        }
        holder.txt_name.setText(service.getName());
        holder.txt_des.setText(service.getDescription());
        String price = formatPrice(String.valueOf(service.getPrice()));
        holder.txt_price.setText(price);
        if (service.getType() ==  0){
            holder.txt_count.setVisibility(View.GONE);
        }
        else{
            String quantity = formatPrice(String.valueOf(service.getCount()));
            holder.txt_count.setText(quantity);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.senData(CLICK, service);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.senData(LONG_CLICK,service);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) return list.size();
        return 0;
    }

    public class ServiceAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_name, txt_count, txt_price, txt_des;
        private ImageView image;
        public ServiceAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.serviceItem_txt_name);
            txt_count = itemView.findViewById(R.id.serviceItem_txt_count);
            txt_price = itemView.findViewById(R.id.serviceItem_txt_price);
            txt_des = itemView.findViewById(R.id.serviceItem_txt_des);
            image = itemView.findViewById(R.id.serviceItem_img);
        }
    }

    // Phương thức để lấy đường dẫn thực tế từ content URI
    private String getPathFromContentUri(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
        return null;
    }
    private String formatPrice(String price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        double fDouble = Double.parseDouble(price);
        String fPrice = numberFormat.format(fDouble);
        return fPrice;
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
                    List<Service> mList1 =  new ArrayList<>();
                    for (Service x: listOld){
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
                list = (List<Service>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
