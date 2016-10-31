package com.lyyj.activity.demo.edittext.validator;

public class AlphaValidator extends RegexpValidator {
	public AlphaValidator(String message) {
		super(message, "[a-zA-Z \\./-]*");
	}
}
