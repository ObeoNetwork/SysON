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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.syson.sysml.ActionUsage;
import org.eclipse.syson.sysml.StateDefinition;
import org.eclipse.syson.sysml.StateUsage;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.Usage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateDefinitionImpl#isIsParallel <em>Is Parallel</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateDefinitionImpl#getDoAction <em>Do Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateDefinitionImpl#getEntryAction <em>Entry Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateDefinitionImpl#getExitAction <em>Exit Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateDefinitionImpl#getState <em>State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateDefinitionImpl extends ActionDefinitionImpl implements StateDefinition {
    /**
     * The default value of the '{@link #isIsParallel() <em>Is Parallel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsParallel()
     * @generated
     * @ordered
     */
    protected static final boolean IS_PARALLEL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsParallel() <em>Is Parallel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsParallel()
     * @generated
     * @ordered
     */
    protected boolean isParallel = IS_PARALLEL_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StateDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getStateDefinition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActionUsage getDoAction() {
        ActionUsage doAction = basicGetDoAction();
        return doAction != null && doAction.eIsProxy() ? (ActionUsage)eResolveProxy((InternalEObject)doAction) : doAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionUsage basicGetDoAction() {
        // TODO: implement this method to return the 'Do Action' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActionUsage getEntryAction() {
        ActionUsage entryAction = basicGetEntryAction();
        return entryAction != null && entryAction.eIsProxy() ? (ActionUsage)eResolveProxy((InternalEObject)entryAction) : entryAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionUsage basicGetEntryAction() {
        // TODO: implement this method to return the 'Entry Action' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActionUsage getExitAction() {
        ActionUsage exitAction = basicGetExitAction();
        return exitAction != null && exitAction.eIsProxy() ? (ActionUsage)eResolveProxy((InternalEObject)exitAction) : exitAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActionUsage basicGetExitAction() {
        // TODO: implement this method to return the 'Exit Action' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsParallel() {
        return isParallel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsParallel(boolean newIsParallel) {
        boolean oldIsParallel = isParallel;
        isParallel = newIsParallel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.STATE_DEFINITION__IS_PARALLEL, oldIsParallel, isParallel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<StateUsage> getState() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getStateDefinition_State(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.STATE_DEFINITION__IS_PARALLEL:
                return isIsParallel();
            case SysmlPackage.STATE_DEFINITION__DO_ACTION:
                if (resolve) return getDoAction();
                return basicGetDoAction();
            case SysmlPackage.STATE_DEFINITION__ENTRY_ACTION:
                if (resolve) return getEntryAction();
                return basicGetEntryAction();
            case SysmlPackage.STATE_DEFINITION__EXIT_ACTION:
                if (resolve) return getExitAction();
                return basicGetExitAction();
            case SysmlPackage.STATE_DEFINITION__STATE:
                return getState();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SysmlPackage.STATE_DEFINITION__IS_PARALLEL:
                setIsParallel((Boolean)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case SysmlPackage.STATE_DEFINITION__IS_PARALLEL:
                setIsParallel(IS_PARALLEL_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SysmlPackage.STATE_DEFINITION__IS_PARALLEL:
                return isParallel != IS_PARALLEL_EDEFAULT;
            case SysmlPackage.STATE_DEFINITION__DO_ACTION:
                return basicGetDoAction() != null;
            case SysmlPackage.STATE_DEFINITION__ENTRY_ACTION:
                return basicGetEntryAction() != null;
            case SysmlPackage.STATE_DEFINITION__EXIT_ACTION:
                return basicGetExitAction() != null;
            case SysmlPackage.STATE_DEFINITION__STATE:
                return !getState().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (isParallel: ");
        result.append(isParallel);
        result.append(')');
        return result.toString();
    }

} //StateDefinitionImpl
