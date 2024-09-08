package com.restcall.caller.service;

import com.restcall.caller.dto.RequestDto;

public interface RestCallerService {
    <T> T call (RequestDto requestDto, Class<T> responseType);
}
