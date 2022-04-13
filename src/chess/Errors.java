package chess;
import chess.ErrorCode;

public enum Errors implements ErrorCode {
    //program boyu fýrlatýlacak hatalar burda toplandý
	
	 DOSYA_BULUNAMADI("dosya bulunamadý");

	
    private String errorCode;
    
    Errors(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getKod() {
        return errorCode;
    }


}
