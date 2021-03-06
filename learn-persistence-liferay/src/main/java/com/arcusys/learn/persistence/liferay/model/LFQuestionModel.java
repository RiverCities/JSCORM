package com.arcusys.learn.persistence.liferay.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
* The base model interface for the LFQuestion service. Represents a row in the &quot;Learn_LFQuestion&quot; database table, with each column mapped to a property of this class.
*
* <p>
    * This interface and its corresponding implementation {@link com.arcusys.learn.persistence.liferay.model.impl.LFQuestionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.arcusys.learn.persistence.liferay.model.impl.LFQuestionImpl}.
    * </p>
*
* @author Brian Wing Shun Chan
* @see LFQuestion
* @see com.arcusys.learn.persistence.liferay.model.impl.LFQuestionImpl
* @see com.arcusys.learn.persistence.liferay.model.impl.LFQuestionModelImpl
* @generated
*/
public interface LFQuestionModel extends BaseModel<LFQuestion> {
    /*
    * NOTE FOR DEVELOPERS:
    *
    * Never modify or reference this interface directly. All methods that expect a l f question model instance should use the {@link LFQuestion} interface instead.
    */

    /**
    * Returns the primary key of this l f question.
    *
    * @return the primary key of this l f question
    */
    public long getPrimaryKey();

    /**
    * Sets the primary key of this l f question.
    *
    * @param primaryKey the primary key of this l f question
    */
    public void setPrimaryKey(long primaryKey);

    /**
    * Returns the ID of this l f question.
    *
    * @return the ID of this l f question
    */
    public long getId();

    /**
        * Sets the ID of this l f question.
    *
    * @param id the ID of this l f question
    */
    public void setId(long id);

    /**
    * Returns the category ID of this l f question.
    *
    * @return the category ID of this l f question
    */
    public Integer getCategoryId();

    /**
        * Sets the category ID of this l f question.
    *
    * @param categoryId the category ID of this l f question
    */
    public void setCategoryId(Integer categoryId);

    /**
    * Returns the title of this l f question.
    *
    * @return the title of this l f question
    */
    @AutoEscape
    public String getTitle();

    /**
        * Sets the title of this l f question.
    *
    * @param title the title of this l f question
    */
    public void setTitle(String title);

    /**
    * Returns the description of this l f question.
    *
    * @return the description of this l f question
    */
    @AutoEscape
    public String getDescription();

    /**
        * Sets the description of this l f question.
    *
    * @param description the description of this l f question
    */
    public void setDescription(String description);

    /**
    * Returns the explanation text of this l f question.
    *
    * @return the explanation text of this l f question
    */
    @AutoEscape
    public String getExplanationText();

    /**
        * Sets the explanation text of this l f question.
    *
    * @param explanationText the explanation text of this l f question
    */
    public void setExplanationText(String explanationText);

    /**
    * Returns the force correct count of this l f question.
    *
    * @return the force correct count of this l f question
    */
    public boolean getForceCorrectCount();

    /**
    * Returns <code>true</code> if this l f question is force correct count.
    *
    * @return <code>true</code> if this l f question is force correct count; <code>false</code> otherwise
    */
    public boolean isForceCorrectCount();

    /**
        * Sets whether this l f question is force correct count.
    *
    * @param forceCorrectCount the force correct count of this l f question
    */
    public void setForceCorrectCount(boolean forceCorrectCount);

    /**
    * Returns the case sensitive of this l f question.
    *
    * @return the case sensitive of this l f question
    */
    public boolean getCaseSensitive();

    /**
    * Returns <code>true</code> if this l f question is case sensitive.
    *
    * @return <code>true</code> if this l f question is case sensitive; <code>false</code> otherwise
    */
    public boolean isCaseSensitive();

    /**
        * Sets whether this l f question is case sensitive.
    *
    * @param caseSensitive the case sensitive of this l f question
    */
    public void setCaseSensitive(boolean caseSensitive);

    /**
    * Returns the question type of this l f question.
    *
    * @return the question type of this l f question
    */
    public Integer getQuestionType();

    /**
        * Sets the question type of this l f question.
    *
    * @param questionType the question type of this l f question
    */
    public void setQuestionType(Integer questionType);

    /**
    * Returns the course ID of this l f question.
    *
    * @return the course ID of this l f question
    */
    public Integer getCourseId();

    /**
        * Sets the course ID of this l f question.
    *
    * @param courseId the course ID of this l f question
    */
    public void setCourseId(Integer courseId);

    /**
    * Returns the arrangement index of this l f question.
    *
    * @return the arrangement index of this l f question
    */
    public Integer getArrangementIndex();

    /**
        * Sets the arrangement index of this l f question.
    *
    * @param arrangementIndex the arrangement index of this l f question
    */
    public void setArrangementIndex(Integer arrangementIndex);

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

    public int compareTo(LFQuestion lfQuestion);

    public int hashCode();

    public CacheModel<LFQuestion> toCacheModel();

    public LFQuestion toEscapedModel();

    public String toString();

    public String toXmlString();
}
