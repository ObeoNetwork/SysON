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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.syson.sysml.ActionDefinition;
import org.eclipse.syson.sysml.ActionUsage;
import org.eclipse.syson.sysml.Behavior;
import org.eclipse.syson.sysml.Feature;
import org.eclipse.syson.sysml.FlowConnectionDefinition;
import org.eclipse.syson.sysml.Interaction;
import org.eclipse.syson.sysml.Step;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Connection Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.FlowConnectionDefinitionImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.FlowConnectionDefinitionImpl#getStep <em>Step</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.FlowConnectionDefinitionImpl#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowConnectionDefinitionImpl extends ConnectionDefinitionImpl implements FlowConnectionDefinition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FlowConnectionDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getFlowConnectionDefinition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Feature> getParameter() {
        // TODO: implement this method to return the 'Parameter' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Step> getStep() {
        // TODO: implement this method to return the 'Step' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<ActionUsage> getAction() {
        // TODO: implement this method to return the 'Action' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
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
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__PARAMETER:
                return getParameter();
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__STEP:
                return getStep();
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__ACTION:
                return getAction();
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
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__PARAMETER:
                return !getParameter().isEmpty();
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__STEP:
                return !getStep().isEmpty();
            case SysmlPackage.FLOW_CONNECTION_DEFINITION__ACTION:
                return !getAction().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Behavior.class) {
            switch (derivedFeatureID) {
                case SysmlPackage.FLOW_CONNECTION_DEFINITION__PARAMETER: return SysmlPackage.BEHAVIOR__PARAMETER;
                case SysmlPackage.FLOW_CONNECTION_DEFINITION__STEP: return SysmlPackage.BEHAVIOR__STEP;
                default: return -1;
            }
        }
        if (baseClass == ActionDefinition.class) {
            switch (derivedFeatureID) {
                case SysmlPackage.FLOW_CONNECTION_DEFINITION__ACTION: return SysmlPackage.ACTION_DEFINITION__ACTION;
                default: return -1;
            }
        }
        if (baseClass == Interaction.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Behavior.class) {
            switch (baseFeatureID) {
                case SysmlPackage.BEHAVIOR__PARAMETER: return SysmlPackage.FLOW_CONNECTION_DEFINITION__PARAMETER;
                case SysmlPackage.BEHAVIOR__STEP: return SysmlPackage.FLOW_CONNECTION_DEFINITION__STEP;
                default: return -1;
            }
        }
        if (baseClass == ActionDefinition.class) {
            switch (baseFeatureID) {
                case SysmlPackage.ACTION_DEFINITION__ACTION: return SysmlPackage.FLOW_CONNECTION_DEFINITION__ACTION;
                default: return -1;
            }
        }
        if (baseClass == Interaction.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //FlowConnectionDefinitionImpl
