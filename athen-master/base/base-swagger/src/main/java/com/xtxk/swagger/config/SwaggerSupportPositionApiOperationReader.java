package com.xtxk.swagger.config;

import com.google.common.base.Optional;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.OperationNameGenerator;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.OperationReader;

import java.util.*;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/12/7
 */
//@Component
public class SwaggerSupportPositionApiOperationReader {
    private static final Set<RequestMethod> allRequestMethods = new LinkedHashSet<>();
//    private final DocumentationPluginsManager pluginsManager;
//    private final OperationNameGenerator nameGenerator;

//    @Autowired
//    public SwaggerSupportPositionApiOperationReader(DocumentationPluginsManager pluginsManager, OperationNameGenerator nameGenerator) {
//        this.pluginsManager = pluginsManager;
//        this.nameGenerator = nameGenerator;
//    }

//    @Override
//    public List<Operation> read(RequestMappingContext requestMappingContext) {
//        List<Operation> operations = new ArrayList<>();
//        Set<RequestMethod> requestMethods = requestMappingContext.getMethodsCondition();
//        Set<RequestMethod> supportedMethods = supportedMethods(requestMethods);
//        Integer currentCount = 0;
//        int position = getApiOperationPosition(requestMappingContext, 0);
//        for (RequestMethod requestMethod : supportedMethods) {
//            OperationContext operationContext = new OperationContext(new OperationBuilder(nameGenerator), requestMethod, requestMappingContext, (position + currentCount));
//            Operation operation = pluginsManager.operation(operationContext);
//            if (!operation.isHidden()) {
//                operations.add(operation);
//                currentCount++;
//            }
//        }
//        Collections.sort(operations, requestMappingContext.operationOrdering());
//        return operations;
//    }

    private Set<RequestMethod> supportedMethods(final Set<RequestMethod> requestMethods) {
        return requestMethods == null || requestMethods.isEmpty() ? allRequestMethods : requestMethods;
    }

}
