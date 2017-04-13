package th.ac.camt.insugar_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 005493 on 4/13/2017.
 */

public class LongInsulin {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Insulin_Name")
    @Expose
    private String insulinName;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsulinName() {
        return insulinName;
    }

    public void setInsulinName(String insulinName) {
        this.insulinName = insulinName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
