package com.hansson.rento.utils.apartments;

import java.util.List;

public class BaseJsonResult {
	private List<BaseJsonApartment> Result;

	public List<BaseJsonApartment> getResult() {
		return Result;
	}

	public void setResult(List<BaseJsonApartment> result) {
		Result = result;
	}
}
