package com.example.ungdungchplay.ActivityManager;

import static android.util.Log.d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.Adapter.TableAdapter;
import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.InterfaceManager.ActivityInterface.TableActivityInterFace;
import com.example.ungdungchplay.InterfaceManager.SendData.TableListener;
import com.example.ungdungchplay.ModelManager.Room;
import com.example.ungdungchplay.ModelManager.Table;
import com.example.ungdungchplay.Presenter.ActivityPresenter.TableActivityPresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity implements TableActivityInterFace, TableListener
                            , View.OnClickListener {
    private TextInputEditText edt_query;
    private FloatingActionButton btn_add;
    private RecyclerView rcv;
    private List<Table> list = new ArrayList<>();
    private TableAdapter tableAdapter;
    private TableActivityPresenter presenter;
    private Intent resultForRoom;
    private ImageView img_back;

    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        unitUI();
        edt_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tableAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tableAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                tableAdapter.getFilter().filter(s);
            }
        });

    }

    @Override
    public void addTableSuccess(String msg, List<Table> list) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tableAdapter.setList(list);
    }

    @Override
    public void addTableErr(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataExists(List<Table> list) {
        tableAdapter.setList(list);
    }

    @Override
    public void dataErr(String msg) {
        d("ca" + "chung", "dataErr: "+msg);
    }
    void unitUI(){
        //
        getDataIntent();
        //
        edt_query = findViewById(R.id.Table_edt_search);
        rcv = findViewById(R.id.Table_rcv);
        btn_add = findViewById(R.id.Table_btnAdd);
        tableAdapter = new TableAdapter(this,this);
        tableAdapter.setList(list);
        rcv.setAdapter(tableAdapter);
        img_back = findViewById(R.id.TableActivity_img_back);
        img_back.setOnClickListener(this);
        //
        btn_add.setOnClickListener(this);
        presenter = new TableActivityPresenter(this,this);
        presenter.getData(room.getId());
        //
    }

    @Override
    public void sendData(int option, Table table) {
        if(option == DbStruct.ITEM_CLICK){
            Intent intent = new Intent(this,OderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Data", table);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            return;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Table_btnAdd:
                btnAddOnclick();
                break;
            case R.id.TableActivity_img_back:
                finish();
                break;
        }
    }
    private void btnAddOnclick (){
        TextInputEditText edt_name;
        TextView txt_msg,title;
        TextInputLayout layout_hint;
        Dialog dialog = new Dialog(this,
                com.google.android.material.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.addroom_layout);
        edt_name = dialog.findViewById(R.id.lAdd_room_edtName);
        txt_msg = dialog.findViewById(R.id.lAdd_room_txtMessage);
        title = dialog.findViewById(R.id.Ladd_room_title);
        layout_hint = dialog.findViewById(R.id.lAdd_add_room_Hint);
        Button btn_add = dialog.findViewById(R.id.lAdd_room_btnAdd);
        Button btn_cancel = dialog.findViewById(R.id.lAdd_room_btnCancel);
        //
        title.setText("New Table");
        layout_hint.setHint("Table Name");

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
                    txt_msg.setTextColor(getColor(R.color.error));
                    return;
                }
                Table table = new Table();
                table.setRoomID(room.getId());
                table.setName(edt_name.getText().toString().trim());
                table.setStatus(Table.TABLE_EXISTS);
                presenter.addTable(table,room.getId());
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void getDataIntent(){
        resultForRoom = getIntent();
        Bundle bundle = resultForRoom.getExtras();
        if (bundle != null){
            room = (Room) bundle.getSerializable("Room");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData(room.getId());
    }
}