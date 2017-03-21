package th.ac.camt.insugar_app;

import android.app.Application;

/**
 * Created by MARK on 19/3/2560.
 */

public class GlobalClass extends Application{

    final public String BASE_URL ="http://192.168.1.38/service";
    public int idUser;
    public String fullNameUser;
    public String birthDateUser;
    public String genderUser;
    public String phoneUser;
    public String emailUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public String getBirthDateUser() {
        return birthDateUser;
    }

    public void setBirthDateUser(String birthDateUser) {
        this.birthDateUser = birthDateUser;
    }

    public String getGenderUser() {
        return genderUser;
    }

    public void setGenderUser(String genderUser) {
        this.genderUser = genderUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
