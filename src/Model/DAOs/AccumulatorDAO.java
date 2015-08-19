package Model.DAOs;

import Model.Contracts.Accumulator;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class AccumulatorDAO extends BaseDaoImpl<Accumulator, String> implements Queryable<Accumulator> {


    protected AccumulatorDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Accumulator.class);
    }

    @Override
    public List<Accumulator> getAllItems() throws SQLException {
        return this.queryForEq("type", "ACC");
    }

    @Override
    public Accumulator getItemById(String id) throws SQLException {
        return this.queryForId(id);
    }

    @Override
    public void addItem(Accumulator accumulator) throws SQLException {
        this.create(accumulator);
    }
}
