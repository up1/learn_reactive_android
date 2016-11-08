package workshop.demorx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StringAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.color_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stringAdapter = new StringAdapter(this);
        recyclerView.setAdapter(stringAdapter);

        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> listObservable = Observable.just(getFriendList());
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Log.d("MainActivity", "onCompleted() called");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<String> friends) {
                stringAdapter.setStrings(friends);
            }
        });
    }

    private List<String> getFriendList() {
        List<String> colors = new ArrayList<>();
        colors.add("First friend");
        colors.add("Second friend");
        colors.add("Third friend");
        return colors;
    }
}
