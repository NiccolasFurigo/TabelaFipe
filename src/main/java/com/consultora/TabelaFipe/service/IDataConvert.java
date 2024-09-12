package com.consultora.TabelaFipe.service;

import java.util.List;

public interface IDataConvert {
    <T> T getData(String json, Class<T> tClass);
    <T> List<T> getList(String json, Class<T> tClass);
}
