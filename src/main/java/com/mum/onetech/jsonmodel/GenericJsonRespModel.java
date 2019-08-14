package com.mum.onetech.jsonmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericJsonRespModel {
    private GenericRespStatus respStatus;
    private String nextUrl;
    private List<Object> arrData;
    private String message = "";
}
