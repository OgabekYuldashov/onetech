package com.mum.onetech.jsonmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericJsonRespModel {
    private GenericRespStatus respStatus;
    private String nextUrl;
    private Map<String, Object> dataMap = new HashMap<>();
    private String message = "";


}
