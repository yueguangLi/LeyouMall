package com.newland.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public enum ExceptionEnums {
    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    CATEGOTY_NOT_FOUND(404, "商品分类不存在"),
    BRAND_NOT_FOUND(404, "商品品牌不存在"),
    INVALID_FILE_TYPE(404,"文件类型不支持"),
    GOOD_NOT_FOND(404,"商品不存在"),
    BRAND_BRAND_SAVE_ERROR(500,"新增品牌分类中间表失败"),
    UPLOAD_FILE_EXCEPTION(500,"文件上传失败"),
    SPEC_GROUP_NOT_FOND(404,"商品规格组不存在"),
    SPEC_PARAM_NOT_FOND(404,"商品规格参数不存在"),
    BRAND_SAVE_ERROR(500,"新增品牌失败");
    private String message;
    private Integer code;

    ExceptionEnums( Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
