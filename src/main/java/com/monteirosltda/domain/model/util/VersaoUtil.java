package com.monteirosltda.domain.model.util;

public class VersaoUtil {

    public static String getVersion1() {
        String version = "";
        Package pkg = VersaoUtil.class.getPackage();
        if (pkg != null) {
            // A versão pode estar no atributo Implementation-Version
            version = pkg.getImplementationVersion();
        }
        return version != null ? version : "Unknown Version";
    }
}
