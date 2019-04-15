package app.shakil.com.dailyexpense.ExpandListViewComponents;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public String title;
    public final List<String> children=new ArrayList<String>();

    public Group(String title){
        this.title=title;
    }
}
