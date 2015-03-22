package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SumitBhatia on 22/03/15.
 */
public class FactItem {

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("imageHref")
    public String imageHref;

}
