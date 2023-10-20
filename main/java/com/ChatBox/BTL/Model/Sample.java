package com.ChatBox.BTL.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
	private int id;
	private int idIntents;
	private String question;
	private String respond;
}
