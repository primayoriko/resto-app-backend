package com.future.restoapp.base;

public class SingleBaseResponse<T> extends BaseResponse {

	private T value;

	public SingleBaseResponse() {
	}

	public SingleBaseResponse(String errorMessage, String errorCode, boolean success, T value) {
		super(errorMessage, errorCode, success);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SingleBaseResponse{" + "value=" + value + '}';
	}
}
