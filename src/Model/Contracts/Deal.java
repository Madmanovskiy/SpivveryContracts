package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "deals")
public class Deal {
    @DatabaseField(columnName = "deal_id", generatedId = true)
    protected int id;
    @DatabaseField(columnName = "contract_id", foreign = true)
    protected Accumulator basicContract;
    @DatabaseField
    protected Date dealDate;
    @DatabaseField
    protected String dealType;
    @DatabaseField
    protected double spotPrice;
    @DatabaseField
    protected double volume;

    public Deal() {

    }

    public Deal(Accumulator basicContract, Date dealDate, String dealType, double spotPrice, double volume) {
        this.basicContract = basicContract;
        this.dealDate = dealDate;
        this.dealType = dealType;
        this.spotPrice = spotPrice;
        this.volume = volume;
    }

    public int getId() {
        return id;
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

    public double getSpotPrice() {
        return spotPrice;
    }

    public double getVolume() {
        return volume;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBasicContract(Accumulator basicContract) {
        this.basicContract = basicContract;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public void setSpotPrice(double spotPrice) {
        this.spotPrice = spotPrice;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", basicContract=" + basicContract +
                ", dealDate=" + dealDate +
                ", dealType='" + dealType + '\'' +
                ", spotPrice=" + spotPrice +
                ", volume=" + volume +
                '}';
    }
}
