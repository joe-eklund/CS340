package shared.ServerMethodRequests;

import javax.annotation.Resource;

public class MaritimeTradeRequest {
	private int ratio;
	private Resource inputResource;
	private Resource outputResource;
	
	public MaritimeTradeRequest(int ratio, Resource inputResource,
			Resource outputResource) {
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}
	
	public int getRatio() {
		return ratio;
	}
	
	public Resource getInputResource() {
		return inputResource;
	}
	
	public Resource getOutputResource() {
		return outputResource;
	}
	
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}
	
	public void setOutputResource(Resource outputResource) {
		this.outputResource = outputResource;
	}
	
}
