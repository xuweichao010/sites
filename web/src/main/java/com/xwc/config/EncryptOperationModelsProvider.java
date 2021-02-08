package com.xwc.config;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.xwc.config.mvc.EncryptBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.spring.web.readers.operation.OperationModelsProvider;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import static springfox.documentation.schema.ResolvedTypes.resolvedTypeSignature;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class EncryptOperationModelsProvider implements OperationModelsProviderPlugin, ParameterBuilderPlugin {
    private static final Logger LOG = LoggerFactory.getLogger(OperationModelsProvider.class);
    private final TypeResolver typeResolver;

    @Autowired
    public EncryptOperationModelsProvider(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }


    @Override
    public void apply(RequestMappingContext context) {
        collectFromReturnType(context);
        collectParameters(context);
        collectGlobalModels(context);
    }

    private void collectGlobalModels(RequestMappingContext context) {
        for (ResolvedType each : context.getAdditionalModels()) {
            context.operationModelsBuilder().addInputParam(each);
            context.operationModelsBuilder().addReturn(each);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private void collectFromReturnType(RequestMappingContext context) {
        ResolvedType modelType = context.getReturnType();
        modelType = context.alternateFor(modelType);
        LOG.debug("Adding return parameter of type {}", resolvedTypeSignature(modelType).or("<null>"));
        context.operationModelsBuilder().addReturn(modelType);
    }

    private void collectParameters(RequestMappingContext context) {
        LOG.debug("Reading parameters models for handlerMethod |{}|", context.getName());
        List<ResolvedMethodParameter> parameterTypes = context.getParameters();
        for (ResolvedMethodParameter parameterType : parameterTypes) {
            if (parameterType.hasParameterAnnotation(EncryptBody.class)) {
                ResolvedType modelType = context.alternateFor(parameterType.getParameterType());
                LOG.debug("Adding input parameter of type {}", resolvedTypeSignature(modelType).or("<null>"));
                context.operationModelsBuilder().addInputParam(modelType);
            }
        }
        LOG.debug("Finished reading parameters models for handlerMethod |{}|", context.getName());
    }


    @Override
    public void apply(ParameterContext context) {
        context.parameterBuilder().parameterType(findParameterType(context));
    }


    public static String findParameterType(ParameterContext parameterContext) {
        ResolvedMethodParameter resolvedMethodParameter = parameterContext.resolvedMethodParameter();
        ResolvedType parameterType = resolvedMethodParameter.getParameterType();
        parameterType = parameterContext.alternateFor(parameterType);

        //Multi-part file trumps any other annotations
        if (MultipartFile.class.isAssignableFrom(parameterType.getErasedType())) {
            return "form";
        }
        if (resolvedMethodParameter.hasParameterAnnotation(PathVariable.class)) {
            return "path";
        } else if (resolvedMethodParameter.hasParameterAnnotation(ModelAttribute.class)) {
            return queryOrForm(parameterContext.getOperationContext());
        } else if (resolvedMethodParameter.hasParameterAnnotation(EncryptBody.class)) {
            return "body";
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestParam.class)) {
            return queryOrForm(parameterContext.getOperationContext());
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestHeader.class)) {
            return "header";
        } else if (resolvedMethodParameter.hasParameterAnnotation(RequestPart.class)) {
            return "form";
        }
        if (!resolvedMethodParameter.hasParameterAnnotations()) {
            return queryOrForm(parameterContext.getOperationContext());
        }
        return "body";
    }

    private static String queryOrForm(OperationContext context) {
        if (context.consumes().contains(MediaType.APPLICATION_FORM_URLENCODED) && context.httpMethod() == HttpMethod
                .POST) {
            return "form";
        }
        return "query";
    }
}
