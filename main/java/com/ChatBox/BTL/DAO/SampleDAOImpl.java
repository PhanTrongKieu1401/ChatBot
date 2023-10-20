package com.ChatBox.BTL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ChatBox.BTL.Model.Sample;

@Repository
public class SampleDAOImpl extends DAO implements SampleDAO {

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Override
	public List<Sample> getSampleByIntents(int idIntents) {
		List<Sample> listSample = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT sample.id, sample.question, sample.respond FROM sample\r\n"
					+ "LEFT JOIN intents ON sample.idIntents = intents.id WHERE intents.id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idIntents);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Sample sample = new Sample();
				sample.setId(rs.getInt("id"));
				sample.setIdIntents(idIntents);
				sample.setQuestion(rs.getString("question"));
				sample.setRespond(rs.getString("respond"));
				listSample.add(sample);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}
		return listSample;
	}

	@Override
	public void addSample(Sample sample, int idIntents) {
		try {
			conn = getConnection();
			String sql = "INSERT INTO sample(idIntents, question, respond) VALUES (?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idIntents);
			stmt.setString(2, sample.getQuestion());
			stmt.setString(3, sample.getRespond());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, rs);
		}

	}

	@Override
	public void updateSapmle(Sample sample, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSample(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sample getSampleByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sample> SearchSample(int idIntents, String key) {
		List<Sample> listSample = new ArrayList<>();
		try {
        	conn = getConnection();
            String sql = "SELECT * FROM sample WHERE idIntents = ? AND (question LIKE ? OR respond LIKE ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idIntents);
            stmt.setString(2, "%"+key+"%");
            stmt.setString(3, "%"+key+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Sample sample = new Sample();
                sample.setId(rs.getInt("id"));
                sample.setIdIntents(idIntents);
                sample.setQuestion(rs.getString("question"));
                sample.setRespond(rs.getString("respond"));
                listSample.add(sample);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
        return listSample;
	}

}
