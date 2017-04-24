package th.ac.camt.insugar_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MARK on 13/4/2560.
 */

public class Calculator {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_User")
    @Expose
    private String idUser;
    @SerializedName("Insulin_Name")
    @Expose
    private String insulinName;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("tdd")
    @Expose
    private String tdd;
    @SerializedName("blood_sugar")
    @Expose
    private String bloodSugar;
    @SerializedName("unit1")
    @Expose
    private String unit1;
    @SerializedName("result_1")
    @Expose
    private String result1;
    @SerializedName("result_2")
    @Expose
    private String result2;
    @SerializedName("sum_carbo")
    @Expose
    private String sumCarbo;
    @SerializedName("unit2")
    @Expose
    private String unit2;
    @SerializedName("sum_units")
    @Expose
    private String sumUnits;
    @SerializedName("sum_activity")
    @Expose
    private String sumActivity;
    @SerializedName("final_sum_units")
    @Expose
    private String finalSumUnits;
    @SerializedName("target_blood_sugar")
    @Expose
    private String targetBloodSugar;
    @SerializedName("result_blood_sugar")
    @Expose
    private String resultBloodSugar;

    @SerializedName("date_time")
    @Expose
    private String dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getInsulinName() {
        return insulinName;
    }

    public void setInsulinName(String insulinName) {
        this.insulinName = insulinName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTdd() {
        return tdd;
    }

    public void setTdd(String tdd) {
        this.tdd = tdd;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getSumCarbo() {
        return sumCarbo;
    }

    public void setSumCarbo(String sumCarbo) {
        this.sumCarbo = sumCarbo;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getSumUnits() {
        return sumUnits;
    }

    public void setSumUnits(String sumUnits) {
        this.sumUnits = sumUnits;
    }

    public String getSumActivity() {
        return sumActivity;
    }

    public void setSumActivity(String sumActivity) {
        this.sumActivity = sumActivity;
    }

    public String getFinalSumUnits() {
        return finalSumUnits;
    }

    public void setFinalSumUnits(String finalSumUnits) {
        this.finalSumUnits = finalSumUnits;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTargetBloodSugar() {
        return targetBloodSugar;
    }

    public void setTargetBloodSugar(String targetBloodSugar) {
        this.targetBloodSugar = targetBloodSugar;
    }

    public String getResultBloodSugar() {
        return resultBloodSugar;
    }

    public void setResultBloodSugar(String resultBloodSugar) {
        this.resultBloodSugar = resultBloodSugar;
    }
}