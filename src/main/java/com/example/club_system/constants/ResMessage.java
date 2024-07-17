package com.example.club_system.constants;

public enum ResMessage {

	SUCCESS(200,"Success!!"),
	UPDATE_ID_NOT_FOUND(404, "Club id not found"),
	PARAM_CLUB_ID_EXIST(400,"Param club id exist!!"),//
	PARAM_CLUB_ID_NOT_EXIST(400,"Param club id not exist!!"),//
	PARAM_CLUB_NAME_ERROR(400,"Param club name error!!"),//
	PARAM_CLUB_INTRO_ERROR(400,"Param club intro error!!"),//
	PARAM_PAY_ERROR(400,"Param pay error!!"),//
	PARAM_CLASSROOM_ERROR(400,"Param classroom error!!"),//
	PARAM_CLUB_MAX_ERROR(400,"Param club max error!!"),//
	ACCOUNT_OR_PASSWORD_ERROR(400, "Account or password error!!"),// �b�K���~
	AMOUNT_ERROR(400, "Amount error"),//���B���~
	ACCOUNT_EXISTS(400,"Account exists"),//�b���w�s�b
	ACCOUNT_NOT_FOUND(404,"Account not found"),//�䤣��b��(�b�����s�b)
	PSAAWORD_ERROR(400, "Password error"),//�K�X���~
	INSUFFICIENT_BALANCE(400,"Insufficient balance"),//�l�B����
	BAD_REQUEST(400,"Bad request"),//
	PLEASE_LOGIN_FIRST(400,"Please login first"),//
	UPDATE_TEACHER_ID_NOT_FOUND(404,"Update teacher id not found"),//������s �ѮvID
	PROCESSING_EXCEPTION(400,"ProcessingException!!"), //
	UPDATE_STUDENT_ID_NOT_FOUND(404, "Update student id not found"),//
	STUDENT_ID_ERROR(400, "Student id error"),
	TEACHER_ID_NOT_FOUND(404,"Teacher id not found"),
	JSON_ERROR(400,"Json error!!"),//JSON�榡����
	STUDENT_ID_NOT_FOUND(404,"Student id not found"),
	CLUB_ID_NOT_FOUND(404, "Update student id not found");//�䤣��b��(�b�����s�b)
	
	
	private int code;
	
	private String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
