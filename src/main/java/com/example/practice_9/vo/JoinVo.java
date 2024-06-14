package com.example.practice_9.vo;

public class JoinVo {

	private String id;

	private String name;

	private Integer belance;

	public JoinVo() {
		super();
	}

	public JoinVo(String id, String name, Integer belance) {
		super();
		this.id = id;
		this.name = name;
		this.belance = belance;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getBelance() {
		return belance;
	}

}
