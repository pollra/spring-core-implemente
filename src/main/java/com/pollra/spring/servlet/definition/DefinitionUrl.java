package com.pollra.spring.servlet.definition;

/**
 * @since       2022.09.03
 * @author      pollra
 * @description definition url
 **********************************************************************************************************************/
public class DefinitionUrl {

    private final String path;
    private final boolean hasPathVariable;

    public DefinitionUrl(String url) {
        this.path = url;
        this.hasPathVariable = urlHasPathVariable(this.path);
    }

    private boolean urlHasPathVariable(String path) {
        if(path.contains("{")) {
            if(path.contains("}")) {
                return true;
            }
            throw new DefinitionException("올바르지 않은 URL 입니다.");
        }
        return false;
    }

    public boolean isMatched(DefinitionUrl requestUrl) {
        if(hasPathVariable) {
            String[] persistPaths = this.path.split("/");
            String[] requestPaths = requestUrl.path.split("/");

            if(persistPaths.length != requestPaths.length) {
                return false;
            }

            for (int i = 0; i < persistPaths.length; i++) {
                boolean isEquals = isMatched(persistPaths[i], requestPaths[i]);
                if(! isEquals) {
                    return false;
                }
            }
            return true;
        }
        return isMatched(this.path, requestUrl.path);
    }

    private boolean isMatched(String persist, String request) {
        if(persist.contains("{")) {
            return true;
        }
        return persist.equals(request);
    }
}
