package com.herokuapp.ezhao.datejar;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

@Table(name = "Ideas")
public class Idea extends Model {
    @Column(name = "ideaText")
    public String ideaText;

    // Required empty constructor
    public Idea() {
        super();
    }

    public Idea(String ideaText) {
        super();
        this.ideaText = ideaText;
    }

    public static List<Idea> getAll() {
        return new Select()
                .from(Idea.class)
                .execute();
    }

    public static Idea getRandom() {
        return new Select().from(Idea.class).orderBy("RANDOM()").executeSingle();
    }
}
