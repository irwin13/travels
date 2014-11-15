package traveldemo.view;

import io.dropwizard.views.View;
import traveldemo.hibernate.entity.Test1;

import java.util.List;

/**
 * Created by irwin on 11/16/14.
 */
public class Test1View extends View {

    private final List<Test1> list;

    public Test1View(List<Test1> list) {
        super("test1FindAll.ftl");
        this.list = list;
    }

    public List<Test1> getList() {
        return list;
    }
}
