package com.example.book.store.rest.response;

public class SingleResponse<T> {

    private T entity;
    private boolean issucessfull;

    public SingleResponse(){}

    public SingleResponse(T entity, boolean issucessfull) {
        this.entity = entity;
        this.issucessfull = issucessfull;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isIssucessfull() {
        return issucessfull;
    }

    public void setIssucessfull(boolean issucessfull) {
        this.issucessfull = issucessfull;
    }

    @Override
    public String toString() {
        return "SingleResponse{" +
                "entity=" + entity +
                ", issucessfull=" + issucessfull +
                '}';
    }
}
