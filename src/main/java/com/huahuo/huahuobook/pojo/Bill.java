package com.huahuo.huahuobook.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.huahuo.huahuobook.converter.TypeOneConverter;
import com.huahuo.huahuobook.converter.TypeTwoConverter;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

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
    @ExcelProperty("ID")
    @ColumnWidth(5)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "num")
    @ExcelProperty("金额")
    @ColumnWidth(10)
    private Double num;

    /**
     * 
     */
    @ExcelProperty("账单名称")
    @ColumnWidth(20)
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "type_one")
    @ExcelProperty(value = "消费类型",converter = TypeOneConverter.class)
    @ColumnWidth(15)
    private Integer typeOne;

    /**
     * 
     */
    @TableField(value = "type_two")
    @ExcelProperty(value = "消费对象",converter = TypeTwoConverter.class)
    @ColumnWidth(15)
    private Integer typeTwo;

    /**
     * 
     */
    @ExcelProperty("备注")
    @ColumnWidth(50)
    @TableField(value = "text")
    private String text;

    /**
     * 
     */
    @TableField(value = "book_id")
    @ExcelIgnore
    private Integer bookId;

    /**
     * 
     */
    @ExcelProperty("账单时间")
    @ColumnWidth(20)
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 
     */
    @TableField(value = "img")
    @ExcelIgnore
    private String img;

    @TableField(exist = false)
    @ExcelIgnore
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
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTypeOne() == null ? other.getTypeOne() == null : this.getTypeOne().equals(other.getTypeOne()))
            && (this.getTypeTwo() == null ? other.getTypeTwo() == null : this.getTypeTwo().equals(other.getTypeTwo()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTypeOne() == null) ? 0 : getTypeOne().hashCode());
        result = prime * result + ((getTypeTwo() == null) ? 0 : getTypeTwo().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", typeOne=").append(typeOne);
        sb.append(", typeTwo=").append(typeTwo);
        sb.append(", text=").append(text);
        sb.append(", bookId=").append(bookId);
        sb.append(", createTime=").append(createTime);
        sb.append(", img=").append(img);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}