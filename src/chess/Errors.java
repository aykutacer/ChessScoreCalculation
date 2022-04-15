package chess;

public enum Errors implements ErrorCode {
    //program boyu fırlatılacak hatalar burda toplandı

    DOSYA_BULUNAMADI("dosya bulunamadı"),
    FAZLA_BOYUT_HATASI("olması gerekenden daha fazla veri girildi"),
    EKSIK_BOYUT_HATASI("olması gerekenden daha az veri girildi");


    private String errorCode;

    Errors(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getKod() {
        return errorCode;
    }


}
