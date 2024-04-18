package com.example.book.store.rest.response;

import java.util.List;

public class MultipleResponse<T>{

    private boolean isSuccessfull;
    private List<T> entityList;

    public MultipleResponse(){}

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    public MultipleResponse(boolean isSuccessfull, List<T> entityList) {
        this.isSuccessfull = isSuccessfull;
        this.entityList = entityList;
    }

    @Override
    public String toString() {
        return "MultipleResponse{" +
                "isSuccessfull=" + isSuccessfull +
                ", entityList=" + entityList +
                '}';
    }
}
