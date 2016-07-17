package com.github.cnlinjie.infrastructure.web.exception;


public class CommonException extends IllegalArgumentException {
	private static final long serialVersionUID = 3230630383646562969L;
	private Integer code;
	public CommonException(Integer code) {
        super();
		this.code = code;
	}
	
	public CommonException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public CommonException(Integer code, String message, String... args) {
		super( String.format(message, args));
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
