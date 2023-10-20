package com.ChatBox.BTL.DAO;

import java.util.List;

import com.ChatBox.BTL.Model.Intents;

public interface IntentsDAO {
	List<Intents> getAllIntents();
	Intents getIntentByID(int id);
	void addIntents(Intents intents);
	void updateIntents(Intents intents, int id);
	void deleteIntents(int id);
	List<Intents> searchIntents(String key);
}
