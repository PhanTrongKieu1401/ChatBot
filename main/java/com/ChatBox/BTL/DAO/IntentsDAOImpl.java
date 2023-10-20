package com.ChatBox.BTL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ChatBox.BTL.Model.Intents;

@Repository
public class IntentsDAOImpl extends DAO implements IntentsDAO{
	Connection conn = null;
	PreparedStatement stmt = null;
    ResultSet rs = null;
    
	@Override
	public List<Intents> getAllIntents() {
		List<Intents> listIntents = new ArrayList<>(); 
		try {
        	conn = getConnection();
            String sql = "SELECT * FROM intents";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Intents intents = new Intents();
                intents.setId(rs.getInt("id"));
                intents.setTextIntents(rs.getString("textIntents"));
                listIntents.add(intents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
        return listIntents;
	}

	@Override
	public Intents getIntentByID(int id) {
		Intents intents = new Intents();     
        try {
        	conn = getConnection();
            String sql = "SELECT intents.id, intents.textIntents "
            		+ "FROM intents "
            		+ "WHERE intents.id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	intents = new Intents();
                intents.setId(rs.getInt("id"));
                intents.setTextIntents(rs.getString("textIntents"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
        return intents;
	}

	@Override
	public void addIntents(Intents intents) {
		try {
            conn = getConnection();
            String sql = "INSERT INTO intents (textIntents) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, intents.getTextIntents());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }
	}

	@Override
	public void updateIntents(Intents intents, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIntents(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Intents> searchIntents(String key) {
		List<Intents> listIntents = new ArrayList<>();
		try {
        	conn = getConnection();
            String sql = "SELECT * FROM intents where intents.textIntents LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+key+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Intents intents = new Intents();
                intents.setId(rs.getInt("id"));
                intents.setTextIntents(rs.getString("textIntents"));
                listIntents.add(intents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
        return listIntents;
	}
	
}
