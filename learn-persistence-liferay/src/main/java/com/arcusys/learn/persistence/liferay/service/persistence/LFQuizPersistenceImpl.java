package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.NoSuchLFQuizException;
import com.arcusys.learn.persistence.liferay.model.LFQuiz;
import com.arcusys.learn.persistence.liferay.model.impl.LFQuizImpl;
import com.arcusys.learn.persistence.liferay.model.impl.LFQuizModelImpl;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityDataMapPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityStateNodePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityStatePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityStateTreePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFAnswerPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFAttemptDataPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFAttemptPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFBigDecimalPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFCertificatePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFCertificateSitePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFCertificateUserPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFChildrenSelectionPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFConditionRulePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFConfigPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFCoursePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFFileStoragePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFGlobalObjectiveStatePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFObjectiveMapPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFObjectivePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFObjectiveStatePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFPackageCommentPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFPackagePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFPackageScopeRulePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFPackageVotePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFPlayerScopeRulePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFQuestionCategoryPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFQuestionPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFQuizPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFQuizQuestionCategoryPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFQuizQuestionPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFResourcePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFRolePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFRollupContributionPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFRollupRulePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFRuleConditionPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFSequencingPermissionsPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFSequencingPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFSequencingTrackingPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFSocialPackagePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFSocialPackageTagPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFTincanActivityPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFTincanLrsEndpointPersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFTincanPackagePersistence;
import com.arcusys.learn.persistence.liferay.service.persistence.LFUserPersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the l f quiz service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFQuizPersistence
 * @see LFQuizUtil
 * @generated
 */
public class LFQuizPersistenceImpl extends BasePersistenceImpl<LFQuiz>
    implements LFQuizPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link LFQuizUtil} to access the l f quiz persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = LFQuizImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID = new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, LFQuizImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
            new String[] {
                Integer.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID =
        new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, LFQuizImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
            new String[] { Integer.class.getName() },
            LFQuizModelImpl.COURSEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_COURSEID = new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
            new String[] { Integer.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_COURSEID =
        new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByCourseId",
            new String[] { Integer.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, LFQuizImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, LFQuizImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_LFQUIZ = "SELECT lfQuiz FROM LFQuiz lfQuiz";
    private static final String _SQL_SELECT_LFQUIZ_WHERE = "SELECT lfQuiz FROM LFQuiz lfQuiz WHERE ";
    private static final String _SQL_COUNT_LFQUIZ = "SELECT COUNT(lfQuiz) FROM LFQuiz lfQuiz";
    private static final String _SQL_COUNT_LFQUIZ_WHERE = "SELECT COUNT(lfQuiz) FROM LFQuiz lfQuiz WHERE ";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_NULL = "lfQuiz.courseID IS NULL";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_NULL_2 = "lfQuiz.courseID IS NULL ";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 = "lfQuiz.courseID = ?";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_5_NULL = "(" +
        _removeConjunction(_FINDER_COLUMN_COURSEID_COURSEID_NULL_2) + ")";
    private static final String _FINDER_COLUMN_COURSEID_COURSEID_5 = "(" +
        _removeConjunction(_FINDER_COLUMN_COURSEID_COURSEID_2) + ")";
    private static final String _ORDER_BY_ENTITY_ALIAS = "lfQuiz.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LFQuiz exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LFQuiz exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(LFQuizPersistenceImpl.class);
    private static LFQuiz _nullLFQuiz = new LFQuizImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<LFQuiz> toCacheModel() {
                return _nullLFQuizCacheModel;
            }
        };

    private static CacheModel<LFQuiz> _nullLFQuizCacheModel = new CacheModel<LFQuiz>() {
            public LFQuiz toEntityModel() {
                return _nullLFQuiz;
            }
        };

    @BeanReference(type = LFActivityPersistence.class)
    protected LFActivityPersistence lfActivityPersistence;
    @BeanReference(type = LFActivityDataMapPersistence.class)
    protected LFActivityDataMapPersistence lfActivityDataMapPersistence;
    @BeanReference(type = LFActivityStatePersistence.class)
    protected LFActivityStatePersistence lfActivityStatePersistence;
    @BeanReference(type = LFActivityStateNodePersistence.class)
    protected LFActivityStateNodePersistence lfActivityStateNodePersistence;
    @BeanReference(type = LFActivityStateTreePersistence.class)
    protected LFActivityStateTreePersistence lfActivityStateTreePersistence;
    @BeanReference(type = LFAnswerPersistence.class)
    protected LFAnswerPersistence lfAnswerPersistence;
    @BeanReference(type = LFAttemptPersistence.class)
    protected LFAttemptPersistence lfAttemptPersistence;
    @BeanReference(type = LFAttemptDataPersistence.class)
    protected LFAttemptDataPersistence lfAttemptDataPersistence;
    @BeanReference(type = LFBigDecimalPersistence.class)
    protected LFBigDecimalPersistence lfBigDecimalPersistence;
    @BeanReference(type = LFCertificatePersistence.class)
    protected LFCertificatePersistence lfCertificatePersistence;
    @BeanReference(type = LFCertificateSitePersistence.class)
    protected LFCertificateSitePersistence lfCertificateSitePersistence;
    @BeanReference(type = LFCertificateUserPersistence.class)
    protected LFCertificateUserPersistence lfCertificateUserPersistence;
    @BeanReference(type = LFChildrenSelectionPersistence.class)
    protected LFChildrenSelectionPersistence lfChildrenSelectionPersistence;
    @BeanReference(type = LFConditionRulePersistence.class)
    protected LFConditionRulePersistence lfConditionRulePersistence;
    @BeanReference(type = LFConfigPersistence.class)
    protected LFConfigPersistence lfConfigPersistence;
    @BeanReference(type = LFCoursePersistence.class)
    protected LFCoursePersistence lfCoursePersistence;
    @BeanReference(type = LFFileStoragePersistence.class)
    protected LFFileStoragePersistence lfFileStoragePersistence;
    @BeanReference(type = LFGlobalObjectiveStatePersistence.class)
    protected LFGlobalObjectiveStatePersistence lfGlobalObjectiveStatePersistence;
    @BeanReference(type = LFObjectivePersistence.class)
    protected LFObjectivePersistence lfObjectivePersistence;
    @BeanReference(type = LFObjectiveMapPersistence.class)
    protected LFObjectiveMapPersistence lfObjectiveMapPersistence;
    @BeanReference(type = LFObjectiveStatePersistence.class)
    protected LFObjectiveStatePersistence lfObjectiveStatePersistence;
    @BeanReference(type = LFPackagePersistence.class)
    protected LFPackagePersistence lfPackagePersistence;
    @BeanReference(type = LFPackageCommentPersistence.class)
    protected LFPackageCommentPersistence lfPackageCommentPersistence;
    @BeanReference(type = LFPackageScopeRulePersistence.class)
    protected LFPackageScopeRulePersistence lfPackageScopeRulePersistence;
    @BeanReference(type = LFPackageVotePersistence.class)
    protected LFPackageVotePersistence lfPackageVotePersistence;
    @BeanReference(type = LFPlayerScopeRulePersistence.class)
    protected LFPlayerScopeRulePersistence lfPlayerScopeRulePersistence;
    @BeanReference(type = LFQuestionPersistence.class)
    protected LFQuestionPersistence lfQuestionPersistence;
    @BeanReference(type = LFQuestionCategoryPersistence.class)
    protected LFQuestionCategoryPersistence lfQuestionCategoryPersistence;
    @BeanReference(type = LFQuizPersistence.class)
    protected LFQuizPersistence lfQuizPersistence;
    @BeanReference(type = LFQuizQuestionPersistence.class)
    protected LFQuizQuestionPersistence lfQuizQuestionPersistence;
    @BeanReference(type = LFQuizQuestionCategoryPersistence.class)
    protected LFQuizQuestionCategoryPersistence lfQuizQuestionCategoryPersistence;
    @BeanReference(type = LFResourcePersistence.class)
    protected LFResourcePersistence lfResourcePersistence;
    @BeanReference(type = LFRolePersistence.class)
    protected LFRolePersistence lfRolePersistence;
    @BeanReference(type = LFRollupContributionPersistence.class)
    protected LFRollupContributionPersistence lfRollupContributionPersistence;
    @BeanReference(type = LFRollupRulePersistence.class)
    protected LFRollupRulePersistence lfRollupRulePersistence;
    @BeanReference(type = LFRuleConditionPersistence.class)
    protected LFRuleConditionPersistence lfRuleConditionPersistence;
    @BeanReference(type = LFSequencingPersistence.class)
    protected LFSequencingPersistence lfSequencingPersistence;
    @BeanReference(type = LFSequencingPermissionsPersistence.class)
    protected LFSequencingPermissionsPersistence lfSequencingPermissionsPersistence;
    @BeanReference(type = LFSequencingTrackingPersistence.class)
    protected LFSequencingTrackingPersistence lfSequencingTrackingPersistence;
    @BeanReference(type = LFSocialPackagePersistence.class)
    protected LFSocialPackagePersistence lfSocialPackagePersistence;
    @BeanReference(type = LFSocialPackageTagPersistence.class)
    protected LFSocialPackageTagPersistence lfSocialPackageTagPersistence;
    @BeanReference(type = LFTincanActivityPersistence.class)
    protected LFTincanActivityPersistence lfTincanActivityPersistence;
    @BeanReference(type = LFTincanLrsEndpointPersistence.class)
    protected LFTincanLrsEndpointPersistence lfTincanLrsEndpointPersistence;
    @BeanReference(type = LFTincanPackagePersistence.class)
    protected LFTincanPackagePersistence lfTincanPackagePersistence;
    @BeanReference(type = LFUserPersistence.class)
    protected LFUserPersistence lfUserPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the l f quiz in the entity cache if it is enabled.
     *
     * @param lfQuiz the l f quiz
     */
    public void cacheResult(LFQuiz lfQuiz) {
        EntityCacheUtil.putResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizImpl.class, lfQuiz.getPrimaryKey(), lfQuiz);

        lfQuiz.resetOriginalValues();
    }

    /**
     * Caches the l f quizs in the entity cache if it is enabled.
     *
     * @param lfQuizs the l f quizs
     */
    public void cacheResult(List<LFQuiz> lfQuizs) {
        for (LFQuiz lfQuiz : lfQuizs) {
            if (EntityCacheUtil.getResult(
                        LFQuizModelImpl.ENTITY_CACHE_ENABLED, LFQuizImpl.class,
                        lfQuiz.getPrimaryKey()) == null) {
                cacheResult(lfQuiz);
            } else {
                lfQuiz.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all l f quizs.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(LFQuizImpl.class.getName());
        }

        EntityCacheUtil.clearCache(LFQuizImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the l f quiz.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(LFQuiz lfQuiz) {
        EntityCacheUtil.removeResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizImpl.class, lfQuiz.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<LFQuiz> lfQuizs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (LFQuiz lfQuiz : lfQuizs) {
            EntityCacheUtil.removeResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
                LFQuizImpl.class, lfQuiz.getPrimaryKey());
        }
    }

    /**
     * Creates a new l f quiz with the primary key. Does not add the l f quiz to the database.
     *
     * @param id the primary key for the new l f quiz
     * @return the new l f quiz
     */
    public LFQuiz create(long id) {
        LFQuiz lfQuiz = new LFQuizImpl();

        lfQuiz.setNew(true);
        lfQuiz.setPrimaryKey(id);

        return lfQuiz;
    }

    /**
     * Removes the l f quiz with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the l f quiz
     * @return the l f quiz that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz remove(long id) throws NoSuchLFQuizException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the l f quiz with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the l f quiz
     * @return the l f quiz that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFQuiz remove(Serializable primaryKey)
        throws NoSuchLFQuizException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LFQuiz lfQuiz = (LFQuiz) session.get(LFQuizImpl.class, primaryKey);

            if (lfQuiz == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchLFQuizException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(lfQuiz);
        } catch (NoSuchLFQuizException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected LFQuiz removeImpl(LFQuiz lfQuiz) throws SystemException {
        lfQuiz = toUnwrappedModel(lfQuiz);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, lfQuiz);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(lfQuiz);

        return lfQuiz;
    }

    @Override
    public LFQuiz updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFQuiz lfQuiz, boolean merge)
        throws SystemException {
        lfQuiz = toUnwrappedModel(lfQuiz);

        boolean isNew = lfQuiz.isNew();

        LFQuizModelImpl lfQuizModelImpl = (LFQuizModelImpl) lfQuiz;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, lfQuiz, merge);

            lfQuiz.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !LFQuizModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((lfQuizModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        /* Integer.valueOf(   */
                        lfQuizModelImpl.getOriginalCourseID()
                    /* ) */
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);

                args = new Object[] { /* Integer.valueOf( */
                        lfQuizModelImpl.getCourseID()/* ) */
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COURSEID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
            LFQuizImpl.class, lfQuiz.getPrimaryKey(), lfQuiz);

        return lfQuiz;
    }

    protected LFQuiz toUnwrappedModel(LFQuiz lfQuiz) {
        if (lfQuiz instanceof LFQuizImpl) {
            return lfQuiz;
        }

        LFQuizImpl lfQuizImpl = new LFQuizImpl();

        lfQuizImpl.setNew(lfQuiz.isNew());
        lfQuizImpl.setPrimaryKey(lfQuiz.getPrimaryKey());

        lfQuizImpl.setId(lfQuiz.getId());
        lfQuizImpl.setTitle(lfQuiz.getTitle());
        lfQuizImpl.setDescription(lfQuiz.getDescription());
        lfQuizImpl.setWelcomePageContent(lfQuiz.getWelcomePageContent());
        lfQuizImpl.setFinalPageContent(lfQuiz.getFinalPageContent());
        lfQuizImpl.setCourseID(lfQuiz.getCourseID());

        return lfQuizImpl;
    }

    /**
     * Returns the l f quiz with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the l f quiz
     * @return the l f quiz
     * @throws com.liferay.portal.NoSuchModelException if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFQuiz findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the l f quiz with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFQuizException} if it could not be found.
     *
     * @param id the primary key of the l f quiz
     * @return the l f quiz
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz findByPrimaryKey(long id)
        throws NoSuchLFQuizException, SystemException {
        LFQuiz lfQuiz = fetchByPrimaryKey(id);

        if (lfQuiz == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchLFQuizException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return lfQuiz;
    }

    /**
     * Returns the l f quiz with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the l f quiz
     * @return the l f quiz, or <code>null</code> if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFQuiz fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the l f quiz with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the l f quiz
     * @return the l f quiz, or <code>null</code> if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz fetchByPrimaryKey(long id) throws SystemException {
        LFQuiz lfQuiz = (LFQuiz) EntityCacheUtil.getResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
                LFQuizImpl.class, id);

        if (lfQuiz == _nullLFQuiz) {
            return null;
        }

        if (lfQuiz == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                lfQuiz = (LFQuiz) session.get(LFQuizImpl.class, Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (lfQuiz != null) {
                    cacheResult(lfQuiz);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(LFQuizModelImpl.ENTITY_CACHE_ENABLED,
                        LFQuizImpl.class, id, _nullLFQuiz);
                }

                closeSession(session);
            }
        }

        return lfQuiz;
    }

    /**
     * Returns all the l f quizs where courseID = &#63;.
     *
     * @param courseID the course i d
     * @return the matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer courseID)
        throws SystemException {
        return findByCourseId(courseID, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the l f quizs where courseID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseID the course i d
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @return the range of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer courseID, int start, int end)
        throws SystemException {
        return findByCourseId(courseID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f quizs where courseID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseID the course i d
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer courseID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COURSEID;
            finderArgs = new Object[] { courseID };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID;
            finderArgs = new Object[] { courseID, start, end, orderByComparator };
        }

        List<LFQuiz> list = (List<LFQuiz>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFQuiz lfQuiz : list) {
                if (!Validator.equals(courseID, lfQuiz.getCourseID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_LFQUIZ_WHERE);

            if (courseID == null) {
                query.append(_FINDER_COLUMN_COURSEID_COURSEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseID != null) {
                    qPos.add(courseID.intValue());
                }

                list = (List<LFQuiz>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first l f quiz in the ordered set where courseID = &#63;.
     *
     * @param courseID the course i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f quiz
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a matching l f quiz could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz findByCourseId_First(Integer courseID,
        OrderByComparator orderByComparator)
        throws NoSuchLFQuizException, SystemException {
        LFQuiz lfQuiz = fetchByCourseId_First(courseID, orderByComparator);

        if (lfQuiz != null) {
            return lfQuiz;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("courseID=");
        msg.append(courseID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFQuizException(msg.toString());
    }

    /**
     * Returns the first l f quiz in the ordered set where courseID = &#63;.
     *
     * @param courseID the course i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f quiz, or <code>null</code> if a matching l f quiz could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz fetchByCourseId_First(Integer courseID,
        OrderByComparator orderByComparator) throws SystemException {
        List<LFQuiz> list = findByCourseId(courseID, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f quiz in the ordered set where courseID = &#63;.
     *
     * @param courseID the course i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f quiz
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a matching l f quiz could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz findByCourseId_Last(Integer courseID,
        OrderByComparator orderByComparator)
        throws NoSuchLFQuizException, SystemException {
        LFQuiz lfQuiz = fetchByCourseId_Last(courseID, orderByComparator);

        if (lfQuiz != null) {
            return lfQuiz;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("courseID=");
        msg.append(courseID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFQuizException(msg.toString());
    }

    /**
     * Returns the last l f quiz in the ordered set where courseID = &#63;.
     *
     * @param courseID the course i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f quiz, or <code>null</code> if a matching l f quiz could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz fetchByCourseId_Last(Integer courseID,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCourseId(courseID);

        List<LFQuiz> list = findByCourseId(courseID, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f quizs before and after the current l f quiz in the ordered set where courseID = &#63;.
     *
     * @param id the primary key of the current l f quiz
     * @param courseID the course i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f quiz
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFQuizException if a l f quiz with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public LFQuiz[] findByCourseId_PrevAndNext(long id, Integer courseID,
        OrderByComparator orderByComparator)
        throws NoSuchLFQuizException, SystemException {
        LFQuiz lfQuiz = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFQuiz[] array = new LFQuizImpl[3];

            array[0] = getByCourseId_PrevAndNext(session, lfQuiz, courseID,
                    orderByComparator, true);

            array[1] = lfQuiz;

            array[2] = getByCourseId_PrevAndNext(session, lfQuiz, courseID,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFQuiz getByCourseId_PrevAndNext(Session session, LFQuiz lfQuiz,
        Integer courseID, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFQUIZ_WHERE);

        if (courseID == null) {
            query.append(_FINDER_COLUMN_COURSEID_COURSEID_NULL_2);
        } else {
            query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (courseID != null) {
            qPos.add(courseID.intValue());
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfQuiz);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFQuiz> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the l f quizs where courseID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIDs the course i ds
     * @return the matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer[] courseIDs)
        throws SystemException {
        return findByCourseId(courseIDs, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the l f quizs where courseID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIDs the course i ds
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @return the range of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer[] courseIDs, int start, int end)
        throws SystemException {
        return findByCourseId(courseIDs, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f quizs where courseID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param courseIDs the course i ds
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findByCourseId(Integer[] courseIDs, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COURSEID;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderArgs = new Object[] { StringUtil.merge(courseIDs) };
        } else {
            finderArgs = new Object[] {
                    StringUtil.merge(courseIDs),
                    
                    start, end, orderByComparator
                };
        }

        List<LFQuiz> list = (List<LFQuiz>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFQuiz lfQuiz : list) {
                if (!ArrayUtil.contains(courseIDs, lfQuiz.getCourseID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_SELECT_LFQUIZ_WHERE);

            boolean conjunctionable = false;

            if ((courseIDs != null) && (courseIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < courseIDs.length; i++) {
                    if (courseIDs[i] == null) {
                        query.append(_FINDER_COLUMN_COURSEID_COURSEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_COURSEID_COURSEID_5);
                    }

                    if ((i + 1) < courseIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseIDs != null) {
                    for (Integer courseID : courseIDs) {
                        if (courseID != null) {
                            qPos.add(courseID);
                        }
                    }
                }

                list = (List<LFQuiz>) QueryUtil.list(q, getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns all the l f quizs.
     *
     * @return the l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f quizs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @return the range of l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the l f quizs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of l f quizs
     * @param end the upper bound of the range of l f quizs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of l f quizs
     * @throws SystemException if a system exception occurred
     */
    public List<LFQuiz> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<LFQuiz> list = (List<LFQuiz>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_LFQUIZ);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_LFQUIZ;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<LFQuiz>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<LFQuiz>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the l f quizs where courseID = &#63; from the database.
     *
     * @param courseID the course i d
     * @throws SystemException if a system exception occurred
     */
    public void removeByCourseId(Integer courseID) throws SystemException {
        for (LFQuiz lfQuiz : findByCourseId(courseID)) {
            remove(lfQuiz);
        }
    }

    /**
     * Removes all the l f quizs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (LFQuiz lfQuiz : findAll()) {
            remove(lfQuiz);
        }
    }

    /**
     * Returns the number of l f quizs where courseID = &#63;.
     *
     * @param courseID the course i d
     * @return the number of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(Integer courseID) throws SystemException {
        Object[] finderArgs = new Object[] { courseID };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LFQUIZ_WHERE);

            if (courseID == null) {
                query.append(_FINDER_COLUMN_COURSEID_COURSEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseID != null) {
                    qPos.add(courseID.intValue());
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COURSEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of l f quizs where courseID = any &#63;.
     *
     * @param courseIDs the course i ds
     * @return the number of matching l f quizs
     * @throws SystemException if a system exception occurred
     */
    public int countByCourseId(Integer[] courseIDs) throws SystemException {
        Object[] finderArgs = new Object[] { StringUtil.merge(courseIDs) };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_COURSEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_COUNT_LFQUIZ_WHERE);

            boolean conjunctionable = false;

            if ((courseIDs != null) && (courseIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < courseIDs.length; i++) {
                    if (courseIDs[i] == null) {
                        query.append(_FINDER_COLUMN_COURSEID_COURSEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_COURSEID_COURSEID_5);
                    }

                    if ((i + 1) < courseIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (courseIDs != null) {
                    for (Integer courseID : courseIDs) {
                        if (courseID != null) {
                            qPos.add(courseID);
                        }
                    }
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_COURSEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of l f quizs.
     *
     * @return the number of l f quizs
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_LFQUIZ);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the l f quiz persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.arcusys.learn.persistence.liferay.model.LFQuiz")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LFQuiz>> listenersList = new ArrayList<ModelListener<LFQuiz>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LFQuiz>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(LFQuizImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    private static String _removeConjunction(String sql) {
        int pos = sql.indexOf(" AND ");

        if (pos != -1) {
            sql = sql.substring(0, pos);
        }

        return sql;
    }
}
