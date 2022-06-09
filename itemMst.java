package com.vnit.api.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
@Entity
@Table(name = "item")
public class itemMst{
@ApiModelProperty(required = false , value = "Primary Key")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "itcode")
private Integer itcode;
public void setitcode(Integer itcode){
this.itcode = itcode;
}
public Integer getitcode(){
return itcode;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "itname")
private String itname;
public void setitname(String itname){
this.itname = itname;
}
public String getitname(){
return itname;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "itrate")
private BigDecimal itrate;
public void setitrate(BigDecimal itrate){
this.itrate = itrate;
}
public BigDecimal getitrate(){
return itrate;
}

}
