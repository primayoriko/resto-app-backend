package com.future.restoapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ListBaseResponse<T> extends BaseResponse {

	private List<T> content = new ArrayList<T>();
	private Metadata metadata;

	public ListBaseResponse() {
	}

	public ListBaseResponse(String errorMessage, String errorCode, boolean success, List<T> content,
			Metadata metadata) {
		super(errorMessage, errorCode, success);
		this.content = content;
		this.metadata = metadata;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "ListBaseResponse{" + "content=" + content + ", metadata=" + metadata + '}';
	}
}
