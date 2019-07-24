package com.bc.pmpheep.back.commuser.materialdec.po;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 *
 */
@Alias("MaterialPosition")
public class MaterialPosition implements Serializable {
    private Long id;
    private Long materialId;
    private String positionCode ;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
}
