package Model.DAOs;

import Model.Contracts.Deal;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maksim on 13.08.2015.
 */
public class DealDAO extends BaseDaoImpl<Deal, Integer> {

    public DealDAO(ConnectionSource connectionSource, Class<Deal> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Deal> getAllDeal() throws SQLException {
        return this.queryForAll();
    }

    public void addDealToDB(Deal d) throws SQLException {
        this.create(d);
    }

}
