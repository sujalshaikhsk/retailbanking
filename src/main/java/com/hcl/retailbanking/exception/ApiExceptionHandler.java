package com.hcl.retailbanking.exception;

import com.hcl.retailbanking.dto.ApiExceptionDto;
import com.hcl.retailbanking.util.ApiConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * handleNullPointerExceptions()
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ApiExceptionDto handleNullPointerExceptions(NullPointerException ex) {
        String defaultMessage = ex.getMessage();
        return new ApiExceptionDto(ApiConstant.NO_ELEMENT_FOUND, defaultMessage);
    }

    /**
     * handleValidationError()
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiExceptionDto handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return new ApiExceptionDto(ApiConstant.VALIDATION_FAILED, defaultMessage);
    }



    /**
     * handleProductNotFoundException()
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiExceptionDto handleProductNotFoundException(ProductNotFoundException ex) {
        String msg = ex.getMessage();
        return new ApiExceptionDto(ApiConstant.VALIDATION_FAILED, msg);
    }

    /**
     * handleAllExceptions()
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ApiExceptionDto handleAllExceptions(RuntimeException ex) {
        String defaultMessage = ex.getMessage();
        return new ApiExceptionDto(ApiConstant.INTERNAL_SERVER_ERROR, defaultMessage);
    }

}
