package th.ac.camt.insugar_app.Model;

/**
 * Created by MARK on 26/3/2560.
 */

public class Calculate {
    private int id;
    private String date;
    private String time;
    private String TDD;
    private String weight;
    private String bloodSugar;
    private String insulinType;
    private String correctionResult;
    private String carboRatio;
    private String someCarbo;
    private String carboResult;


    public Calculate(int id, String date, String time, String TDD, String weight,
                     String bloodSugar, String insulinType, String correctionResult,
                     String carboRatio, String someCarbo, String carboResult) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.TDD = TDD;
        this.weight = weight;
        this.bloodSugar = bloodSugar;
        this.insulinType = insulinType;
        this.correctionResult = correctionResult;
        this.carboRatio = carboRatio;
        this.someCarbo = someCarbo;
        this.carboResult = carboResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTDD() {
        return TDD;
    }

    public void setTDD(String TDD) {
        this.TDD = TDD;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getInsulinType() {
        return insulinType;
    }

    public void setInsulinType(String insulinType) {
        this.insulinType = insulinType;
    }

    public String getCorrectionResult() {
        return correctionResult;
    }

    public void setCorrectionResult(String correctionResult) {
        this.correctionResult = correctionResult;
    }

    public String getCarboRatio() {
        return carboRatio;
    }

    public void setCarboRatio(String carboRatio) {
        this.carboRatio = carboRatio;
    }

    public String getSomeCarbo() {
        return someCarbo;
    }

    public void setSomeCarbo(String someCarbo) {
        this.someCarbo = someCarbo;
    }

    public String getCarboResult() {
        return carboResult;
    }

    public void setCarboResult(String carboResult) {
        this.carboResult = carboResult;
    }
}
