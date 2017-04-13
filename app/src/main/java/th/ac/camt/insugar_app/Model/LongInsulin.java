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
    @SerializedName("id_Insulin_Name")
    @Expose
    private String idInsulinName;
    @SerializedName("id_User")
    @Expose
    private String idUser;
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

    public String getIdInsulinName() {
        return idInsulinName;
    }

    public void setIdInsulinName(String idInsulinName) {
        this.idInsulinName = idInsulinName;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
