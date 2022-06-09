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
@Table(name = "customer")
public class customerMst{
@ApiModelProperty(required = false , value = "Primary Key")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "ccode")
private Integer ccode;
public void setccode(Integer ccode){
this.ccode = ccode;
}
public Integer getccode(){
return ccode;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "cname")
private String cname;
public void setcname(String cname){
this.cname = cname;
}
public String getcname(){
return cname;
}

}
