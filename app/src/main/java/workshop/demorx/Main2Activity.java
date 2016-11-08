package workshop.demorx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import workshop.demorx.network.FriendAPI;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StringAdapter stringAdapter;
    private Subscription allFriendSubscription;
    private FriendAPI friendAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.color_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stringAdapter = new StringAdapter(this);
        recyclerView.setAdapter(stringAdapter);

        friendAPI = new FriendAPI(this);

        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> listObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return friendAPI.getAllFriend();
            }
        });

        allFriendSubscription = listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Main2Activity", "onCompleted() called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Main2Activity", "onError() called");
                        Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(List<String> friends) {
                        stringAdapter.setStrings(friends);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unsubscribe();
    }

    private void unsubscribe() {
        if (allFriendSubscription != null && !allFriendSubscription.isUnsubscribed()) {
            allFriendSubscription.unsubscribe();
        }
    }
}
