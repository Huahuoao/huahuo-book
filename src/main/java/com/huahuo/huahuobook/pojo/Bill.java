package com.huahuo.huahuobook.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName bill
 */
@TableName(value ="bill")
@Data
public class Bill implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "num")
    private Double num;

    /**
     * 
     */
    @TableField(value = "type_one")
    private Integer typeOne;

    /**
     * 
     */
    @TableField(value = "type_two")
    private Integer typeTwo;

    /**
     * 
     */
    @TableField(value = "text")
    private String text;

    /**
     * 
     */
    @TableField(value = "book_id")
    private Integer bookId;

    /**
     * 
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 
     */
    @TableField(value = "img")
    private String img;

    /**
     * 
     */
    @TableField(value = "is_collect")
    private Integer isCollect;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Bill other = (Bill) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getTypeOne() == null ? other.getTypeOne() == null : this.getTypeOne().equals(other.getTypeOne()))
            && (this.getTypeTwo() == null ? other.getTypeTwo() == null : this.getTypeTwo().equals(other.getTypeTwo()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getIsCollect() == null ? other.getIsCollect() == null : this.getIsCollect().equals(other.getIsCollect()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getTypeOne() == null) ? 0 : getTypeOne().hashCode());
        result = prime * result + ((getTypeTwo() == null) ? 0 : getTypeTwo().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getIsCollect() == null) ? 0 : getIsCollect().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", typeOne=").append(typeOne);
        sb.append(", typeTwo=").append(typeTwo);
        sb.append(", text=").append(text);
        sb.append(", bookId=").append(bookId);
        sb.append(", createTime=").append(createTime);
        sb.append(", img=").append(img);
        sb.append(", isCollect=").append(isCollect);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}