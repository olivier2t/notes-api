package com.agendup.yoda.cassandra.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;

import com.agendup.yoda.CassandraClientManager;
import com.agendup.yoda.Utils;
import com.agendup.yoda.model.ApiMessage;
import com.datastax.driver.mapping.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiRequest<A, D> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Class<A> apiClass = null;
	private Class<D> dbClass = null;

	private boolean httpGetOne = false;
	private boolean httpGetList = false;
	private boolean httpPut = false;
	private boolean httpPost = false;
	private boolean httpPatch = false;
	private boolean httpDelete = false;

	private Object[] keys = null;
	private D requestObject = null;
	private D currentObject = null;
	private List<D> responseObjects = new ArrayList<>();
	private int responseCode;
	private String responseMessage = "";
	private Map<String, String> responseHeaders = new HashMap<String, String>();

	public ApiRequest() {
	}

	public ApiRequest(Class<A> apiClass, Class<D> dbClass) {
		this.apiClass = apiClass;
		this.dbClass = dbClass;
	}

	public void setGetOne(Object...keys) {
		this.httpGetOne = true;
		this.keys = keys;
		
		Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
		this.currentObject = mapper.get(keys);
	}

	public void setGetList() {
		this.httpGetList = true;
	}
	
	@SuppressWarnings("unchecked")
	public void setPost(A requestObject, Object...keys) {
		this.httpPost = true;
		this.requestObject = (D) Utils.copyProperties(requestObject, this.dbClass);
		this.keys = keys;

		Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
		if (mapper.get(keys) != null) {
			this.responseCode = 409;
			this.responseMessage = "Conflict";
		}
	}

	@SuppressWarnings("unchecked")
	public void setPut(A requestObject, Object...keys) {
		this.httpPut = true;
		this.requestObject = (D) Utils.copyProperties(requestObject, this.dbClass);
		this.keys = keys;
		
		Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
		this.currentObject = mapper.get(keys);
		Utils.copyDbProperties(this.currentObject, this.requestObject, this.apiClass);
	}

	@SuppressWarnings("unchecked")
	public void setPatch(A requestObject, Object...keys) {
		this.httpPatch = true;
		this.keys = keys;
		
		Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
		this.currentObject = mapper.get(keys);
		this.requestObject = (D) Utils.copyProperties(this.currentObject, this.dbClass);
		
		if (this.currentObject != null) {
			Utils.copyDbProperties(this.currentObject, this.requestObject, this.apiClass);

			Method m[] = requestObject.getClass().getMethods();
			for (int i = 0; i < m.length; i++) {
				if ((m[i].getName().startsWith("get") || m[i].getName().startsWith("is")) && !m[i].getName().equals("getClass")) {
					try {
						Object requestValue = new Object();
						requestValue = m[i].invoke(requestObject);

						if (requestValue != null) {
							Class rtype = m[i].getReturnType();
							String setterName = m[i].getName().replaceFirst("^(get|is)", "set");
							Class s = this.requestObject.getClass();
							Method setter = s.getMethod(setterName, rtype);
							setter.invoke(this.requestObject, requestValue);
							logger.debug("PATCHing requested object {}({})", setter.getName(), requestValue);
						}
					} catch (Exception e) {
						logger.warn("Copy failed while preparing HTTP PATCH. RequestObject is: "+ this.requestObject, e);
					}
				}
			}
		}
	}

	public void setDelete(Object...keys) {
		this.httpDelete = true;
		this.keys = keys;
		
		Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
		this.currentObject = mapper.get(keys);
	}

	public Object[] getKeys() {
		return keys;
	}

	public void setKeys(Object...keys) {
		this.keys = keys;
	}

	public D getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(D requestObject) {
		this.requestObject = requestObject;
	}

	@SuppressWarnings("unchecked")
	public void setRequestObjectFromApi(A requestObject) {
		this.requestObject = (D) Utils.copyProperties(requestObject, this.dbClass);
	}

	public D getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(D currentObject) {
		this.currentObject = currentObject;
	}

	public List<D> getResponseObjects() {
		return responseObjects;
	}

	public void addResponseObject(D object) {
		if (object != null) {
			this.responseObjects.add(object);
		}
	}

	public void setResponseObjects(List<D> responseObjects) {
		this.responseObjects = responseObjects;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public void addResponseHeader(String k, String v) {
		if (k != null && v != null) {
			this.responseHeaders.put(k, v);
		}
	}

	/**
	 * Execute a write operation in the database (POST, PUT, PATCH and DELETE).
	 * 
	 * @return ApiRequest<A, D> with operation resulting status
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ApiRequest<A, D> execute() {

		if (this.httpGetOne) {
//			Mapper<D> mapper = DSEClientManager.getManager().mapper(this.dbClass);
//			this.addResponseObject((D) mapper.get(this.keys));
			this.addResponseObject(this.getCurrentObject());
			this.setResponseCode(200);
			this.setResponseMessage("OK");

		} else if (this.httpGetList) {
			// this.sortResponseObjects();
			// this.pageResponseObjects();
			this.setResponseCode(200);
			this.setResponseMessage("OK");
			
		} else if (this.isHttpPost()) {
			Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
			mapper.save(this.getRequestObject());
			this.setResponseCode(200);
			this.setResponseMessage("OK");

		} else if (this.isHttpPut() || this.isHttpPatch()) {
			if (this.getCurrentObject() == null) {
				this.setResponseCode(404);
				this.setResponseMessage("Not Found");
				
			} else {
				Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
				mapper.save(this.getRequestObject());
				this.setResponseCode(200);
				this.setResponseMessage("OK");
			}

		} else if (this.isHttpDelete()) {
			if (this.getCurrentObject() == null) {
				this.setResponseCode(404);
				this.setResponseMessage("Not Found");
				
			} else {
				Mapper<D> mapper = CassandraClientManager.getManager().mapper(this.dbClass);
				mapper.delete(this.getCurrentObject());
				this.setResponseCode(200);
				this.setResponseMessage("OK");
			}
		}

		return this;
	}

	@SuppressWarnings("unchecked")
	public javax.ws.rs.core.Response buildApiResponse() {
		ResponseBuilder rb = javax.ws.rs.core.Response.status(this.getResponseCode());
		rb.type("application/json");

		ApiMessage apiMessage = new ApiMessage();
		apiMessage.setCode(this.getResponseCode());
		apiMessage.setMessage(this.getResponseMessage());

		if (this.isHttpGetList()) {
			if (this.responseObjects.size() > 0) {
				List<A> responseApiObjects = new ArrayList<>();
				for (D obj : responseObjects)
					responseApiObjects.add((A) Utils.copyProperties(obj, this.apiClass));
				rb.entity(responseApiObjects);
	
			} else {
				apiMessage.setCode(404);
				apiMessage.setMessage("Not Found");
				rb.status(404);
				rb.entity(apiMessage);
			}

		} else if (this.isHttpGetOne()) {
			// Returns the queried object
			if (this.responseObjects.size() > 0) {
				rb.entity((A) Utils.copyProperties(this.responseObjects.get(0), this.apiClass));

			} else {
				apiMessage.setCode(404);
				apiMessage.setMessage("Not Found");
				rb.status(404);
				rb.entity(apiMessage);
			}

		} else if (this.isHttpDelete()) {
			// Returns response code and message in body
			rb.entity(apiMessage);

		} else if (this.isHttpPut() || this.isHttpPatch() || this.isHttpPost()) {
			rb.entity((A) Utils.copyProperties(this.getRequestObject(), this.apiClass));

		} else if (this.getResponseCode() >= 300) {
			// Query has failed. Returns response code and message in body
			rb.entity(apiMessage);

		} else {
			rb.entity(apiMessage);
			logger.error("We should never reach this point... Have a code review !");
		}

		// Insert headers in Response
		for (String key : this.getResponseHeaders().keySet())
			rb.header(key, this.getResponseHeaders().get(key));

		return rb.build();
	}

	public boolean isHttpGetOne() {
		return httpGetOne;
	}

	public void setHttpGetOne(boolean httpGetOne) {
		this.httpGetOne = httpGetOne;
	}

	public boolean isHttpGetList() {
		return httpGetList;
	}

	public void setHttpGetList(boolean httpGetList) {
		this.httpGetList = httpGetList;
	}
	
	public boolean isHttpPut() {
		return httpPut;
	}

	public void setHttpPut(boolean httpPut) {
		this.httpPut = httpPut;
	}

	public boolean isHttpPost() {
		return httpPost;
	}

	public void setHttpPost(boolean httpPost) {
		this.httpPost = httpPost;
	}

	public boolean isHttpPatch() {
		return httpPatch;
	}

	public void setHttpPatch(boolean httpPatch) {
		this.httpPatch = httpPatch;
	}

	public boolean isHttpDelete() {
		return httpDelete;
	}

	public void setHttpDelete(boolean httpDelete) {
		this.httpDelete = httpDelete;
	}

	@Override
	public String toString() {
		return "ApiRequest [apiClass=" + apiClass + ", dbClass=" + dbClass + ", httpGetOne=" + httpGetOne
				+ ", httpGetList=" + httpGetList + ", httpPut=" + httpPut + ", httpPost=" + httpPost + ", httpPatch="
				+ httpPatch + ", httpDelete=" + httpDelete + ", requestObject=" + requestObject + ", responseObjects=" + responseObjects + ", responseCode="
				+ responseCode + ", responseMessage=" + responseMessage + ", responseHeaders=" + responseHeaders + "]";
	}

}
