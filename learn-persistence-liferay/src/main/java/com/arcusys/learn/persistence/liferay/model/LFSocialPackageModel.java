package com.arcusys.learn.persistence.liferay.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
* The base model interface for the LFSocialPackage service. Represents a row in the &quot;Learn_LFSocialPackage&quot; database table, with each column mapped to a property of this class.
*
* <p>
    * This interface and its corresponding implementation {@link com.arcusys.learn.persistence.liferay.model.impl.LFSocialPackageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.arcusys.learn.persistence.liferay.model.impl.LFSocialPackageImpl}.
    * </p>
*
* @author Brian Wing Shun Chan
* @see LFSocialPackage
* @see com.arcusys.learn.persistence.liferay.model.impl.LFSocialPackageImpl
* @see com.arcusys.learn.persistence.liferay.model.impl.LFSocialPackageModelImpl
* @generated
*/
public interface LFSocialPackageModel extends BaseModel<LFSocialPackage> {
    /*
    * NOTE FOR DEVELOPERS:
    *
    * Never modify or reference this interface directly. All methods that expect a l f social package model instance should use the {@link LFSocialPackage} interface instead.
    */

    /**
    * Returns the primary key of this l f social package.
    *
    * @return the primary key of this l f social package
    */
    public long getPrimaryKey();

    /**
    * Sets the primary key of this l f social package.
    *
    * @param primaryKey the primary key of this l f social package
    */
    public void setPrimaryKey(long primaryKey);

    /**
    * Returns the ID of this l f social package.
    *
    * @return the ID of this l f social package
    */
    public long getId();

    /**
        * Sets the ID of this l f social package.
    *
    * @param id the ID of this l f social package
    */
    public void setId(long id);

    /**
    * Returns the package i d of this l f social package.
    *
    * @return the package i d of this l f social package
    */
    public Integer getPackageID();

    /**
        * Sets the package i d of this l f social package.
    *
    * @param packageID the package i d of this l f social package
    */
    public void setPackageID(Integer packageID);

    /**
    * Returns the about package of this l f social package.
    *
    * @return the about package of this l f social package
    */
    @AutoEscape
    public String getAboutPackage();

    /**
        * Sets the about package of this l f social package.
    *
    * @param aboutPackage the about package of this l f social package
    */
    public void setAboutPackage(String aboutPackage);

    /**
    * Returns the publish date of this l f social package.
    *
    * @return the publish date of this l f social package
    */
    public Date getPublishDate();

    /**
        * Sets the publish date of this l f social package.
    *
    * @param publishDate the publish date of this l f social package
    */
    public void setPublishDate(Date publishDate);

    /**
    * Returns the author i d of this l f social package.
    *
    * @return the author i d of this l f social package
    */
    public Integer getAuthorID();

    /**
        * Sets the author i d of this l f social package.
    *
    * @param authorID the author i d of this l f social package
    */
    public void setAuthorID(Integer authorID);

    public boolean isNew();

    public void setNew(boolean n);

    public boolean isCachedModel();

    public void setCachedModel(boolean cachedModel);

    public boolean isEscapedModel();

    public Serializable getPrimaryKeyObj();

    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    public ExpandoBridge getExpandoBridge();

    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    public Object clone();

    public int compareTo(LFSocialPackage lfSocialPackage);

    public int hashCode();

    public CacheModel<LFSocialPackage> toCacheModel();

    public LFSocialPackage toEscapedModel();

    public String toString();

    public String toXmlString();
}
