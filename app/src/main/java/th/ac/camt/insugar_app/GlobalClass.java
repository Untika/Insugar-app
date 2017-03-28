package th.ac.camt.insugar_app;

import android.app.Application;

import th.ac.camt.insugar_app.Model.User;

/**
 * Created by MARK on 19/3/2560.
 */

public class GlobalClass extends Application {

    public final String URL_REGISTRATION = "http://insugardmtype1.com/service/Registration.php";
    public final String URL_LOGIN = "http://www.insugardmtype1.com/service/login.php";
    private User user;
    public double tDD;
    public double bloodSugar;
    public String insulinName;
    public String insulinType;
    public int unit1;
    public int unit2;
    public int sumUnit;
    public double sumCarbo;
    public String longInsulinName;
    public String longInsulinUnit;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}