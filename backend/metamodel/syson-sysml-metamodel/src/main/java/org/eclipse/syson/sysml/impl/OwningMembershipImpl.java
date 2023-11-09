/**
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.syson.sysml.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.OwningMembership;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Owning Membership</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.OwningMembershipImpl#getOwnedMemberElementId <em>Owned Member Element Id</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.OwningMembershipImpl#getOwnedMemberName <em>Owned Member Name</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.OwningMembershipImpl#getOwnedMemberShortName <em>Owned Member Short Name</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.OwningMembershipImpl#getOwnedMemberElement <em>Owned Member Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OwningMembershipImpl extends MembershipImpl implements OwningMembership {
    /**
     * The default value of the '{@link #getOwnedMemberElementId() <em>Owned Member Element Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedMemberElementId()
     * @generated
     * @ordered
     */
    protected static final String OWNED_MEMBER_ELEMENT_ID_EDEFAULT = null;

    /**
     * The default value of the '{@link #getOwnedMemberName() <em>Owned Member Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedMemberName()
     * @generated
     * @ordered
     */
    protected static final String OWNED_MEMBER_NAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #getOwnedMemberShortName() <em>Owned Member Short Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOwnedMemberShortName()
     * @generated
     * @ordered
     */
    protected static final String OWNED_MEMBER_SHORT_NAME_EDEFAULT = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OwningMembershipImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getOwningMembership();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Element getOwnedMemberElement() {
        Element ownedMemberElement = basicGetOwnedMemberElement();
        return ownedMemberElement != null && ownedMemberElement.eIsProxy() ? (Element)eResolveProxy((InternalEObject)ownedMemberElement) : ownedMemberElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Element basicGetOwnedMemberElement() {
        // TODO: implement this method to return the 'Owned Member Element' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getOwnedMemberElementId() {
        // TODO: implement this method to return the 'Owned Member Element Id' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getOwnedMemberName() {
        // TODO: implement this method to return the 'Owned Member Name' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String getOwnedMemberShortName() {
        // TODO: implement this method to return the 'Owned Member Short Name' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_ELEMENT_ID:
                return getOwnedMemberElementId();
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_NAME:
                return getOwnedMemberName();
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_SHORT_NAME:
                return getOwnedMemberShortName();
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_ELEMENT:
                if (resolve) return getOwnedMemberElement();
                return basicGetOwnedMemberElement();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_ELEMENT_ID:
                return OWNED_MEMBER_ELEMENT_ID_EDEFAULT == null ? getOwnedMemberElementId() != null : !OWNED_MEMBER_ELEMENT_ID_EDEFAULT.equals(getOwnedMemberElementId());
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_NAME:
                return OWNED_MEMBER_NAME_EDEFAULT == null ? getOwnedMemberName() != null : !OWNED_MEMBER_NAME_EDEFAULT.equals(getOwnedMemberName());
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_SHORT_NAME:
                return OWNED_MEMBER_SHORT_NAME_EDEFAULT == null ? getOwnedMemberShortName() != null : !OWNED_MEMBER_SHORT_NAME_EDEFAULT.equals(getOwnedMemberShortName());
            case SysmlPackage.OWNING_MEMBERSHIP__OWNED_MEMBER_ELEMENT:
                return basicGetOwnedMemberElement() != null;
        }
        return super.eIsSet(featureID);
    }

} //OwningMembershipImpl
