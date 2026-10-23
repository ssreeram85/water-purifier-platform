package com.waterpurifier.customer.controller.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public static ServiceResponse<RichText> successMessage(String messageBundle,String[] args){
       return ServiceResponse.of(new RichText(messageBundle,args));
    }

    public static <T> ServiceResponse<T> of(T data){
        return new ServiceResponse<>(data);
    }


}
