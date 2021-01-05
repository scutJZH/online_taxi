package com.online_taxi.entity;

import com.online_taxi.constant.CommonConstants;
import com.sun.deploy.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Data
public class Query {
    private static final Integer ONE = 1;

    protected static final List<String> SUPPORT_SORT_DIRECTION_LIST = Arrays.asList("asc", "ASC", "desc", "DESC");

    public String getDefaultSortBy() {
        return "";
    }

    protected String getDefaultSortDirection() {
        return "";
    }

    protected List<String> getSupportSortByList() {
        return new ArrayList<>();
    }

    /**
     * 初始化分页排序参数
     *
     * @param pageNo            页码
     * @param pageSize          总页数
     * @param sortByList        排序字段列表
     * @param sortDirectionList 排序方式列表
     */
    public void init(Integer pageNo, Integer pageSize, List<String> sortByList, List<String> sortDirectionList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortByList = sortByList;
        this.sortDirectionList = sortDirectionList;
        checkAndCorrectSort();
    }

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private List<String> sortByList;

    /**
     * 排序方式
     */
    private List<String> sortDirectionList;

    /**
     * 连接排序，调用该方法前必须先调用{@link #init}方法
     *
     * @history 2020/03/02 zhaoaohan@tp-link.com.cn create
     */
    public String joinSort() {
        if (CollectionUtils.isEmpty(sortByList)) {
            sortByList.add(getDefaultSortBy());
        }
        List<String> sortList = new ArrayList<>();
        for (int i = 0; i < sortByList.size(); i++) {
            sortList.add(sortByList.get(i) + CommonConstants.BLANK_DELIMITER
                    + ((CollectionUtils.isEmpty(sortDirectionList) || i > sortDirectionList.size()) ? getDefaultSortBy() : sortDirectionList.get(i)));
        }
        return StringUtils.join(sortList, CommonConstants.COMMA_DELIMITER);
    }

    /**
     * 检查并处理排序字段和排序
     *
     * @history 2020/03/02 zhaoaohan@tp-link.com.cn create
     */
    private void checkAndCorrectSort() {
        if (!CollectionUtils.isEmpty(sortByList)) {
            // 检查排序字段
            for (Iterator<String> iterator = sortByList.iterator(); iterator.hasNext(); ) {
                String sortBy = iterator.next();
                if (!getSupportSortByList().contains(sortBy)) {
                    log.warn("不支持的排序字段：{}，强制删除", sortBy);
                    iterator.remove();
                }
            }
        }
        if (!CollectionUtils.isEmpty(sortDirectionList)) {
            // 检查排序方式
            for (int i = 0; i < sortDirectionList.size(); i++) {
                String sortDirection = sortDirectionList.get(i);
                if (!SUPPORT_SORT_DIRECTION_LIST.contains(sortDirection)) {
                    log.warn("不支持的排序方式：{}，强制置为默认排序方式：{}", sortDirection, getDefaultSortDirection());
                    sortDirectionList.set(i, getDefaultSortDirection());
                }
            }
        }
    }

}
