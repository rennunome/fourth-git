package com.emyus.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserRequest implements Serializable{

	private int id;
	//@NotBlank
	//@Pattern(message = "ユーザーネームを半角英数字入力してください。", regexp = "^[a-zA-Z0-9]+$")
	private String name;
	//@NotBlank
	//@Size(min = 8)
	//@Pattern(message = "パスワードを半角英数字で入力してください。", regexp = "^[a-zA-Z0-9]+$")
	private String password;
	private byte admin_flag;
	private Timestamp created_at;
	private Timestamp updated_at;
	private byte deleteflag;
	private Timestamp deleted_at;

	public UserRequest(int id, String name, String password, byte admin_flag){
		this.id = id;
		this.name = name;
		this.password = password;
		this.admin_flag = admin_flag;
	}

	public UserRequest(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getAdminFlag() {
		return admin_flag;
	}

	public void setAdminFlag(byte admin_flag) {
		this.admin_flag = admin_flag;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public byte getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Timestamp getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

}
