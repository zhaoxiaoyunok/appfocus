package com.lyyj.activity.demo.edittext.validator.sample;

import android.text.TextUtils;
import android.widget.EditText;

import com.lyyj.activity.demo.edittext.validator.Validator;

public class CiaoValidator
    extends Validator
{

	public CiaoValidator( String customErrorMessage )
	{
		super( customErrorMessage );

	}

	@Override
	public boolean isValid( EditText et )
	{
		return TextUtils.equals( et.getText(), "ciao" );
	}

}