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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.syson.sysml.AcceptActionUsage;
import org.eclipse.syson.sysml.Expression;
import org.eclipse.syson.sysml.ReferenceUsage;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Accept Action Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.AcceptActionUsageImpl#getPayloadArgument <em>Payload Argument</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.AcceptActionUsageImpl#getPayloadParameter <em>Payload Parameter</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.AcceptActionUsageImpl#getReceiverArgument <em>Receiver Argument</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AcceptActionUsageImpl extends ActionUsageImpl implements AcceptActionUsage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AcceptActionUsageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getAcceptActionUsage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Expression getPayloadArgument() {
        Expression payloadArgument = this.basicGetPayloadArgument();
        return payloadArgument != null && payloadArgument.eIsProxy() ? (Expression)this.eResolveProxy((InternalEObject)payloadArgument) : payloadArgument;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression basicGetPayloadArgument() {
        // TODO: implement this method to return the 'Payload Argument' reference
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
    public ReferenceUsage getPayloadParameter() {
        ReferenceUsage payloadParameter = this.basicGetPayloadParameter();
        return payloadParameter != null && payloadParameter.eIsProxy() ? (ReferenceUsage)this.eResolveProxy((InternalEObject)payloadParameter) : payloadParameter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReferenceUsage basicGetPayloadParameter() {
        // TODO: implement this method to return the 'Payload Parameter' reference
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
    public Expression getReceiverArgument() {
        Expression receiverArgument = this.basicGetReceiverArgument();
        return receiverArgument != null && receiverArgument.eIsProxy() ? (Expression)this.eResolveProxy((InternalEObject)receiverArgument) : receiverArgument;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression basicGetReceiverArgument() {
        // TODO: implement this method to return the 'Receiver Argument' reference
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
    public boolean isTriggerAction() {
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
            case SysmlPackage.ACCEPT_ACTION_USAGE__PAYLOAD_ARGUMENT:
                if (resolve) return this.getPayloadArgument();
                return this.basicGetPayloadArgument();
            case SysmlPackage.ACCEPT_ACTION_USAGE__PAYLOAD_PARAMETER:
                if (resolve) return this.getPayloadParameter();
                return this.basicGetPayloadParameter();
            case SysmlPackage.ACCEPT_ACTION_USAGE__RECEIVER_ARGUMENT:
                if (resolve) return this.getReceiverArgument();
                return this.basicGetReceiverArgument();
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
            case SysmlPackage.ACCEPT_ACTION_USAGE__PAYLOAD_ARGUMENT:
                return this.basicGetPayloadArgument() != null;
            case SysmlPackage.ACCEPT_ACTION_USAGE__PAYLOAD_PARAMETER:
                return this.basicGetPayloadParameter() != null;
            case SysmlPackage.ACCEPT_ACTION_USAGE__RECEIVER_ARGUMENT:
                return this.basicGetReceiverArgument() != null;
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
            case SysmlPackage.ACCEPT_ACTION_USAGE___IS_TRIGGER_ACTION:
                return this.isTriggerAction();
        }
        return super.eInvoke(operationID, arguments);
    }

} //AcceptActionUsageImpl
