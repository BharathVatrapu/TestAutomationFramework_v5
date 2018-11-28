package com.testProject.common;

import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.utils.Generic;

public class ProjectGeneric {

    private Generic generic = null;
    public ConfigTestData configTestData=null;

    public ProjectGeneric(ConfigTestData configTestData) {
        this.configTestData = configTestData;
        generic = new Generic(configTestData);

    }


}
