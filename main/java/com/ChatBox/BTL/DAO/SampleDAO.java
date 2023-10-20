package com.ChatBox.BTL.DAO;

import java.util.List;

import com.ChatBox.BTL.Model.Sample;

public interface SampleDAO {
	List<Sample> getSampleByIntents(int idIntents);
	void addSample(Sample sample, int idIntents);
	void updateSapmle(Sample sample, int id);
	void deleteSample(int id);
	Sample getSampleByID(int id);
	List<Sample> SearchSample(int idIntents, String key);
	
}
