package cn.actional.blanc.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-09-28 22:40
 */
@Data
public class PageUtils {
    /**
     *  总条数
     */
    private Integer total;
    /**
     *  总页数
     */
    private Integer totalPage;
    /**
     *  单页页码
     */
    private Integer currentPage;
    /**
     *  每页条数
     */
    private Integer pageSize;

    /**
     * 是否有前一页
     */
    private boolean hasPre = false;
    /**
     * 是否有下一页
     */
    private boolean hasNext = false;
    /**
     *   数据
     */
    private List<?> list;


    public PageUtils(Integer total,  Integer currentPage,
                     Integer pageSize, List<?> list,
                     boolean hasPre,boolean hasNext) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.totalPage = (int) Math.ceil((double) total / pageSize);
        this.hasNext = hasNext;
        this.hasPre = hasPre;
    }

    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.currentPage = Math.toIntExact(page.getCurrent());
        this.pageSize = Math.toIntExact(page.getSize());
        this.total = Math.toIntExact(page.getTotal());
        this.totalPage = Math.toIntExact(page.getPages());
    }
}
