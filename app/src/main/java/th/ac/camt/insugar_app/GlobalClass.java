package th.ac.camt.insugar_app;

import android.app.Application;

import th.ac.camt.insugar_app.Model.User;

/**
 * Created by MARK on 19/3/2560.
 */

public class GlobalClass extends Application {

    public final String URL_REGISTRATION = "http://insugardmtype1.com/service/Registration.php";
    public final String URL_LOGIN = "http://www.insugardmtype1.com/service/login.php";
    public final String URL_ADD_CALCULATOR = "http://www.insugardmtype1.com/service/History.php";
    public final String URL_ADD_LONGINSULIN = "http://www.insugardmtype1.com/service/LongInsulin.php";
    public final String URL_GET_LONGINSULIN_LIST ="http://www.insugardmtype1.com/service/getLongInsulin.php";
    public final String URL_GET_CALCULATOR_LIST ="http://www.insugardmtype1.com/service/getCalculator.php";
    public final String URL_FOOD_LIST = "http://www.insugardmtype1.com/service/getListFood.php";
    public final String URL_ACTIVITY_LIST = "http://www.insugardmtype1.com/service/getListActivity.php";
    private User user;
    public double tDD;
    public double bloodSugar;
    public double targetBloodSugar;
    public double resultBloodSugar;
    public String insulinName;
    public String insulinType;
    public double unit1;
    public double unit2;
    public double sumUnit;
    public double sumCarbo;
    public String longInsulinName;
    public int longInsulinUnit;
    public double sumActivity;
    public int finalSumUnits;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}