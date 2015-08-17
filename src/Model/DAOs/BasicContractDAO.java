package Model.DAOs;

import Model.Contracts.Accumulator;
import Model.Contracts.BasicContract;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class BasicContractDAO extends BaseDaoImpl<Accumulator, String> {

    public BasicContractDAO(ConnectionSource connectionSource, Class<Accumulator> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Accumulator> getAllBasicContracts() throws SQLException {
        return this.queryForAll();
    }

    public void addContractToDB(Accumulator bc) throws SQLException {
        this.create(bc);
    }
}
