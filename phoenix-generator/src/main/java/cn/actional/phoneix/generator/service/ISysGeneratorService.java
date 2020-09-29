package cn.actional.phoneix.generator.service;

import cn.actional.phoneix.common.utils.PageUtils;
import cn.actional.phoneix.common.utils.QueryParam;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 9:35 PM
 */
public interface ISysGeneratorService {

    /**
     *  逆向工程
     * @param tables 表名
     * @return  zip
     */
    byte[] generatorCode(String[] tables) ;

    /**
     *  查询所有表信息
     * @param queryParam 参数
     * @return  表信息
     */
    PageUtils queryList(QueryParam queryParam);
}
