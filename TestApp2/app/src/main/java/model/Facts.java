package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SumitBhatia on 22/03/15.
 */
public class Facts {

    @SerializedName("title")
    public String title;

    @SerializedName("rows")
    public List<FactItem> factItemList;
}
