package chess;
import chess.ErrorCode;

public enum Errors implements ErrorCode {
    //program boyu f�rlat�lacak hatalar burda topland�
	
	 DOSYA_BULUNAMADI("dosya bulunamad�");

	
    private String errorCode;
    
    Errors(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getKod() {
        return errorCode;
    }


}
