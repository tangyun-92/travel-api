package com.tang.travel.model.req.pms;

import com.tang.travel.model.req.PageReq;
import io.swagger.annotations.ApiModelProperty;

public class PmsBrandListReq extends PageReq {

    @ApiModelProperty(value = "品牌名称", name = "name", dataType = "String")
    private String name;

    @ApiModelProperty(value = "首字母", name = "firstLetter", dataType = "String")
    private String firstLetter;

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是", name = "factoryStatus", dataType = "Integer")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否显示：0->不显示；1->显示", name = "showStatus", dataType = "Integer")
    private Integer showStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", firstLetter=").append(firstLetter);
        sb.append(", factoryStatus=").append(factoryStatus);
        sb.append(", showStatus=").append(showStatus);
        sb.append("]");
        return sb.toString();
    }
}