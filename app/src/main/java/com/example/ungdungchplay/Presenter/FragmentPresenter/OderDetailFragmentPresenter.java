package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.OderDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderDetailFragmentInterface;
import com.example.ungdungchplay.ModelManager.Oder;

import java.util.List;

public class OderDetailFragmentPresenter {
    private Context context;
    private OderDAO oderDAO;
    private OderDetailFragmentInterface oderDetailFragmentInterface;

    public OderDetailFragmentPresenter(Context context, OderDetailFragmentInterface oderDetailFragmentInterface) {
        this.context = context;
        this.oderDetailFragmentInterface = oderDetailFragmentInterface;
        oderDAO = new OderDAO(context);
    }
    public void getData(int Status, String tableID){
        List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
        if (list.size() == 0){
            oderDetailFragmentInterface.getDataError("No item Oder");
            return;
        }
        oderDetailFragmentInterface.getDataSuccess("get Data success", list);
    }
    public void updateOder (int option,Oder oder,int Status, String tableID){
        if (option ==1){
            try {
                oderDAO.updateOder(oder);
                List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
                oderDetailFragmentInterface.updateSuccess("Update Success !",list);
            }
            catch (Exception x){
                oderDetailFragmentInterface.updateError(x.toString());
            }
        }
        else{
            try{
                oderDAO.deleteOder(oder);
                List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
                oderDetailFragmentInterface.updateSuccess("Delete Success !",list);
            }
            catch (Exception x){
                oderDetailFragmentInterface.updateError(x.toString());
            }
        }

    }
}
