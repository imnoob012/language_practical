package com.forgeon.membermanagement.exception;



public class DataAccessException extends RuntimeException {
        // シリアルバージョンUIDは、フィールドの削除や型変更の際にIDを変更する。
		private static final long serialVersionUID = 1L;

		public DataAccessException(String message, Throwable cause) {
            super(message, cause); 
        }
    }

//	super(message, cause)は親クラス(RuntimeException)に情報を渡している。
//	cause⇨原因となった元の例外を受け取っている