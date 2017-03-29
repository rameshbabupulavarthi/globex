package com.globex.model.vo;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 3/19/2017.
 */
@Data
public class PageModel<T> {

    private Integer pageNo;

    private Integer pageSize;

    private Integer rowCount;

    private Long totalRecords;

    private List<T> content;

    private Map<String,Object> filters;

    private LinkedHashMap<String,SortOrder> sortFields;


    public enum SortOrder{
        ASC,DESC;
    }
}


