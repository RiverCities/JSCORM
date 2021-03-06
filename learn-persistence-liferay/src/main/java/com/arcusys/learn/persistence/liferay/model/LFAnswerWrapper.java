package com.arcusys.learn.persistence.liferay.model;

import com.liferay.portal.model.ModelWrapper;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

/**
* <p>
    * This class is a wrapper for {@link LFAnswer}.
    * </p>
*
* @author    Brian Wing Shun Chan
* @see       LFAnswer
* @generated
*/
public class LFAnswerWrapper implements LFAnswer, ModelWrapper<LFAnswer> {
    private LFAnswer _lfAnswer;

    public LFAnswerWrapper(LFAnswer lfAnswer) {
        _lfAnswer = lfAnswer;
    }

    public Class<?> getModelClass() {
        return LFAnswer.class;
    }

    public String getModelClassName() {
        return LFAnswer.class.getName();
    }

    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("description", getDescription());
        attributes.put("correct", getCorrect());
        attributes.put("questionId", getQuestionId());
        attributes.put("rangeFrom", getRangeFrom());
        attributes.put("rangeTo", getRangeTo());
        attributes.put("matchingText", getMatchingText());
        attributes.put("answerPosition", getAnswerPosition());
        attributes.put("answerType", getAnswerType());

        return attributes;
    }

    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Boolean correct = (Boolean) attributes.get("correct");

        if (correct != null) {
            setCorrect(correct);
        }

        Integer questionId = (Integer) attributes.get("questionId");

        if (questionId != null) {
            setQuestionId(questionId);
        }

        BigDecimal rangeFrom = (BigDecimal) attributes.get("rangeFrom");

        if (rangeFrom != null) {
            setRangeFrom(rangeFrom);
        }

        BigDecimal rangeTo = (BigDecimal) attributes.get("rangeTo");

        if (rangeTo != null) {
            setRangeTo(rangeTo);
        }

        String matchingText = (String) attributes.get("matchingText");

        if (matchingText != null) {
            setMatchingText(matchingText);
        }

        Integer answerPosition = (Integer) attributes.get("answerPosition");

        if (answerPosition != null) {
            setAnswerPosition(answerPosition);
        }

        Integer answerType = (Integer) attributes.get("answerType");

        if (answerType != null) {
            setAnswerType(answerType);
        }
    }

    /**
     * Returns the primary key of this l f answer.
     *
     * @return the primary key of this l f answer
     */
    public long getPrimaryKey() {
        return _lfAnswer.getPrimaryKey();
    }

    /**
     * Sets the primary key of this l f answer.
     *
     * @param primaryKey the primary key of this l f answer
     */
    public void setPrimaryKey(long primaryKey) {
        _lfAnswer.setPrimaryKey(primaryKey);
    }

    /**
     * Returns the ID of this l f answer.
     *
     * @return the ID of this l f answer
     */
    public long getId() {
        return _lfAnswer.getId();
    }

    /**
     * Sets the ID of this l f answer.
     *
     * @param id the ID of this l f answer
     */
    public void setId(long id) {
        _lfAnswer.setId(id);
    }

    /**
     * Returns the description of this l f answer.
     *
     * @return the description of this l f answer
     */
    public java.lang.String getDescription() {
        return _lfAnswer.getDescription();
    }

    /**
     * Sets the description of this l f answer.
     *
     * @param description the description of this l f answer
     */
    public void setDescription(java.lang.String description) {
        _lfAnswer.setDescription(description);
    }

    /**
     * Returns the correct of this l f answer.
     *
     * @return the correct of this l f answer
     */
    public boolean getCorrect() {
        return _lfAnswer.getCorrect();
    }

    /**
     * Returns <code>true</code> if this l f answer is correct.
     *
     * @return <code>true</code> if this l f answer is correct; <code>false</code> otherwise
     */
    public boolean isCorrect() {
        return _lfAnswer.isCorrect();
    }

    /**
     * Sets whether this l f answer is correct.
     *
     * @param correct the correct of this l f answer
     */
    public void setCorrect(boolean correct) {
        _lfAnswer.setCorrect(correct);
    }

    /**
     * Returns the question ID of this l f answer.
     *
     * @return the question ID of this l f answer
     */
    public java.lang.Integer getQuestionId() {
        return _lfAnswer.getQuestionId();
    }

    /**
     * Sets the question ID of this l f answer.
     *
     * @param questionId the question ID of this l f answer
     */
    public void setQuestionId(java.lang.Integer questionId) {
        _lfAnswer.setQuestionId(questionId);
    }

    /**
     * Returns the range from of this l f answer.
     *
     * @return the range from of this l f answer
     */
    public java.math.BigDecimal getRangeFrom() {
        return _lfAnswer.getRangeFrom();
    }

    /**
     * Sets the range from of this l f answer.
     *
     * @param rangeFrom the range from of this l f answer
     */
    public void setRangeFrom(java.math.BigDecimal rangeFrom) {
        _lfAnswer.setRangeFrom(rangeFrom);
    }

    /**
     * Returns the range to of this l f answer.
     *
     * @return the range to of this l f answer
     */
    public java.math.BigDecimal getRangeTo() {
        return _lfAnswer.getRangeTo();
    }

    /**
     * Sets the range to of this l f answer.
     *
     * @param rangeTo the range to of this l f answer
     */
    public void setRangeTo(java.math.BigDecimal rangeTo) {
        _lfAnswer.setRangeTo(rangeTo);
    }

    /**
     * Returns the matching text of this l f answer.
     *
     * @return the matching text of this l f answer
     */
    public java.lang.String getMatchingText() {
        return _lfAnswer.getMatchingText();
    }

    /**
     * Sets the matching text of this l f answer.
     *
     * @param matchingText the matching text of this l f answer
     */
    public void setMatchingText(java.lang.String matchingText) {
        _lfAnswer.setMatchingText(matchingText);
    }

    /**
     * Returns the answer position of this l f answer.
     *
     * @return the answer position of this l f answer
     */
    public java.lang.Integer getAnswerPosition() {
        return _lfAnswer.getAnswerPosition();
    }

    /**
     * Sets the answer position of this l f answer.
     *
     * @param answerPosition the answer position of this l f answer
     */
    public void setAnswerPosition(java.lang.Integer answerPosition) {
        _lfAnswer.setAnswerPosition(answerPosition);
    }

    /**
     * Returns the answer type of this l f answer.
     *
     * @return the answer type of this l f answer
     */
    public java.lang.Integer getAnswerType() {
        return _lfAnswer.getAnswerType();
    }

    /**
     * Sets the answer type of this l f answer.
     *
     * @param answerType the answer type of this l f answer
     */
    public void setAnswerType(java.lang.Integer answerType) {
        _lfAnswer.setAnswerType(answerType);
    }

    public boolean isNew() {
        return _lfAnswer.isNew();
    }

    public void setNew(boolean n) {
        _lfAnswer.setNew(n);
    }

    public boolean isCachedModel() {
        return _lfAnswer.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _lfAnswer.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _lfAnswer.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _lfAnswer.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _lfAnswer.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _lfAnswer.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _lfAnswer.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new LFAnswerWrapper((LFAnswer) _lfAnswer.clone());
    }

    public int compareTo(LFAnswer lfAnswer) {
        return _lfAnswer.compareTo(lfAnswer);
    }

    @Override
    public int hashCode() {
        return _lfAnswer.hashCode();
    }

    public com.liferay.portal.model.CacheModel<LFAnswer> toCacheModel() {
        return _lfAnswer.toCacheModel();
    }

    public LFAnswer toEscapedModel() {
        return new LFAnswerWrapper(_lfAnswer.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _lfAnswer.toString();
    }

    public java.lang.String toXmlString() {
        return _lfAnswer.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _lfAnswer.persist();
    }

    /**
    * @deprecated Renamed to {@link #getWrappedModel}
    */
    public LFAnswer getWrappedLFAnswer() {
        return _lfAnswer;
    }

    public LFAnswer getWrappedModel() {
        return _lfAnswer;
    }

    public void resetOriginalValues() {
        _lfAnswer.resetOriginalValues();
    }
}
