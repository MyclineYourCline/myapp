package com.example.ungdungchplay.FragmentManager;

import static android.util.Log.d;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.Adapter.RoomAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentHomeInterface;
import com.example.ungdungchplay.InterfaceManager.RoomListener;
import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.Presenter.FragmentHomePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, FragmentHomeInterface, RoomListener {
    private FloatingActionButton btn_add;
    private View view;
    private RecyclerView rcv;
    private FragmentHomePresenter presenter;
    private RoomAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unitUI();
        return view;
    }
    private void unitUI(){
        btn_add = view.findViewById(R.id.fHome_btnAdd);
        btn_add.setOnClickListener(this);
        rcv = view.findViewById(R.id.fHome_rcv);
        adapter = new RoomAdapter(getContext(),this);
        presenter = new FragmentHomePresenter(getContext(),this);
        presenter.getData();

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
                    txt_msg.setVisibility(View.GONE);
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        adapter.setList(list);
    }

    @Override
    public void addRoomErr(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        d("ca" + "chung", "addRoomErr: ");
    }

    @Override
    public void dataExists(List<Room> list) {
        adapter.setList(list);
        rcv.setAdapter(adapter);
    }

    @Override
    public void dataErr(String msg) {
        d("ca" + "chung", "dataErr: ");
    }

    @Override
    public void query(List<Room> list) {
        adapter.setList(list);
        d("ca" + "chung", "query: "+list.size());
    }

    @Override
    public void sendData(int option, Room room) {
        d("ca" + "chung", "sendData: ");
    }
}