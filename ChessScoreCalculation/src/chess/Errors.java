package chess;
import chess.ErrorCode;

public enum Errors implements ErrorCode {
    //program boyu fırlatılacak hatalar burda toplandı
	
	 DOSYA_BULUNAMADI("dosya bulunamadı");

	
    private String errorCode;
    
    Errors(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getKod() {
        return errorCode;
    }


}
