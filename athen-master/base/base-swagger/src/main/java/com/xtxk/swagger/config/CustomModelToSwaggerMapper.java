package com.xtxk.swagger.config;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Documentation;
import springfox.documentation.service.Operation;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2021/11/26
 */

//@Primary //同一个接口，可能会有几种不同的实现类，而默认只会采取其中一种的情况下
//@Component("CustomModelToSwaggerMapper")
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomModelToSwaggerMapper {


//    @Override
//    public Swagger mapDocumentation(Documentation from) {
//        final Swagger swagger = super.mapDocumentation(from);
//        if (Objects.isNull(swagger)) {
//            return null;
//        }
//        Map<String, Path> map = mapOriginalSortedApiListings(from.getApiListings());
//        if (map != null) {
//            swagger.setPaths(map);
//        }
//        return swagger;
//    }

//    private Map<String, Path> mapOriginalSortedApiListings(Multimap<String, ApiListing> multimap) {
//        Map<String, Path> pathMap = new LinkedHashMap<>();
//        for (ApiListing each : multimap.values()) {
//            for (ApiDescription api : each.getApis()) {
//                pathMap.put(api.getPath(), mapOperations(api, com.google.common.base.Optional.fromNullable(pathMap.get(api.getPath()))));
//            }
//        }
//        return pathMap;
//    }

//    private Path mapOperations(ApiDescription api, Optional<Path> optionalPath) {
//        Path path = optionalPath.or(new Path());
//        for (Operation each : api.getOperations()) {
//            io.swagger.models.Operation operation1 = mapOperation(each);
//            path.set(each.getMethod().toString().toLowerCase(), operation1);
//        }
//        return path;
//    }
}
