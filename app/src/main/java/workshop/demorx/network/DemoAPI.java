package workshop.demorx.network;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import workshop.demorx.data.BaseData;
import workshop.demorx.data.Step1;
import workshop.demorx.data.Step2;

public class DemoAPI {

    private Context context;

    public DemoAPI(Context context) {
        context = context;
    }

    public BaseData<Step1> getStep1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Step1 step1 = new Step1(1, "Somkiat");
        BaseData<Step1> result = new BaseData<>();
        result.setCode(200);
        result.setDatas(step1);
        return result;
    }

    public BaseData<List<Step2>> getStep2(int id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Step2 step_1 = new Step2(1, "SCK");
        Step2 step_2 = new Step2(2, "Somkiat.cc");
        List<Step2> step2List = new ArrayList<>();
        step2List.add(step_1);
        step2List.add(step_2);

        BaseData<List<Step2>> result = new BaseData<>();
        result.setCode(200);
        result.setDatas(step2List);
        return result;
    }



}
