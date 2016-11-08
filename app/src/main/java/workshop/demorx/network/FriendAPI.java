package workshop.demorx.network;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FriendAPI {

    private Context context;

    public FriendAPI(Context context) {
        context = context;
    }

    public List<String> getAllFriend() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException();
        return getFriendList();
    }

    private List<String> getFriendList() {
        List<String> colors = new ArrayList<>();
        colors.add("First friend");
        colors.add("Second friend");
        colors.add("Third friend");
        return colors;
    }
}
