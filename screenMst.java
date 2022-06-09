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
@Table(name = "screen")
public class screenMst{
@ApiModelProperty(required = false , value = "Primary Key")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "screenid")
private Integer screenid;
public void setscreenid(Integer screenid){
this.screenid = screenid;
}
public Integer getscreenid(){
return screenid;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "screenpurpose")
private String screenpurpose;
public void setscreenpurpose(String screenpurpose){
this.screenpurpose = screenpurpose;
}
public String getscreenpurpose(){
return screenpurpose;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "screenname")
private String screenname;
public void setscreenname(String screenname){
this.screenname = screenname;
}
public String getscreenname(){
return screenname;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "screentype")
private Integer screentype;
public void setscreentype(Integer screentype){
this.screentype = screentype;
}
public Integer getscreentype(){
return screentype;
}
@ApiModelProperty(required = true , value = "(size = 500)(required)")


@Column(name = "screendate")
private Date screendate;
public void setscreendate(Date screendate){
this.screendate = screendate;
}
public Date getscreendate(){
return screendate;
}

}
