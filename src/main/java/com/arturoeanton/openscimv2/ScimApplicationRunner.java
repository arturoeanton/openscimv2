package com.arturoeanton.openscimv2;

import com.arturoeanton.openscimv2.commons.Config;
import com.arturoeanton.openscimv2.commons.Loader;
import com.arturoeanton.openscimv2.components.Schemas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ScimApplicationRunner implements ApplicationRunner {

    @Value("${scim.folder.config}")
    String folderConfig;

    @Value("${scim.format.date}")
    String formatDate;

    @Autowired
    Schemas schemas;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Config.FORMAT_DATE = formatDate;
        Config.PATH_CONFIG = folderConfig;
        Loader.loadAttributes(folderConfig, schemas);

    }
}
