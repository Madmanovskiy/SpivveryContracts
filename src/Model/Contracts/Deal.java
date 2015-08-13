package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable (tableName = "deals")
public class Deal {
    @DatabaseField(generatedId = true)
    protected   int id;
    @DatabaseField(columnName = "contractsID", foreign = true)
    protected  BasicContract basicContract;
    @DatabaseField
    protected  long dealDate;
    @DatabaseField
    protected  String dealType;
    @DatabaseField(columnName = "dealValue")
    protected  double assetsPrice;
    @DatabaseField(columnName = "dealVolume")
    protected  double volume;

    public Deal(){

    }

    public Deal(int id, BasicContract basicContract, Date dealDate, DealsType dealType, double assetsPrice, double volume) {
        this.id = id;
        this.basicContract = basicContract;
        this.dealDate = dealDate.getTime();
        this.dealType = dealType.toString();
        this.assetsPrice = assetsPrice;
        this.volume = volume;
    }


    public double getAssetsPrice() {
        return assetsPrice;
    }

    public BasicContract getBasicContract() {
        return basicContract;
    }

    public long getDealDate() {
        return dealDate;
    }

    public String getDealType() {
        return dealType;
    }

    public int getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public void setAssetsPrice(double assetsPrice) {
        this.assetsPrice = assetsPrice;
    }

    public void setBasicContract(BasicContract basicContract) {
        this.basicContract = basicContract;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate.getTime();
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public enum DealsType {
        BUY,
        SELL
    }
}
