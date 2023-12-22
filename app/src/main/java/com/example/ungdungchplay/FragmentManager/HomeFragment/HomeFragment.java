package com.example.ungdungchplay.FragmentManager.HomeFragment;

import static android.util.Log.d;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ungdungchplay.ActivityManager.TableActivity;
import com.example.ungdungchplay.Adapter.RoomAdapter;
import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentHomeInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.RoomListener;
import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentHomePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, FragmentHomeInterface, RoomListener {
    private FloatingActionButton btn_add;
    private View view;
    private RecyclerView rcv;
    private FragmentHomePresenter presenter;
    private RoomAdapter adapter;
    private TextInputEditText edt_query;
    private List<Room> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unitUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
    }

    private void unitUI(){
        btn_add = view.findViewById(R.id.fHome_btnAdd);
        btn_add.setOnClickListener(this);
        rcv = view.findViewById(R.id.fHome_rcv);
        adapter = new RoomAdapter(getContext(),this);
        adapter.setList(this.list);
        rcv.setAdapter(adapter);
        presenter = new FragmentHomePresenter(getContext(),this);
        edt_query = view.findViewById(R.id.fHome_edt_search);
        edt_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.getFilter().filter(s);
                btn_add.setVisibility(View.GONE);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                btn_add.setVisibility(View.GONE);
            }
            @Override
            public void afterTextChanged(Editable s) {
               adapter.getFilter().filter(s);
                btn_add.setVisibility(View.VISIBLE);
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fHome_btnAdd:
                btnAddOnclick();
                break;
        }
    }
    private void btnAddOnclick (){
        TextInputEditText edt_name;
        TextView txt_msg;
        Dialog dialog = new Dialog(getContext(), com.google.android.material.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.addroom_layout);
        edt_name = dialog.findViewById(R.id.lAdd_room_edtName);
        txt_msg = dialog.findViewById(R.id.lAdd_room_txtMessage);
        Button btn_add = dialog.findViewById(R.id.lAdd_room_btnAdd);
        Button btn_cancel = dialog.findViewById(R.id.lAdd_room_btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().toString().isEmpty()){
                    txt_msg.setText("Cannot be empty");
                    txt_msg.setVisibility(View.VISIBLE);
                    txt_msg.setTextColor(getContext().getColor(R.color.error));
                    return;
                }
                Room room = new Room();
                room.setName(edt_name.getText().toString());
                presenter.addRoom(room);
                dialog.cancel();
            }
        });
        dialog.show();
    }
    @Override
    public void addRoomSuccess(String msg, List<Room> list) {
        adapter.setList(list);
    }

    @Override
    public void addRoomErr(String msg) {
        adapter.setList(this.list);
    }
    @Override
    public void dataExists(List<Room> list) {
        adapter.setList(list);
    }
    @Override
    public void dataErr(String msg) {
        d("ca" + "chung", "dataEsssssrr: ");
    }
    @Override
    public void sendData(int option, Room room) {
       if (option == DbStruct.ITEM_CLICK){
           Intent intent = new Intent(getContext(), TableActivity.class);
           Bundle bundle = new Bundle();
           bundle.putSerializable("Room", room);
           intent.putExtras(bundle);
           getActivity().startActivity(intent);
       }
       else{
           ///todo...
       }
    }
}