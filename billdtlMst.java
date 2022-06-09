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
@Table(name = "billdtl")
public class billdtlMst{
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "billno")
private Integer billno;
public void setbillno(Integer billno){
this.billno = billno;
}
public Integer getbillno(){
return billno;
}
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


@Column(name = "itrate")
private BigDecimal itrate;
public void setitrate(BigDecimal itrate){
this.itrate = itrate;
}
public BigDecimal getitrate(){
return itrate;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "qty")
private Integer qty;
public void setqty(Integer qty){
this.qty = qty;
}
public Integer getqty(){
return qty;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "value")
private BigDecimal value;
public void setvalue(BigDecimal value){
this.value = value;
}
public BigDecimal getvalue(){
return value;
}

}
