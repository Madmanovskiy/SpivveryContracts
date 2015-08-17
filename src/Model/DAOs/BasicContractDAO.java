package Model.DAOs;

import Model.Contracts.BasicContract;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class BasicContractDAO extends BaseDaoImpl<BasicContract, String> {

    public BasicContractDAO(ConnectionSource connectionSource, Class<BasicContract> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<BasicContract> getAllBasicContracts() throws SQLException {
        return this.queryForAll();
    }

    public void addContractToDB(BasicContract bc) throws SQLException {
        this.create(bc);
    }
}
