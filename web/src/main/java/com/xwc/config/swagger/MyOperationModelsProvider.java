package com.xwc.config.swagger;

import com.fasterxml.classmate.ResolvedType;
import com.xwc.config.mvc.EncryptBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import springfox.documentation.schema.plugins.SchemaPluginsManager;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ViewProviderPlugin;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import static springfox.documentation.schema.ResolvedTypes.resolvedTypeSignature;

@Component
@Order
public class MyOperationModelsProvider implements OperationModelsProviderPlugin {
    private static final Logger LOG = LoggerFactory.getLogger(MyOperationModelsProvider.class);
    private final SchemaPluginsManager pluginsManager;

    @Autowired
    public MyOperationModelsProvider(SchemaPluginsManager pluginsManager) {
        this.pluginsManager = pluginsManager;
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
        LOG.debug(
                "Adding return parameter of type {}",
                resolvedTypeSignature(modelType).orElse("<null>"));

        context.operationModelsBuilder()
                .addReturn(
                        modelType,
                        viewForReturn(context));
    }

    private void collectParameters(RequestMappingContext context) {
        List<ResolvedMethodParameter> parameterTypes = context.getParameters();
        for (ResolvedMethodParameter parameterType : parameterTypes) {
            if (parameterType.hasParameterAnnotation(RequestBody.class)
                    || parameterType.hasParameterAnnotation(RequestPart.class)
                    || parameterType.hasParameterAnnotation(EncryptBody.class)) {
                System.out.println(parameterType.getAnnotations());
                ResolvedType modelType = context.alternateFor(parameterType.getParameterType());
                System.out.println("modelType" + modelType);
                Optional<ResolvedType> resolvedType = viewForParameter(
                        context,
                        parameterType);
                System.out.println("resolvedType" + resolvedType.orElse(null));
                context.operationModelsBuilder().addInputParam(
                        modelType,
                        resolvedType,
                        new HashSet<>());
            }
        }
    }

    private Optional<ResolvedType> viewForReturn(RequestMappingContext context) {
        ViewProviderPlugin viewProvider =
                pluginsManager.viewProvider(context.getDocumentationContext().getDocumentationType());
        return viewProvider.viewFor(
                context);
    }

    private Optional<ResolvedType> viewForParameter(
            RequestMappingContext context,
            ResolvedMethodParameter parameter) {
        ViewProviderPlugin viewProvider =
                pluginsManager.viewProvider(context.getDocumentationContext().getDocumentationType());
        return viewProvider.viewFor(
                parameter);
    }
}
