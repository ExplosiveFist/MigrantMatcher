package pt.migrantmatcher.facade.dto;

public class AjudasDTOBuilder {

	private String date;
	private String code;
	private int ownerPhone;
	private String type;
	private String region = null; //default
	private String desc;
	
	public AjudasDTOBuilder setCode(String c) {
		this.code = c;
		return this;
	}

	public AjudasDTOBuilder setDate(String d) {
		this.date = d;
		return this;
	}
	
	public AjudasDTOBuilder setOwnerPhone(int x) {
		this.ownerPhone = x;
		return this;
	}
	
	public AjudasDTOBuilder setType(String t) {
		this.type = t;
		return this;
	}
	
	public AjudasDTOBuilder setRegion(String r) {
		this.region = r;
		return this;
	}
	
	public AjudasDTOBuilder setDesc(String d) {
		this.desc = d;
		return this;
	}
	
	public AjudasDTO build() {
		return new AjudasDTO(type, ownerPhone, code, desc, region, date);
	}
	
	
}
