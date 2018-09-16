package com.edu.commons.vo;

import com.edu.pojo.Resource;

import java.util.List;

public class MenuVo {
    private Resource parent;
    private List<Resource> children;

    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
