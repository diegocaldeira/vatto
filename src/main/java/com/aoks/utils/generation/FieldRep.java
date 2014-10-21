package com.aoks.utils.generation;

public class FieldRep {

	private String name;
	private String type;
	private String visibility;
	
	private boolean finall;
	private boolean staticc;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public boolean isFinall() {
		return finall;
	}
	public void setFinall(boolean finall) {
		this.finall = finall;
	}
	
	public boolean isStaticc() {
		return staticc;
	}
	public void setStaticc(boolean staticc) {
		this.staticc = staticc;
	}
	
	public static FieldRep getSerialVersionUIDField(){
		FieldRep ret = new FieldRep();
		
		ret.setFinall(true);
		ret.setName("serialVersionUID");
		ret.setType("long");
		ret.setVisibility("private");
		ret.setStaticc(true);
		
		return ret;
	}
	
}
