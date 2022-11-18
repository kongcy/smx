package com.xtxk.dataSource.processor.iterator;

import com.xtxk.core.util.U;
import com.xtxk.dataSource.processor.DsProcessor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

import static com.xtxk.core.Constant.DEFAULT_DB;

/**
 * User: chenying
 * Date: 2019-07-10
 * Time: 14:59
 * since: 1.0.0
 */
public class ProcessBuilder extends AbstractProcessorBuilder {

    public ProcessBuilder(List<DsProcessor> processors) {
        super(processors);
    }

    @Override
    public String determineDatasource(MethodInvocation invocation, String key) {
        for(DsProcessor processor:processors){
            String dataSource=processor.determineDatasource(invocation,key);
            if(U.isNotBlank(dataSource)){
                return dataSource;
            }
        }
        return DEFAULT_DB;
    }
}
