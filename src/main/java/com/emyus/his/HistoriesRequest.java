package com.emyus.his;

import java.io.Serializable;
import java.sql.Timestamp;

public class HistoriesRequest implements Serializable{

	private int id;
	private int user_id;
	private int point;
	private Timestamp created_at;

	HistoriesRequest(int id, int user_id, int point, Timestamp created_at){
		this.id = id;
		this.user_id = user_id;
		this.point = point;
		this.created_at = created_at;
	}

	HistoriesRequest(){
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Timestamp getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

}
