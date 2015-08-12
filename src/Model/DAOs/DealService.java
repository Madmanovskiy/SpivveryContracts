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

}
