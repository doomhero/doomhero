package com.shangchain.contract.sdk;

public class ShangchainSdkException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShangchainSdkException() {
		super();
	}

	public ShangchainSdkException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ShangchainSdkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShangchainSdkException(String message) {
		super(message);
	}

	public ShangchainSdkException(Throwable cause) {
		super(cause);
	}

}
