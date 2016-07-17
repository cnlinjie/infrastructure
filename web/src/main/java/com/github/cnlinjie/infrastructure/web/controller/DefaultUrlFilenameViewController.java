package com.github.cnlinjie.infrastructure.web.controller;

import com.github.cnlinjie.infrastructure.web.exception.MyNoHandlerFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-7-15
 */
public class DefaultUrlFilenameViewController extends UrlFilenameViewController {

    @Autowired
    private AbstractTemplateViewResolver resolver;

    @Override
    protected String getViewNameForRequest(HttpServletRequest request) {
        String uri = extractOperableUrl(request);
        String viewNameForUrlPath = getViewNameForUrlPath(uri);
        Locale locale = request.getLocale();
        View view = null;
        try {
            view = resolver.resolveViewName(viewNameForUrlPath, locale);
        } catch (Exception e) {
            logger.error("解析错误", e);
        }
        if (view == null) {
            throw new MyNoHandlerFoundException(request.getMethod(), uri);
        }
        return viewNameForUrlPath;
    }
}
