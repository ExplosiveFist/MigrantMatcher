
package pt.migrantmatcher.facade.dto;


public class AjudasDTO{
	
	private String type;
	private String region;
	private String desc;
	
	public AjudasDTO(String t) {
		
		this.type = t;
		
		
	}
	public void setDesc(String d) {
		this.desc = d;
	}
	
	public void setRegion(String r) {
		this.region = r;
	}
	
	public String getType() {
		return type;
	}
	public String getRegion() {
		return region;
	}
	public String getDesc() {
		return desc;
		
	}
}