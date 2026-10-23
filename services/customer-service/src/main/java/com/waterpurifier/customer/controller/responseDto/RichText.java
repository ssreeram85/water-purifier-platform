package com.waterpurifier.customer.controller.responseDto;

import lombok.Builder;

import java.util.Arrays;
@Builder
public record RichText(String bundleId, Object[] args) {

    @Override
    public String toString(){
        return "RichText[" + "bundleId=" + bundleId + ", args=" + Arrays.toString(args) + ']';
    }
    public static RichText of(String bundleId){
        return RichText.builder().bundleId(bundleId).build();
    }

    public static RichText of(String bundleId, Object... args){
        return RichText.builder().bundleId(bundleId).args(args).build();
    }
}
