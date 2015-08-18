package Model.Contracts;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Deals")
public class Deal {
    @DatabaseField(columnName = "deal_id", generatedId = true)
    protected int id;
    @DatabaseField(columnName = "contract_id")
    protected String contractId;
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

    public Deal(String contractId, Date dealDate, String dealType, double spotPrice, double volume) {
        this.contractId = contractId;
        this.dealDate = dealDate;
        this.dealType = dealType;
        this.spotPrice = spotPrice;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public double getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(double spotPrice) {
        this.spotPrice = spotPrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", basicContract=" + contractId +
                ", dealDate=" + dealDate +
                ", dealType='" + dealType + '\'' +
                ", spotPrice=" + spotPrice +
                ", volume=" + volume +
                '}';
    }
}
