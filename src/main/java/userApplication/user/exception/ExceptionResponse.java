package userApplication.user.exception;

import java.util.Date;

public class ExceptionResponse {

    public Date timeStamp;
    public String message;

    public ExceptionResponse(Date timeStamp, String message){
        this.timeStamp= timeStamp;
        this.message= message;

    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
