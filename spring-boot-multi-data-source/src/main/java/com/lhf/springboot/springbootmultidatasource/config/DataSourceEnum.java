package com.lhf.springboot.springbootmultidatasource.config;

import org.springframework.boot.jdbc.DataSourceBuilder;

public enum DataSourceEnum {
    MASTER,
    SLAVE;

    public Object getDataSourceName() {
        return DataSourceBuilder.create().build();
    }
}
