package com.xtxk.dataSource.processor.iterator;

import com.xtxk.dataSource.processor.DsProcessor;
import org.aopalliance.intercept.MethodInvocation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: chenying
 * Date: 2019-07-10
 * Time: 15:25
 * since: 1.0.0
 */
public abstract class AbstractProcessorBuilder implements Iterator<DsProcessor> {

    protected List<DsProcessor> processors = null;

    /**
     * 游标
     **/
    protected int cursor;

    public AbstractProcessorBuilder(List<DsProcessor> processors) {
        if (processors != null) {
            this.processors = processors;
            this.cursor = this.processors.size() - 1;
        }
    }

    /**
     * @param invocation 拦截器方法
     * @param key        数据源注解DS value值
     * @return 数据源对应的key值
     **/
    protected abstract String determineDatasource(MethodInvocation invocation, String key);

    /**
     * 动态添加 DsProcessor
     *
     * @param processor
     **/
    public void addDsProcessor(DsProcessor processor) {
        if (this.processors == null) {
            this.processors = new ArrayList<>();
        }
        this.processors.add(processor);
        cursor = processors.size() < 1 ? 0 : processors.size() - 1;

    }

    @Override
    public boolean hasNext() {
        return this.processors.size() > 0;
    }

    @Override
    public DsProcessor next() {
        DsProcessor processor = this.processors.get(this.cursor);
        this.cursor--;
        return processor;
    }

    @Override
    public void remove() {
        this.processors.clear();
    }


}
