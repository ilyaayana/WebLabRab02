package DAO;

import Database.JdbcConnector;
public abstract class DAO {
    protected JdbcConnector cnr;
    public DAO() throws DAOException
    {
        cnr = new JdbcConnector();
    }
    public JdbcConnector getJdbcConnector()
    {
    return cnr;
    }
}


