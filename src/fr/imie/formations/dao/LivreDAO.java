package fr.imie.formations.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.formations.dto.LivreDTO;

public class LivreDAO extends DAO<LivreDTO> {
    /** Requête pour la récupération d'un livre en base. */
    private static final String SQL_READ    = "SELECT * FROM livre WHERE livre_id=?;";
    /** Requête pour l'ajout d'un livre en base. */
    private static final String SQL_INSERT  = "INSERT INTO livre (nom, prenom, date_parution) VALUES (?, ?, ?)";
    /** Requête pour la modification d'un livre en base. */
    private static final String SQL_UPDATE  = "UPDATE livre SET nom=?, prenom=?, date_parution=? WHERE livre_id=?;";
    /** Requête pour la suppression d'un livre en base. */
    private static final String SQL_DELETE  = "DELETE FROM livre WHERE livre_id=?;";
    /** Requête pour la récupération de tous les livres en base. */
    private static final String SQL_GET_ALL = "SELECT * FROM livre ORDER BY livre_id;";

    @Override
    public LivreDTO read( int pId ) {
        LivreDTO result = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connexion.prepareStatement( SQL_READ );
            statement.setInt( 1, pId );
            resultSet = statement.executeQuery();
            if ( resultSet.next() ) {
                result = bind( resultSet );
            }
        } catch ( final SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }

            try {
                statement.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public LivreDTO insert( LivreDTO pObject ) {
        LivreDTO result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connexion.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );

            statement.setString( 1, pObject.getNom() );
            statement.setString( 2, pObject.getPrenom() );
            statement.setDate( 3, pObject.getDateParution() );

            int resultQuery = statement.executeUpdate();

            if ( resultQuery > 0 ) {
                resultSet = statement.getGeneratedKeys();
                if ( resultSet.next() ) {
                    result = read( resultSet.getInt( "livre_id" ) );
                } else {
                    System.err.println( "Aucune clef retournée ?!" );
                }
            } else {
                System.err.println(
                        "Une erreur est survenue lors de l'ajout du livre..." );
            }
        } catch ( final SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }

            try {
                statement.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public LivreDTO update( LivreDTO pObject ) {
        PreparedStatement statement = null;

        try {
            statement = connexion.prepareStatement( SQL_UPDATE );

            statement.setString( 1, pObject.getNom() );
            statement.setString( 2, pObject.getPrenom() );
            statement.setDate( 3, pObject.getDateParution() );
            statement.setInt( 4, pObject.getId() );

            int resultQuery = statement.executeUpdate();

            if ( resultQuery == 0 ) {
                System.err.println( "Une erreur est survenue lors de la mise à jour du livre..." );
            }
        } catch ( final SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }
        }

        return pObject;
    }

    @Override
    public boolean delete( LivreDTO pObject ) {
        boolean result = false;
        PreparedStatement statement = null;

        try {
            statement = connexion.prepareStatement( SQL_DELETE );

            statement.setInt( 1, pObject.getId() );

            int resultQuery = statement.executeUpdate();

            if ( resultQuery < 1 ) {
                System.err.println( "Une erreur est survenue lors de la suppression du livre..." );
            } else {
                result = true;
            }
        } catch ( final SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public List<LivreDTO> getAll() {
        List<LivreDTO> listeLivres = new ArrayList<LivreDTO>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connexion.prepareStatement( SQL_GET_ALL );

            resultSet = statement.executeQuery();

            while ( resultSet.next() ) {
                listeLivres.add( bind( resultSet ) );
            }
        } catch ( final SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }

            try {
                statement.close();
            } catch ( final SQLException ex ) {
                ex.printStackTrace();
            }
        }

        return listeLivres;
    }

    LivreDTO bind( ResultSet pResultSet ) throws SQLException {
        return new LivreDTO( pResultSet.getInt( "livre_id" ), pResultSet.getString( "nom" ),
                pResultSet.getString( "prenom" ), pResultSet.getDate( "date_parution" ) );
    }

}
