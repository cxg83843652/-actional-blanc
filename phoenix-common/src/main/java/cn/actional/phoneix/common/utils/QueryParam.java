package cn.actional.phoneix.common.utils;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 10:15 PM
 */
@Data
public class QueryParam extends LinkedHashMap<String,Object> {

    /**
     * 当前页码
     */
    private Integer page;
    /**
     *  每页条数
     */
    private Integer limit;

    public QueryParam(Map<String,Object> map) {
        this.putAll(map);
        this.page = Integer.parseInt(map.get("page").toString());
        this.limit = Integer.parseInt(map.get("limit").toString());
        this.put("page", page);
        this.put("limit", limit);
    }
}
