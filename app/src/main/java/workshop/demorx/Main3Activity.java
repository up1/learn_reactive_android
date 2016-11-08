package workshop.demorx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import workshop.demorx.data.BaseData;
import workshop.demorx.data.Step1;
import workshop.demorx.data.Step2;
import workshop.demorx.network.DemoAPI;
import workshop.demorx.network.FriendAPI;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StringAdapter stringAdapter;
    private Subscription allFriendSubscription;
    private DemoAPI demoAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.color_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stringAdapter = new StringAdapter(this);
        recyclerView.setAdapter(stringAdapter);

        demoAPI = new DemoAPI(this);

        startProcess();
    }

    private void startProcess() {
        Observable.fromCallable(callStep1()).fla
    }


    private Callable<BaseData<Step1>> callStep1() {
        return new Callable<BaseData<Step1>>() {
            @Override
            public BaseData<Step1> call() throws Exception {
                Log.d("Main3Activity", "Step 1 called");
                return demoAPI.getStep1();
            }
        };
    }

    private Callable<BaseData<List<Step2>>> callStep2(final int id) {
        return new Callable<BaseData<List<Step2>>>() {
            @Override
            public BaseData<List<Step2>> call() throws Exception {
                Log.d("Main3Activity", "Step 2 called");
                return demoAPI.getStep2(id);
            }
        };
    }


}
