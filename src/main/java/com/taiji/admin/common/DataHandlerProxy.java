/**
 * 
 */
package com.taiji.admin.common;

import java.io.Serializable;

import javax.activation.DataHandler;

/**
 * 
 * sw-view com.taiji.admin.common DataHandlerProxy.java
 *
 * @author hsl
 *
 * 2019年11月30日 下午9:23:40
 *
 * desc:
 */
public class DataHandlerProxy implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4287454647254033415L;
	private DataHandler dataHandler;
    private String prifix;
    
	public DataHandler getDataHandler() {
		return dataHandler;
	}
	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	public String getPrifix() {
		return prifix;
	}
	public void setPrifix(String prifix) {
		this.prifix = prifix;
	}

}
