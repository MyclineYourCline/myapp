package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.app.Activity.RESULT_OK;
import static android.util.Log.d;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentAddServiceInterface;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentAddServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.InputStream;
import java.util.List;

public class AddServices extends Fragment implements View.OnClickListener, FragmentAddServiceInterface {
    private static final int REQUEST_PERMISSION = 123;
    private TextInputEditText edt_name, edt_price, edt_des, edt_count;
    private AutoCompleteTextView auto_type;
    private TextView txt_msg;
    private Button btn_add, btn_cancel;
    private ImageButton btn_addImg;
    private TextInputLayout showCountEdit;
    private RadioGroup radioGroup;
    private int count = -1;
    private int type = -1;
    private View view;
    private Drawable drawable;
    private  String imageUri;
    private LayoutInflater inflater;
    private ViewGroup viewGroup;
    private FragmentAddServicePresenter presenter;
    private final  ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    btn_addImg.setImageURI(uri);
                    btn_addImg.setBackground(null);
                    imageUri = uri.toString();
                } else {
                    d("ca" + "chung", "addImageClick: ");
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_add_services, container, false);
        presenter = new FragmentAddServicePresenter(this,getContext());
        unitUI();
        return view;
    }
    private void unitUI() {
        edt_name = view.findViewById(R.id.addService_name);
        edt_price = view.findViewById(R.id.addService_price);
        edt_des = view.findViewById(R.id.addService_des);
        edt_count = view.findViewById(R.id.addService_count);
        btn_add = view.findViewById(R.id.addService_btnAdd);
        btn_cancel = view.findViewById(R.id.addService_btnCancel);
        btn_addImg = view.findViewById(R.id.addService_btnAddImg);
        txt_msg = view.findViewById(R.id.addService_msg);
        drawable = btn_addImg.getBackground();
        showCountEdit = view.findViewById(R.id.showCount);
        // check radio group
        radioGroup = view.findViewById(R.id.addService_rdg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.addService_rb_exists:
                        type = 0;
                        edt_count.setText("");
                        showCountEdit.setVisibility(View.GONE);
                        break;
                    case R.id.addService_rb_notExists:
                        type = 1;
                        edt_count.setText("0");
                        showCountEdit.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });
        //

        btn_add.setOnClickListener(this);
        btn_addImg.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        //

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addService_btnAdd:
                addService();
                break;
            case R.id.addService_btnCancel:
                    clearForm();
                break;
            case R.id.addService_btnAddImg:
                addImageClick();
                break;

        }
    }
    private void addImageClick (){
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }
    private void addService(){
        if (edt_name.getText().toString().isEmpty()
        || edt_price.getText().toString().isEmpty() || type == -1){
            txt_msg.setText("Can not be empty");
            txt_msg.setVisibility(View.VISIBLE);
            txt_msg.setTextColor(getResources().getColor(R.color.error));
        }
        else{
            if (!edt_count.getText().toString().isEmpty()){
                count = Integer.parseInt(edt_count.getText().toString().trim());
            }
            else{
                count = 0;
            }
            String name = edt_name.getText().toString().trim();
            String description = edt_des.getText().toString().trim();
            int price = Integer.parseInt(edt_price.getText().toString().trim());
            //
            Service service = new Service();
            service.setType(type);
            service.setName(name);
            service.setCount(count);
            service.setImageURi(imageUri);
            service.setDescription(description);
            service.setPrice(price);
            //
            presenter.addService(service);
        }
    }
    private void clearForm(){
        edt_name.setText("");
        edt_des.setText("");
        edt_count.setText("");
        edt_price.setText("");
        btn_addImg.setImageURI(null);
        btn_addImg.setBackground(drawable);
        showCountEdit.setVisibility(View.GONE);
        txt_msg.setVisibility(View.GONE);
        radioGroup.clearCheck();
    }

    @Override
    public void addServiceSuccess(String message) {
        clearForm();
        txt_msg.setText(message);
        txt_msg.setVisibility(View.VISIBLE);
        txt_msg.setTextColor(getResources().getColor(R.color.success));
    }

    @Override
    public void addServiceError(String msg) {
        txt_msg.setText(msg);
        txt_msg.setVisibility(View.VISIBLE);
        txt_msg.setTextColor(getResources().getColor(R.color.success));
    }

    @Override
    public void addServiceExists(String msg) {
        txt_msg.setText(msg);
        txt_msg.setVisibility(View.VISIBLE);
        txt_msg.setTextColor(getResources().getColor(R.color.error));
    }
}
