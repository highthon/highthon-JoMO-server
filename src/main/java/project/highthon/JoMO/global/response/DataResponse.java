package project.highthon.JoMO.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DataResponse<T> extends Response{

    private T data;

    public DataResponse(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

    public static <T> DataResponse<T> of(HttpStatus status, String message, T data){
        return new DataResponse<>(status, message, data);
    }

    public static <T> ResponseEntity<DataResponse<T>> ok(String message, T data){
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, message, data), HttpStatus.OK);
    }
}
