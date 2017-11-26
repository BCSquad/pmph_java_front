/*
 * Copyright 2017 Masslink technology Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.bc.pmpheep.service.exception;

/**
 * 自定义服务层异常，用于抛出已知异常
 * 
 * @author L.X <gugia@qq.com>
 */
@SuppressWarnings("serial")
public class CheckedServiceException extends RuntimeException {

    private final String                 business;
    private String                       url;
    private final CheckedExceptionResult result;

    public CheckedServiceException(String business, CheckedExceptionResult result, String message) {
        super(message);
        this.business = business;
        this.result = result;
    }

    public CheckedServiceException(String business, CheckedExceptionResult result, String message,
    String url) {
        super(message);
        this.business = business;
        this.result = result;
        this.url = url;
    }

    public CheckedServiceException(String business, CheckedExceptionResult result, String message,
    Throwable cause) {
        super(message, cause);
        this.business = business;
        this.result = result;
    }

    public CheckedServiceException(String business, CheckedExceptionResult result, Throwable cause) {
        super(cause);
        this.business = business;
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[业务类型: ");
        // sb.append(super.toString()).append(" - [业务类型: ");
        sb.append(this.business).append("] <");
        sb.append("异常结果: ").append(this.result.getMessage()).append("> ");
        sb.append(this.getMessage());
        return sb.toString();
    }

    public String getBusiness() {
        return business;
    }

    public CheckedExceptionResult getResult() {
        return result;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
