package com.xtxk.core.util;

import com.github.pagehelper.PageInfo;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @ClassName: PageHelperUtils
 * @Description: 分页工具类
 * @Author
 * @Date 2021/1/18 0018 16:00
 */
public abstract class PageHelperUtils {
    /**
     * PageHelper中，如果当前页是最后一页，则返回的nextPage是0，即首页，而有时我们需要最后一页的nextPage是lastPage，因此写此方法
     *
     * @param currentPage
     * @param pageObj
     * @return
     */
    public static <T> PageInfo<T> lastPageSetNextPage(int currentPage, PageInfo<T> pageObj) {
        if (currentPage == pageObj.getPages()) {
            pageObj.setNextPage(pageObj.getPages());
        }
        return pageObj;
    }

    public static <T> PageInfo<T> initPageInfoObj(int currentPage, int total, int pageSize, PageInfo<T> pageInfo) {
        pageInfo.setNextPage(currentPage < ((total + pageSize - 1) / pageSize) ? currentPage + 1 : currentPage);
        pageInfo.setTotal(total);
        pageInfo.setPageNum(currentPage);
        pageInfo.setPages((total + pageSize - 1) / pageSize);
        pageInfo.setLastPage((total + pageSize - 1) / pageSize);
        pageInfo.setPrePage(currentPage > 1 ? currentPage - 1 : currentPage);
        pageInfo.setIsFirstPage(currentPage == 1);
        pageInfo.setIsLastPage(currentPage == (total + pageSize - 1) / pageSize);
        pageInfo.setHasPreviousPage(currentPage != 1);
        pageInfo.setHasNextPage(currentPage != (total + pageSize - 1) / pageSize);
        return calcNavigatepageNums(pageInfo);
    }

    private static <T> PageInfo<T> calcNavigatepageNums(PageInfo<T> pageInfo) {
        //当总页数小于或等于导航页码数时
        if (pageInfo.getPages() <= pageInfo.getNavigatePages()) {
            pageInfo.setNavigatepageNums(new int[pageInfo.getPages()]);
            for (int i = 0; i < pageInfo.getPages(); i++) {
                pageInfo.getNavigatepageNums()[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            pageInfo.setNavigatepageNums(new int[pageInfo.getNavigatePages()]);
            int startNum = pageInfo.getPageNum() - pageInfo.getNavigatePages() / 2;
            int endNum = pageInfo.getPageNum() + pageInfo.getNavigatePages() / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < pageInfo.getNavigatePages(); i++) {
                    pageInfo.getNavigatepageNums()[i] = startNum++;
                }
            } else if (endNum > pageInfo.getPages()) {
                endNum = pageInfo.getPages();
                //最后navigatePages页
                for (int i = pageInfo.getNavigatePages() - 1; i >= 0; i--) {
                    pageInfo.getNavigatepageNums()[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < pageInfo.getNavigatePages(); i++) {
                    pageInfo.getNavigatepageNums()[i] = startNum++;
                }
            }
        }
        return pageInfo;
    }
    
    private static <T> List<T> setIndex(List<T> list){
        if(A.isNotEmpty(list)){
            for(int i=0;i<list.size();i++){
                try {
                    Object o = list.get(i);
                    Field field = o.getClass().getDeclaredField("index");
                    field.setAccessible(true);
                    field.set(o,i);
                } catch (NoSuchFieldException e) {
                    LogUtil.ROOT_LOG.error(e.getMessage());
                }catch (IllegalAccessException e) {
                   LogUtil.ROOT_LOG.error(e.getMessage());
                }
            }
        }
        return list;
    }
    public static <T> PageInfo<T> resultPageInfo(int currentPage, int pageSize, List<T> list) {
        list = setIndex(list);
        int fromIndex = 0;
        int toIndex = 0;
        //取出总记录数
        int total = list.size();
        if(total==0) {
            return initPageInfoObj(currentPage, total, pageSize,new PageInfo<>(list));
        }
        //当前页码
        currentPage = (currentPage <= 0) ? 1 : currentPage;
        //当前页条目数
        pageSize = (pageSize <= 0) ? 10 : (Math.min(pageSize, total));

        if (total / pageSize == 0 && total % pageSize > 0) {
            fromIndex = 0;
            toIndex = total;
        } else {
            if (total / pageSize >= 1 && total % pageSize >= 0) {
                fromIndex = pageSize * (currentPage - 1);
                if (pageSize * currentPage >= total) {
                    toIndex = total;
                } else {
                    toIndex = pageSize * currentPage;
                }
            }
        }

        List pageList = list.subList(fromIndex, toIndex);
        PageInfo pageInfo = new PageInfo<>(pageList);
        return initPageInfoObj(currentPage, total, pageSize, pageInfo);
    }

}
