package com.arturoeanton.openscimv2.commons;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Transformer {

    static String externalRegex = "^(urn[:\\w\\_-]*\\:[A-Z][\\w]*)(\\..*)?$";
    static String internalRegex = "^(urn[:\\w\\.\\_-]*\\:[A-Z][\\w]*)(:.*)?$";

    static Map<String,String> cacheInternal = new HashMap<String,String>();
    static Map<String,String>  cacheExternal = new HashMap<String,String>();


    static public String internal(String external) {
        var result = cacheInternal.get(external);
        if (result!= null){
            return result;
        }
        if (external.startsWith(Constants.BEGIN_URN)) {
            var urnPart = external.replaceAll(internalRegex, "$1")
                    .replaceAll("\\.", Constants.DOT);

            var pathPart = external.replaceAll(internalRegex, "$2")
                    .replaceAll(":", ".")
                    .replaceAll("\\$", Constants.DOLLAR);
            result = urnPart + pathPart;
            cacheInternal.put(external,result);
            return result;
        }
        return external.replaceAll("\\$", Constants.DOLLAR);
    }

    static public String external(String internal) {
        var result = cacheExternal.get(internal);
        if (result!= null){
            return result;
        }
        if (internal.startsWith(Constants.BEGIN_URN)) {
            var urnPart = internal.replaceAll(externalRegex, "$1")
                    .replaceAll(Constants.DOT, "\\.");

            var pathPart = internal.replaceAll(externalRegex, "$2")
                    .replaceAll(Constants.DOLLAR, "\\$")
                    .replaceFirst("\\.", ":");
            result = urnPart + pathPart;
            cacheExternal.put(internal,result);
            return result;
        }
        return internal.replaceAll(Constants.DOLLAR, "\\$");
    }



}
