package workshop.demorx.data;

/**
 * Created by somkiat on 11/7/2016 AD.
 */

public class Step1 {

    private int id;
    private String name;

    public Step1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
