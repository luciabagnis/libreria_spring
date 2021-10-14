package com.example.demo.error;

@SuppressWarnings("serial")
public class ErrorServicio extends Exception {

	public ErrorServicio(String msn) {
		super(msn);
	}
}