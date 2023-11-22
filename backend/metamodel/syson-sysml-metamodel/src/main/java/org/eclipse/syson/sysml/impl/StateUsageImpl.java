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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.syson.sysml.ActionUsage;
import org.eclipse.syson.sysml.Behavior;
import org.eclipse.syson.sysml.StateUsage;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.Usage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateUsageImpl#isIsParallel <em>Is Parallel</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateUsageImpl#getDoAction <em>Do Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateUsageImpl#getEntryAction <em>Entry Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateUsageImpl#getExitAction <em>Exit Action</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.StateUsageImpl#getStateDefinition <em>State Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateUsageImpl extends ActionUsageImpl implements StateUsage {
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
    protected StateUsageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getStateUsage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActionUsage getDoAction() {
        ActionUsage doAction = this.basicGetDoAction();
        return doAction != null && doAction.eIsProxy() ? (ActionUsage)this.eResolveProxy((InternalEObject)doAction) : doAction;
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
        ActionUsage entryAction = this.basicGetEntryAction();
        return entryAction != null && entryAction.eIsProxy() ? (ActionUsage)this.eResolveProxy((InternalEObject)entryAction) : entryAction;
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
        ActionUsage exitAction = this.basicGetExitAction();
        return exitAction != null && exitAction.eIsProxy() ? (ActionUsage)this.eResolveProxy((InternalEObject)exitAction) : exitAction;
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
        return this.isParallel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsParallel(boolean newIsParallel) {
        boolean oldIsParallel = this.isParallel;
        this.isParallel = newIsParallel;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.STATE_USAGE__IS_PARALLEL, oldIsParallel, this.isParallel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Behavior> getStateDefinition() {
        List<Usage> data = new ArrayList<>();
        return new EcoreEList.UnmodifiableEList<>(this, SysmlPackage.eINSTANCE.getStateUsage_StateDefinition(), data.size(), data.toArray());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public boolean isSubstateUsage(boolean isParallel) {
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.STATE_USAGE__IS_PARALLEL:
                return this.isIsParallel();
            case SysmlPackage.STATE_USAGE__DO_ACTION:
                if (resolve) return this.getDoAction();
                return this.basicGetDoAction();
            case SysmlPackage.STATE_USAGE__ENTRY_ACTION:
                if (resolve) return this.getEntryAction();
                return this.basicGetEntryAction();
            case SysmlPackage.STATE_USAGE__EXIT_ACTION:
                if (resolve) return this.getExitAction();
                return this.basicGetExitAction();
            case SysmlPackage.STATE_USAGE__STATE_DEFINITION:
                return this.getStateDefinition();
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
            case SysmlPackage.STATE_USAGE__IS_PARALLEL:
                this.setIsParallel((Boolean)newValue);
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
            case SysmlPackage.STATE_USAGE__IS_PARALLEL:
                this.setIsParallel(IS_PARALLEL_EDEFAULT);
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
            case SysmlPackage.STATE_USAGE__IS_PARALLEL:
                return this.isParallel != IS_PARALLEL_EDEFAULT;
            case SysmlPackage.STATE_USAGE__DO_ACTION:
                return this.basicGetDoAction() != null;
            case SysmlPackage.STATE_USAGE__ENTRY_ACTION:
                return this.basicGetEntryAction() != null;
            case SysmlPackage.STATE_USAGE__EXIT_ACTION:
                return this.basicGetExitAction() != null;
            case SysmlPackage.STATE_USAGE__STATE_DEFINITION:
                return !this.getStateDefinition().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case SysmlPackage.STATE_USAGE___IS_SUBSTATE_USAGE__BOOLEAN:
                return this.isSubstateUsage((Boolean)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (isParallel: ");
        result.append(this.isParallel);
        result.append(')');
        return result.toString();
    }

} //StateUsageImpl
