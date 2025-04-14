package ru.jurden.calcs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INCORRECT_BODY(-1000, "Некорректный запрос"),
    VALIDATION_ERROR(-1001, "Ошибка валидации"),
    ;

    private final Integer code;
    private final String message;

}
