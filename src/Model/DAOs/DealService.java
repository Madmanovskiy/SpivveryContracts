package Model.DAOs;

import Model.Contracts.BasicContract;
import Model.Contracts.Deal;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Maksim on 12.08.2015.
 */
public class DealService {

    private final String url = "jdbc:sqlite:test.s3db";
    private  ConnectionSource source;
    private  Dao<Deal, String> dao;

    public DealService() throws SQLException {
        source = new JdbcConnectionSource(url);
        dao = DaoManager.createDao(source, Deal.class);
    }

    public void addDealToDB(Deal d) throws SQLException {
        dao.create(d);
    }

    public static void main(String[] args) {
        BasicContract ac = new BasicContract(0, BasicContract.Bank.BNP, BasicContract.ContractsType.ACC, new Date(System.currentTimeMillis()), Date.valueOf("2015-10-21"),
                BasicContract.Assets.CHF, BasicContract.Assets.EUR,500000d, 2, 1.5034d);

        Deal deal1 = new Deal(0,ac,Date.valueOf("2015-08-30"), Deal.DealsType.BUY, 1.5060d, 500000d);

        try{
            DealService bcs = new DealService();
            bcs.addDealToDB(deal1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
