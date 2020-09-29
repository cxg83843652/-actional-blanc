package cn.actional.phoneix.generator.mapper;

import cn.actional.phoneix.common.utils.QueryParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 9:35 PM
 */
@Repository
public interface SysGeneratorMapper {
    /**
     *  查询所有表信息
     * @param queryParam  参数
     * @return  表信息
     */
    List<Map<String,Object>> queryList(QueryParam queryParam);


    /**
     *  获取表信息
     * @param tableName   表名
     * @return  表信息
     */
    Map<String,String> selectTableByTableName(@Param("tableName") String tableName);

    /**
     *  获取表字段信息
     * @param tableName  表名
     * @return  表字段
     */
    List<Map<String, String>> selectColumnsByTableName(@Param("tableName") String tableName);
}
