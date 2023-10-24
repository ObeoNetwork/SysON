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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.syson.sysml.Expression;
import org.eclipse.syson.sysml.Step;
import org.eclipse.syson.sysml.Succession;
import org.eclipse.syson.sysml.SuccessionItemFlow;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Succession Item Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.SuccessionItemFlowImpl#getEffectStep <em>Effect Step</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.SuccessionItemFlowImpl#getGuardExpression <em>Guard Expression</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.SuccessionItemFlowImpl#getTransitionStep <em>Transition Step</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.SuccessionItemFlowImpl#getTriggerStep <em>Trigger Step</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SuccessionItemFlowImpl extends ItemFlowImpl implements SuccessionItemFlow {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SuccessionItemFlowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getSuccessionItemFlow();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Step> getEffectStep() {
        // TODO: implement this method to return the 'Effect Step' reference list
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
    public EList<Expression> getGuardExpression() {
        // TODO: implement this method to return the 'Guard Expression' reference list
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
    public Step getTransitionStep() {
        Step transitionStep = basicGetTransitionStep();
        return transitionStep != null && transitionStep.eIsProxy() ? (Step)eResolveProxy((InternalEObject)transitionStep) : transitionStep;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Step basicGetTransitionStep() {
        // TODO: implement this method to return the 'Transition Step' reference
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
    public EList<Step> getTriggerStep() {
        // TODO: implement this method to return the 'Trigger Step' reference list
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
            case SysmlPackage.SUCCESSION_ITEM_FLOW__EFFECT_STEP:
                return getEffectStep();
            case SysmlPackage.SUCCESSION_ITEM_FLOW__GUARD_EXPRESSION:
                return getGuardExpression();
            case SysmlPackage.SUCCESSION_ITEM_FLOW__TRANSITION_STEP:
                if (resolve) return getTransitionStep();
                return basicGetTransitionStep();
            case SysmlPackage.SUCCESSION_ITEM_FLOW__TRIGGER_STEP:
                return getTriggerStep();
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
            case SysmlPackage.SUCCESSION_ITEM_FLOW__EFFECT_STEP:
                return !getEffectStep().isEmpty();
            case SysmlPackage.SUCCESSION_ITEM_FLOW__GUARD_EXPRESSION:
                return !getGuardExpression().isEmpty();
            case SysmlPackage.SUCCESSION_ITEM_FLOW__TRANSITION_STEP:
                return basicGetTransitionStep() != null;
            case SysmlPackage.SUCCESSION_ITEM_FLOW__TRIGGER_STEP:
                return !getTriggerStep().isEmpty();
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
        if (baseClass == Succession.class) {
            switch (derivedFeatureID) {
                case SysmlPackage.SUCCESSION_ITEM_FLOW__EFFECT_STEP: return SysmlPackage.SUCCESSION__EFFECT_STEP;
                case SysmlPackage.SUCCESSION_ITEM_FLOW__GUARD_EXPRESSION: return SysmlPackage.SUCCESSION__GUARD_EXPRESSION;
                case SysmlPackage.SUCCESSION_ITEM_FLOW__TRANSITION_STEP: return SysmlPackage.SUCCESSION__TRANSITION_STEP;
                case SysmlPackage.SUCCESSION_ITEM_FLOW__TRIGGER_STEP: return SysmlPackage.SUCCESSION__TRIGGER_STEP;
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
        if (baseClass == Succession.class) {
            switch (baseFeatureID) {
                case SysmlPackage.SUCCESSION__EFFECT_STEP: return SysmlPackage.SUCCESSION_ITEM_FLOW__EFFECT_STEP;
                case SysmlPackage.SUCCESSION__GUARD_EXPRESSION: return SysmlPackage.SUCCESSION_ITEM_FLOW__GUARD_EXPRESSION;
                case SysmlPackage.SUCCESSION__TRANSITION_STEP: return SysmlPackage.SUCCESSION_ITEM_FLOW__TRANSITION_STEP;
                case SysmlPackage.SUCCESSION__TRIGGER_STEP: return SysmlPackage.SUCCESSION_ITEM_FLOW__TRIGGER_STEP;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //SuccessionItemFlowImpl
