package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable (tableName = "deals")
public class Deal {
    @DatabaseField(generatedId = true)
    private  int id;
    @DatabaseField(columnName = "contractsID", foreign = true)
    private  BasicContract basicContract;
    @DatabaseField
    private  Date dealDate;
    @DatabaseField
    private  String dealType;
    @DatabaseField(columnName = "dealValue")
    private  double assetsPrice;
    @DatabaseField(columnName = "dealVolume")
    private  double volume;

    public Deal(){

    }

    public Deal(int id, BasicContract basicContract, Date dealDate, DealsType dealType, double assetsPrice, double volume) {
        this.id = id;
        this.basicContract = basicContract;
        this.dealDate = dealDate;
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

    public Date getDealDate() {
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

    public enum DealsType {
        BUY,
        SELL
    }
}
