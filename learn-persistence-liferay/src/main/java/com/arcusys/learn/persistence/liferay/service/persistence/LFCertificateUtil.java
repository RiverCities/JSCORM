package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.model.LFCertificate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the l f certificate service. This utility wraps {@link LFCertificatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFCertificatePersistence
 * @see LFCertificatePersistenceImpl
 * @generated
 */
public class LFCertificateUtil {
    private static LFCertificatePersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(LFCertificate lfCertificate) {
        getPersistence().clearCache(lfCertificate);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<LFCertificate> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<LFCertificate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<LFCertificate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static LFCertificate update(LFCertificate lfCertificate,
        boolean merge) throws SystemException {
        return getPersistence().update(lfCertificate, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static LFCertificate update(LFCertificate lfCertificate,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(lfCertificate, merge, serviceContext);
    }

    /**
    * Caches the l f certificate in the entity cache if it is enabled.
    *
    * @param lfCertificate the l f certificate
    */
    public static void cacheResult(
        com.arcusys.learn.persistence.liferay.model.LFCertificate lfCertificate) {
        getPersistence().cacheResult(lfCertificate);
    }

    /**
    * Caches the l f certificates in the entity cache if it is enabled.
    *
    * @param lfCertificates the l f certificates
    */
    public static void cacheResult(
        java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> lfCertificates) {
        getPersistence().cacheResult(lfCertificates);
    }

    /**
    * Creates a new l f certificate with the primary key. Does not add the l f certificate to the database.
    *
    * @param id the primary key for the new l f certificate
    * @return the new l f certificate
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate create(
        long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the l f certificate with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the l f certificate
    * @return the l f certificate that was removed
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a l f certificate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate remove(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.arcusys.learn.persistence.liferay.model.LFCertificate updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFCertificate lfCertificate,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(lfCertificate, merge);
    }

    /**
    * Returns the l f certificate with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException} if it could not be found.
    *
    * @param id the primary key of the l f certificate
    * @return the l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a l f certificate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate findByPrimaryKey(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the l f certificate with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the l f certificate
    * @return the l f certificate, or <code>null</code> if a l f certificate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the l f certificates where companyID = &#63;.
    *
    * @param companyID the company i d
    * @return the matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByCompanyID(
        java.lang.Integer companyID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyID(companyID);
    }

    /**
    * Returns a range of all the l f certificates where companyID = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyID the company i d
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @return the range of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByCompanyID(
        java.lang.Integer companyID, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyID(companyID, start, end);
    }

    /**
    * Returns an ordered range of all the l f certificates where companyID = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param companyID the company i d
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByCompanyID(
        java.lang.Integer companyID, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyID(companyID, start, end, orderByComparator);
    }

    /**
    * Returns the first l f certificate in the ordered set where companyID = &#63;.
    *
    * @param companyID the company i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate findByCompanyID_First(
        java.lang.Integer companyID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyID_First(companyID, orderByComparator);
    }

    /**
    * Returns the first l f certificate in the ordered set where companyID = &#63;.
    *
    * @param companyID the company i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f certificate, or <code>null</code> if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate fetchByCompanyID_First(
        java.lang.Integer companyID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyID_First(companyID, orderByComparator);
    }

    /**
    * Returns the last l f certificate in the ordered set where companyID = &#63;.
    *
    * @param companyID the company i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate findByCompanyID_Last(
        java.lang.Integer companyID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyID_Last(companyID, orderByComparator);
    }

    /**
    * Returns the last l f certificate in the ordered set where companyID = &#63;.
    *
    * @param companyID the company i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f certificate, or <code>null</code> if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate fetchByCompanyID_Last(
        java.lang.Integer companyID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyID_Last(companyID, orderByComparator);
    }

    /**
    * Returns the l f certificates before and after the current l f certificate in the ordered set where companyID = &#63;.
    *
    * @param id the primary key of the current l f certificate
    * @param companyID the company i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a l f certificate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate[] findByCompanyID_PrevAndNext(
        long id, java.lang.Integer companyID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyID_PrevAndNext(id, companyID, orderByComparator);
    }

    /**
    * Returns all the l f certificates where title LIKE &#63;.
    *
    * @param title the title
    * @return the matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByTitle(
        java.lang.String title)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTitle(title);
    }

    /**
    * Returns a range of all the l f certificates where title LIKE &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param title the title
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @return the range of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByTitle(
        java.lang.String title, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTitle(title, start, end);
    }

    /**
    * Returns an ordered range of all the l f certificates where title LIKE &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param title the title
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findByTitle(
        java.lang.String title, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTitle(title, start, end, orderByComparator);
    }

    /**
    * Returns the first l f certificate in the ordered set where title LIKE &#63;.
    *
    * @param title the title
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate findByTitle_First(
        java.lang.String title,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTitle_First(title, orderByComparator);
    }

    /**
    * Returns the first l f certificate in the ordered set where title LIKE &#63;.
    *
    * @param title the title
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f certificate, or <code>null</code> if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate fetchByTitle_First(
        java.lang.String title,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByTitle_First(title, orderByComparator);
    }

    /**
    * Returns the last l f certificate in the ordered set where title LIKE &#63;.
    *
    * @param title the title
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate findByTitle_Last(
        java.lang.String title,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTitle_Last(title, orderByComparator);
    }

    /**
    * Returns the last l f certificate in the ordered set where title LIKE &#63;.
    *
    * @param title the title
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f certificate, or <code>null</code> if a matching l f certificate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate fetchByTitle_Last(
        java.lang.String title,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByTitle_Last(title, orderByComparator);
    }

    /**
    * Returns the l f certificates before and after the current l f certificate in the ordered set where title LIKE &#63;.
    *
    * @param id the primary key of the current l f certificate
    * @param title the title
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next l f certificate
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException if a l f certificate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.arcusys.learn.persistence.liferay.model.LFCertificate[] findByTitle_PrevAndNext(
        long id, java.lang.String title,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFCertificateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTitle_PrevAndNext(id, title, orderByComparator);
    }

    /**
    * Returns all the l f certificates.
    *
    * @return the l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the l f certificates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @return the range of l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the l f certificates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of l f certificates
    * @param end the upper bound of the range of l f certificates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.arcusys.learn.persistence.liferay.model.LFCertificate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the l f certificates where companyID = &#63; from the database.
    *
    * @param companyID the company i d
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyID(java.lang.Integer companyID)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyID(companyID);
    }

    /**
    * Removes all the l f certificates where title LIKE &#63; from the database.
    *
    * @param title the title
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTitle(java.lang.String title)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTitle(title);
    }

    /**
    * Removes all the l f certificates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of l f certificates where companyID = &#63;.
    *
    * @param companyID the company i d
    * @return the number of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyID(java.lang.Integer companyID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyID(companyID);
    }

    /**
    * Returns the number of l f certificates where title LIKE &#63;.
    *
    * @param title the title
    * @return the number of matching l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static int countByTitle(java.lang.String title)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTitle(title);
    }

    /**
    * Returns the number of l f certificates.
    *
    * @return the number of l f certificates
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static LFCertificatePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (LFCertificatePersistence) PortletBeanLocatorUtil.locate(com.arcusys.learn.persistence.liferay.service.ClpSerializer.getServletContextName(),
                    LFCertificatePersistence.class.getName());

            ReferenceRegistry.registerReference(LFCertificateUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated
     */
    public void setPersistence(LFCertificatePersistence persistence) {
    }
}
