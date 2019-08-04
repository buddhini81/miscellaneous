/**
 * 
 */
package dto;

/**
 * @author buddhini
 *
 */
public class ResultDTO {

	private String ip;
	private Integer requests;
	
	
	public ResultDTO(String ip, Integer requests) {
		super();
		this.ip = ip;
		this.requests = requests;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getRequests() {
		return requests;
	}

	public void setRequests(Integer requests) {
		this.requests = requests;
	}
	
}
