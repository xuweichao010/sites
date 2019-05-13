package com.xwc.entity.base;

import com.xwc.esbatis.anno.enums.KeyEnum;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;

import java.io.Serializable;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  11:20
 * 业务：
 * 功能：
 */
@Table("t_client")
@SuppressWarnings("unused")
public class Client implements Serializable {
    private static final long serialVersionUID = -4485038631510667928L;
    /**
     * id
     */
    @PrimaryKey(type = KeyEnum.CUSTOM)
    private String clientId ;
    /** 授权码 */
    private String secret ;
    /** 授权范围 */
    private String scope ;
    /** 授权类型 */
    private String grantType ;
    /** 客户端信息 */
    private String description ;
    /** 所属机构 */
    private String orgName ;
    /** 机构代码 */
    private String orgCode ;
    /** token有效期 */
    private Integer accessSeconds ;
    /** refresh_token的有效期 */
    private Integer refreshSeconds ;

    /** id */
    public String getClientId(){
        return this.clientId;
    }
    /** id */
    public void setClientId(String clientId){
        this.clientId = clientId;
    }
    /** 授权码 */
    public String getSecret(){
        return this.secret;
    }
    /** 授权码 */
    public void setSecret(String secret){
        this.secret = secret;
    }
    /** 授权范围 */
    public String getScope(){
        return this.scope;
    }
    /** 授权范围 */
    public void setScope(String scope){
        this.scope = scope;
    }
    /** 授权类型 */
    public String getGrantType(){
        return this.grantType;
    }
    /** 授权类型 */
    public void setGrantType(String grantType){
        this.grantType = grantType;
    }
    /** 客户端信息 */
    public String getDescription(){
        return this.description;
    }
    /** 客户端信息 */
    public void setDescription(String desc){
        this.description = desc;
    }
    /** 所属机构 */
    public String getOrgName(){
        return this.orgName;
    }
    /** 所属机构 */
    public void setOrgName(String orgName){
        this.orgName = orgName;
    }
    /** 机构代码 */
    public String getOrgCode(){
        return this.orgCode;
    }
    /** 机构代码 */
    public void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
    /** token有效期 */
    public Integer getAccessSeconds(){
        return this.accessSeconds;
    }
    /** token有效期 */
    public void setAccessSeconds(Integer accessSeconds){
        this.accessSeconds = accessSeconds;
    }
    /** refresh_token的有效期 */
    public Integer getRefreshSeconds(){
        return this.refreshSeconds;
    }
    /** refresh_token的有效期 */
    public void setRefreshSeconds(Integer refreshSeconds){
        this.refreshSeconds = refreshSeconds;
    }
}
